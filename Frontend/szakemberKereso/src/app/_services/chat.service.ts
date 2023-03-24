import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Chat } from '../_model/Chat';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient, private userService: UsersService) { }

  apiUrl: string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Chats/";

  getAllChatsByUserId(userId: number):Observable<Chat[]>{
    return this.http.post<Chat[]>(`${this.apiUrl}getAllChatsByUserId`,{
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

  createChat(senderId: number,receiverId: number){
    return this.http.post(`${this.apiUrl}createChat`,{
      senderId,
      receiverId,
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
