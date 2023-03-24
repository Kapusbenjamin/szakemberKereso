import { Component, Input, OnInit } from '@angular/core';
import { Job } from 'src/app/_model/Job';
import { UserData } from 'src/app/_model/UserData';
import { JobsService } from 'src/app/_services/jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  @Input() job!: Job;
  currentUser: UserData;

  constructor(private jobService: JobsService, private UserService: UsersService) { 
    this.currentUser = UserService.userData;
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


  
}
