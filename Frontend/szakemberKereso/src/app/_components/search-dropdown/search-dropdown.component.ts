import { InvokeFunctionExpr } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl } from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-search-dropdown',
  templateUrl: './search-dropdown.component.html',
  styleUrls: ['./search-dropdown.component.css']
})
export class SearchDropdownComponent implements OnInit {
  @Input() name!: string;
  @Input() control!: FormControl;
  @Input() options!: string[];
  filteredOptions!: Observable<any[]>;

  ngOnInit() {
    this.filteredOptions = this.control.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

}
