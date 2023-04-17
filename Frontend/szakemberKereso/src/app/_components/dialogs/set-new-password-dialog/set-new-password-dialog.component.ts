import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UsersService } from 'src/app/_services/users.service';
import { PasswordValidators } from 'src/app/_validators/password-validators';

@Component({
  selector: 'app-set-new-password-dialog',
  templateUrl: './set-new-password-dialog.component.html',
  styleUrls: ['./set-new-password-dialog.component.css']
})
export class SetNewPasswordDialogComponent implements OnInit {

  formG = new FormGroup({
    oldPass: new FormControl('',Validators.required),
    newPass: new FormControl('',[Validators.required,Validators.minLength(8),Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)]),
    newPassConfirm: new FormControl('',[Validators.required]),
  })

  constructor(private userService: UsersService,
    public dialogRef: MatDialogRef<SetNewPasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {userId: number}
    ) { }

  ngOnInit(): void {
    this.formG.addValidators([PasswordValidators.same('newPass','newPassConfirm')]);
  }

  setNewPassword(){
    let oldPass = this.formG.controls['oldPass'].value!;
    let newPass = this.formG.controls['newPass'].value!;
    this.userService.changePassword(this.data.userId,oldPass,newPass).subscribe(res=>{
      if(res){
        this.close();
      }
    })
  }

  close(){
    this.dialogRef.close();
  }

}
