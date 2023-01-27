import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { City } from 'src/app/_model/City';

@Component({
  selector: 'app-search-dropdown',
  templateUrl: './search-dropdown.component.html',
  styleUrls: ['./search-dropdown.component.css']
})
export class SearchDropdownComponent implements OnInit {
  myControl = new FormControl<string | any>('');
  @Input() options!: City[];
  filteredOptions!: Observable<any[]>;

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const city = typeof value === 'string' ? value : value?.city;
        return city ? this._filter(city as string) : this.options.slice();
      }),
    );
  }

  displayFn(user: any): string {
    return user && user.city ? user.city : '';
  }

  private _filter(name: string): any[] {
    const filterValue = name.toLowerCase();

    console.log(this.options)

    return this.options.filter(option => option.city.toLowerCase().includes(filterValue));
  }
}
