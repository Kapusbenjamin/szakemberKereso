import { Component, Input, OnInit } from '@angular/core';
import { Rating } from 'src/app/_model/Rating';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  @Input() rating!: Rating 

  constructor() { }

  ngOnInit(): void {
    
  }

}
