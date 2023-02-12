import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './_components/login/login-page/login-page.component';
import { LoginFormComponent } from './_components/login/login-form/login-form.component';
import { RegistFormComponent } from './_components/login/regist-form/regist-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MainPageComponent } from './_components/main/main-page/main-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchDropdownComponent } from './_components/search-dropdown/search-dropdown.component';
import { InputComponent } from './_components/input/input.component';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MenuComponent } from './_components/menu/menu.component';
import { NotFoundComponent } from './_components/not-found/not-found.component';
import { AdPageComponent } from './_components/main/ad-page/ad-page.component';
import { UserPageComponent } from './_components/main/user-page/user-page.component';
import { AdvestComponent } from './_components/advest/advest.component';
import { AddressFormComponent } from './_components/address-form/address-form.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    LoginFormComponent,
    RegistFormComponent,
    MainPageComponent,
    AdPageComponent,
    UserPageComponent,
    SearchDropdownComponent,
    InputComponent,
    MenuComponent,
    NotFoundComponent,
    AdvestComponent,
    AddressFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatButtonModule,
    MatSlideToggleModule,
  ],
  providers: [
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
