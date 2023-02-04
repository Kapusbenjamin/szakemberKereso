import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/";

  constructor(private http:HttpClient) { }

  loginUser(email:string | null, phone:string | null, password:string | null){
    return this.http.post(`${this.apiUrl}`,
    {
      email: email,
      phone: phone,
      password: password
  });
  }

}
