import { Component, Input, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-advest',
  templateUrl: './advest.component.html',
  styleUrls: ['./advest.component.css']
})
export class AdvestComponent implements OnInit {

  @Input() ad!: Ad;
  name:string = "";

  constructor(private userService: UsersService) { }

  ngOnInit(): void {
    this.getNameByUser();
  }

  getNameByUser(){
    this.userService.getUserById(this.ad.id).subscribe(user=>{
      this.name = user.firstName + " " + user. lastName;
    });
  }

}
