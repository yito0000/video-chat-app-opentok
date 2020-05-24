export interface RoomConnectionIdProps {
  connectionId: string;

  apiKey: string;

}

export default class RoomConnectionId {

  connectionId: string;

  apiKey: string;

  constructor(props: RoomConnectionIdProps) {
    this.connectionId = props.connectionId;
    this.apiKey = props.apiKey;
  }
}