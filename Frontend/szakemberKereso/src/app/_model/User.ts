import { Address } from "./Address";

export interface User{
  id:number,
  firstName: string,
  lastName: string,
  email: string,
  phone: string,
  password: string,
  address: Address
}