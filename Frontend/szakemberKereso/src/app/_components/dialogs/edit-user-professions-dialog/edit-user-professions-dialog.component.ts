import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { UserData } from 'src/app/_model/UserData';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersJobsService } from 'src/app/_services/users-jobs.service';
import { UsersService } from 'src/app/_services/users.service';

@Component({
  selector: 'app-edit-user-professions-dialog',
  templateUrl: './edit-user-professions-dialog.component.html',
  styleUrls: ['./edit-user-professions-dialog.component.css']
})
export class EditUserProfessionsDialogComponent implements OnInit {

  formG = new FormGroup({
    professions: new FormArray([])
  })

  professionTags: Tag[] = []
  originalProf: Tag[] = [];
  userData: UserData;

  constructor(private fb: FormBuilder, private jobTagService: JobTagsService,
    private userJobsService: UsersJobsService, private userService: UsersService,
    public dialogRef: MatDialogRef<EditUserProfessionsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {user: User}) {
      this.userData = this.userService.userData
     }

  ngOnInit(): void {
    this.setValues();
    this.getJobTags();
    this.professionTags.forEach(profession => {
      const control = this.fb.control(profession);
      (this.formG.get('professions') as FormArray).push(control);
    });
  }

  updateUserProfessions(){
    let updated: number[] = this.formG.controls['professions'].value;
    this.originalProf.forEach((profession: Tag)=>{
      if(!updated.includes(profession.id)){
        this.userJobsService.deleteUserJob(this.userData.userId,profession.id).subscribe()
      }
    })
    updated.forEach((tagId: number, index: number)=>{
      console.log(this.userData.userId + " " + tagId + " " + this.userData.userId);
      this.userJobsService.addNewJobToUser(this.userData.userId,tagId,this.userData.userId).subscribe(res=>{
        if(index+1 == updated.length){
          window.location.reload();
        }
      })
    })
  }

  setValues(){
    this.originalProf = this.data.user.jobTags!;
    const counties = this.formG.get('professions') as FormArray;
    this.data.user.jobTags!.forEach((county: Tag) => {
      counties.push(this.fb.control(county.id));
    });
  }

  getJobTags(){
    this.jobTagService.getAllJobTags().subscribe((jobTags:Tag[])=>{
      this.professionTags = jobTags
    })
  }

  addProfession() {
    const control = this.fb.control('');
    (this.formG.get('professions') as FormArray).push(control);
  }

  removeProfession(index: number) {
    (this.formG.get('professions') as FormArray).removeAt(index);
  }

  close(){
    this.dialogRef.close();
  }

}
