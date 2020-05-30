import {IRoomAreaRepository} from "@/domain/repository/IRoomAreaRepository";
import RoomArea from "@/domain/RoomArea";
import OpenTokDriver from "@/driver/OpenTokDriver";

export class RoomRepository implements IRoomAreaRepository{

  openTokDriver: OpenTokDriver;

  constructor() {
    this.openTokDriver = new OpenTokDriver();
  }

  public findByRoomId(roomId: string): Promise<RoomArea> {
    return this.openTokDriver.fetchRoom(roomId);
  }

}