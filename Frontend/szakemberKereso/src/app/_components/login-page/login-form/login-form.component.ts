import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  loginForm = this.fb.group({
    emailNumber: new FormControl('',[Validators.required, Validators.email]),
    password: new FormControl('',[Validators.required]),
  })

  ngOnInit(): void {

  }

  login(){
    let emailNumber = this.loginForm.controls['emailNumber'].value;
    let password = this.loginForm.controls['password'].value;
    console.log(emailNumber, password);
  }

}
