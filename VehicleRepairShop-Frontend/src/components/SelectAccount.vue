<template>
  <div class="selectAccount">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#/" style="color: black">VRSS</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <br /><br />
    <h1>Select your account type</h1>
    <br /><br />
    <button
      @click="goToAdminAccountLogin"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>I am an Admin!</b></font>
    </button>
    <button
      @click="goToCustomerAccountLogin"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>I am a Customer!</b></font>
    </button>
    <button
      @click="goToTechnicianAccountLogin"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>I am a Technician!</b></font>
    </button>
  </div>
</template>

<script>
export default {
  methods: {
    goToAdminAccountLogin() {
      this.$router.push("/adminAccountLogin");
    },
    goToCustomerAccountLogin() {
      this.$router.push("/customerAccountLogin");
    },
    goToTechnicianAccountLogin() {
      this.$router.push("/technicianAccountLogin");
    }
  },
};
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
</style>