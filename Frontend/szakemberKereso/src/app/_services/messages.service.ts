import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../_model/Message';
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

  createMessage(message: Message){
    return this.http.post(`${this.apiUrl}createMessage`,message);
  }

  checkMessage(chatId: number, receiverId: number){
    return this.http.post(`${this.apiUrl}checkMessage`,{
      chatId,
      receiverId,
      currentUserId: this.userService.userData.userId
  });
  }

}
