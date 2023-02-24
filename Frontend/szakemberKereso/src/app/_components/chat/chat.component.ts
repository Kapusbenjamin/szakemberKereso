import { Component, Input, OnInit } from '@angular/core';
import { Chat } from 'src/app/_model/Chat';
import { MessagesService } from 'src/app/_services/messages.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  @Input() chat!:any;
  messages:any[] = [];

  constructor(private userService: UsersService, private messagesService: MessagesService) { }

  ngOnInit(): void {
    this.chat.subscribe((chat:Chat)=>{
      console.log("change");
      this.getMessegesByChat(chat);
    })
  }

  getMessegesByChat(chat: Chat){
    this.messagesService.getAllMessagesBetweenUsers(chat.senderId,chat.receiverId).subscribe((messages: any)=>{
      this.messages = messages;
      messages.forEach((message:any)=>{
        console.log(message);
      })
    })
  }

}
