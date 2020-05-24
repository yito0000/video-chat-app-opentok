<template>
  <label class="name-label" v-bind:value="value"> {{ value }} </label>
</template>

<script lang="ts">

  import {Component, Prop, Vue} from "vue-property-decorator";

  @Component
  export default class NameLabel extends Vue {
    @Prop() private value!: string;
  }
</script>

<style scoped>

</style>