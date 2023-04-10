import { User } from "./User";

export interface Job{
  id?: number,
  description: string,
  total?: number,
  status?: number,
  customerId: number,
  workerId: number,
  customer?: User,
  worker?: User
  customerAccepted?: number,
  workerAccepted?: number,
  updatedAt?: number,
  deleted?: number,
}
