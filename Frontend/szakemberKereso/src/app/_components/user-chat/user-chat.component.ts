import { Component, Input, OnInit } from '@angular/core';
import { Chat } from 'src/app/_model/Chat';
import { MessagesService } from 'src/app/_services/messages.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-user-chat',
  templateUrl: './user-chat.component.html',
  styleUrls: ['./user-chat.component.css']
})
export class UserChatComponent implements OnInit {
  @Input() chats!:Chat[];
  @Input() activeChat!:any;
  userId!:number;

  constructor(private messagesService: MessagesService, private UserService: UsersService) { }

  ngOnInit(): void {
    this.userId = this.UserService.userData.userId;
  }

  chatSelect(chat:Chat){
    this.activeChat.next(chat);
    this.messagesService.checkMessage(chat.id,this.userId).subscribe((res)=>{
    });
  }

}
