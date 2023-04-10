import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Job } from '../_model/Job';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Jobs/";

  constructor(private http:HttpClient, private userService: UsersService) { }

  getAllJobs(){
    return this.http.post(`${this.apiUrl}getAllJobs`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllJobsByWorker(workerId: number){
    return this.http.post(`${this.apiUrl}getAllJobsByWorker`,{
      workerId,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllJobsByCustomer(customerId: number){
    return this.http.post(`${this.apiUrl}getAllJobsByCustomer`,{
      customerId,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  deleteJob(id: number){
    return this.http.post(`${this.apiUrl}deleteJob`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateJobByWorker(id: number, total: number){
    return this.http.post(`${this.apiUrl}updateJobByWorker`,{
      id,
      total,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateJobByCustomer(id: number, description: string){
    return this.http.post(`${this.apiUrl}updateJobByCustomer`,{
      id,
      description,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  changeJobStatus(id: number){
    return this.http.post(`${this.apiUrl}changeJobStatus`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getJobById(id: number){
    return this.http.post(`${this.apiUrl}getJobById`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  acceptByWorker(id: number){
    return this.http.post(`${this.apiUrl}acceptByWorker`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  acceptByCustomer(id: number){
    return this.http.post(`${this.apiUrl}acceptByCustomer`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  createJob(customerId: number, workerId: number, description:string){
    return this.http.post(`${this.apiUrl}createJob`,{
      customerId,
      workerId,
      description
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

}
