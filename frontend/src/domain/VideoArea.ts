export interface VideoAreaProps {
  videoElementId: string;

  height: string;

  width: string;
}

export default class VideoArea {

  videoElementId: string;

  height: string;

  width: string;

  constructor(props: VideoAreaProps) {
    this.videoElementId = props.videoElementId;
    this.height = props.height;
    this.width = props.width;
  }

}