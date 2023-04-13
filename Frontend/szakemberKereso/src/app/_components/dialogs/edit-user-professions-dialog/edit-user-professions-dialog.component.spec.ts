import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserProfessionsDialogComponent } from './edit-user-professions-dialog.component';

describe('EditUserProfessionsDialogComponent', () => {
  let component: EditUserProfessionsDialogComponent;
  let fixture: ComponentFixture<EditUserProfessionsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUserProfessionsDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserProfessionsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
