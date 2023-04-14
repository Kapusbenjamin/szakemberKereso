import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { Rating } from '../_model/Rating';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class RatingsService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Ratings/";

  constructor(private http:HttpClient, private userService: UsersService) { }

  getRatingById(id: number): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getRatingById`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllRatings():Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatings`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllNotAcceptedRatings(): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllNotAcceptedRatings`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllRatingsByRatinger(ratingerUserId: number): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatingsByRatinger`,{
      ratingerUserId,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllRatingsByRatinged(ratingedUserId: number): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatingsByRatinged`,{
      ratingedUserId,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  updateRatingById(rating: Rating): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}updateRatingById`,{
      id: rating.id,
      description: rating.description,
      ratingsStars: rating.ratingsStars
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  acceptRating(id: number): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}acceptRating`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  deleteRatingById(id: number): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}deleteRatingById`,{
      id,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  createRating(rating: Rating): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}createRating`,{
      ratingedUserId: rating.ratingedUserId,
      ratingerUserId: rating.ratingerUserId,
      description: rating.description,
      ratingsStars: rating.ratingsStars,
      currentUserId: this.userService.userData.userId
    }).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  canWriteRating(ratingedUserId: number): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}canWriteRating`,{
      ratingedUserId, 
      currentUserId: this.userService.userData.userId
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
