import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { AdsService } from 'src/app/_services/ads.service';
import { FormBuilder, FormControl } from '@angular/forms';
import { CountiesService } from 'src/app/_services/counties.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { JsonPipe } from '@angular/common';
import { Tag } from 'src/app/_model/Tag';

@Component({
  selector: 'app-ad-page',
  templateUrl: './ad-page.component.html',
  styleUrls: ['./ad-page.component.css']
})
export class AdPageComponent implements OnInit {

  counties: Tag[] = [];
  jobTags: Tag[] = [];

  ads!: Ad[];

  searchForm = this.fb.group({
    county: new FormControl(''),
    jobTag: new FormControl('')
  })

  constructor(private adService: AdsService, private fb: FormBuilder,
    private countiesService: CountiesService, private jobTagsService: JobTagsService) { }

  ngOnInit(): void {
    this.getAllCounties();
    this.getAllJobTags();
    this.filteringAds("",1);
    // this.getAllAcceptedAds();
  }

  getAllAcceptedAds(){
    this.adService.getAllAcceptedAds().subscribe((response)=>{
      console.log(response);
    });
  }

  filteringAds(countyId:number | "",jobTagId:number | ""){
    this.adService.filteringAds(countyId,jobTagId).subscribe((response:Ad[])=>{
      this.ads = response
      console.log(this.ads);
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
          this.counties.push({id: county.id, name: county.name})
      });
    });
  }

  search(){
    let county = this.searchForm.value.county;
    let jobTag = this.searchForm.value.jobTag;
    let countyId: number | "" = this.getIdFromDropDown(county!,this.counties);
    let jobTagId: number | "" = this.getIdFromDropDown(jobTag!,this.jobTags);
    if(countyId == -1 && jobTagId == -1){
      this.getAllAcceptedAds();
    }else{
      if(countyId == -1){
        countyId = "";
      }
      if(jobTagId == -1){
        jobTagId = ""
      }
      this.filteringAds(countyId,jobTagId);
    }

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
