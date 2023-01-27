import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/internal/Observable';
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

  registForm!:FormGroup
  fields:Field[] = [
    {key:'firstName', display:'First Name'},
    {key:'lastName', display:'Last Name'},
    {key:'email', display:'Email'},
    {key:'telNumber', display:'Tel. number'},
  ];
  addressFields:Field[] = [
    {key:'zipCode', display:'Zip code'},
    {key:'streetName', display:'Street Name'},
    {key:'number', display:'House number'},
    {key:'staircase', display:'Staircase'},
    {key:'floor', display:'Floor'},
    {key:'door', display:'Door'},
  ];
  counties:County[] = [];
  cities:City[] = [];

  // User: Firstname, Lastname, Email, Tel. number, password;
  // address: megye, város, irányítószám, utca, házszám, (lépcsőház, emelet, ajtó); https://stackblitz.com/edit/angular-searchable-dropdown?file=src%2Fapp%2Fhello.component.ts
  // ? szakember: foglalkozás
  // ?? company: address, név, adószám


  constructor(private fb: FormBuilder, private http: HttpService) { }

  ngOnInit(): void {
    this.registForm = this.fb.group({
      firstName:'',
      lastName:'',
      email:'',
      telNumber:'',
      city:'',
      zipCode:'',
      streetName:'',
      number:'',
      staircase:'',
      floor:'',
      door:''
    });
    this.loadCounties();
    this.loadCities();
  }

  regist(){
    console.log(this.registForm.controls['firstName'].value);
  }

  loadCounties(){
    this.http.getAllCounties().subscribe((response)=>{
      this.counties = response;
    });
  }

  compare( a: City, b :City ) {
    if ( a.city < b.city ){
      return -1;
    }
    if ( a.city > b.city ){
      return 1;
    }
    return 0;
  }

  loadCities(){
    this.http.getAllCities().subscribe((response)=>{
      this.cities = response.sort(this.compare);
    });
  }

}
