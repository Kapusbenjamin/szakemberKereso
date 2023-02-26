export interface Chat{
  id: number,
  senderId: number,
  receiverId: number,
  name?: string,
  unreadNumber?: number
}
