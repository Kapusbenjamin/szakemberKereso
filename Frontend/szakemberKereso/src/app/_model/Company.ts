import { Address } from "./Address";

export interface Company{
  id: number
  name: string,
  taxNumber: string,
  addressId?: number,
  address?: Address
}
