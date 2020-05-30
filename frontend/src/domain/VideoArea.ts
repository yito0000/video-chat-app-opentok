export interface VideoAreaProps {
  videoElementId: string;

  userName: string;

  height: string;

  width: string;
}

export default class VideoArea {

  videoElementId: string;

  userName: string;

  height: string;

  width: string;

  constructor(props: VideoAreaProps) {
    this.videoElementId = props.videoElementId;
    this.userName = props.userName;
    this.height = props.height;
    this.width = props.width;
  }

}