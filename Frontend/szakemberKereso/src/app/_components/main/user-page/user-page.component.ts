import { Component, OnInit } from '@angular/core';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { UsersJobsService } from 'src/app/_services/users-jobs.service';
import { UsersService } from 'src/app/_services/users.service';


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(private userService: UsersService, private userJobService: UsersJobsService) { }

  user!: User;
  userName!: string
  professions!: Tag[]

  ngOnInit(): void {
    this.userService.getUserById(this.userService.userData.userId).subscribe((user: User)=>{
      this.userName = this.userService.userData.name;
      this.user = user;
      console.log(user);
    });
    this.userJobService.getAllJobsByUser(this.userService.userData.userId).subscribe((tags: Tag[])=>{
      this.professions = tags;
    });
  }

  userType(){
    return this.user.accessType == 0 ? "Általános felhasználó" : this.user.accessType == 2 ? "Admin" : "Szakember"
  }



}
