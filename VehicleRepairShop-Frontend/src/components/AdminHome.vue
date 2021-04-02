<template>
  <div id="adminHome">
    <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#/adminHome" style="color: black"
            >VRSS</b-navbar-brand
          >

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white"
                >Go Back</b-nav-item
              >
              <b-nav-item href="#/adminHome" color="white">Home</b-nav-item>
              <b-nav-item href="#/viewAdminAccount" color="white"
                >Profile</b-nav-item
              >
              <b-nav-item href="#/calendarAdmin" color="white"
                >Calendar</b-nav-item
              >

              <b-nav-item href="#/offeredServiceTableAdmin"
                >Services and Pricing</b-nav-item
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
    <h1 style="color: #409eff">Manage Business</h1>

    <br /><br />
    <div>
      <div class="container">
        <h3 class="p-3 text-center">List of Garages</h3>
        <table
          v-on="getAllGarages()"
          class="table table-striped table-bordered"
        >
          <thead>
            <tr>
              <th>GarageId</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="garage in garages" :key="garage.id">
              <td>{{ garage.garageId }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="container">
        <h3 class="p-3 text-center">List of Admin Accounts</h3>
        <table
          v-on="getAllAdminAccounts()"
          class="table table-striped table-bordered"
        >
          <thead>
            <tr>
              <th>Username</th>
              <th>Name</th>
              <th>Password</th>
              <th style="color: red">Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in adminAccounts" :key="user.id">
              <td>{{ user.username }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.password }}</td>
              <td>
                <button
                  @click="deleteAdminAccount(user.username)"
                  onclick=""
                  type="button"
                  style="border-color: red; color: red"
                  class="btn"
                >
                  <font size="3"><b>Delete Account</b></font>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <br /><br />
      <div class="container">
        <h3 class="p-3 text-center">List of Customer Accounts</h3>
        <table
          v-on="getAllCustomerAccounts()"
          class="table table-striped table-bordered"
        >
          <thead>
            <tr>
              <th>Username</th>
              <th>Name</th>
              <th>Password</th>
              <th style="color: red">Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in customerAccounts" :key="user.id">
              <td>{{ user.username }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.password }}</td>
              <td>
                <button
                  @click="deleteCustomerAccount(user.username)"
                  onclick=""
                  type="button"
                  style="border-color: red; color: red"
                  class="btn"
                >
                  <font size="3"><b>Delete Account</b></font>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <br /><br />
      <div class="container">
        <h3 class="p-3 text-center">List of Technician Accounts</h3>
        <table
          v-on="getAllTechnicianAccounts()"
          class="table table-striped table-bordered"
        >
          <thead>
            <tr>
              <th>Username</th>
              <th>Name</th>
              <th>Password</th>
              <th style="color: red">Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in technicianAccounts" :key="user.id">
              <td>{{ user.username }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.password }}</td>
              <td>
                <button
                  @click="deleteTechnicianAccount(user.username)"
                  onclick=""
                  type="button"
                  style="border-color: red; color: red"
                  class="btn"
                >
                  <font size="3"><b>Delete Account</b></font>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <br /><br />
  </div>
</template>

<script src="./adminHome.js">
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
#adminAccountProfile {
  margin-bottom: 0;
  margin-top: 0;
  /* background-color: #909399;
    color: #409EFF; */
}
#t01 {
  width: 18%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>

