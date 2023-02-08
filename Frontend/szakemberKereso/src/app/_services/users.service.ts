import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../_model/User';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/";

  constructor(private http:HttpClient) { }

  loginUser(email:string | null, phone:string | null, password:string): Observable<User>{
    let User = {
      email: email,
      phone: phone,
      password: password
    }
    return this.http.post<User>(`${this.apiUrl}loginUser`,User);
  }

  createUser(user:any)/*:Observable<User>*/{
    console.log(user)
    // return this.http.post<User>(`${this.apiUrl}createUser`,{
    //   user,
    // });
  }



}
