import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export default class LoginServiceService {

  apiUrl = "http://localhost:3000/";

  constructor(private http:HttpClient) { }

  loginUser() : Observable<any>{
    return this.http.get(`${this.apiUrl}/login`);
  }

}
