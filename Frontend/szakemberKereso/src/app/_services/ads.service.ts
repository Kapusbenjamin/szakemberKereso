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
  currentUserId: number;

  constructor(private http: HttpClient, private userService: UsersService) {
    this.currentUserId = this.userService.userData.userId
   }

  createAd(userId: number, jobTagId: number, description: string){
    return this.http.post(`${this.apiUrl}createAd`,{
      userId,
      jobTagId,
      description,
      currentUserId: this.currentUserId
    })
  }

  updateAd(id: number, description: string){
    return this.http.post(`${this.apiUrl}updateAd`,{
      id,
      description,
      currentUserId: this.currentUserId
    });
  }
  
  deleteAd(id: number){
    return this.http.post(`${this.apiUrl}deleteAd`,{
      id,
      currentUserId: this.currentUserId
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

  acceptAd(id: number){
    return this.http.post(`${this.apiUrl}acceptAd`,{
      id,
      currentUserId: this.currentUserId
    })
  }
  
  getAllAds(){
    return this.http.post(`${this.apiUrl}getAllAds`,this.currentUserId)
  }
  
  getAdById(id: number): Observable<Ad>{
    return this.http.get<Ad>(`${this.apiUrl}getAdsById/${id}`)
  }
  
  getAllAdsByUserId(userId: number){
    return this.http.post(`${this.apiUrl}getAllAdsByUserId`,{
      userId,
      currentUserId: this.currentUserId
    })
  }

}
