import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Job } from '../_model/Job';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Jobs/";

  constructor(private http:HttpClient, private userService: UsersService) { }

  getAllJobs(){
    return this.http.post(`${this.apiUrl}getAllJobs`,this.userService.userData.userId);
  }

  getAllJobsByWorker(workerId: number){
    return this.http.post(`${this.apiUrl}getAllJobsByWorker`,{
      workerId,
      currentUserId: this.userService.userData.userId
    })
  }

  getAllJobsByCustomer(customerId: number){
    return this.http.post(`${this.apiUrl}getAllJobsByCustomer`,{
      customerId,
      currentUserId: this.userService.userData.userId
    })
  }

  deleteJob(id: number){
    return this.http.post(`${this.apiUrl}deleteJob`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  updateJobByWorker(id: number, total: number){
    return this.http.post(`${this.apiUrl}updateJobByWorker`,{
      id,
      total,
      currentUserId: this.userService.userData.userId
    })
  }

  updateJobByCustomer(id: number, description: string){
    return this.http.post(`${this.apiUrl}updateJobByCustomer`,{
      id,
      description,
      currentUserId: this.userService.userData.userId
    })
  }

  changeJobStatus(id: number){
    return this.http.post(`${this.apiUrl}changeJobStatus`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  getJobById(id: number){
    return this.http.post(`${this.apiUrl}getJobById`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  acceptByWorker(id: number){
    return this.http.post(`${this.apiUrl}acceptByWorker`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  acceptByCustomer(id: number){
    return this.http.post(`${this.apiUrl}acceptByCustomer`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  createJob(job: Job){
    return this.http.post(`${this.apiUrl}acceptByCustomer`,job)
  }

}
