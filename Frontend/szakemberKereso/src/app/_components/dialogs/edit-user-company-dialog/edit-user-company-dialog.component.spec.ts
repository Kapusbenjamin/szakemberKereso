import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserCompanyDialogComponent } from './edit-user-company-dialog.component';

describe('EditUserCompanyDialogComponent', () => {
  let component: EditUserCompanyDialogComponent;
  let fixture: ComponentFixture<EditUserCompanyDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUserCompanyDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserCompanyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
