<template>
  <div id="video-chat">
    <div>
      <div>uri : {{ roomUri }}</div>
      <div>status : {{ connectStatus }}</div>
    </div>
    <ul style="display: flex; flex-wrap: wrap; margin: auto">
      <VideoArea v-for="videoArea in videoAreaList" :key="videoArea.videoElementId" v-bind:videoId="videoArea.videoElementId" v-bind:nameLabelValue="videoArea.userName" />
    </ul>
  </div>
</template>

<script>
  import VideoArea from "../molecules/VideoArea.vue";
  import ChatUserUseCase from "../../usecase/ChatUserUseCase";

  const chatUserUseCase = new ChatUserUseCase();

  export default {
    name: "VideoChat",
    components: {VideoArea},
    computed: {
      roomName: function() {
        return this.$data && this.$data.roomArea ? this.$data.roomArea.roomName : '';
      },
      roomUri: function () {
        return this.$data.chatUser ? "https://" + window.location.host + "/video-chat?roomId=" + this.$data.chatUser.userConnection.roomId : '';
      },
      connectStatus: function () {
        if (this.$data.chatUser && this.$data.isNotStartedConnect) {
          this.$nextTick(() => {
            this.$data.chatUser.connect(this.$data.videoAreaList, this);

            this.started();
          });
        }

        if (this.$data.isNotStartedConnect) {
          return 'disconnected';
        }

        return 'connected';
      },
    },
    methods: {
      started: function () {
        this.$data.isNotStartedConnect = false;
      }
    },
    data() {
      return {
        videoAreaList: [],
        chatUser: null,
        isNotStartedConnect: true,
      }
    },
    created() {
      if (this.$route && this.$route.query.roomId) {
        const roomId = this.$route.query.roomId;
        const userName = this.$route.query.userName ? this.$route.query.userName : 'ユーザー';
        chatUserUseCase.joinRoom(roomId, userName).then(chatUser => {

          this.$data.videoAreaList.push(chatUser.publisherVideoArea);

          this.$data.chatUser = chatUser;
        })
      } else {
        chatUserUseCase.joinNewRoom('テスト部屋', 'ユーザー1').then(chatUser => {
          this.$data.videoAreaList.push(chatUser.publisherVideoArea);

          this.$data.chatUser = chatUser;
        });
      }
    },

  }
</script>

<style scoped>

</style>