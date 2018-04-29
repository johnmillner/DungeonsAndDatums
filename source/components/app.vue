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
  module.exports = {
    data: function(){
      return{
        greeting: 'hello'
      }
    }
  }
</script>

<style></style>
