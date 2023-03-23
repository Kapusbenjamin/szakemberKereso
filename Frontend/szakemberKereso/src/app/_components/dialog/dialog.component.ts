import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Tag } from 'src/app/_model/Tag';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service';
import { AdsService } from 'src/app/_services/ads.service';
import { CountiesService } from 'src/app/_services/counties.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersJobsService } from 'src/app/_services/users-jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  jobTags: Tag[] = [];
  formG: FormGroup;
  userId: number;
  countiesTags: Tag[] = [];
  @Input() function! : any;
  
  constructor(private userJobService: UsersJobsService,private fb: FormBuilder,
    private adsCounties: AdsCountiesService, private adsService: AdsService,
    private userService: UsersService, private  countiesService: CountiesService 
    ) {    
    this.formG = new FormGroup({
      jobTagId: new FormControl(-1),
      counties: this.fb.array([this.createCounty()]),
      description: new FormControl('')
    })
    this.userId = this.userService.userData.userId
   }

  ngOnInit(): void {
    this.getAllJobTags();
    this.getAllCounties();
  }

  getAllCounties(){
    this.countiesService.getAllCounties().subscribe((counties: Tag[])=>{
      this.countiesTags = counties; 
    })
  }
  
  getAllJobTags(){    
    this.userJobService.getAllJobsByUser(this.userId).subscribe((jobTags: any[])=>{
      this.jobTags = jobTags;
    })
  }

  createAd(){
    let jobTagId = this.formG.get('jobTagId')?.value
    let description = this.formG.get('description')?.value
    this.adsService.createAd(this.userId,jobTagId,description).subscribe((res)=>{
      let adId = res as number;
      console.log(adId);
      this.counties.value.forEach((county: number) => {
        this.addcountyToAd(adId, county);
      });
    })
  }

  addcountyToAd(id:number, county: number){
    this.adsCounties.addNewCountyToAd(id,county).subscribe()
  }

  get counties() {
    return this.formG.get('counties') as FormArray;
  }

  createCounty(): FormControl {
    return this.fb.control('');
  }

  addCounty(): void {
    this.counties.push(this.createCounty());
  }

  deleteCounty(index: number) {
    const counties = this.formG.get('counties') as FormArray;
    counties.removeAt(index);
  }

}
