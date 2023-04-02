import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Rating } from 'src/app/_model/Rating';
import { UserData } from 'src/app/_model/UserData';
import { RatingsService } from 'src/app/_services/ratings.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-rating-page',
  templateUrl: './rating-page.component.html',
  styleUrls: ['./rating-page.component.css']
})
export class RatingPageComponent implements OnInit {

  ratingedUserId: number;
  userData: UserData
  ratings!: Rating[]

  constructor(private ratingsService: RatingsService, private userService: UsersService, private router: Router) { 
    this.ratingedUserId = this.userService.ratingedUserId;
    this.userData = this.userService.userData;
  }

  ngOnInit(): void {
    if(this.ratingedUserId > 0){
      this.getUserRatings();
    }else{
      this.router.navigate(['main'])
    }
  }

  getUserRatings(){
    this.ratingsService.getAllRatingsByRatinged(this.ratingedUserId).subscribe((res:Rating[])=>{     
      this.ratings = res;
    });
  }


}
