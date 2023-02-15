import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Field } from 'src/app/_model/Field';
import { Tag } from 'src/app/_model/Tag';
import { User } from 'src/app/_model/User';
import { JobTagsService } from 'src/app/_services/job-tags.service';
import { UsersService } from 'src/app/_services/users.service';
import { DropdownValidator } from 'src/app/_validators/dropdown-validators';
import { PasswordValidators } from 'src/app/_validators/password-validators';

@Component({
  selector: 'app-regist-form',
  templateUrl: './regist-form.component.html',
  styleUrls: ['./regist-form.component.css']
})
export class RegistFormComponent implements OnInit {

  counties: Tag[] = [];
  cities: Tag[] = [];
  companyCities: Tag[] = [];
  streetsNames: Tag[] = [];
  professions: Tag[] = [];
  jobTag: string = "";

  professional: boolean = false;
  hasCompany: boolean = false;
  county: any = null;
  companyCounty: any = null;

  professionControl = new FormControl('')
  jobTagId = new FormControl(-1);

  registForm = new FormGroup({
    lastName:new FormControl('',[Validators.required]),
    firstName: new FormControl('',[Validators.required]),
    email:new FormControl('',[Validators.required]),
    phone:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    passwordConfirm: new FormControl('',[Validators.required]),
  });

  fields:Field[] = [
    {control: this.registForm.controls['lastName'], name:'Vezetéknév', type:"text"},
    {control: this.registForm.controls['firstName'], name:'Keresztnév', type:"text"},
    {control: this.registForm.controls['email'], name:'Email', type:"email"},
    {control: this.registForm.controls['phone'], name:'Telefon szám', type:"text"},
    {control: this.registForm.controls['password'], name:'Jelszó', type:"password"},
    {control: this.registForm.controls['passwordConfirm'], name:'Jelszó megerősítése', type:"password"},
  ];


  constructor(private jobTagsService: JobTagsService, private userService: UsersService) { }

  ngOnInit(): void {
    this.registForm.addValidators([PasswordValidators.same('password','passwordConfirm')])
    this.loadProfessions();
    this.professionControl.valueChanges.subscribe((value)=>{
      this.jobTag = value!;
      let jobTagId = this.getIdFromDropDown(this.jobTag,this.professions)
      if(jobTagId != -1){
        this.jobTagId.setValue(jobTagId);
      }
    })
  }

  professionalSlide(){
    if(this.professional == false){
      this.professional = true;
      this.hasCompany = false;
      this.professionControl.addValidators([Validators.required,DropdownValidator(this.professions)]);
    }else{
      this.professional = false;
      this.professionControl.removeValidators([Validators.required,DropdownValidator(this.professions)]);
    }
  }

  companySlide(){
    if(this.hasCompany == false){
      this.hasCompany = true;
    }
    else{
      this.hasCompany = false;
    }
  }

  regist(){
    this.registForm.markAllAsTouched();
    if(this.registForm.valid){;
    let formValue = this.registForm.value!;
    delete formValue.passwordConfirm;
      if(this.professional){
        this.userService.createUserWorker(formValue).subscribe();
      }else{
        this.userService.createUser(formValue).subscribe();
      }
    }else{
        alert("invalidForm");
        window.scroll({
          top: 0,
          behavior: 'smooth'
        });
      console.log("Not Valid");
    }
  }

  loadProfessions(){
    this.jobTagsService.getAllJobTags().subscribe((response)=>{
      response.forEach((jobTag:Tag) => {
        this.professions.push(jobTag);
      });
    })
  }

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
