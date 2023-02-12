import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../_model/City';
import { Tag } from '../_model/Tag';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiUrl: string = 'http://localhost:3000/';
  cityApiUrl: string = ''
  streetApiUrl: string = '';

  constructor(private http:HttpClient) { }

  getAllCounties(): Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}counties`);
  }

  getAllCities(): Observable<City[]>{
    return this.http.get<City[]>(`${this.apiUrl}cities`);
  }

  getAllStreetsByCity(city: string){
    return this.http.get(``);
  }

}
