import axios, {AxiosResponse} from 'axios'
import UserConnection from "@/domain/UserConnection";
import RoomConnectionId from "@/domain/RoomConnectionId";

export default class OpenTokDriver {

  public createRoom(roomName: string, userName: string): Promise<UserConnection> {
    return axios.post(`/api/create/connection?userName=${userName}&roomName=${roomName}`).then(response => {
      const userConnection = this.convertToUserConnection(response);

      return Promise.resolve(userConnection);
    }).catch(error => {
      return Promise.reject(error);
    })
  }

  private convertToUserConnection(response: AxiosResponse) {
    const json: UserToRoomConnectionJson = response.data;

    const roomId = json.roomId;
    const roomName = json.roomName;

    const connectionId = json.roomConnectionId;
    const apiKey = json.apiKey;
    const roomConnectionId = new RoomConnectionId({
      connectionId: connectionId,
      apiKey: apiKey
    });

    const userId = json.userInfo.userId;
    const userName = json.userInfo.userName;
    const userConnectionToken = json.userConnectionToken;

    const userToRoomConnection = new UserConnection({
      roomId: roomId,
      roomName: roomName,
      connectionId: roomConnectionId,
      userId: userId,
      userName: userName,
      userConnectionToken: userConnectionToken
    });
    return userToRoomConnection;
  }

  public joinRoom(roomId: string, userName: string) {
    return axios.get(`/api/create/connection/${roomId}/join?userName=${userName}`).then(response => {
      const userConnection = this.convertToUserConnection(response);

      return Promise.resolve(userConnection);
    }).catch(error => {
      return Promise.reject(error);
    })
  }
}

export type UserToRoomConnectionJson = {
  roomId: string;
  apiKey: string;
  roomConnectionId: string;
  roomName: string;
  userInfo: UserInfoJson;
  userConnectionToken: string;
}

export type UserInfoJson = {
  userId: string;
  userName: string;
}