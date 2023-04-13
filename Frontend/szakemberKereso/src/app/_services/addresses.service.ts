import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Address } from '../_model/Address';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class AddressesService {

  apiUrl: string = 'http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Addresses/';

  constructor(private http: HttpClient, private userService: UsersService) { }

  getAddressById(id: number):Observable<Address>{
    return this.http.get<Address>(`${this.apiUrl}getAddressById/${id}`).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  createAddress(address: Address){
    return this.http.post(`${this.apiUrl}createAddress`,address).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateAddressById(id: number, address:Address){
    return this.http.post(`${this.apiUrl}updateAddressById`,{
      id,
      countyId: address.countyId,
      zipCode: address.zipCode,
      city: address.city,
      street: address.street,
      number: address.number, 
      staircase: address.staircase,
      floor: address.floor,
      door: address.door,
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

  deleteAddressById(id: number){
    let currentUserId = this.userService.userData.userId;
    return this.http.post(`${this.apiUrl}deleteAddressById`,{
      id,
      currentUserId
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
