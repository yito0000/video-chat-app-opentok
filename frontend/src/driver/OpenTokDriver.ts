import axios, {AxiosResponse} from 'axios'
import UserConnection from "@/domain/UserConnection";
import RoomConnectionId from "@/domain/RoomConnectionId";
import {UserConnectionJson} from "@/driver/datamodel/UserConnectionJson";

export default class OpenTokDriver {

  public createRoom(roomName: string, userName: string): Promise<UserConnection> {
    return axios.post(`/api/create/room?userName=${userName}&roomName=${roomName}`).then(response => {
      const userConnection = this.convertUserConnection(response);

      return Promise.resolve(userConnection);
    }).catch(error => {
      return Promise.reject(error);
    })
  }

  public joinRoom(roomId: string, userName: string): Promise<UserConnection> {
    return axios.post(`/api/room/${roomId}/create/connection?userName=${userName}`).then(response => {
      const userConnection = this.convertUserConnection(response);

      return Promise.resolve(userConnection);
    }).catch(error => {
      return Promise.reject(error);
    })
  }

  private convertUserConnection(response: AxiosResponse) {
    const json: UserConnectionJson = response.data;

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

    const userConnection = new UserConnection({
      roomId: roomId,
      roomName: roomName,
      connectionId: roomConnectionId,
      userId: userId,
      userName: userName,
      userConnectionToken: userConnectionToken
    });
    return userConnection;
  }

}

