import { Component, Input, OnInit } from '@angular/core';
import { Chat } from 'src/app/_model/Chat';

@Component({
  selector: 'app-user-chat',
  templateUrl: './user-chat.component.html',
  styleUrls: ['./user-chat.component.css']
})
export class UserChatComponent implements OnInit {
  @Input() chats!:Chat[];
  @Input() activeChat!:any;

  constructor() { }

  ngOnInit(): void {

  }

  chatSelect(chat:Chat){
    this.activeChat.next(chat);
    console.log(this.activeChat);
  }

}
