import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/_model/Job';
import { UserData } from 'src/app/_model/UserData';
import { JobsService } from 'src/app/_services/jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-job-page',
  templateUrl: './job-page.component.html',
  styleUrls: ['./job-page.component.css']
})
export class JobPageComponent implements OnInit {

  works: Job[] = [];
  orders: Job[] = [];
  currentUser: UserData;
  loaded: boolean = false;

  constructor(private jobService: JobsService, private userService: UsersService) {
    this.currentUser = this.userService.userData
   }

  ngOnInit(): void {
    this.getAllJobForUser();
  }

  getAllJobForUser(){
    if(this.currentUser.access_type > 0){
      this.loadWorks();
      this.loadOrders();
    }else{
      this.loadOrders();
    }
  }
  
  loadWorks(){
    this.jobService.getAllJobsByWorker(this.currentUser.userId).subscribe((jobs: Job[])=>{
      this.works = jobs
      this.loaded = true; 
    });
  }
  
  loadOrders(){
    this.jobService.getAllJobsByCustomer(this.currentUser.userId).subscribe((jobs: Job[])=>{
      this.orders = jobs
      this.loaded = true; 
    });
  }

}
