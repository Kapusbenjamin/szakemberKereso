import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UsersService } from './_services/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'szakemberKereso';

  constructor(private userService: UsersService){
    userService.getUserData();
  }

  ngOnInit(): void{

  }

}
