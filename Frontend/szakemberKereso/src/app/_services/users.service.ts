import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../_model/User';
import { UserData } from '../_model/UserData';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/";
  userData: UserData = {userId: -1, name:"", access_type: -1};

  constructor(private http:HttpClient) { }

  getUserById(id: number):Observable<User>{
    return this.http.post<User>(`${this.apiUrl}getUserById`,{
      id,
      currentUserId: this.userData.userId
    });
  }

  getAllUsers():Observable<User[]>{
    return this.http.post<User[]>(`${this.apiUrl}getAllUsers`,this.userData.userId);
  }

  loginUser(email:string | null, phone:string | null, password:string): Observable<User>{
    let user = {
      email: email,
      phone: phone,
      password: password
    }
    return this.http.post<User>(`${this.apiUrl}loginUser`,user);
  }

  logoutUser(){
    return this.http.post(`${this.apiUrl}logoutUser`,this.userData.userId)
  }

  createUser(user:any):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}createUser`,user);
  }

  createUserWorker(user: any){
    return this.http.post(`${this.apiUrl}createUserWorker`,user);
  }

  updateUser(id: number, user: User){
    return this.http.post(`${this.apiUrl}updateUser`,{
      id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone,
      currentUserId: this.userData.userId
    })
  }

  deleteUser(id: number){
    return this.http.post(`${this.apiUrl}deleteUser`,{
      id,
      currentUserId: this.userData.userId
    });
  }

  changeAccess(id: number){
    return this.http.post(`${this.apiUrl}changeAccess`,{
      id,
      currentUserId: this.userData.userId
    });
  }

  changePassword(id:number, password: string){
    return this.http.post(`${this.apiUrl}changePassword`,{
      id,
      password,
      currentUserId: this.userData.userId
    });
  }

  getUserData(){
    let user = JSON.parse(localStorage.getItem('userData')!);
    if(user){
      this.userData = user;
    }
    return this.userData;
  }

  setUserData(user: UserData){
    this.userData = user;
    localStorage.setItem('userData', JSON.stringify(this.userData));
  }

  clearUserData(){
    this.userData = {userId: -1, name:"", access_type: -1};
    localStorage.removeItem('userData');
  }
}
