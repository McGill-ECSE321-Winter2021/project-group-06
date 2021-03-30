<template>
  <div id="app">
    <!-- <CalendarAdminAccount /> -->
    <!-- <MainMenu /> -->
    <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#">VRSS</b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
              <b-nav-item href="#/app" color="white">Home</b-nav-item>
              <b-nav-item href="#/Calender">Calendar</b-nav-item>
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
                  >Search</b-button>
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
    <template>
      <div>
        <footer class="bg-white">
          <div class="container py-5">
            <div class="row py-4">
              <div class="col-lg-6 col-md-6 mb-4 mb-lg-0"><img src="img/logo.png" alt="" width="180" class="mb-3">
                <p class="font-italic text-muted">Welcome to the website for our amazing, outstanding, mindblowing, Vehicle Repair Shop!</p>
              </div>
            <div class="col-lg-6 col-md-6 mb-2 mb-lg-0">
              <h6 class="text-uppercase font-weight-bold mb-4">Business Information</h6>
              <ul class="list-unstyled mb-0">
                <li class="mb-2"><a class="text-muted">Name</a></li>
                <li class="mb-2"><a class="text-muted">Address</a></li>
                <li class="mb-2"><a class="text-muted">Phone Number</a></li>
                <li class="mb-2"><a class="text-muted">Email</a></li>
              </ul>
            </div>
          </div>
        </div>

    <!-- Copyrights -->
    <div class="bg-light py-4">
      <div class="container text-center">
        <p class="text-muted mb-0 py-2">© 2021 Project Group 6 All rights reserved.</p>
      </div>
    </div>
  </footer>
  <!-- End -->
        
      </div>
    </template>
  </div>
</template>

<script>
import CalendarAdminAccount from "./components/CalendarAdminAccount.vue";
import MainMenu from "./components/MainMenu.vue";

export default {
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
