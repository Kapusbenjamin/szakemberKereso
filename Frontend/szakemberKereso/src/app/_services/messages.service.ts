import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http:HttpClient, private userService: UsersService) { }

  apiUrl:string = "http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Messages/";

  getAllMessages(){
    return this.http.post(`${this.apiUrl}getAllMessages`,this.userService.userData.userId);
  }

  getAllMessagesBetweenUsers(sender:number, reciever:number){
    return this.http.post(`${this.apiUrl}getAllMessagesBetweenUsers`,{
      senderId: sender,
      receiverId: reciever,
      currentUserId: this.userService.userData.userId
  });
  }

  createMessage(){
    return this.http.post(`${this.apiUrl}createMessage`,{
      "chatId": 3,
      "senderId": 2,
      "receiverId": 3,
      "message": "Uzi",
      "currentUserId": 4
  });
  }

  checkMessage(){
    return this.http.post(`${this.apiUrl}checkMessage`,{
      "chatId": 4,
      "receiverId": 2,
      "currentUserId": 4
  });
  }

}
