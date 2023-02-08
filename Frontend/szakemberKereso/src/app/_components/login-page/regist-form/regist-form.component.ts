import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { City } from 'src/app/_model/City';
import { County } from 'src/app/_model/County';
import { DropDown } from 'src/app/_model/DropDown';
import { Field } from 'src/app/_model/Field';
import { JobTag } from 'src/app/_model/JobTag';
import { User } from 'src/app/_model/User';
import { HttpService } from 'src/app/_services/http.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-regist-form',
  templateUrl: './regist-form.component.html',
  styleUrls: ['./regist-form.component.css']
})
export class RegistFormComponent implements OnInit {

  registForm = this.fb.group({
    lastName:new FormControl('',[Validators.required]),
    firstName: new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required]),
    telNumber:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    passwordConfirm:new FormControl('',[Validators.required]),
    address: this.fb.group({
      county:new FormControl(''),
      city:new FormControl(''),
      zipCode:new FormControl(''),
      streetName:new FormControl(''),
      number:new FormControl(''),
      staircase:new FormControl(''),
      floor:new FormControl(''),
      door:new FormControl(''),
    }),
    profession: new FormControl(''),
    company: this.fb.group({
      companyName: new FormControl(''),
      taxNumber: new FormControl(''),
      address: this.fb.group({
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
    {control: this.registForm.controls['address'].controls['zipCode'], name:'Irányítószám', type:"text"},
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

  counties: DropDown[] = [];
  citiesNames: DropDown[] = [];
  streetsNames: DropDown[] = [];
  professional: boolean = false;
  professions: DropDown[] = [];
  company: boolean = false;
  county: any = null;

  constructor(private fb: FormBuilder,
      private http: HttpService,
      private jobTagsService: JobTagsService,
      private userService: UsersService) { }

  ngOnInit(): void {
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
      this.userService.createUser(this.registForm.value)
    // if(this.registForm.valid){
    // }else{
    //   alert("invalidForm");
    //   window.scroll({
    //     top: 0,
    //     behavior: 'smooth'
    //   });
    // }
  }

  loadCounties(){
    this.http.getAllCounties().subscribe((response:County[])=>{
      response.forEach((county:County)=>{
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
          this.citiesNames.push({id: index, name: city.city})
        }
      });
    });
  }

  loadProfessions(){
    this.jobTagsService.getAllJobTags().subscribe((response)=>{
      response.forEach((jobTag:JobTag) => {
        this.professions.push(jobTag);
      });
    })
  }

  getIdFromDropDown(name: string):number {
    let id = -1
    this.professions.forEach((element:DropDown)=>{
      if(name == element.name){
        id = element.id;
      }
    });
    return id;
  }

}
