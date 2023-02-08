import { Address } from "./Address";
import { Company } from "./Company";

export interface User{
  id:number,
  firstName: string,
  lastName: string,
  email: string,
  phone: string,
  password: string,
  address: Address,
  company: Company
  accessType: number
}
