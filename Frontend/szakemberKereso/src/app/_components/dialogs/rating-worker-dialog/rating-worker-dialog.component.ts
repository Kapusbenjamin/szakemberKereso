import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rating } from 'src/app/_model/Rating';
import { UserData } from 'src/app/_model/UserData';
import { RatingsService } from 'src/app/_services/ratings.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-rating-worker-dialog',
  templateUrl: './rating-worker-dialog.component.html',
  styleUrls: ['./rating-worker-dialog.component.css']
})
export class RatingWorkerDialogComponent implements OnInit {

  userData: UserData
  formG = new FormGroup({
    ratingsStars: new FormControl(0),
    description: new FormControl(''),
  })

  constructor(private userService: UsersService,
    private ratingsService: RatingsService,
    public dialogRef: MatDialogRef<RatingWorkerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { ratingedUserId: number, ratingedUserName: string}) { 
      this.userData = this.userService.userData
    }

  ngOnInit(): void {

  }

  ratingWorker(){
    let rating: Rating = {
      ratingedUserId: this.data.ratingedUserId,
      ratingerUserId: this.userData.userId,
      description: this.formG.controls['description'].value!,
      ratingsStars: this.formG.controls['ratingsStars'].value!
    }
    this.ratingsService.createRating(rating).subscribe(res=>{
      window.location.reload();
    })
  }

  close(){
    this.dialogRef.close();
  }



}
