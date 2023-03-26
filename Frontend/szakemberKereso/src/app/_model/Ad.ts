import { Tag } from "./Tag";
import { User } from "./User";

export interface Ad{
  id: number,
  userId: number,
  user: User,
  jobTagId: number,
  description: string,
  updatedAt: number,
  status: number,
  deleted: number,
  countyId: number | null,
  favorite: boolean,
  jobTag: Tag,
  counties: Tag[],
  userRatings: any
}
