import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../_model/Company';
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

  createUser(user:User):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}createUser`,{
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone,
      password: user.password,
      address: user.address
    });
  }

  createUserWorker(user: User, company: Company){
    return this.http.post(`${this.apiUrl}createUserWorker`,{
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone,
      password: user.password,
      address: user.address,
      company: company
    });
  }

  getUserById(id: number):Observable<User>{
    return this.http.get<User>(`${this.apiUrl}getUserById/${id}`);
  }

  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.apiUrl}getAllUsers`);
  }

  updateUser(id: number, user: User){
    return this.http.post(`${this.apiUrl}updateUser`,{
      id: id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone
    })
  }

  logoutUser(id: number){
    return this.http.post(`${this.apiUrl}logoutUser`,{
      id:id
    })
  }

  // deleteUser(id: number){
  //   return this.http.post(`${this.apiUrl}deleteUser`,{
  //    id: id
  //  });
  // }

}
