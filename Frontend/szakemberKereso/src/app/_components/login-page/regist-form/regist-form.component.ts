import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { City } from 'src/app/_model/City';
import { County } from 'src/app/_model/County';
import { DropDown } from 'src/app/_model/DropDown';
import { Field } from 'src/app/_model/Field';
import { JobTag } from 'src/app/_model/JobTag';
import { HttpService } from 'src/app/_services/http.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';

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
    county:new FormControl('',[Validators.required]),
    city:new FormControl('',[Validators.required]),
    zipCode:new FormControl('',[Validators.required]),
    streetName:new FormControl('',[Validators.required]),
    number:new FormControl('',[Validators.required]),
    staircase:new FormControl(''),
    floor:new FormControl(''),
    door:new FormControl(''),
    profession: new FormControl(''),
    companyName: new FormControl(''),
    taxNumber: new FormControl(''),
    companyZipCode: new FormControl(''),
    companyCounty: new FormControl(''),
    companyCity: new FormControl(''),
    companyStreetName: new FormControl(''),
    companyNumber: new FormControl(''),
    companyStaircase: new FormControl(''),
    companyFloor: new FormControl(''),
    companyDoor: new FormControl('')
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
    {control: this.registForm.controls['zipCode'], name:'Irányítószám', type:"text"},
    {control: this.registForm.controls['number'], name:'Házszám', type:"text"},
    {control: this.registForm.controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.registForm.controls['floor'], name:'Emelet', type:"text"},
    {control: this.registForm.controls['door'], name:'Ajtó', type:"text"},
  ];
  companyFields:Field[] = [
    {control: this.registForm.controls['companyName'], name:'Cég név', type:"text"},
    {control: this.registForm.controls['taxNumber'], name:'Adószám', type:"text"},
  ];
  companyAddressFields: Field[] = [
    {control: this.registForm.controls['companyZipCode'], name:'Irányítószám', type:"text"},
    {control: this.registForm.controls['companyNumber'], name:'Házszám', type:"text"},
    {control: this.registForm.controls['companyStaircase'], name:'Lépcsőház', type:"text"},
    {control: this.registForm.controls['companyFloor'], name:'Emelet', type:"text"},
    {control: this.registForm.controls['companyDoor'], name:'Ajtó', type:"text"},
  ]

  counties: DropDown[] = [];
  citiesNames: DropDown[] = [];
  streetsNames: DropDown[] = [];
  professional: boolean = false;
  professions: DropDown[] = [];
  company: boolean = false;

  constructor(private fb: FormBuilder, private http: HttpService, private jobTagsService: JobTagsService) { }

  ngOnInit(): void {
    this.loadCounties();
    this.loadCities();
    this.loadProfessions();
  }

  professionalSlide(){
    this.professional = this.professional == false;
    if(this.professional == false){
      this.company = false;
      this.registForm.controls['profession'].removeValidators(Validators.required);
      this.companyValidatorsRemove();
    }
    if(this.professional == true){
      this.registForm.controls['profession'].addValidators(Validators.required);
    }
  }

  companyValidatorsRemove(){
    this.registForm.controls['companyName'].removeValidators(Validators.required);
    this.registForm.controls['taxNumber'].removeValidators(Validators.required);
    this.registForm.controls['companyZipCode'].removeValidators(Validators.required);
    this.registForm.controls['companyCounty'].removeValidators(Validators.required);
    this.registForm.controls['companyCity'].removeValidators(Validators.required);
    this.registForm.controls['companyStreetName'].removeValidators(Validators.required);
    this.registForm.controls['companyNumber'].removeValidators(Validators.required);
  }

  companyValidatorsAdd(){
    this.registForm.controls['companyName'].addValidators(Validators.required);
    this.registForm.controls['taxNumber'].addValidators(Validators.required);
    this.registForm.controls['companyZipCode'].addValidators(Validators.required);
    this.registForm.controls['companyCounty'].addValidators(Validators.required);
    this.registForm.controls['companyCity'].addValidators(Validators.required);
    this.registForm.controls['companyStreetName'].addValidators(Validators.required);
    this.registForm.controls['companyNumber'].addValidators(Validators.required);
  }

  companySlide(){
    this.company = this.company == false;
    if(this.company == false){
      this.companyValidatorsRemove();
    }
    if(this.company == true){
      this.companyValidatorsAdd();
    }
  }

  regist(){
    if(this.registForm.valid){
      console.log(this.registForm.value);
    }else{
      alert("invalidForm");
      window.scroll({
        top: 0,
        behavior: 'smooth'
      });
    }
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

  loadCities(){
    this.http.getAllCities().subscribe((response)=>{
      response.forEach((city:City, index:number)=>{
        this.citiesNames.push({id: index, name: city.city})
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

}
