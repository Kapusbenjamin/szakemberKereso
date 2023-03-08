import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { UsersService } from 'src/app/_services/users.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-users-admin-page',
  templateUrl: './users-admin-page.component.html',
  styleUrls: ['./users-admin-page.component.css']
})
export class UsersAdminPageComponent implements OnInit {
  displayedColumns: string[] = ['id','name','email','status','muvelet'];

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  userData: UserData;
  users!:User[];

  constructor(private userService: UsersService) { 
      this.userData = this.userService.userData;
    }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe((users:User[])=>{
      this.users = users;
      console.log(this.users);
    })
  }

  deleteUser(id:number){
    this.userService.deleteUser(id).subscribe();
  }

}