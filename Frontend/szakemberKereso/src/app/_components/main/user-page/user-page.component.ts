import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';
import { EditUserAddressDialogComponent } from '../../dialogs/edit-user-address-dialog/edit-user-address-dialog.component';
import { EditUserCompanyDialogComponent } from '../../dialogs/edit-user-company-dialog/edit-user-company-dialog.component';


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(private userService: UsersService,
    private companyService: CompaniesService,
    public dialog: MatDialog
    ) {
      this.userData =  this.userService.userData
    }
    
  user!: User;
  userData!: UserData;
    
  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.userService.getUserById(this.userService.userData.userId).subscribe((user:any)=>{
      this.user = user;
    });
  }

  deleteCompany() {
    this.companyService.deleteCompanyById(this.user.company!.id).subscribe(res=>{
      if(res){
        delete this.user.company;
      }
    })
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
    throw new Error('Method not implemented.');
  }

  editUserDialog() {
    throw new Error('Method not implemented.');
  }
    
  userType(){
    return this.user.accessType == 0 ? "Általános felhasználó" : this.user.accessType == 2 ? "Admin" : "Szakember"
  }
    
}
  