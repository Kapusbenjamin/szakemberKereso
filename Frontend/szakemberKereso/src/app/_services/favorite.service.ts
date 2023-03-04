import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Favorites/";

  constructor(private http:HttpClient, private userService: UsersService) { }

  getFavoriteById(id: number){
    return this.http.post(`${this.apiUrl}getFavoriteById`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }

  getAllfavoritesByUserId(userId: number){
    return this.http.post(`${this.apiUrl}getAllfavoritesByUserId`,{
      userId,
      currentUserId: this.userService.userData.userId
    })
  }

  addFavorite(userId: number, adId: number){
    return this.http.post(`${this.apiUrl}addFavorite`,{
      userId,
      adId,
      currentUserId: this.userService.userData.userId
    })
  }

  deleteFavorite(id: number){
    return this.http.post(`${this.apiUrl}deleteFavorite`,{
      id,
      currentUserId: this.userService.userData.userId
    })
  }


}
