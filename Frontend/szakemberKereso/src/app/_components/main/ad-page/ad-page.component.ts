import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/_model/Ad';
import { AdsService } from 'src/app/_services/ads.service';
import { FormBuilder, FormControl } from '@angular/forms';
import { CountiesService } from 'src/app/_services/counties.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { Tag } from 'src/app/_model/Tag';
import { UserData } from 'src/app/_model/UserData';
import { UsersService } from 'src/app/_services/users.service';
import { DropdownValidator } from 'src/app/_validators/dropdown-validators';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogComponent } from '../../mat-dialog/mat-dialog.component';


@Component({
  selector: 'app-ad-page',
  templateUrl: './ad-page.component.html',
  styleUrls: ['./ad-page.component.css']
})
export class AdPageComponent implements OnInit {

  counties: Tag[] = [];
  jobTags: Tag[] = [];
  title:string = "Title";
  userData!: UserData;
  modalOpen:boolean = false;

  ads!: Ad[];

  searchForm = this.fb.group({
    county: new FormControl(''),
    jobTag: new FormControl('')
  })

  constructor(private adService: AdsService, private fb: FormBuilder,
    private countiesService: CountiesService, private jobTagsService: JobTagsService,
    private userService: UsersService, private dialog: MatDialog
    ) { }

  ngOnInit(): void {
    this.userData = this.userService.userData;
    this.getAllCounties();
    this.getAllJobTags();
    this.getAllAcceptedAds();
  }

  getAllAcceptedAds(){
    this.adService.getAllAcceptedAds().subscribe((res)=>{
     this.ads = res
    });
  }

  filteringAds(filter:Object){
    this.adService.filteringAds(filter).subscribe((response:Ad[])=>{
      this.ads = response
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

  openDialog() {
    const dialogRef = this.dialog.open(MatDialogComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed with result:', result);
    });
  }

}
