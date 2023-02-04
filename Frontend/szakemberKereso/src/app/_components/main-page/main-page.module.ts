import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainPageRoutingModule } from './main-page-routing.module';
import { UserPageComponent } from './user-page/user-page.component';
import { AdPageComponent } from './ad-page/ad-page.component';


@NgModule({
  declarations: [
    UserPageComponent,
    AdPageComponent
  ],
  imports: [
    CommonModule,
    MainPageRoutingModule
  ]
})
export class MainPageModule { }
