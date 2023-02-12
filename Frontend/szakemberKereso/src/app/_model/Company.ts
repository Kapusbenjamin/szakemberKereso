import { Address } from "./Address";

export interface Company{
  name: string,
  taxNumber: string,
  addressId?: number,
  address?: Address
}
