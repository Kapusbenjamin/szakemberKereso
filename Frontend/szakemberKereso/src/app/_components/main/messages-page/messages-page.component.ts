import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Chat } from 'src/app/_model/Chat';
import { User } from 'src/app/_model/User';
import { ChatService } from 'src/app/_services/chat.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-messages-page',
  templateUrl: './messages-page.component.html',
  styleUrls: ['./messages-page.component.css']
})
export class MessagesPageComponent implements OnInit {

  constructor(private userService: UsersService, private chatService: ChatService) { }

  chats: Chat[] = [];
  userId!: number;
  activeChat = new BehaviorSubject<string>('');

  ngOnInit(): void {
    this.getAllChats();
  }

  getAllChats(){
    this.userId = this.userService.userData.userId;
    this.chatService.getAllChatsByUserId(this.userId).subscribe((chats: Chat[])=>{
    this.loadChats(chats)
    })
  }

  loadChats(chats: Chat[]){
    chats.forEach((chat:Chat)=>{
      if(chat.receiverId == this.userId){
        this.userService.getUserById(chat.senderId).subscribe((user: User)=>{
          chat.name = (user.firstName + " " + user.lastName);
          this.chats.push(chat);
        })
      }else{
        this.userService.getUserById(chat.receiverId).subscribe((user: User)=>{
          chat.name = (user.firstName + " " + user.lastName);
          this.chats.push(chat);
        })
      }
    });
  }



}
