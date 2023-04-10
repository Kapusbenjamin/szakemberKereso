import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UserData } from 'src/app/_model/UserData';
import { JobsService } from 'src/app/_services/jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-create-job-dialog',
  templateUrl: './create-job-dialog.component.html',
  styleUrls: ['./create-job-dialog.component.css']
})
export class CreateJobDialogComponent implements OnInit {

  userData: UserData
  description = new FormControl('')
  
  
  constructor(private userService: UsersService,
    private jobService: JobsService,
    public dialogRef: MatDialogRef<CreateJobDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {workerId: number, name: string, jobTag: string}) { 
      this.userData = this.userService.userData
     }

  ngOnInit(): void {

  }

  createJob(){   
    this.jobService.createJob(this.userData.userId, this.data.workerId, this.description.value!).subscribe(res=>{
      this.close();
    });
  }

  close():void{
    this.dialogRef.close();
  }

}
