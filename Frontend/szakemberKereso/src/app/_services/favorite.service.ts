import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Ad } from '../_model/Ad';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Favorites/";
  currentUserId: number;

  constructor(private http:HttpClient, private userService: UsersService) { 
    this.currentUserId = this.userService.userData.userId;
  }

  getFavoriteById(id: number){
    return this.http.post(`${this.apiUrl}getFavoriteById`,{
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

  getAllfavoritesByUserId(userId: number): Observable<any[]>{
    return this.http.post<any[]>(`${this.apiUrl}getAllfavoritesByUserId`,{
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

  addFavorite(userId: number, adId: number){
    return this.http.post(`${this.apiUrl}addFavorite`,{
      userId,
      adId,
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

  deleteFavorite(id: number){
    return this.http.post(`${this.apiUrl}deleteFavorite`,{
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


}
