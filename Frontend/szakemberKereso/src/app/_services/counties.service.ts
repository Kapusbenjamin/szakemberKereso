import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tag } from '../_model/Tag';

@Injectable({
  providedIn: 'root'
})
export class CountiesService {

  constructor(private http:HttpClient) { }

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Counties/";

  getAllCounties():Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllCounties`);
  }

}
