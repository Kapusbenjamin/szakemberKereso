import { User } from "./User";

export interface Rating{
  id: number,
  ratingedUserId: number,
  ratingerUserId: 2,
  description: string,
  ratingsStars: number,
  ratinged: User,
  ratinger: User,
  status: number,
  updatedAt: Date,
  deleted: number,
}
