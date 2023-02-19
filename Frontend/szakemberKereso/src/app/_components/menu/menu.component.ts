import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  userName: string = "";

  constructor(private userService: UsersService) { }

  ngOnInit(): void {
    document.querySelector('[data-toggle="collapse"]')!.addEventListener('click', function (this: HTMLElement) {
      const target = this.getAttribute('data-target');
      const element = document.querySelector(target!);
      element!.classList.toggle('collapse');
    });
    this.userName = this.userService.userData.name;
  }

  logOut(){
    this.userService.logoutUser(this.userService.userData.userId).subscribe();
  }

}
