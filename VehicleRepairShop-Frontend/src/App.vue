<template>
  <div id="app">
    <img src="./assets/newLogo.png">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: top left;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
