import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {


  formG = new FormGroup({
    firstName: new FormControl("",[Validators.required]),
    lastName: new FormControl("",[Validators.required]),
    email: new FormControl("",[Validators.required,Validators.email]),
    phone: new FormControl("",[Validators.required,Validators.maxLength(12)]),
  })

  constructor(private userService: UsersService,
    public dialogRef: MatDialogRef<EditUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {user: User}) { }

  ngOnInit(): void {
    this.setValues();
  }

  setValues(){
    this.formG.controls['firstName'].setValue(this.data.user.firstName);
    this.formG.controls['lastName'].setValue(this.data.user.lastName);
    this.formG.controls['email'].setValue(this.data.user.email);
    this.formG.controls['phone'].setValue(this.data.user.phone);
  }

  updateUser(){
    let user :User = this.formG.value as User
    let userData: UserData = {
      userId: this.data.user.id!,
      name: user.firstName + " " + user.lastName,
      access_type: this.data.user.accessType!
    }
    this.userService.updateUser(this.data.user.id!,user).subscribe(res=>{
      this.userService.setUserData(userData);
      window.location.reload();
    })
  }

  close() {
    this.dialogRef.close();
  }

}
