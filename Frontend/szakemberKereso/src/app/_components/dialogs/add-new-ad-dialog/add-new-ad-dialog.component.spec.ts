import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewAdDialogComponent } from './add-new-ad-dialog.component';

describe('AddNewAdDialogComponent', () => {
  let component: AddNewAdDialogComponent;
  let fixture: ComponentFixture<AddNewAdDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewAdDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewAdDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
