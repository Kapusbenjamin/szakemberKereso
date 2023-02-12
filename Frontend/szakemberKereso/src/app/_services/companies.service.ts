import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Company } from '../_model/Company';

@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Companies/";

  constructor(private http: HttpClient) { }

  createCompany(company: Company):Observable<any>{
    return this.http.post(`${this.apiUrl}createCompany`,{
      name: company.name,
      taxNumber: company.taxNumber,
      address: company.address
    })
  }

  getCompanyById(id: number):Observable<Company>{
    return this.http.post<Company>(`${this.apiUrl}getCompanyById`,{
      id: id
    })
  }

  updateCompanyById(id: number,name: string, taxNumber: string):Observable<any>{
    return this.http.post(`${this.apiUrl}updateCompanyById`,{
      id: id,
      name: name,
      taxNumber: taxNumber
    })
  }

  // Nope
  // deleteCompanyById(id: number):Observable<any>{
  //   return this.http.delete(`${this.apiUrl}deleteCompanyById`);
  // }

}
