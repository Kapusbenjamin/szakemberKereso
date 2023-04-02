import { Component, OnInit } from '@angular/core';
import { Rating } from 'src/app/_model/Rating';
import { RatingsService } from 'src/app/_services/ratings.service';

@Component({
  selector: 'app-ratings-page',
  templateUrl: './ratings-page.component.html',
  styleUrls: ['./ratings-page.component.css']
})
export class RatingsPageComponent implements OnInit {

  ratings: Rating[] = [];

  constructor(private ratingsService: RatingsService) { }

  ngOnInit(): void {
    this.ratingsService.getAllNotAcceptedRatings().subscribe((ratings : Rating[])=>{
      this.ratings = ratings
    })
  }

  acceptRating(rating: Rating){
    this.ratingsService.acceptRating(rating.id).subscribe(res=>{
      if(res){
        this.removeRatingFromArray(rating);
      }
    });
  }

  deleteRating(rating: Rating){
    this.ratingsService.deleteRatingById(rating.id).subscribe(res=>{
      if(res){
        this.removeRatingFromArray(rating);
      }
    });
  }

  removeRatingFromArray(rating: Rating){
    this.ratings.splice(this.ratings.indexOf(rating),1);
  }
}
