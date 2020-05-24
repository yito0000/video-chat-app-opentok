import IUserConnectionRepository from "@/domain/repository/IUserConnectionRepository";
import UserConnection from "@/domain/UserConnection";
import OpenTokDriver from "@/driver/OpenTokDriver";

export default class UserConnectionRepository implements IUserConnectionRepository{

  openTokDriver: OpenTokDriver;

  constructor() {
    this.openTokDriver = new OpenTokDriver();
  }

  public createUserConnection(roomName: string, userName: string): Promise<UserConnection> {
    return this.openTokDriver.createRoom(roomName, userName);
  }

  public createUserConnectionById(roomId: string, userName: string): Promise<UserConnection> {
    return this.createUserConnectionById(roomId, userName);
  }

}