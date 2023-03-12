import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Image } from '../_model/Image';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Images/";

  constructor(private http:HttpClient, private userService: UsersService) { }

  getAllImages(){
    return this.http.post(`${this.apiUrl}getAllImages`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllNotAcceptedImages(){
    return this.http.post(`${this.apiUrl}getAllNotAcceptedImages`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllAcceptedImagesByUserId(id: number){
    return this.http.get(`${this.apiUrl}getAllAcceptedImagesByUserId/${id}`).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getImagesByUserId(userId: number){
    return this.http.post(`${this.apiUrl}getImagesByUserId`,{
      userId,
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

  acceptImage(id: number){
    return this.http.post(`${this.apiUrl}acceptImage`,{
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

  addImage(image: Image){
    return this.http.post(`${this.apiUrl}addImage`,{
      url: image.url,
      title: image.title,
      userId: image.userId,
      createdAt: image.createdAt,
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

  deleteImage(id: number){
    return this.http.post(`${this.apiUrl}deleteImage`,{
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

}
