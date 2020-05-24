import UserConnection from "@/domain/UserConnection";

export default interface IUserConnectionRepository {

  createUserConnection(roomName: string, userName: string): Promise<UserConnection>;

  createUserConnectionById(roomId: string, userName: string): Promise<UserConnection>;

}