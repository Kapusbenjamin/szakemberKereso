import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent {
  @Input() type!:string;
  @Input() name!: string;
  @Input() control!:FormControl;
  @Input() placeholder!:string;
}
