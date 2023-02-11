import { AbstractControl } from "@angular/forms";

export class PasswordValidators {
  static same(password: string, passwordConfirm: string){
    return (form: AbstractControl) =>{
      const pwd = form.value[password];
      const pwdc = form.value[passwordConfirm];
      if(pwd == pwdc){
        return null;
      }
      return { notSame: true};
    }
  }
}
