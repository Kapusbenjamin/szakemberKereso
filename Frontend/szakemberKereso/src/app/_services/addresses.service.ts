import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
    return this.http.get<Address>(`${this.apiUrl}getAddressById/${id}`);
  }

  createAddress(address: Address){
    return this.http.post(`${this.apiUrl}createAddress`,address);
  }

  updateAddressById(address:Address){
    return this.http.post(`${this.apiUrl}updateAddressById`,address);
  }

  deleteAddressById(id: number){
    let currentUserId = this.userService.userData.userId;
    return this.http.post(`${this.apiUrl}deleteAddressById`,{
      id,
      currentUserId
    });
  }

}
