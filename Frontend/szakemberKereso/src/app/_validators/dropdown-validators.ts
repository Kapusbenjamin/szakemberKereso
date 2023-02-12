import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";
import { Tag } from "../_model/Tag";

export function DropdownValidator(dropdown :Tag[]): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const name = control.value;
    let find: boolean = false
    dropdown.forEach(element => {
      if(element.name == name){
        find = true;
      }
    });
    return find ? null :{notContains: {value: control.value}};
  };
}
