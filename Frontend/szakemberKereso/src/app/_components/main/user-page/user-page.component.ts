import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/_model/Address';
import { Company } from 'src/app/_model/Company';
import { AddressesService } from 'src/app/_services/addresses.service';
import { CompaniesService } from 'src/app/_services/companies.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  address: Address = {
    id: 42,
    countyId: 2,
    zipCode: 7759,
    city: "Lánycsók",
    street: "Hörpintő",
    number: "2",
    staircase: null,
    floor: null,
    door: null
  }

  company: Company = {
    name: "Teszt",
    taxNumber: "TestTax",
    address: this.address
  }

  constructor(private userService: UsersService, private addressService: AddressesService, private companyService: CompaniesService) { }

  ngOnInit(): void {
    // this.userService.getAllUsers().subscribe(res=>{
    //   console.log(res);
    // });

    // this.addressService.updateAddressById(this.address).subscribe(res=>{
    //   console.log(res);
    // });

    // this.companyService.updateCompanyById(5,"Teszt update","123123").subscribe((res:any)=>{
    //   console.log(res);
    // });

    // this.companyService.getCompanyById(1).subscribe(res=>{
    //   this.addressService.getAddressById(res.addressId!).subscribe(r => {
    //     res.address = r;
    //     delete res.addressId
    //     console.log(res);
    //   })
    // });

    // this.companyService.createCompany(this.company).subscribe(res=>{
    //   console.log(res);
    // })

  }

}
