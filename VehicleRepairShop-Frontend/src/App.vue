<template>
  <div id="app">
    <CalendarAdminAccount />
  </div>
</template>

<script>
import CalendarAdminAccount from './components/CalendarAdminAccount.vue'

export default {
  name: 'app',
  components: { 
    CalendarAdminAccount
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
