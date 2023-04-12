import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Job } from 'src/app/_model/Job';
import { UserData } from 'src/app/_model/UserData';
import { JobsService } from 'src/app/_services/jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-edit-job-dialog',
  templateUrl: './edit-job-dialog.component.html',
  styleUrls: ['./edit-job-dialog.component.css']
})
export class EditJobDialogComponent implements OnInit {

  userData: UserData
  formG = new FormGroup({
    description: new FormControl(''),
    total: new FormControl<number>(0),
  })

  constructor(private userService: UsersService,
    private jobService: JobsService,
    public dialogRef: MatDialogRef<EditJobDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {job: Job}) { 
      this.userData = this.userService.userData;
    }

  ngOnInit(): void {
    this.setDefaultValues()
  }
  
  setDefaultValues(){
    this.formG.controls['total'].setValue(this.data.job.total!);
    this.formG.controls['description'].setValue(this.data.job.description);
    if(this.userData.userId == this.data.job.customerId){
      this.formG.controls['total'].disable;
    }else if(this.userData.userId == this.data.job.workerId){
      this.formG.controls['description'].disable;
    }
  }

  editJob(){
    if(this.userData.userId == this.data.job.customerId){
      this.editJobByCustomer();
    }else if(this.userData.userId == this.data.job.workerId){
      this.editJobByWorker();
    }
  }

  editJobByWorker(){
    let total = this.formG.controls['total'].value!
    this.jobService.updateJobByWorker(this.data.job.id!,total).subscribe(res=>{
      window.location.reload();
    })
  }
  
  editJobByCustomer(){
    let description = this.formG.controls['description'].value!
    this.jobService.updateJobByCustomer(this.data.job.id!,description).subscribe(res=>{
      window.location.reload();
    })
  }

  close():void{
    this.dialogRef.close();
  }

}
