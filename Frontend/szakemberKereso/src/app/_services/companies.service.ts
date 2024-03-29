import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Company } from '../_model/Company';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Companies/";

  constructor(private http: HttpClient, private userService: UsersService) { }

  createCompany(company: Company):Observable<any>{
    if(company.address!.staircase == null){
      delete company.address!.staircase
      delete company.address!.floor
      delete company.address!.door
    }
    return this.http.post(`${this.apiUrl}createCompany`,{
      name: company.name,
      taxNumber: company.taxNumber,
      address: company.address,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getCompanyById(id: number):Observable<Company>{
    return this.http.get<Company>(`${this.apiUrl}getCompanyById/${id}`).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateCompanyById(id: number,name: string, taxNumber: string):Observable<any>{
    return this.http.post(`${this.apiUrl}updateCompanyById`,{
      id: id,
      name: name,
      taxNumber: taxNumber,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  deleteCompanyById(id: number):Observable<any>{
    return this.http.post(`${this.apiUrl}deleteCompanyById`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

}
