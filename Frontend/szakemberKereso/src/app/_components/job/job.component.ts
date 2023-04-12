import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Job } from 'src/app/_model/Job';
import { UserData } from 'src/app/_model/UserData';
import { JobsService } from 'src/app/_services/jobs.service';
import { UsersService } from 'src/app/_services/users.service';
import { EditJobDialogComponent } from '../dialogs/edit-job-dialog/edit-job-dialog.component';
import { RatingWorkerDialogComponent } from '../dialogs/rating-worker-dialog/rating-worker-dialog.component';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  @Input() job!: Job;
  currentUser: UserData;

  constructor(private jobService: JobsService,
     private UserService: UsersService,
     public dialog: MatDialog) { 
    this.currentUser = this.UserService.userData;
  }

  ngOnInit(): void {

  }

  acceptByWorker(){
    this.jobService.acceptByWorker(this.job.id!).subscribe(res=>{
      this.job.workerAccepted = 1;
    })
  }
  
  acceptByCustomer(){
    this.jobService.acceptByCustomer(this.job.id!).subscribe(res=>{
      this.job.customerAccepted = 1;
    })
  }

  jobClose(){
    this.jobService.changeJobStatus(this.job.id!).subscribe(res=>{
      if(res){
        this.job.status = 1;
      }
    })
  }

  editByWorkerDialog(){
    const dialogRef = this.dialog.open(EditJobDialogComponent, {
      data: { job: this.job},
    });
  }

  editByCustomerDialog(){
    const dialogRef = this.dialog.open(EditJobDialogComponent, {
      data: { job: this.job},
    });
  }

  ratingWorkerDialog(){
    const dialogRef = this.dialog.open(RatingWorkerDialogComponent, {
      data: { ratingedUserId: this.job.workerId, ratingedUserName: this.job.worker!.firstName + " " + this.job.worker!.lastName },
    });
  }
  
}
