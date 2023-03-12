import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';
import { Message } from '../_model/Message';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http:HttpClient, private userService: UsersService) { }

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Messages/";

  getAllMessages(){
    return this.http.post(`${this.apiUrl}getAllMessages`,this.userService.userData.userId).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  getAllMessagesBetweenUsers(sender:number, reciever:number){
    return this.http.post(`${this.apiUrl}getAllMessagesBetweenUsers`,{
      senderId: sender,
      receiverId: reciever,
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

  createMessage(message: Message){
    return this.http.post(`${this.apiUrl}createMessage`,message).pipe(
      map((response: any)=> {
        return response.result;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  checkMessage(chatId: number, receiverId: number){
    return this.http.post(`${this.apiUrl}checkMessage`,{
      chatId,
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
