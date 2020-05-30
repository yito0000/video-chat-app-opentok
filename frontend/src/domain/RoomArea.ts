import VideoArea, {VideoAreaProps} from "@/domain/VideoArea";

export interface RoomAreaProps {
  roomId: string;

  roomName: string

  videoAreaList: VideoArea[];

}

export default class RoomArea {

  roomId: string;

  roomName: string;

  videoAreaList: VideoArea[];

  constructor(props: RoomAreaProps) {
    this.roomId = props.roomId;
    this.roomName = props.roomName;
    this.videoAreaList = props.videoAreaList;
  }

  addVideoArea(props: VideoAreaProps) {
    const videoArea = new VideoArea({
      videoElementId: props.videoElementId,
      userName: props.userName,
      height: props.height,
      width: props.width
    });

    this.videoAreaList.push(videoArea);
  }

  getRoomUri(): string {
    return window.location + "?roomId=" + this.roomId;
  }
}