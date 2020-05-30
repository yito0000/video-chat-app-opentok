<template>
  <li class="video-area">
    <NameLabel v-bind:value="nameLabelValue"></NameLabel>
    <Video v-bind:videoId="videoId"></Video>
  </li>
</template>

<script lang="ts">
  import Video from "../atoms/video/Video.vue";
  import NameLabel from "../atoms/label/NameLabel.vue";
  import {Component, Prop, Vue} from "vue-property-decorator";

  @Component({
    components: {
      Video, NameLabel
    }
  })
  export default class VideoArea extends Vue {
    @Prop() private nameLabelValue!: string;
    @Prop() private videoId!: string;
  }

</script>

<style scoped>
  .video-area {
    display: table-cell;
    padding: 10px;
    width: 400px;
    box-sizing:border-box;
  }
</style>