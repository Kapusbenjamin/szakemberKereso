export interface Ad{
  id: number,
  userId: number,
  jobTagId: number,
  description: string,
  updatedAt: number,
  status: number,
  deleted: number,
  countyId: number | null,
  favorite: boolean;
}
