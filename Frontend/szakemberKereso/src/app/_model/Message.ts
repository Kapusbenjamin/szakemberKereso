export interface Message{
  chatId: number,
  senderId: number,
  receiverId: number,
  message: string,
  currentUserId: number
  checked?: number,
  sendedAt?: 1675874151000,
}
