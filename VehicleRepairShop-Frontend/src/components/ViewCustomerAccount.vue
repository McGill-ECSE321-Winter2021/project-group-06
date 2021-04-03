<template>
  <div id="viewCustomerAccount">
    <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#/calendarCustomer" style="color: black"
            >VRSS</b-navbar-brand
          >

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white"
                >Go Back</b-nav-item
              >
              <b-nav-item href="#/calendarCustomer" color="white">Home</b-nav-item>
              <b-nav-item href="#/viewCustomerAccount" color="white"
                >Profile</b-nav-item
              >
              <b-nav-item href="#/calendarCustomer" color="white"
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
                  <td><input type="text" v-model="searchInput" placeholder="Search"/></td>
                </tr>
                <!-- <b-form-input type="text" v-model="searchInput"
                  size="sm"
                  class="mr-sm-2"
                  placeholder="Search"
                ></b-form-input> -->
                <b-button
                  @click="searchButton(searchInput)"
                  size="sm"
                  class="my-2 my-sm-0"
                  type="submit"
                  style="background-color: #909399; color: white"
                  >Search</b-button
                >
              </b-nav-form>

              <!-- <b-nav-item-dropdown right> -->
              <!-- Using 'button-content' slot -->
              <!-- <template #button-content> -->
              <!-- <em>User</em> -->
              <!-- </template> -->
              <!-- <b-dropdown-item href="#/viewCustomerAccount" style="background-color: #909399">Profile</b-dropdown-item> -->
              <!-- <b-dropdown-item href="#" style="background-color: #909399">>Sign Out</b-dropdown-item> -->
              <!-- </b-nav-item-dropdown>  -->
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
      <span v-if="errorCustomerAccount" style="color: red"
        >Error: {{ errorCustomerAccount }}
      </span>
    </p>

    <button
      @click="deleteCustomerAccount()"
      onclick=""
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Delete Account</b></font>
    </button>
    <button
      @click="goToEditCustomerAccount()"
      onclick=""
      type="button"
      style="border-color: #409eff; color: #409eff"
      class="btn"
    >
      <font size="3"><b>Manage Account</b></font>
    </button>
    <br /><br />
    <button
      @click="logoutCustomerAccount()"
      type="button"
      style="background-color: #909399; color: white"
      class="btn"
    >
      <font size="3"><b>Log Out</b></font>
    </button>
    <br /><br />
  </div>
</template>

<script src="./viewCustomerAccount.js">
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
#customerAccountProfile {
  margin-bottom: 0;
  margin-top: 0;
  /* background-color: #909399;
    color: #409EFF; */
}
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>

