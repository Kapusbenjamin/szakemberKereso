import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Tag } from '../_model/Tag';

@Injectable({
  providedIn: 'root'
})
export class UsersJobsService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/UsersJobs/";

  constructor(private http: HttpClient) { }

  addNewJobToUser(userId: number, tagId: number){
    return this.http.post(`${this.apiUrl}addNewJobToUser`,{
      userId: userId,
      jobTagId: tagId
    });
  }

  getAllJobsByUser(id: number):Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllJobsByUser/${id}`);
  }

  //nope
  // deleteUserJob(){
  //  return this.http.delete(`${this.apiUrl}`);
  // }

}
