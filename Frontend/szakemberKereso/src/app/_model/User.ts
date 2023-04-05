import { Address } from "./Address";
import { Company } from "./Company";
import { Tag } from "./Tag";

export interface User{
  id?:number,
  firstName: string,
  lastName: string,
  email: string,
  phone: string, 
  password?: string,
  address?: Address,
  jobTags?: Tag[],
  company?: Company,
  accessType?: number
}
