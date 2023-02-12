import { FormControl } from "@angular/forms";

export interface Field{
  control :FormControl,
  name: string,
  type: string,
  errorMessage?: string;

}
