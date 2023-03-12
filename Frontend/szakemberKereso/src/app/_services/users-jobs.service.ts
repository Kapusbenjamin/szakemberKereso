import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Tag } from '../_model/Tag';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class UsersJobsService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/UsersJobs/";

  constructor(private http: HttpClient, private userService: UsersService) { }

  addNewJobToUser(userId: number, tagId: number,currentUserId:number ){
    return this.http.post(`${this.apiUrl}addNewJobToUser`,{
      userId: userId,
      jobTagId: tagId,
      currentUserId,
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllJobsByUser(id: number):Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}getAllJobsByUser/${id}`).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  deleteUserJob(userId: number, jobTagId: number){
   return this.http.post(`${this.apiUrl}`,{
      userId,
      jobTagId,
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

}
