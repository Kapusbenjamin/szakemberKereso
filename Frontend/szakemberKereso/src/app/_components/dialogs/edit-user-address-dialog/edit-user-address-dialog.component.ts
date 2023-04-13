import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Address } from 'src/app/_model/Address';
import { AddressesService } from 'src/app/_services/addresses.service';

@Component({
  selector: 'app-edit-user-address-dialog',
  templateUrl: './edit-user-address-dialog.component.html',
  styleUrls: ['./edit-user-address-dialog.component.css']
})
export class EditUserAddressDialogComponent implements OnInit {

  addressForm = new FormGroup({
    currentAddress: new FormControl()
  })

  constructor(private addressService: AddressesService,
    public dialogRef: MatDialogRef<EditUserAddressDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {address: Address}) { }

  ngOnInit(): void {
    this.addressForm.controls['currentAddress'].setValue(this.data.address);
  }

  editAddress(){
    let address: Address = this.addressForm.get('address')!.value as Address;
    this.addressService.updateAddressById(this.data.address.id!,address).subscribe(res=>{
      window.location.reload();
    });
  }

  close(){
    this.dialogRef.close();
  }

}
