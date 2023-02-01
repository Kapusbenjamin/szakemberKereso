import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { City } from 'src/app/_model/City';
import { County } from 'src/app/_model/County';
import { Field } from 'src/app/_model/Field';
import { HttpService } from 'src/app/_services/http.service';

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
    county:new FormControl('',[Validators.required]),
    city:new FormControl('',[Validators.required]),
    zipCode:new FormControl('',[Validators.required]),
    streetName:new FormControl('',[Validators.required]),
    number:new FormControl('',[Validators.required]),
    staircase:new FormControl(''),
    floor:new FormControl(''),
    door:new FormControl(''),
    profession: new FormControl(''),
  });

  fields:Field[] = [
    {control: this.registForm.controls['lastName'], name:'Vezetéknév', type:"text"},
    {control: this.registForm.controls['firstName'], name:'Keresztnév', type:"text"},
    {control: this.registForm.controls['email'], name:'Email', type:"email"},
    {control: this.registForm.controls['number'], name:'Tel. number', type:"text"},
    {control: this.registForm.controls['password'], name:'Password', type:"password"},
  ];

  addressFields:Field[] = [
    {control: this.registForm.controls['zipCode'], name:'Irányítószám', type:"text"},
    {control: this.registForm.controls['number'], name:'Házszám', type:"text"},
    {control: this.registForm.controls['staircase'], name:'Lépcsőház', type:"text"},
    {control: this.registForm.controls['floor'], name:'Emelet', type:"text"},
    {control: this.registForm.controls['door'], name:'Ajtó', type:"text"},
  ];
  counties:string[] = [];
  citiesNames:string[] = [];
  streetsNames:string[] = [];
  professional:boolean = false;

  // User: Firstname, Lastname, Email, Tel. number, password;
  // address: megye, város, irányítószám, utca, házszám, (lépcsőház, emelet, ajtó); https://stackblitz.com/edit/angular-searchable-dropdown?file=src%2Fapp%2Fhello.component.ts
  // ? szakember: foglalkozás
  // ?? company: address, név, adószám


  constructor(private fb: FormBuilder, private http: HttpService) { }


  ngOnInit(): void {

    this.loadCounties();
    this.loadCities();

  }

  professionalSlide(){
    this.professional = this.professional == false;
  }


  regist(){
    console.log(this.registForm.controls['county'].value);
  }

  loadCounties(){
    this.http.getAllCounties().subscribe((response:County[])=>{
      response.forEach((county:County)=>{
        if(county.countyName != "Budapest"){
          this.counties.push(county.countyName)
        }
      });
    });
  }

  loadCities(){
    this.http.getAllCities().subscribe((response)=>{
      response.forEach((city:City)=>{
        this.citiesNames.push(city.city)
      });
    });
  }


}
