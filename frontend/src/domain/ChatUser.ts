import VideoArea from "@/domain/VideoArea";
import UserConnection from "@/domain/UserConnection";
import OT from '@opentok/client'

export interface ChatUserProps {
  publisherVideoArea: VideoArea;

  userConnection: UserConnection;

}

export default class ChatUser {

  publisherVideoArea: VideoArea;

  publisher?: OT.Publisher;

  userConnection: UserConnection;

  session?: OT.Session;

  constructor(props: ChatUserProps) {
    this.publisherVideoArea = props.publisherVideoArea;
    this.userConnection = props.userConnection;

  }

  public connect() {
    const handleError = (error: any) => {
      if (error) console.log(error);
    };

    this.session = OT.initSession(this.userConnection.roomConnectionId.apiKey, this.userConnection.roomConnectionId.connectionId);

    this.publisher = OT.initPublisher(this.publisherVideoArea.videoElementId, {
      insertMode: 'append',
      width: '100%',
      height: '100%'
    }, handleError);

    this.session.connect(this.userConnection.userConnectionToken, error => {
      if (error) {
        handleError(error);
      } else {
        if(this.publisher == null) {
          handleError(new Error('not found publisher'));
          return;
        }
        if(this.session == null) return new Error('not found session');
        this.session.publish(this.publisher, handleError);
      }
    });

    this.session.on('streamCreated', event => {
      if(this.session == null) return new Error('not found session');
      this.session.subscribe(event.stream, event.stream.name, {
        insertMode: 'append',
        width: '100%',
        height: '100%'
      }, handleError);
    });
  }

}