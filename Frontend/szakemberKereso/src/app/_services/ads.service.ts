import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
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
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateAd(id: number, description: string){
    return this.http.post(`${this.apiUrl}updateAd`,{
      id,
      description,
      currentUserId: this.currentUserId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  
  deleteAd(id: number){
    return this.http.post(`${this.apiUrl}deleteAd`,{
      id,
      currentUserId: this.currentUserId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

getAllAcceptedAds(): Observable<any> {
  return this.http.get(`${this.apiUrl}getAllAcceptedAds`).pipe(
    map((response: any) => {
      if (response.message == "Sikeresen lekérte az elfogadott hirdetéseket!") {
        return response.result;
      }
    }),
    catchError((error: any) => {
      return throwError(error);
    })
  );
}

  filteringAds(filter:Object):Observable<Ad[]>{
    return this.http.post<Ad[]>(`${this.apiUrl}filteringAds`,filter).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllNonAcceptedAds():Observable<any>{
    return this.http.post<any>(`${this.apiUrl}getAllNonAcceptedAds`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  acceptAd(id: number){
    return this.http.post(`${this.apiUrl}acceptAd`,{
      id,
      currentUserId: this.currentUserId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  
  getAllAds(){
    return this.http.post(`${this.apiUrl}getAllAds`,this.currentUserId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  
  getAdById(id: number): Observable<Ad>{
    return this.http.get<Ad>(`${this.apiUrl}getAdsById/${id}`).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  
  getAllAdsByUserId(userId: number){
    return this.http.post(`${this.apiUrl}getAllAdsByUserId`,{
      userId,
      currentUserId: this.currentUserId
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
