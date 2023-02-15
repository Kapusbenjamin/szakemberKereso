import { ChangeDetectorRef, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Field } from 'src/app/_model/Field';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.css']
})
export class CompanyFormComponent implements OnInit, OnDestroy {

  @Input() formGroup!: FormGroup;

  constructor(private cdr: ChangeDetectorRef) { }

  company = new FormGroup({
    name: new FormControl(''),
    taxNumber: new FormControl(''),
  });


  fields:Field[] = [
    {control: this.company.controls['name'], name:'Cég név', type:"text"},
    {control: this.company.controls['taxNumber'], name:'Adószám', type:"text"},
  ];

  ngOnInit(): void {
    setTimeout(() => {
      this.formGroup.addControl('company',this.company);
      this.cdr.detectChanges();
    }, 10);
  }

  ngOnDestroy(): void {
    setTimeout(() => {
      this.formGroup.removeControl('company');
      this.cdr.detectChanges();
    }, 10);
  }
}
