import { Component, Input, OnInit } from '@angular/core';
import { Rating } from 'src/app/_model/Rating';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  @Input() rating!: Rating 
  stars: string = "☆☆☆☆☆";
  
  constructor() { }
  
  ngOnInit(): void {
    this.setStars(this.rating.ratingsStars);
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

}
