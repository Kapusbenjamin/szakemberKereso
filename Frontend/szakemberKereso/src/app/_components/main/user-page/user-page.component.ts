import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/_model/Address';
import { Company } from 'src/app/_model/Company';
import { User } from 'src/app/_model/User';
import { AddressesService } from 'src/app/_services/addresses.service';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(private userService: UsersService) { }

  user!: User;

  ngOnInit(): void {
    this.userService.getUserById(this.userService.userData.userId).subscribe((user)=>{
      this.user = user
      console.log(this.user);
    })

  }

}
