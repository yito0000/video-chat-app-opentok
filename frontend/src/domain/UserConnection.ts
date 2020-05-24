import { Session } from '@opentok/client'
import RoomConnectionId from "@/domain/RoomConnectionId";

export interface UserToRoomConnectionProps {
  roomId: string;

  connectionId: RoomConnectionId;

  roomName: string;

  userConnectionToken: string;

  userName: string;

  userId: string;

}

export default class UserConnection {

  roomId: string;

  roomName: string;

  roomConnectionId: RoomConnectionId;

  userConnectionToken: string;

  userName: string;

  userId: string;

  constructor(props: UserToRoomConnectionProps) {
    this.roomId = props.roomId;
    this.roomName = props.roomName;
    this.roomConnectionId = props.connectionId;
    this.userConnectionToken = props.userConnectionToken;
    this.userId = props.userId;
    this.userName = props.userName;
  }

}