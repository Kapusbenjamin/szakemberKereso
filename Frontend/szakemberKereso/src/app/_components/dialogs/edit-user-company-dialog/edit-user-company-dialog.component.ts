import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Company } from 'src/app/_model/Company';
import { UserData } from 'src/app/_model/UserData';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-edit-user-company-dialog',
  templateUrl: './edit-user-company-dialog.component.html',
  styleUrls: ['./edit-user-company-dialog.component.css']
})
export class EditUserCompanyDialogComponent implements OnInit {

  formG = new FormGroup({
    name: new FormControl("",[Validators.required]),
    taxNumber: new FormControl("",[Validators.required,Validators.pattern('[0-9]+')])
  })
  userData: UserData

  constructor(private userService: UsersService,
    private companyService: CompaniesService,
    public dialogRef: MatDialogRef<EditUserCompanyDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {company: Company}
  ) {
    this.userData = this.userService.userData
   }

  ngOnInit(): void {
    this.setValues();
  }

  setValues(){
    this.formG.controls['name'].setValue(this.data.company.name);
    this.formG.controls['taxNumber'].setValue(this.data.company.taxNumber);
  }

  editCompany(){
    let name = this.formG.controls['name'].value!;
    let taxNumber = this.formG.controls['taxNumber'].value!; 
    this.companyService.updateCompanyById(this.data.company.id, name, taxNumber).subscribe(res=>{
      window.location.reload();
    })
  }

  close(){
    this.dialogRef.close();
  }

}
