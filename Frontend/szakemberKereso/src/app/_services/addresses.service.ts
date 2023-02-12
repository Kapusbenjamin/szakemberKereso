import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Address } from '../_model/Address';

@Injectable({
  providedIn: 'root'
})
export class AddressesService {

  apiUrl: string = 'http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Addresses/';

  constructor(private http: HttpClient) { }

  getAddressById(id: number):Observable<Address>{
    return this.http.get<Address>(`${this.apiUrl}getAddressById/${id}`);
  }

  createAddress(address: Address){
    return this.http.post(`${this.apiUrl}createAddress`,{
      countyId: address.countyId,
      zipCode: address.zipCode,
      city: address.city,
      street: address.street,
      number: address.number,
      staircase: address.staircase,
      floor: address.floor,
      door: address.door
    });
  }

  updateAddressById(address:Address){
    return this.http.post(`${this.apiUrl}updateAddressById`,{
      id: address.id,
      countyId: address.countyId,
      zipCode: address.zipCode,
      city: address.city,
      street: address.street,
      number: address.number,
      staircase: address.staircase,
      floor: address.floor,
      door: address.door
    });
  }

  deleteAddressById(id: number){
    return this.http.delete(`${this.apiUrl}deleteAddressById/${id}`);
  }

}
