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
import { CompanyFormComponent } from './_components/company-form/company-form.component';
import { UserChatComponent } from './_components/user-chat/user-chat.component';
import { MessagesPageComponent } from './_components/main/messages-page/messages-page.component';
import { ChatComponent } from './_components/chat/chat.component';
import { ChatFormComponent } from './_components/chat-form/chat-form.component';
import { FavoritesPageComponent } from './_components/main/favorites-page/favorites-page.component';
import { AdminPageComponent } from './_components/admin/admin-page/admin-page.component';
import { MatMenuModule } from '@angular/material/menu'; 
import { AdsAdminPageComponent } from './_components/admin/ads-admin-page/ads-admin-page.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { UsersAdminPageComponent } from './_components/admin/users-admin-page/users-admin-page.component';
import { JobComponent } from './_components/job/job.component';
import { RatingComponent } from './_components/rating/rating.component';
import { JobPageComponent } from './_components/main/job-page/job-page.component';
import { RatingPageComponent } from './_components/main/rating-page/rating-page.component';
import { LoaderComponent } from './_components/loader/loader.component';
import { RatingsPageComponent } from './_components/admin/ratings-page/ratings-page.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AddNewAdDialogComponent } from './_components/dialogs/add-new-ad-dialog/add-new-ad-dialog.component';
import { CreateJobDialogComponent } from './_components/dialogs/create-job-dialog/create-job-dialog.component';
import { EditAdDialogComponent } from './_components/dialogs/edit-ad-dialog/edit-ad-dialog.component';
import { EditJobDialogComponent } from './_components/dialogs/edit-job-dialog/edit-job-dialog.component';
import { RatingWorkerDialogComponent } from './_components/dialogs/rating-worker-dialog/rating-worker-dialog.component';
import { EditUserDialogComponent } from './_components/dialogs/edit-user-dialog/edit-user-dialog.component';
import { EditUserProfessionsDialogComponent } from './_components/dialogs/edit-user-professions-dialog/edit-user-professions-dialog.component';
import { EditUserCompanyDialogComponent } from './_components/dialogs/edit-user-company-dialog/edit-user-company-dialog.component';
import { EditUserAddressDialogComponent } from './_components/dialogs/edit-user-address-dialog/edit-user-address-dialog.component';
import { CreateCompanyDialogComponent } from './_components/dialogs/create-company-dialog/create-company-dialog.component';
import { SetNewPasswordDialogComponent } from './_components/dialogs/set-new-password-dialog/set-new-password-dialog.component';

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
    AddressFormComponent,
    CompanyFormComponent,
    UserChatComponent,
    MessagesPageComponent,
    ChatComponent,
    ChatFormComponent,
    FavoritesPageComponent,
    AdminPageComponent,
    AdsAdminPageComponent,
    UsersAdminPageComponent,
    JobComponent,
    RatingComponent,
    JobPageComponent,
    RatingPageComponent,
    LoaderComponent,
    RatingsPageComponent,
    AddNewAdDialogComponent,
    CreateJobDialogComponent,
    EditAdDialogComponent,
    EditJobDialogComponent,
    RatingWorkerDialogComponent,
    EditUserDialogComponent,
    EditUserProfessionsDialogComponent,
    EditUserCompanyDialogComponent,
    EditUserAddressDialogComponent,
    CreateCompanyDialogComponent,
    SetNewPasswordDialogComponent
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
    MatButtonModule,
    MatSlideToggleModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule
  ],
  providers: [
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
