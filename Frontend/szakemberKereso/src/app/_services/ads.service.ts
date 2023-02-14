import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ad } from '../_model/Ad';

@Injectable({
  providedIn: 'root'
})
export class AdsService {

  apiUrl: string = 'http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Ads/';

  constructor(private http: HttpClient) { }

  getAllAcceptedAds(){
    return this.http.get(`${this.apiUrl}getAllAcceptedAds`);
  }

  filteringAds(filter:Object):Observable<Ad[]>{
    return this.http.post<Ad[]>(`${this.apiUrl}filteringAds`,filter);
  }

  getAllNonAcceptedAds():Observable<any>{
    return this.http.get<any>(`${this.apiUrl}getAllNonAcceptedAds`);
  }
}
