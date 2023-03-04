import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
    });
  }

  getAllRatings():Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatings`,this.userService.userData.userId);
  }

  getAllNotAcceptedRatings(): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllNotAcceptedRatings`,this.userService.userData.userId)
  }

  getAllRatingsByRatinger(ratingerUserId: number): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatingsByRatinger`,{
      ratingerUserId,
      currentUserId: this.userService.userData.userId
    });
  }

  getAllRatingsByRatinged(ratingedUserId: number): Observable<Rating[]>{
    return this.http.post<Rating[]>(`${this.apiUrl}getAllRatingsByRatinged`,{
      ratingedUserId,
      currentUserId: this.userService.userData.userId
    })
  }

  updateRatingById(rating: Rating): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}updateRatingById`,{
      id: rating.id,
      description: rating.description,
      ratingsStars: rating.ratingsStars
    });
  }

  acceptRating(id: number): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}acceptRating`,{
      id,
      currentUserId: this.userService.userData.userId
    });
  }

  deleteRatingById(id: number): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}deleteRatingById`,{
      id,
      currentUserId: this.userService.userData.userId
    });
  }

  createRating(rating: Rating): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}createRating`,{
      ratingedUserId: rating.ratingedUserId,
      ratingerUserId: rating.ratingerUserId,
      description: rating.description,
      ratingsStars: rating.ratingsStars
    });
  }

}
