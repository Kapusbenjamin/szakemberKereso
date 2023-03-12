import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Tag } from '../_model/Tag';


@Injectable({
  providedIn: 'root'
})
export class JobTagsService {

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/JobTags/";

  constructor(private http:HttpClient) { }

  getAllJobTags():Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllJobTags`).pipe(
      map((response: any) => {
        if (response.message == "Sikeresen lekérte az összes szakmát!") {
          return response.result;
        }
      }),
      catchError((error: any) => {
        return throwError(error);
      })
    );
  }

  getJobTagById(id: number):Observable<Tag>{
    return this.http.get<Tag>(`${this.apiUrl}getJobTagById/${id}`).pipe(
      map((response: any) => {
        if (response.message == "Sikeresen lekérte a szakmát!") {
          return response.result;
        }
      }),
      catchError((error: any) => {
        return throwError(error);
      })
    );
  }

}
