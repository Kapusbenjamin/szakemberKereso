import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Chat } from 'src/app/_model/Chat';
import { Message } from 'src/app/_model/Message';
import { User } from 'src/app/_model/User';
import { ChatService } from 'src/app/_services/chat.service';
import { MessagesService } from 'src/app/_services/messages.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-messages-page',
  templateUrl: './messages-page.component.html',
  styleUrls: ['./messages-page.component.css']
})
export class MessagesPageComponent implements OnInit {

  constructor(private userService: UsersService, private chatService: ChatService, private messageService: MessagesService) { }

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
          this.getUnreadMessagesNumber(chat);
        })
      }else{
        this.userService.getUserById(chat.receiverId).subscribe((user: User)=>{
          chat.name = (user.firstName + " " + user.lastName);
          this.getUnreadMessagesNumber(chat);
        })
      }
    });
  }

  getUnreadMessagesNumber(chat: Chat){
    this.messageService.getAllMessagesBetweenUsers(chat.senderId,chat.receiverId).subscribe((messages: any)=>{
      let unreadNumber = 0;
      messages.forEach((message: Message)=>{
        if(message.checked == 0){
          unreadNumber++;
        }
        chat.unreadNumber = unreadNumber;
      })
      console.log(chat.name! + chat.unreadNumber);
      this.chats.push(chat);
    })
  }



}
