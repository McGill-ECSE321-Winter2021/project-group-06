<template>
  <div id="app">
    <!-- <CalendarAdminAccount /> -->
    <!-- <MainMenu /> -->
    <template>
      <div>
		<my-header></my-header>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#">VRSS</b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white"
                >Go Back</b-nav-item
              >
              <b-nav-item href="#/app" color="white">Home</b-nav-item>
              <b-nav-item href="#" disabled>Services and Pricing</b-nav-item>
              <b-nav-item href="#" disabled>Information</b-nav-item>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
              <b-nav-form>
                <b-form-input
                  size="sm"
                  class="mr-sm-2"
                  placeholder="Search"
                ></b-form-input>
                <b-button
                  size="sm"
                  class="my-2 my-sm-0"
                  type="submit"
                  style="background-color: #a9a9a9; color: white"
                  >Search</b-button
                >
              </b-nav-form>

              <b-nav-item-dropdown text="Lang" right>
                <b-dropdown-item href="#">EN</b-dropdown-item>
                <b-dropdown-item href="#">FA</b-dropdown-item>
              </b-nav-item-dropdown>

              <b-nav-item-dropdown right>
                <!-- Using 'button-content' slot -->
                <template #button-content>
                  <em>User</em>
                </template>
                <b-dropdown-item href="#/viewAccount">Profile</b-dropdown-item>
                <b-dropdown-item href="#">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    </template>
    <router-view></router-view>
  </div>
</template>

<script>
import CalendarAdminAccount from "./components/CalendarAdminAccount.vue";
import MainMenu from "./components/MainMenu.vue";
import Header from './components/Header'

export default {
  'my-header':Header,
  name: "app",
  components: {},
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.navbar.navbar-dark.bg-dark {
  background-color: #c30000 !important;
}
nav .navbar-nav li a {
  color: white !important;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
  margin-top: 60px;
}
</style>
