import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { User } from '../_model/User';
import { UserData } from '../_model/UserData';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/";
  userData: UserData = {userId: -1, name:"", access_type: -1};
  ratingedUserId: number = -1;

  constructor(private http:HttpClient) { }

  getUserById(id: number):Observable<User>{
    return this.http.post<User>(`${this.apiUrl}getUserById`,{
      id,
      currentUserId: this.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllUsers():Observable<User[]>{
    return this.http.post<User[]>(`${this.apiUrl}getAllUsers`,this.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  loginUser(email:string | null, phone:string | null, password:string):Observable<any>{
    let user = {
      email,
      phone,
      password
    }
    return this.http.post<any>(`${this.apiUrl}loginUser`,user).pipe(
      catchError(error => {
        console.log(error.status);
        alert("Sikertelen Bejelentkezés");
        return throwError(error);
      })
    )
  }

  logoutUser(){
    return this.http.post(`${this.apiUrl}logoutUser`,this.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  createUser(user:any):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}createUser`,user).pipe(
      map((response: any)=> {
          return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  createUserWorker(user: any){
    return this.http.post(`${this.apiUrl}createUserWorker`,user).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateUser(id: number, user: User){
    return this.http.post(`${this.apiUrl}updateUser`,{
      id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone,
      currentUserId: this.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  deleteUser(id: number){
    return this.http.post(`${this.apiUrl}deleteUser`,{
      id,
      currentUserId: this.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  changeAccess(id: number){
    return this.http.post(`${this.apiUrl}changeAccess`,{
      id,
      currentUserId: this.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  changePassword(id:number, password: string,newPassword: string){
    return this.http.post(`${this.apiUrl}changePassword`,{
      id,
      password,
      newPassword,
      currentUserId: this.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        if(error.status == 401){
          alert('jelszó változtatás sikertelen ok: Nem megfeleő a régi jelszó');
        }else{
          alert('jelszó változtatás sikertelen');
        }
        return throwError(error);
      })
      );
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

  setRatingedUser(id: number){
    this.ratingedUserId = id;
  }

  forgotPassword(email: any){
    return this.http.get(`${this.apiUrl}forgotPassword?email=${email}`
    ).pipe(
      map((response: any)=> {
        return response;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

}
