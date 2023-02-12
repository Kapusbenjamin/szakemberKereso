import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private fb: FormBuilder, private usersService: UsersService, private route: Router) { }

  loginForm = this.fb.group({
    emailNumber: new FormControl<string>('',[Validators.required]),
    password: new FormControl<string>('',[Validators.required]),
  })

  ngOnInit(): void {
  }

  login(){
    let User = null;
    if(this.loginForm.valid){
      let emailNumber = this.loginForm.controls['emailNumber'].value;
      let reg = new RegExp('@');
      let email = null;
      let phone = null;
      if(reg.test(emailNumber!)){
        email = emailNumber;
      }else{
        phone = emailNumber;
      }
      let password = this.loginForm.controls['password'].value;
      this.usersService.loginUser(email,phone,password!).subscribe((response)=>{
        User = response;
        if(User.id! > 0){
          localStorage.setItem('id',User.id + '');
          localStorage.setItem('access_type',User.accessType + '');
          localStorage.setItem('firstName',User.firstName);
          localStorage.setItem('lastName',User.lastName);
          this.route.navigateByUrl('/main/advertisement');
        }else{
          alert('Sikertelen bejelentkezés');
        }
      });
     }else{
      alert('Érvénytelen bejelentkezés');
    }
  }



}
