import RoomArea from "@/domain/RoomArea";

export interface IRoomAreaRepository {

  findByRoomId(roomId: string): Promise<RoomArea>;
}