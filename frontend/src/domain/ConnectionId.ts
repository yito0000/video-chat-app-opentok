export interface ConnectionIdProps {
  connectionId: string;

  apiKey: string;

}

export default class ConnectionId {

  connectionId: string;

  apiKey: string;

  constructor(props: ConnectionIdProps) {
    this.connectionId = props.connectionId;
    this.apiKey = props.apiKey;
  }

}