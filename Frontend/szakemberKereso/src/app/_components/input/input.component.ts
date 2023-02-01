import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent {
  @Input() type!:string;
  @Input() name!: string;
  @Input() control!:FormControl;
  @Input() matcher!:ErrorStateMatcher;
  @Input() placeholder!:string;
}
