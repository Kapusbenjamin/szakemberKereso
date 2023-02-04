import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { County } from '../_model/County';

@Injectable({
  providedIn: 'root'
})
export class CountiesService {

  constructor(private http:HttpClient) { }

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Counties/";

  getAllCounties():Observable<County[]>{
    return this.http.get<County[]>(`${this.apiUrl}getAllCounties`);
  }

}
