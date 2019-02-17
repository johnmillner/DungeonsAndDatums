<!--
	Created by:		John Millner
	Created for:	Dungeons & Datums
-->

<template>
  <div id = "app">
    <p>{{ test }}</p>
    <router-view></router-view>
  </div>
</template>

<script>
  export default = {
    data() {
      return{
        test: 'hello'
      }
    }
  }
</script>

<style></style>
