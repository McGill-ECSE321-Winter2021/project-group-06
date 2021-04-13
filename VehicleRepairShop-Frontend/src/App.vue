<template>
  <div id="app">
    <router-view></router-view>
    <template>
      <div>
        <footer class="bg-white">
          <div class="container py-5">
            <img src="./assets/logo.png" alt="" width="180" class="mb-3" />
            <div class="row py-4">
              <div class="col-lg-6 col-md-6 mb-4 mb-lg-0">
                <h6 class="text-uppercase font-weight-bold mb-4">About Us</h6>
                <p class="font-italic text-muted">
                  Welcome to the website for our amazing, outstanding,
                  mindblowing, Vehicle Repair Shop!<br />
                  This website is designed for small businesses to manage their
                  business effectively and efficiently.<br />
                  This website is designed by: Aurelia, Catherine, Cheng, James
                  and Mike.<br />
                  “It's not a bug it's a feature" - Sleeping Beauty
                </p>
                
              </div>
              <div class="col-lg-1 col-md-1 mb-1 mb-lg-0"></div>
              <div class="col-lg-5 col-md-5 mb-2 mb-lg-0 textLeft">
                <h6 class="text-uppercase font-weight-bold mb-4">
                  Business Information
                </h6>
                <ul class="list-unstyled mb-0">
                  <li class="mb-1">
                    <p class="text-muted">{{ this.$root.businessName }}</p>
                  </li>
                  <li class="mb-1">
                    <p class="text-muted">{{ this.$root.businessAddress }}</p>
                  </li>
                  <li class="mb-1">
                    <p class="text-muted">
                      {{ this.$root.businessPhoneNumber }}
                    </p>
                  </li>
                  <li class="mb-1">
                    <p class="text-muted">
                      {{ this.$root.businessEmailAddress }}
                    </p>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <!-- Copyrights -->
          <div class="bg-light py-4">
            <div class="container text-center">
              <p class="text-muted mb-0 py-2">
                © 2021 Project Group 6 All rights reserved.
              </p>
            </div>
          </div>
        </footer>
        <!-- End -->
      </div>
    </template>
  </div>
</template>

<script>
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

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
  margin-top: 60px;
}
</style>