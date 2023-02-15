import { ChangeDetectorRef, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { City } from 'src/app/_model/City';
import { Field } from 'src/app/_model/Field';
import { Tag } from 'src/app/_model/Tag';
import { CountiesService } from 'src/app/_services/counties.service';
import { HttpService } from 'src/app/_services/http.service';
import { DropdownValidator } from 'src/app/_validators/dropdown-validators';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})

export class AddressFormComponent implements OnInit {

  @Input() formGroup!: FormGroup;
  cities: Tag[] = [];
  counties: Tag[] = [];
  streetsNames: Tag[] = [];
  currentCounty: any = "";

  county = new FormControl('',[Validators.required,DropdownValidator(this.counties)]);
  address = new FormGroup({
    zipCode: new FormControl('',[Validators.required,Validators.pattern('[0-9]+')]),
    countyId: new FormControl(-1),
    city: new FormControl('',[Validators.required,DropdownValidator(this.cities)]),
    street: new FormControl('',[Validators.required]),
    number: new FormControl('',[Validators.required]),
    staircase: new FormControl(null),
    floor: new FormControl(null),
    door: new FormControl(null),
  });


  fields: Field[] = [
    {control: this.address.controls['zipCode'], name:'Irányítószám', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.address.controls['number'], name:'Házszám', type:"text"},
    {control: this.address.controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.address.controls['floor'], name:'Emelet', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.address.controls['door'], name:'Ajtó', type:"text", errorMessage:"Csak számokat tartalmazhat"},
  ]


  constructor(private countiesService: CountiesService, private http: HttpService,private cdr: ChangeDetectorRef) { }
  ngOnDestroy(): void {
    setTimeout(() => {
      this.formGroup.removeControl('address');
      this.cdr.detectChanges();
    }, 10);
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.formGroup.addControl('address',this.address);
      this.cdr.detectChanges();
    }, 10);
    this.loadCounties(this.counties);
    this.county.valueChanges.subscribe((value)=>{
      this.currentCounty = value;
      let countyId = this.getIdFromDropDown(this.currentCounty,this.counties)
      if(countyId != -1){
        this.address.controls['countyId'].setValue(countyId);
      }
      this.loadCities(this.currentCounty,this.cities);
    })
  }

  loadCounties(counties: Tag[]){
    this.countiesService.getAllCounties().subscribe((response:Tag[])=>{
      response.forEach((county:Tag)=>{
        if(county.name != "Budapest"){
          counties.push({id: county.id, name: county.name})
        }
      });
    });
  }

  loadCities(county: string,cities: Tag[]){
    this.http.getAllCities().subscribe((response)=>{
      response.forEach((city:City, index:number)=>{
        if(county == city.admin_name){
          cities.push({id: index, name: city.city})
        }
      });
    });
  }

  getIdFromDropDown(name: string, dropdown:Tag[]):number {
    let id = -1
    dropdown.forEach((element:Tag)=>{
      if(name == element.name){
        id = element.id;
      }
    });
    return id;
  }

}
