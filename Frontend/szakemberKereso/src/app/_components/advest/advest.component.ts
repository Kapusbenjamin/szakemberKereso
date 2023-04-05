import { Component, Input, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service'
import { RatingsService } from 'src/app/_services/ratings.service';
import { Rating } from 'src/app/_model/Rating';
import { AdsService } from 'src/app/_services/ads.service';
import { FavoriteService } from 'src/app/_services/favorite.service';
import { Router } from '@angular/router';
import { ChatService } from 'src/app/_services/chat.service';

@Component({
  selector: 'app-advest',
  templateUrl: './advest.component.html',
  styleUrls: ['./advest.component.css']
})
export class AdvestComponent implements OnInit {

  @Input() ad!: Ad;
  @Input() favorite!: boolean;
  name:string = "";
  jobTag: string = "";
  countiesNames: string[] = [];
  rating: number =  0;
  stars: string = "☆☆☆☆☆";
  currentUser:number;

  constructor(private userService: UsersService, private adsService: AdsService,
     private favoriteService: FavoriteService, private router: Router, private chatService: ChatService
     ) {
      this.currentUser = this.userService.userData.userId;
      }

  ngOnInit(): void {
    this.getRatingsByUserId();
  }

  getRatingsByUserId(){
    let sum: number = 0;
    this.ad.userRatings.forEach((element:any) => {
      sum = sum + element.ratingsStars;
    });
    if(this.ad.userRatings.length > 0){
      this.rating = sum / this.ad.userRatings.length
    }
    this.setStars(this.rating);
  }

  deleteAd(){
    this.adsService.deleteAd(this.ad.id).subscribe(()=>window.location.reload());
  }

  addAdToFavorite(){
    let adId = this.ad.id
    let userId = this.currentUser
    this.favoriteService.addFavorite(userId,adId).subscribe(res=>{
      this.favorite = true;
    });
  }

  removeAdFromFavorites(){
    let adId = this.ad.id
    this.favoriteService.deleteFavorite(adId).subscribe(res=>{
      this.favorite = false;
    });
  }

  goToMessage(){
    this.chatService.createChat(this.currentUser,this.ad.userId).subscribe((res)=>{
      this.chatService.chatName = this.ad.user.firstName + " " + this.ad.user.lastName;
      this.router.navigate(['main/messages']);
    })
  }

  setStars(star: number){
    if(star == 5){
      this.stars = "★★★★★";
    }else if(star >= 4){
      this.stars = "★★★★☆";
    }else if(star >= 3){
      this.stars = "★★★☆☆";
    }else if(star >= 2){
      this.stars = "★★☆☆☆";
    }else if(star >= 1){
      this.stars = "★☆☆☆☆";
    }
  }

  setRatingedUser(id: number){
    if(this.rating != 0){
      this.userService.setRatingedUser(id);
      this.router.navigate(['main/ratings']);
    }
  }

}
