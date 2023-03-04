import { Component, Input, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service'
import { RatingsService } from 'src/app/_services/ratings.service';
import { Rating } from 'src/app/_model/Rating';

@Component({
  selector: 'app-advest',
  templateUrl: './advest.component.html',
  styleUrls: ['./advest.component.css']
})
export class AdvestComponent implements OnInit {

  @Input() ad!: Ad;
  name:string = "";
  jobTag: string = "";
  countiesNames: string[] = [];
  rating:number = 4.8;

  constructor(private userService: UsersService,
     private jobTagsService: JobTagsService,
     private adsCountiesService: AdsCountiesService,
     private ratingService: RatingsService
     ) { }

  ngOnInit(): void {
    this.getUsernameByAd();
    this.getRatingsByUserId();
    this.getTagsByAd();
    this.getCountiesByAd();
  }

  getRatingsByUserId(){
    this.ratingService.getAllRatingsByRatinged(this.ad.userId).subscribe((ratings)=>{
      let sum = 0;
      ratings.forEach((rating: Rating)=>{
        sum =+ rating.ratingsStars;
      })
      if(ratings.length == 0){
        this.rating = 0;
      }else{
        this.rating = (sum / ratings.length)
      }
    })
  }

  getTagsByAd(){
    this.jobTagsService.getJobTagById(this.ad.jobTagId).subscribe(value=>{
      this.jobTag = value.name;
    })
  }


  getCountiesByAd(){
    this.adsCountiesService.getAllCountiesByAd(this.ad.id).subscribe(values=>{
      values.forEach(value=>{
        this.countiesNames.push(value.name)
      })
    })
  }

  getUsernameByAd(){
    this.userService.getUserById(this.ad.userId).subscribe(user=>{
      this.name = user.firstName + " " + user. lastName;
    });
  }

}
