import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { JobTag } from '../_model/JobTag';


@Injectable({
  providedIn: 'root'
})
export class JobTagsService {

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/JobTags/";

  constructor(private http:HttpClient) { }

  getAllJobTags():Observable<JobTag[]>{
    return this.http.get<JobTag[]>(`${this.apiUrl}getAllJobTags`);
  }

  getJobTagById(id: number):Observable<JobTag>{
    return this.http.post<JobTag>(`${this.apiUrl}getJobTagById`,{
      id
    });
  }

}
