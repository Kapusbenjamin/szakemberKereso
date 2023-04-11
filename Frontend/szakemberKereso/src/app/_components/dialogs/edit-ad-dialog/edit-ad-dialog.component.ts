import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { Ad } from 'src/app/_model/Ad';
import { Tag } from 'src/app/_model/Tag';
import { UserData } from 'src/app/_model/UserData';
import { AdsCountiesService } from 'src/app/_services/ads-counties.service';
import { AdsService } from 'src/app/_services/ads.service';
import { CountiesService } from 'src/app/_services/counties.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-edit-ad-dialog',
  templateUrl: './edit-ad-dialog.component.html',
  styleUrls: ['./edit-ad-dialog.component.css']
})
export class EditAdDialogComponent implements OnInit {

  userData: UserData
  formG = new FormGroup({
    counties: this.fb.array([]),
    description: new FormControl(''),
  })
  countiesTags: Tag[] = [];
  countiesChanged: boolean = false;

  constructor(private userService: UsersService,
    private adService: AdsService,
    private fb: FormBuilder,
    private countiesService: CountiesService,
    private adCountiesService: AdsCountiesService,
    public dialogRef: MatDialogRef<EditAdDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {ad: Ad}) {
      this.userData = this.userService.userData;
    }


  ngOnInit(): void {
    this.getCounties();
    this.setValues();
  }
  
  getCounties(){
    this.countiesService.getAllCounties().subscribe((counties:Tag[])=>{
      this.countiesTags = counties;
    })
  }
  
  setValues(){
    this.setDescription();
    this.setFormArray();
  }

  setDescription(){
    this.description?.setValue(this.data.ad.description);
  }

  setFormArray(): void {
    const counties = this.formG.get('counties') as FormArray;
    this.data.ad.counties.forEach((county: Tag) => {
      counties.push(this.fb.control(county.id));
    });
  }

  get counties() {
    return this.formG.get('counties') as FormArray;
  }

  get description(){
    return this.formG.get('description');
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

  editAd(){   
    if(this.description!.value){
      let description = this.description!.value!
      this.adService.updateAd(this.data.ad.id,description).subscribe();
      this.updateCounties();
    }
  }

  addcountyToAd(id:number, county: number){
    this.adCountiesService.addNewCountyToAd(id,county).subscribe()
  }

  updateCounties(){
    let original = this.data.ad.counties;
    let updated: number[] = this.counties.value;
    updated.forEach((countyId: number)=>{
      this.addcountyToAd(this.data.ad.id, countyId);
    })
    original.forEach((county: Tag)=>{
        if(!updated.includes(county.id)){
          this.adCountiesService.deleteCountyFromAd(this.data.ad.id,county.id).subscribe(res=>{
            window.location.reload();
          });
        }
    })
  }

  close():void{
    this.dialogRef.close();
  }

}
