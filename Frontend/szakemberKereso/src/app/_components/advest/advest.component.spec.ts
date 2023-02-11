import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvestComponent } from './advest.component';

describe('AdvestComponent', () => {
  let component: AdvestComponent;
  let fixture: ComponentFixture<AdvestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
