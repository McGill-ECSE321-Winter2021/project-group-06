<template>
  <div id="viewTechnicianAccount">
    <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#/calendarTechnician" style="color: black"
            >VRSS</b-navbar-brand
          >

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white"
                >Go Back</b-nav-item
              >
              <b-nav-item href="#/calendarTechnician" color="white"
                >Home</b-nav-item
              >
              <b-nav-item href="#/viewTechnicianAccount" color="white"
                >Profile</b-nav-item
              >
              <b-nav-item href="#/calendarTechnician" color="white"
                >Calendar</b-nav-item
              >
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
              <b-nav-form>
                <tr>
                  <td>
                    <input
                      type="text"
                      v-model="searchInput"
                      placeholder="Search"
                    />
                  </td>
                </tr>
                <b-button
                  @click="searchButton(searchInput)"
                  size="sm"
                  class="my-2 my-sm-0"
                  type="submit"
                  style="background-color: #909399; color: white"
                  >Search</b-button
                >
              </b-nav-form>
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    </template>

    <br /><br />
    <h1 style="color: #409eff">Profile</h1>

    <br /><br />

    <table id="t01">
      <tr>
        <th>Username:</th>
        <td>{{ this.$currentUsername.value }}</td>
      </tr>
      <tr>
        <th>Name:</th>
        <td>{{ this.$currentName.value }}</td>
      </tr>
    </table>
    <br /><br />

    <p>
      <span v-if="errorTechnicianAccount" style="color: red"
        >Error: {{ errorTechnicianAccount }}
      </span>
    </p>

    <button
      @click="deleteTechnicianAccount()"
      onclick=""
      type="button"
      style="border-color: red; color: red"
      class="btn"
    >
      <font size="3"><b>Delete Account</b></font>
    </button>
    <button
      @click="goToEditTechnicianAccount()"
      onclick=""
      type="button"
      style="border-color: #409eff; color: #409eff"
      class="btn"
    >
      <font size="3"><b>Manage Account</b></font>
    </button>
    <br /><br />
    <button
      @click="logoutTechnicianAccount()"
      type="button"
      style="background-color: #909399; color: white"
      class="btn"
    >
      <font size="3"><b>Log Out</b></font>
    </button>
    <br /><br />
  </div>
</template>

<script src="./viewTechnicianAccount.js">
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
#technicianAccountProfile {
  margin-bottom: 0;
  margin-top: 0;
}
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>

