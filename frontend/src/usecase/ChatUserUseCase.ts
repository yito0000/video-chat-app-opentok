import ChatUser from "@/domain/ChatUser";
import IUserConnectionRepository from "@/domain/repository/IUserConnectionRepository";
import UserConnectionRepository from "@/repository/UserConnectionRepository";
import VideoArea from "@/domain/VideoArea";
import {IRoomAreaRepository} from "@/domain/repository/IRoomAreaRepository";
import {RoomRepository} from "@/repository/RoomRepository";
import RoomArea from "@/domain/RoomArea";

export default class ChatUserUseCase {

  userConnectionRepository: IUserConnectionRepository;
  roomAreaRepository: IRoomAreaRepository;

  constructor() {
    this.userConnectionRepository = new UserConnectionRepository();
    this.roomAreaRepository = new RoomRepository();
  }

  public joinNewRoom(roomName: string, userName: string): Promise<ChatUser> {
    return this.userConnectionRepository.createUserConnection(roomName, userName).then(connection => {
      const videoArea = new VideoArea({
        videoElementId: connection.userId,
        userName: userName,
        height: '100%',
        width: '100%'
      });
      const chatUser = new ChatUser({
        publisherVideoArea: videoArea,
        userConnection: connection
      });
      return Promise.resolve(chatUser);
    });
  }

  public fetchRoom(roomId: string): Promise<RoomArea> {
    return this.roomAreaRepository.findByRoomId(roomId);
  }

  public joinRoom(roomId: string, userName: string): Promise<ChatUser> {
    return this.userConnectionRepository.createUserConnectionById(roomId, userName).then(connection => {
      const videoArea = new VideoArea({
        videoElementId: connection.userId,
        userName: userName,
        height: '100%',
        width: '100%'
      });
      const chatUser = new ChatUser({
        publisherVideoArea: videoArea,
        userConnection: connection
      });
      return Promise.resolve(chatUser);
    });
  }
}