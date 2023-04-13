import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserAddressDialogComponent } from './edit-user-address-dialog.component';

describe('EditUserAddressDialogComponent', () => {
  let component: EditUserAddressDialogComponent;
  let fixture: ComponentFixture<EditUserAddressDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUserAddressDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserAddressDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
