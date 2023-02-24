import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/_model/Address';
import { Company } from 'src/app/_model/Company';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
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
     ) { }

  user!: User;
  userName!: string;
  professions!: Tag[];
  address!: Address;
  company!: Company;

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(){
    this.userService.getUserById(this.userService.userData.userId).subscribe((user:any)=>{
      this.userName = this.userService.userData.name;
      this.user = user;
      this.getAddress(user.addressId);
      if(user.accessType! > 0){
        this.getJobs(this.user.id!);
        if(user.companyId > 0){
          this.companyService.getCompanyById(user.companyId).subscribe((company: Company)=>{
            this.company = company;
            this.addressService.getAddressById(company.addressId!).subscribe((address: Address)=>{
              this.company.address = address;
            })
          })
        }
      }
    });
  }

  getAddress(id: number){
    this.addressService.getAddressById(id).subscribe((address)=>{
      this.address = address;
      this.getCountyName(address.countyId);
    })
  }

  getCountyName(id: number){
    this.countiesService.getAllCounties().subscribe((counties: Tag[])=>{
      counties.forEach((county: Tag)=>{
        if(county.id == id){
          this.address.county = county.name;
        }
      })
    })
  }

  getJobs(userId:number){
    this.userJobService.getAllJobsByUser(userId).subscribe((tags: Tag[])=>{
      this.professions = tags;
    });
  }

  userType(){
    return this.user.accessType == 0 ? "Általános felhasználó" : this.user.accessType == 2 ? "Admin" : "Szakember"
  }

}
