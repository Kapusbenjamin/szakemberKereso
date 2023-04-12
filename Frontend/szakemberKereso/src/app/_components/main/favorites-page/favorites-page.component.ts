import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { AdsService } from 'src/app/_services/ads.service';
import { FavoriteService } from 'src/app/_services/favorite.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-favorites-page',
  templateUrl: './favorites-page.component.html',
  styleUrls: ['./favorites-page.component.css']
})
export class FavoritesPageComponent implements OnInit {

  ads: Ad[] = [];
  userId: number;
  loaded: boolean = false;


  constructor(private userService: UsersService,  private adsService: AdsService,
     private favoriteService: FavoriteService) { 
    this.userId = this.userService.userData.userId;
  }

  ngOnInit(): void {
    this.getAllFavorites();
  }

  getAllFavorites(){
    this.loaded = false;
    this.favoriteService.getAllfavoritesByUserId(this.userId).subscribe((favorites: any[])=>{
      favorites.forEach((favorite)=>{
        this.adsService.getAdById(favorite.adId).subscribe((ad: Ad)=>{
          this.ads.push(ad);
            this.loaded = true;
        })
      })
    })
  }



}
