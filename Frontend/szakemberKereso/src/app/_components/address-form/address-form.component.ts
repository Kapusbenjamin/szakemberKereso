import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Field } from 'src/app/_model/Field';
import { Tag } from 'src/app/_model/Tag';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent implements OnInit {

  @Input() addressFormGroup!: FormGroup;
  cities: Tag[] = [];
  counties: Tag[] = [];
  streetsNames: Tag[] = [];
  @Input() addressFields!: Field[];
  // @Input() city!: FormControl;
  // @Input() county!: FormControl;
  // @Input() streetName!: FormControl;

  constructor() { }

  ngOnInit(): void {
    this.addressFormGroup = new FormGroup({
      zipCode: new FormControl(''),
      county: new FormControl(''),
      city: new FormControl(''),
      streetName: new FormControl(''),
      number: new FormControl(''),
      staircase: new FormControl(''),
      floor: new FormControl(''),
      door: new FormControl('')
    })
  }
}
