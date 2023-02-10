import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdPageComponent } from './ad-page/ad-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { UserPageComponent } from './user-page/user-page.component';

const routes: Routes = [
  {
    path: '', component: MainPageComponent,
    children: [
      { path:'user', component:UserPageComponent },
      { path:'advertisement', component:AdPageComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
