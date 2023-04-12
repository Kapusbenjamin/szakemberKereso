import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingWorkerDialogComponent } from './rating-worker-dialog.component';

describe('RatingWorkerDialogComponent', () => {
  let component: RatingWorkerDialogComponent;
  let fixture: ComponentFixture<RatingWorkerDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatingWorkerDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RatingWorkerDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
