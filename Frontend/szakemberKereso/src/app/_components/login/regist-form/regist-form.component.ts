import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from 'src/app/_model/Address';
import { City } from 'src/app/_model/City';
import { Field } from 'src/app/_model/Field';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { HttpService } from 'src/app/_services/http.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';
import { DropdownValidator } from 'src/app/_validators/dropdown-validators';
import { PasswordValidators } from 'src/app/_validators/password-validators';

@Component({
  selector: 'app-regist-form',
  templateUrl: './regist-form.component.html',
  styleUrls: ['./regist-form.component.css']
})
export class RegistFormComponent implements OnInit {

  counties: Tag[] = [];
  cities: Tag[] = [];
  streetsNames: Tag[] = [];
  professional: boolean = false;
  professions: Tag[] = [];
  company: boolean = false;
  county: any = null;

  registForm = new FormGroup({
    lastName:new FormControl('',[Validators.required]),
    firstName: new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required]),
    telNumber:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    passwordConfirm:new FormControl('',[Validators.required]),
    address: new FormGroup({
      county:new FormControl('',[Validators.required,DropdownValidator(this.counties)]),
      city:new FormControl('',[Validators.required,DropdownValidator(this.cities)]),
      zipCode:new FormControl('',[Validators.required,Validators.pattern('[0-9]+')]),
      streetName:new FormControl('',[Validators.required]),
      number:new FormControl('',[Validators.required]),
      staircase:new FormControl(''),
      floor:new FormControl(''),
      door:new FormControl(''),
    }),
    profession: new FormControl(''),
    company : new FormGroup({
      companyName: new FormControl(''),
      taxNumber: new FormControl(''),
      address: new FormGroup({
        zipCode: new FormControl(''),
        county: new FormControl(''),
        city: new FormControl(''),
        streetName: new FormControl(''),
        number: new FormControl(''),
        staircase: new FormControl(''),
        floor: new FormControl(''),
        door: new FormControl(''),
      }),
    }),
  });

  fields:Field[] = [
    {control: this.registForm.controls['lastName'], name:'Vezetéknév', type:"text"},
    {control: this.registForm.controls['firstName'], name:'Keresztnév', type:"text"},
    {control: this.registForm.controls['email'], name:'Email', type:"email"},
    {control: this.registForm.controls['telNumber'], name:'Telefon szám', type:"text"},
    {control: this.registForm.controls['password'], name:'Jelszó', type:"password"},
    {control: this.registForm.controls['passwordConfirm'], name:'Jelszó megerősítése', type:"password"},
  ];
  addressFields:Field[] = [
    {control: this.registForm.controls['address'].controls['zipCode'], name:'Irányítószám', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.registForm.controls['address'].controls['number'], name:'Házszám', type:"text"},
    {control: this.registForm.controls['address'].controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.registForm.controls['address'].controls['floor'], name:'Emelet', type:"text"},
    {control: this.registForm.controls['address'].controls['door'], name:'Ajtó', type:"text"},
  ];
  companyFields:Field[] = [
    {control: this.registForm.controls['company'].controls['companyName'], name:'Cég név', type:"text"},
    {control: this.registForm.controls['company'].controls['taxNumber'], name:'Adószám', type:"text"},
  ];
  companyAddressFields: Field[] = [
    {control: this.registForm.controls['company'].controls['address'].controls['zipCode'], name:'Irányítószám', type:"text"},
    {control: this.registForm.controls['company'].controls['address'].controls['number'], name:'Házszám', type:"text"},
    {control: this.registForm.controls['company'].controls['address'].controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.registForm.controls['company'].controls['address'].controls['floor'], name:'Emelet', type:"text"},
    {control: this.registForm.controls['company'].controls['address'].controls['door'], name:'Ajtó', type:"text"},
  ]


  constructor(private http: HttpService,
      private jobTagsService: JobTagsService,
      private userService: UsersService) { }

  ngOnInit(): void {
    this.registForm.addValidators([PasswordValidators.same('password','passwordConfirm')])
    this.loadCounties();
    this.registForm.controls['address'].controls['county'].valueChanges.subscribe((value)=>{
      this.county = value;
      this.loadCities(this.county);
    })
    this.loadProfessions();
  }

  professionalSlide(){
    this.professional = this.professional == false;
    if(this.professional == false){
      this.company = false;
      this.registForm.controls['profession'].removeValidators(Validators.required);
      // this.companyValidatorsRemove();
    }
    if(this.professional == true){
      this.registForm.controls['profession'].addValidators(Validators.required);
    }
  }

  // companyValidatorsRemove(){
  //   this.registForm.controls['companyName'].removeValidators(Validators.required);
  //   this.registForm.controls['taxNumber'].removeValidators(Validators.required);
  //   this.registForm.controls['companyZipCode'].removeValidators(Validators.required);
  //   this.registForm.controls['companyCounty'].removeValidators(Validators.required);
  //   this.registForm.controls['companyCity'].removeValidators(Validators.required);
  //   this.registForm.controls['companyStreetName'].removeValidators(Validators.required);
  //   this.registForm.controls['companyNumber'].removeValidators(Validators.required);
  // }

  // companyValidatorsAdd(){
  //   this.registForm.controls['companyName'].addValidators(Validators.required);
  //   this.registForm.controls['taxNumber'].addValidators(Validators.required);
  //   this.registForm.controls['companyZipCode'].addValidators(Validators.required);
  //   this.registForm.controls['companyCounty'].addValidators(Validators.required);
  //   this.registForm.controls['companyCity'].addValidators(Validators.required);
  //   this.registForm.controls['companyStreetName'].addValidators(Validators.required);
  //   this.registForm.controls['companyNumber'].addValidators(Validators.required);
  // }

  companySlide(){
    this.company = this.company == false;
    if(this.company == false){
      // this.companyValidatorsRemove();
    }
    if(this.company == true){
      // this.companyValidatorsAdd();
    }
  }

  regist(){
    if(this.registForm.valid){
      let county = this.registForm.controls['address'].controls['county'].value!
      let countyId = this.getIdFromDropDown(county,this.counties);
      let zipCode = parseInt(this.registForm.controls['address'].controls['zipCode'].value!);
      let address: Address = {
        countyId: countyId,
        zipCode: zipCode,
        city: this.registForm.value.firstName!,
        street: this.registForm.value.firstName!,
        number: this.registForm.value.firstName!,
        staircase: null,
        floor: null,
        door: null
      }
      let user: User = {
        firstName: this.registForm.value.firstName!,
        lastName: this.registForm.value.lastName!,
        email: this.registForm.value.email!,
        phone: this.registForm.value.telNumber!,
        password: this.registForm.value.password!,
        address: address,
      }
      this.userService.createUser(user).subscribe(res=>{
        console.log(res);
      });
    }else{
      alert("invalidForm");
      window.scroll({
        top: 0,
        behavior: 'smooth'
      });
    }
  }

  loadCounties(){
    this.http.getAllCounties().subscribe((response:Tag[])=>{
      response.forEach((county:Tag)=>{
        if(county.name != "Budapest"){
          this.counties.push({id: county.id, name: county.name})
        }
      });
    });
  }

  loadCities(county: any){
    this.http.getAllCities().subscribe((response)=>{
      response.forEach((city:City, index:number)=>{
        if(county == city.admin_name){
          this.cities.push({id: index, name: city.city})
        }
      });
    });
  }

  loadProfessions(){
    this.jobTagsService.getAllJobTags().subscribe((response)=>{
      response.forEach((jobTag:Tag) => {
        this.professions.push(jobTag);
      });
    })
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

  get companyAddress(){
    return this.registForm.controls['company'].controls['address'];
  }

  get address(){
    return this.registForm.controls['address'];
  }

}
