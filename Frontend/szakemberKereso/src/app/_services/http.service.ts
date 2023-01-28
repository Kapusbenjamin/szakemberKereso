import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../_model/City';
import { County } from '../_model/County';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiUrl: string = 'http://localhost:3000/';
  cityApiUrl: string = ''
  streetApiUrl: string = '';

  constructor(private http:HttpClient) { }

  getAllCounties(): Observable<County[]>{
    return this.http.get<County[]>(`${this.apiUrl}counties`);
  }

  getAllCities(): Observable<City[]>{
    return this.http.get<City[]>(`${this.apiUrl}cities`);
  }

  getAllStreetsByCity(city: string){
    return this.http.get(``);
  }

}
