import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { City } from 'src/app/_model/City';
import { Tag } from 'src/app/_model/Tag';
import { UserData } from 'src/app/_model/UserData';
import { AddressesService } from 'src/app/_services/addresses.service';
import { CountiesService } from 'src/app/_services/counties.service';
import { HttpService } from 'src/app/_services/http.service';
import { UsersService } from 'src/app/_services/users.service';
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

  userData: UserData;

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

  constructor(private countiesService: CountiesService,
    private usersService: UsersService,
    private http: HttpService,private cdr: ChangeDetectorRef) {
    this.userData = this.usersService.userData
   }
  ngOnDestroy(): void {
    setTimeout(() => {
      this.formGroup.removeControl('address');
      this.cdr.detectChanges();
    }, 10);
  }

  ngOnInit(): void {
    this.setValueIfHasAddress();
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

  setValueIfHasAddress(){
    let currentAddress = this.formGroup.controls['currentAddress'].value
    if(currentAddress){
      this.county.setValue(currentAddress.county.name);
      this.address.controls['countyId'].setValue(currentAddress.countyId);
      this.address.controls['zipCode'].setValue(currentAddress.zipCode);
      this.address.controls['city'].setValue(currentAddress.city);
      this.address.controls['street'].setValue(currentAddress.street);
      this.address.controls['number'].setValue(currentAddress.number);
      if(currentAddress.staircase){
        this.address.controls['staircase'].setValue(currentAddress.staircase);
        this.address.controls['floor'].setValue(currentAddress.floor);
        this.address.controls['door'].setValue(currentAddress.door);
      }
    }
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
