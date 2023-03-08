import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { UserData } from 'src/app/_model/UserData';
import { AdsService } from 'src/app/_services/ads.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-ads-admin-page',
  templateUrl: './ads-admin-page.component.html',
  styleUrls: ['./ads-admin-page.component.css']
})
export class AdsAdminPageComponent implements OnInit {

  userData: UserData;
  ads: Ad[] = [];

  constructor(private userService: UsersService, private adsService: AdsService
    ) { 
      this.userData = this.userService.userData;
    }

  ngOnInit(): void {
    this.getAllNonAcceptedAds();
  }

  getAllNonAcceptedAds(){
    this.adsService.getAllNonAcceptedAds().subscribe((ads :Ad[])=>{
      this.ads = ads;
    })
  }

  removeAdFromArray(ad: Ad){
    this.ads.splice(this.ads.indexOf(ad),1);
  }

  deleteAd(ad: Ad){
    this.adsService.deleteAd(ad.id).subscribe(()=>{
      this.removeAdFromArray(ad);
    });
  }

  acceptAd(ad: Ad){
    this.adsService.acceptAd(ad.id).subscribe(()=>{
      this.removeAdFromArray(ad);
    });
  }

}
