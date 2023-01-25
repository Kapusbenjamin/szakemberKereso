import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-regist-form',
  templateUrl: './regist-form.component.html',
  styleUrls: ['./regist-form.component.css']
})
export class RegistFormComponent implements OnInit {

  registForm!:FormGroup
  fields:any[] = [
    {key:'firstName', display:'First Name'},
    {key:'lastName', display:'Last Name'},
    {key:'email', display:'Email'},
    {key:'telNumber', display:'Tel. number'},
  ];
  counties:any[]=[];

  // User: Firstname, Lastname, Email, Tel. number, password;
  // address: megye, város, irányítószám, utca, házszám, (lépcsőház, emelet, ajtó);
  // ? szakember: foglalkozás
  // ?? company: address, név, adószám


  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.registForm = this.fb.group({
      firstName:'',
      lastName:'',
      email:'',
      telNumber:'',
    })
  }

  regist(){
    console.log(this.registForm.controls['firstName'].value);
  }

}
