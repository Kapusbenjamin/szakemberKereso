import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';
import { EditUserAddressDialogComponent } from '../../dialogs/edit-user-address-dialog/edit-user-address-dialog.component';
import { EditUserCompanyDialogComponent } from '../../dialogs/edit-user-company-dialog/edit-user-company-dialog.component';
import { EditUserDialogComponent } from '../../dialogs/edit-user-dialog/edit-user-dialog.component';
import { EditUserProfessionsDialogComponent } from '../../dialogs/edit-user-professions-dialog/edit-user-professions-dialog.component';
import { CreateCompanyDialogComponent } from '../../dialogs/create-company-dialog/create-company-dialog.component';
import { Router } from '@angular/router';
import { SetNewPasswordDialogComponent } from '../../dialogs/set-new-password-dialog/set-new-password-dialog.component';


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {


  constructor(private userService: UsersService,
    private companyService: CompaniesService,
    private router: Router,
    public dialog: MatDialog
    ) {
      this.userData =  this.userService.userData
    }
    
  user!: User;
  userData!: UserData;
  userDelete: boolean = false;
    
  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.userService.getUserById(this.userService.userData.userId).subscribe((user:any)=>{
      this.user = user;
    });
  }

  setDelete(){
    this.userDelete = true;
  }

  cancel(){
    this.userDelete = false;
  }

  deleteCompany() {
    this.companyService.deleteCompanyById(this.user.company!.id).subscribe(res=>{
      if(res){
        delete this.user.company;
      }
    })
  }

  deleteUser(){
    this.userService.deleteUser(this.userData.userId).subscribe(res=>{
      if(res){
        this.userService.clearUserData();
        this.router.navigate(['']);
      }
    })
  }
  setNewPasswordDialog(){
    const dialogRef = this.dialog.open(SetNewPasswordDialogComponent, {
      data: {userId: this.userData.userId}
    });
  }

  editCompanyAddressDialog() {
    const dialogRef = this.dialog.open(EditUserAddressDialogComponent, {
      data: {address: this.user.company!.address}
    });
  }

  editCompanyDialog() {
    const dialogRef = this.dialog.open(EditUserCompanyDialogComponent, {
      data: {company: this.user.company}
    });
  }

  editAddressDialog() {
    const dialogRef = this.dialog.open(EditUserAddressDialogComponent, {
      data: {address: this.user.address}
    });
  }

  editUserProfessionsDialog() {
    const dialogRef = this.dialog.open(EditUserProfessionsDialogComponent, {
      data: {user: this.user}
    });
  }

  editUserDialog() {
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      data: {user: this.user}
    });
  }

  createCompanyDialog() {
    const dialogRef = this.dialog.open(CreateCompanyDialogComponent, {
    });
  }

  changeUserType(){
    this.userService.changeAccess(this.userData.userId).subscribe(res=>{
      if(res){
        this.userData.access_type = 1;
        this.userService.setUserData(this.userData);
        window.location.reload();
      }
    })
  }
    
  userType(){
    return this.user.accessType == 0 ? "Megrendelő" : this.user.accessType == 2 ? "Admin" : "Szakember"
  }
    
}
  