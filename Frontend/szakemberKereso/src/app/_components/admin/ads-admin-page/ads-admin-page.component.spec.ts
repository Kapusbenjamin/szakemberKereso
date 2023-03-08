import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdsAdminPageComponent } from './ads-admin-page.component';

describe('AdsAdminPageComponent', () => {
  let component: AdsAdminPageComponent;
  let fixture: ComponentFixture<AdsAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdsAdminPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdsAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
