import {UserInfoJson} from "@/driver/datamodel/UserInfoJson";

export type UserConnectionJson = {
  roomId: string;

  apiKey: string;

  roomConnectionId: string;

  roomName: string;

  userInfo: UserInfoJson;

  userConnectionToken: string;
}

