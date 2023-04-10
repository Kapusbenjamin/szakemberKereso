import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Tag } from 'src/app/_model/Tag';
import { UserData } from 'src/app/_model/UserData';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service';
import { AdsService } from 'src/app/_services/ads.service';
import { CountiesService } from 'src/app/_services/counties.service';
import { UsersJobsService } from 'src/app/_services/users-jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-add-new-ad-dialog',
  templateUrl: './add-new-ad-dialog.component.html',
  styleUrls: ['./add-new-ad-dialog.component.css']
})
export class AddNewAdDialogComponent implements OnInit {

  jobTags: Tag[] = [];
  formG: FormGroup;
  userId: number;
  userData: UserData
  countiesTags: Tag[] = [];

  constructor(private userService: UsersService,
    private adService: AdsService,private fb: FormBuilder,
    private userJobService: UsersJobsService,
    private adsCounties: AdsCountiesService,
    public dialogRef: MatDialogRef<AddNewAdDialogComponent>,
    private countiesService: CountiesService) {
      this.formG = new FormGroup({
        jobTagId: new FormControl(-1),
        counties: this.fb.array([this.createCounty()]),
        description: new FormControl('')
      })
      this.userData = this.userService.userData
      this.userId = this.userData.userId
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
      this.adService.createAd(this.userId,jobTagId,description).subscribe((res)=>{
        let adId = res as number;
        this.counties.value.forEach((county: number) => {
          this.addcountyToAd(adId, county);
        });
        this.close();
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

    close(){
      this.dialogRef.close();
    }

    

}
