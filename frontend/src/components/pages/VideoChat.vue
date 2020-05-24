<template>
  <div id="video-chat">
    <VideoArea v-for="user in visitedChatUsers" :key="user.userConnection.userId" v-bind:videoId="user.publisherVideoArea.videoElementId" v-bind:nameLabelValue="user.userConnection.userName" />
  </div>
</template>

<script>
  import VideoArea from "../molecules/VideoArea.vue";
  import ChatUserUseCase from "../../usecase/ChatUserUseCase";

  const chatUserUseCase = new ChatUserUseCase();

  export default {
    name: "VideoChat",
    components: {VideoArea},
    data() {
      return {
        chatUser: null,
        visitedChatUsers: []
      }
    },
    created() {
      let chatUserPromise = null;
      if (this.$route && this.$route.query.roomId) {
        const roomId = this.$route.query.roomId;
        console.log(roomId);
        // 入室者一覧を取得してコンポーネントを作成する
        const userName = this.$route.query.userName ? this.$route.query.userName : 'ユーザー';
        chatUserPromise = chatUserUseCase.joinRoom(roomId, userName);
      } else {
        chatUserPromise = chatUserUseCase.joinNewRoom('テスト', 'ユーザー1');
      }

      chatUserPromise.then(chatUser => {
        this.visitedChatUsers.push(chatUser);
        this.chatUser = chatUser;
      });

    },

    updated() {
      this.$nextTick().then(() => {
        this.chatUser.connect();
      })

    },
  }
</script>

<style scoped>

</style>