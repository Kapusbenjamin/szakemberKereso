import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/_model/Address';
import { Company } from 'src/app/_model/Company';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { AddressesService } from 'src/app/_services/addresses.service';
import { CompaniesService } from 'src/app/_services/companies.service';
import { CountiesService } from 'src/app/_services/counties.service';
import { UsersJobsService } from 'src/app/_services/users-jobs.service';
import { UsersService } from 'src/app/_services/users.service';


@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(private userService: UsersService,
     private userJobService: UsersJobsService,
     private addressService: AddressesService,
     private countiesService: CountiesService,
     private companyService: CompaniesService
     ) {
      this.userData =  this.userService.userData
     }

  user!: User;
  userData!: UserData;

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.userService.getUserById(this.userService.userData.userId).subscribe((user:any)=>{
      this.user = user;
    });
  }

  userType(){
    return this.user.accessType == 0 ? "Általános felhasználó" : this.user.accessType == 2 ? "Admin" : "Szakember"
  }

}
