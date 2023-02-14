import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    document.querySelector('[data-toggle="collapse"]')!.addEventListener('click', function (this: HTMLElement) {
      const target = this.getAttribute('data-target');
      const element = document.querySelector(target!);
      element!.classList.toggle('collapse');
    });
  }



}
