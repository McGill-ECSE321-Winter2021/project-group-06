<template>
  <div id="app">
    <!-- <CalendarAdminAccount /> -->
    <!-- <MainMenu /> -->
    <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
          <b-navbar-brand href="#">VRSS</b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack">Go Back</b-nav-item>
              <b-nav-item href="#/app">Home</b-nav-item>
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
                <b-button size="sm" class="my-2 my-sm-0" type="submit"
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
                  <!-- <div>
                <!-- Image and text -->
                  <!-- <b-navbar variant="faded" type="light">
                  <b-navbar-brand href="#">
                    <img
                      src="https://placekitten.com/g/30/30"
                      class="d-inline-block align-top"
                      alt="Kitten"
                    />
                    User name
                  </b-navbar-brand>
                </b-navbar> -->
                  <!-- </div> -->
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

export default {
  name: "app",
  components: {},
  methods:{
    goBack(){
              this.$router.go(-1);
            }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
