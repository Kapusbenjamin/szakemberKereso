import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from 'src/app/_model/UserData';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  userData: UserData;


  constructor(private userService: UsersService, private router: Router) { 
    this.userData = this.userService.userData;
  }

  ngOnInit(): void {
    document.querySelector('[data-toggle="collapse"]')!.addEventListener('click', function (this: HTMLElement) {
      const target = this.getAttribute('data-target');
      const element = document.querySelector(target!);
      element!.classList.toggle('collapse');
    });
  }

  logOut(){
    this.userService.logoutUser().subscribe((res)=>{
      if(res){
        this.userService.clearUserData();
        this.router.navigate(['/login']);
      }
    });
  }

}
