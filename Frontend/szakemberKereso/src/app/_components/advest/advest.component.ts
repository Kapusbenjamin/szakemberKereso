import { Component, Input, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service'

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

  constructor(private userService: UsersService, private jobTagsService: JobTagsService, private adsCountiesService: AdsCountiesService) { }

  ngOnInit(): void {
    this.getUsernameByAd();
    this.getTagsByAd();
    this.getCountiesByAd();
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
