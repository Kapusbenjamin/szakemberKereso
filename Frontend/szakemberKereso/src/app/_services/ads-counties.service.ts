import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tag } from '../_model/Tag';

@Injectable({
  providedIn: 'root'
})
export class AdsCountiesService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/AdsCounties/";

  constructor(private http: HttpClient) { }

  getAllCountiesByAd(id: number):Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllCountiesByAd/${id}`);
  }

  addNewCountyToAd(adId: number, countyId:number):Observable<any>{
    return this.http.post(`${this.apiUrl}addNewCountyToAd`,{
      adId: adId,
      countyId: countyId
    });
  }

  //Nope
  // deleteCountyFromAd(adId: number, countyId:number){
  //   return this.http.delete(`${this.apiUrl}deleteCountyFromAd`)
  // }

}
