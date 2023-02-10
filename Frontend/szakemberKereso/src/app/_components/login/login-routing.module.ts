import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistFormComponent } from './regist-form/regist-form.component';

const routes: Routes = [
  {
    path: '', component: LoginPageComponent,
    children: [
      { path:'', component:LoginFormComponent},
      { path:'regist', component:RegistFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
