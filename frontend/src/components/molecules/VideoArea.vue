<template>
  <div class="video-area">
    <Video v-bind:videoId="videoId"></Video>
    <NameLabel v-bind:value="nameLabelValue"></NameLabel>
  </div>
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

</style>