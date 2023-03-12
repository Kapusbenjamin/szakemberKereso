import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Tag } from '../_model/Tag';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class AdsCountiesService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/AdsCounties/";
  currentUserId: number

  constructor(private http: HttpClient, private userService: UsersService) { 
    this.currentUserId = this.userService.userData.userId
  }

  getAllCountiesByAd(id: number):Observable<Tag[]>{
    return this.http.get<Tag[]>(`${this.apiUrl}getAllCountiesByAd/${id}`).pipe(
      map((response: any) => {
        if (response.message == "Sikeresen lekérte a hirdetéshez tartozó megyéket!") {
          return response.result;
        }
      }),
      catchError((error: any) => {
        return throwError(error);
      })
    );
  }

  addNewCountyToAd(adId: number, countyId:number):Observable<any>{
    return this.http.post(`${this.apiUrl}addNewCountyToAd`,{
      adId,
      countyId,
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

  //Nope
  // deleteCountyFromAd(adId: number, countyId:number){
  //   return this.http.delete(`${this.apiUrl}deleteCountyFromAd`)
  // }

}
