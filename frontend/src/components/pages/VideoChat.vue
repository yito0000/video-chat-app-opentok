<template>
  <div id="video-chat">
    <div>
      <div>{{ roomName }}</div>
      <div>url : {{ roomUri }}</div>
      <div>status : {{ connectStatus }}</div>
    </div>
    <VideoArea v-for="videoArea in this.$data.roomArea.videoAreaList" :key="videoArea.videoElementId" v-bind:videoId="videoArea.videoElementId" v-bind:nameLabelValue="videoArea.userName" />
  </div>
</template>

<script>
  import VideoArea from "../molecules/VideoArea.vue";
  import ChatUserUseCase from "../../usecase/ChatUserUseCase";
  import RoomArea from "../../domain/RoomArea";

  const chatUserUseCase = new ChatUserUseCase();

  export default {
    name: "VideoChat",
    components: {VideoArea},
    computed: {
      roomName: function() {
        return this.$data && this.$data.roomArea ? this.$data.roomArea.roomName : '';
      },
      roomUri: function () {
        return this.$data && this.$data.roomArea ? this.$data.roomArea.getRoomUri() : '';
      },
      connectStatus: function () {
        if (this.$data && this.$data.chatUser && this.$data.isNotStartedConnect) {
          this.$nextTick(() => {
            this.$data.chatUser.connect(this.$data.roomArea.videoAreaList);
            const handleError = (error) => {
              if (error) console.log(error);
            };

            const session = this.$data.chatUser.session;
            session.on('streamCreated', event => {
              this.$data.latestEvent = event;
              this.$data.roomArea.addVideoArea({
                videoElementId: event.stream.name,
                userName: '追加された',
                height: '100%',
                width: '100%'
              });
              console.log(this.$data.roomArea);
              this.$nextTick(() => {
                console.log(event.stream.name);
                session.subscribe(event.stream, event.stream.name, {
                  insertMode: 'append',
                  width: '100%',
                  height: '100%'
                }, handleError);
              });
            });

          });

          this.started();
          return '接続中';
        } else {
          return '未接続';
        }
      },
    },
    methods: {
      started: function () {
        this.$data.isNotStartedConnect = false;
      }
    },
    data() {
      return {
        roomArea: null,
        chatUser: null,
        isNotStartedConnect: true,
        session: null,
        latestEvent: null,
      }
    },
    created() {
      if (this.$route && this.$route.query.roomId) {
        const roomId = this.$route.query.roomId;
        chatUserUseCase.fetchRoom(roomId).then(roomArea => {
          this.$data.roomArea = roomArea;
          const userName = this.$route.query.userName ? this.$route.query.userName : 'ユーザー';
          chatUserUseCase.joinRoom(roomId, userName).then(chatUser => {

            this.$data.roomArea.addVideoArea({
              videoElementId: chatUser.publisherVideoArea.videoElementId,
              userName: chatUser.publisherVideoArea.userName,
              height: '100%',
              width: '100%'
            });

            this.$data.chatUser = chatUser;
          })
        });
      } else {
        chatUserUseCase.joinNewRoom('テスト部屋', 'ユーザー1').then(chatUser => {
          const roomArea = new RoomArea({
            roomId: chatUser.userConnection.roomId,
            roomName: chatUser.userConnection.roomName,
            videoAreaList: [chatUser.publisherVideoArea]
          });
          this.$data.roomArea = roomArea;

          this.$data.chatUser = chatUser;
        });
      }
    },

  }
</script>

<style scoped>

</style>