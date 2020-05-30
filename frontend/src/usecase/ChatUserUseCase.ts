import ChatUser from "@/domain/ChatUser";
import IUserConnectionRepository from "@/domain/repository/IUserConnectionRepository";
import UserConnectionRepository from "@/repository/UserConnectionRepository";
import VideoArea from "@/domain/VideoArea";

export default class ChatUserUseCase {

  userConnectionRepository: IUserConnectionRepository;

  constructor() {
    this.userConnectionRepository = new UserConnectionRepository();
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