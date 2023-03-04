import { Component, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Chat } from 'src/app/_model/Chat';
import { Message } from 'src/app/_model/Message';
import { MessagesService } from 'src/app/_services/messages.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-chat-form',
  templateUrl: './chat-form.component.html',
  styleUrls: ['./chat-form.component.css']
})
export class ChatFormComponent implements OnInit {

  @Input() chat!: any;
  @Input() messages:any = [];
  hasSelectedChat: boolean = false;
  anotherUserId!: number;
  userId!: number

  chatControl =  new FormControl<string>('');

  constructor(private userService: UsersService, private messageService: MessagesService) { }

  ngOnInit(): void {
    this.userId = this.userService.userData.userId
    this.chat.subscribe((chat:Chat)=>{
      if(chat.id > 0){
        this.hasSelectedChat = true;
        if(chat.senderId == this.userId){
          this.anotherUserId = chat.receiverId;
        }else{
          this.anotherUserId = chat.senderId;
        }
      }
    })
  }

  sendMessage(){
    let message:Message = {
      chatId: this.chat.value.id,
      senderId: this.userId,
      receiverId: this.anotherUserId,
      message: this.chatControl.value!,
      currentUserId: this.userId
    }
    this.messages.push(message);
    this.chatControl.setValue("");
    this.messageService.createMessage(message).subscribe((res)=>{
    });
  }
}
