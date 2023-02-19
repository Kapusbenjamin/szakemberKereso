import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ad } from '../_model/Ad';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class AdsService {

  apiUrl: string = 'http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Ads/';

  constructor(private http: HttpClient, private userService: UsersService) { }

  createAd(userId: number, jobTagId: number, description: string){
    return this.http.post(`${this.apiUrl}createAd`,{
      userId,
      jobTagId,
      description,
      currentUserId: this.userService.userData.userId
    })
  }

  getAllAcceptedAds():Observable<any>{
    return this.http.get(`${this.apiUrl}getAllAcceptedAds`,);
  }

  filteringAds(filter:Object):Observable<Ad[]>{
    return this.http.post<Ad[]>(`${this.apiUrl}filteringAds`,filter);
  }

  getAllNonAcceptedAds():Observable<any>{
    return this.http.post<any>(`${this.apiUrl}getAllNonAcceptedAds`,this.userService.userData.userId);
  }
}
