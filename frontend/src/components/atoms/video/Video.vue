<template>
  <div class="video" v-bind:id="videoId">
  </div>
</template>

<script lang="ts">

  import {Component, Prop, Vue} from "vue-property-decorator";

  @Component
  export default class Video extends Vue {
    @Prop() private videoId!: string;
  }
</script>

<style scoped>
  .video {
    height: 300px;
    width: 400px;
    margin: auto;
  }
</style>