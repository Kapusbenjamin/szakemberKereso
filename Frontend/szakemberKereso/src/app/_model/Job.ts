export interface Job{
  id?: number,
  description: string,
  total?: number,
  status?: number,
  customerId: number,
  workerId: number,
  customerAccepted?: number,
  workerAccepted?: number,
  updatedAt?: number,
  deleted?: number,
}
