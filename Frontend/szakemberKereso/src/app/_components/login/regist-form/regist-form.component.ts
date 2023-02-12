import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from 'src/app/_model/Address';
import { City } from 'src/app/_model/City';
import { Company } from 'src/app/_model/Company';
import { Field } from 'src/app/_model/Field';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { CountiesService } from 'src/app/_services/counties.service';
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
  companyCities: Tag[] = [];
  streetsNames: Tag[] = [];
  professions: Tag[] = [];

  professional: boolean = false;
  hasCompany: boolean = false;
  county: any = null;
  companyCounty: any = null;

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
    {control: this.address.controls['zipCode'], name:'Irányítószám', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.address.controls['number'], name:'Házszám', type:"text"},
    {control: this.address.controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.address.controls['floor'], name:'Emelet', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.address.controls['door'], name:'Ajtó', type:"text", errorMessage:"Csak számokat tartalmazhat"},
  ];
  companyFields:Field[] = [
    {control: this.registForm.controls['company'].controls['companyName'], name:'Cég név', type:"text"},
    {control: this.registForm.controls['company'].controls['taxNumber'], name:'Adószám', type:"text"},
  ];
  companyAddressFields: Field[] = [
    {control: this.companyAddress.controls['zipCode'], name:'Irányítószám', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.companyAddress.controls['number'], name:'Házszám', type:"text"},
    {control: this.companyAddress.controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.companyAddress.controls['floor'], name:'Emelet', type:"text", errorMessage:"Csak számokat tartalmazhat"},
    {control: this.companyAddress.controls['door'], name:'Ajtó', type:"text", errorMessage:"Csak számokat tartalmazhat"},
  ]


  constructor(private http: HttpService,
      private countiesService: CountiesService,
      private jobTagsService: JobTagsService,
      private userService: UsersService) { }

  ngOnInit(): void {
    this.registForm.addValidators([PasswordValidators.same('password','passwordConfirm')])
    this.loadCounties(this.counties);

    this.address.controls['county'].valueChanges.subscribe((value)=>{
      this.county = value;
      this.loadCities(this.county,this.cities);
    })
    this.companyAddress.controls['county'].valueChanges.subscribe((value)=>{
      this.companyCounty = value;
      this.loadCities(this.companyCounty,this.companyCities);
    })
    this.loadProfessions();
  }

  professionalSlide(){
    if(this.professional == false){
      this.professional = true;
      this.hasCompany = false;
      this.registForm.controls['profession'].addValidators([Validators.required,DropdownValidator(this.professions)]);
      this.companyValidatorsRemove();
    }else{
      this.professional = false;
      this.registForm.controls['profession'].removeValidators([Validators.required,DropdownValidator(this.professions)]);
    }
  }

  companyValidatorsRemove(){
    this.company.controls['companyName'].removeValidators(Validators.required);
    this.company.controls['taxNumber'].removeValidators(Validators.required);
    this.companyAddress.controls['zipCode'].removeValidators(Validators.required);
    this.companyAddress.controls['county'].removeValidators(Validators.required);
    this.companyAddress.controls['city'].removeValidators(Validators.required);
    this.companyAddress.controls['streetName'].removeValidators(Validators.required);
    this.companyAddress.controls['number'].removeValidators(Validators.required);
  }

  companyValidatorsAdd(){
    this.company.controls['companyName'].addValidators(Validators.required);
    this.company.controls['taxNumber'].addValidators(Validators.required);
    this.companyAddress.controls['zipCode'].addValidators(Validators.required);
    this.companyAddress.controls['county'].addValidators(Validators.required);
    this.companyAddress.controls['city'].addValidators(Validators.required);
    this.companyAddress.controls['streetName'].addValidators(Validators.required);
    this.companyAddress.controls['number'].addValidators(Validators.required);
  }

  companySlide(){
    if(this.hasCompany == false){
      this.hasCompany = true;
      this.companyValidatorsAdd();
    }
    else{
      this.hasCompany = false;
      this.companyValidatorsRemove();
    }
  }

  regist(){
    if(this.registForm.valid){
      let user = this.buildUser();
      if(this.professional){
        let jobTag = this.registForm.controls['profession'].value!;
        let JobTagId = this.getIdFromDropDown(jobTag,this.professions)
        user.profession = JobTagId;
        if(this.hasCompany){
          let company: Company = this.companyBuilder();
          this.userService.createUserWorker(user,company).subscribe(res=>{
            console.log(res);
          });
        }
      }else{
        this.userService.createUser(user).subscribe(res=>{
          console.log(res);
        });
      }
    }else{
      alert("invalidForm");
      window.scroll({
        top: 0,
        behavior: 'smooth'
      });
    }
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

  loadProfessions(){
    this.jobTagsService.getAllJobTags().subscribe((response)=>{
      response.forEach((jobTag:Tag) => {
        this.professions.push(jobTag);
      });
    })
  }

  buildUser():User{
    let address: Address = this.addressBuilder(this.address,this.counties)
    let user: User = {
      firstName: this.registForm.value.firstName!,
      lastName: this.registForm.value.lastName!,
      email: this.registForm.value.email!,
      phone: this.registForm.value.telNumber!,
      password: this.registForm.value.password!,
      address: address,
    }
    return user;
  }

  companyBuilder(): Company {
    let address: Address = this.addressBuilder(this.companyAddress,this.counties);
    let company: Company = {
      name: this.company.controls['companyName'].value!,
      taxNumber: this.company.controls['taxNumber'].value!,
      address: address
    }
    return company;
  }

  addressBuilder(addressForm: FormGroup,countyDropDown: Tag[]): Address{
    let county = addressForm.controls['county'].value!;
    let countyId = this.getIdFromDropDown(county,countyDropDown);
    let zipCode = parseInt(addressForm.controls['zipCode'].value!);
    let staircase = addressForm.controls['staircase'].value!;
    let floor:number | null = parseInt(addressForm.controls['floor'].value!);
    let door:number | null = parseInt(addressForm.controls['door'].value!)
    if(staircase == ""){
      staircase = null;
      floor = null;
      door = null;
    }
    let address: Address = {
      countyId: countyId,
      zipCode: zipCode,
      city: addressForm.controls['city'].value!,
      street: addressForm.controls['streetName'].value!,
      number: addressForm.controls['number'].value!,
      staircase: staircase,
      floor: floor,
      door: door
    }
    return address;
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

  get company(){
    return this.registForm.controls['company'];
  }

  get companyAddress(){
    return this.company.controls['address'];
  }

  get address(){
    return this.registForm.controls['address'];
  }

}
