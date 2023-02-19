import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Tag } from '../_model/Tag';


@Injectable({
  providedIn: 'root'
})
export class JobTagsService {

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/JobTags/";

  constructor(private http:HttpClient) { }

  getAllJobTags():Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllJobTags`);
  }

  getJobTagById(id: number):Observable<Tag>{
    return this.http.get<Tag>(`${this.apiUrl}getJobTagById/${id}`);
  }

}
