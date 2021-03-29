<template>
  <div id="app">
    <!-- <CalendarAdminAccount /> -->
    <!-- <MainMenu /> -->
    
    <router-view></router-view>
  </div>
</template>

<script>
import CalendarAdminAccount from './components/CalendarAdminAccount.vue'
import MainMenu from './components/MainMenu.vue'

export default {
  name: 'app',
  components: { 

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50; 
  margin-top: 60px;
}
</style>
