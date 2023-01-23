import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  loginForm!:FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      emailNumber: '',
      password:'',
    })
  }

  login(){
    let emailNumber = this.loginForm.controls['emailNumber'].value;
    let password = this.loginForm.controls['password'].value;
    console.log(emailNumber, password);
  }

}
