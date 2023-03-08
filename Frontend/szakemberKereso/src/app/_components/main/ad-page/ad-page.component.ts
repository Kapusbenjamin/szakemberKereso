import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { AdsService } from 'src/app/_services/ads.service';
import { FormBuilder, FormControl } from '@angular/forms';
import { CountiesService } from 'src/app/_services/counties.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { Tag } from 'src/app/_model/Tag';
import { UserData } from 'src/app/_model/UserData';
import { UsersService } from 'src/app/_services/users.service';
import { MatDialog } from '@angular/material/dialog';
import { FavoriteService } from 'src/app/_services/favorite.service';


@Component({
  selector: 'app-ad-page',
  templateUrl: './ad-page.component.html',
  styleUrls: ['./ad-page.component.css']
})
export class AdPageComponent implements OnInit {

  counties: Tag[] = [];
  jobTags: Tag[] = [];
  userData!: UserData;
  modalOpen:boolean = false;
  inner: boolean = false;
  favorites: any[] = [];

  ads: Ad[] = [];

  searchForm = this.fb.group({
    county: new FormControl(''),
    jobTag: new FormControl('')
  })

  constructor(private adService: AdsService, private fb: FormBuilder,
    private countiesService: CountiesService, private jobTagsService: JobTagsService,
    private userService: UsersService, private favoriteService: FavoriteService
    ) { 
      this.userData = this.userService.userData;
    }

  ngOnInit(): void {
    this.getFavorites();
    this.getAllCounties();
    this.getAllJobTags();
  }
  
  getFavorites(){
    this.favoriteService.getAllfavoritesByUserId(this.userData.userId).subscribe(res=>{
      this.favorites = res;
      this.getAllAcceptedAds();
    })
  }

  isFavorite(ad: Ad){
    this.favorites.forEach(favorite => {
      if(favorite.adId == ad.id){
        ad.favorite = true;
      }
    });
  }

  click(){
    this.modalOpen = !this.modalOpen;
  }

  getAllAcceptedAds(){
    this.adService.getAllAcceptedAds().subscribe((res)=>{
     this.ads = res
     this.ads.forEach(ad=>{
      this.isFavorite(ad);      
     })
    });
  }

  filteringAds(filter:Object){
    this.adService.filteringAds(filter).subscribe((response:Ad[])=>{
      this.ads = response
      this.ads.forEach(ad=>{
        this.isFavorite(ad);
      })
    });
  }

  getAllJobTags(){
    this.jobTagsService.getAllJobTags().subscribe((response)=>{
      response.forEach((jobTag:Tag) => {
        this.jobTags.push(jobTag);
      });
    })
  }

  getAllCounties(){
    this.countiesService.getAllCounties().subscribe((response:Tag[])=>{
      response.forEach((county:Tag)=>{
          this.counties.push(county)
      });
    });
  }

  search(){
    let filter:any = {};
    let county = this.searchForm.value.county;
    let jobTag = this.searchForm.value.jobTag;
    let countyId: number = this.getIdFromDropDown(county!,this.counties);
    let jobTagId: number = this.getIdFromDropDown(jobTag!,this.jobTags);
    if(countyId == -1 && jobTagId == -1){
      this.getAllAcceptedAds();
    }else{
    if(countyId != -1){
      filter['countyId'] = countyId;
    }
    if(jobTagId != -1){
      filter["jobTagId"] = jobTagId;
    }
      this.filteringAds(filter);
    }
  }

  close(){
    setTimeout(() => {
      if(this.inner){
        this.inner = false
      }else{        
        this.modalOpen = false;
      }
    }, 10);
  }

  stay(){
    this.inner = true;
  }

  // Máshol is felhasználható
  getIdFromDropDown(name: string, dropdown:Tag[]):number {
    let id = -1
    dropdown.forEach((element:Tag)=>{
      if(name == element.name){
        id = element.id;
      }
    });
    return id;
  }
}
