import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Company } from 'src/app/_model/Company';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-create-company-dialog',
  templateUrl: './create-company-dialog.component.html',
  styleUrls: ['./create-company-dialog.component.css']
})
export class CreateCompanyDialogComponent implements OnInit {

  formG = new FormGroup({
    
  })

  constructor(private userService: UsersService,
     private companyService: CompaniesService,
     public dialogRef: MatDialogRef<CreateCompanyDialogComponent>
     ) { }

  ngOnInit(): void {

  }

  createCompany(){
    let company = this.formG.get('company')!.value as Company
    console.log(company);
    this.companyService.createCompany(company).subscribe(res=>{
      window.location.reload();
    })
  }

  close(){
    this.dialogRef.close();
  }

}
