import {UserInfoJson} from "@/driver/datamodel/UserInfoJson";

export type RoomJson = {
  roomId: string;

  roomName: string;

  userInfoList: Array<UserInfoJson>;
}