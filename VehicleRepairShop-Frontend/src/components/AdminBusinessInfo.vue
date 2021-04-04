<template>
  <div id="customerCreateCar">
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

              <b-nav-item href="#/adminBusinessInfo" color="white"
                >Business Information</b-nav-item
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
    <h1 style="color: #409eff">Manage Your Business Information</h1>
    <br/>

    <table id="t01">
      <tr>
        <th>Business Name:</th>
        <td><input type="text" v-model="businessName" /></td>
      </tr>

      <tr>
        <th>Business Address:</th>
        <td><input type="text" v-model="businessAddress" /></td>
      </tr>

      <tr>
        <th>Phone Number:</th>
        <td><input type="text" v-model="businessPhoneNumber" /></td>
      </tr>

      <tr>
        <th>Email:</th>
        <td><input type="text" v-model="businessEmailAddress" /></td>
      </tr>
    </table>
    <br />
    <p>
      <span v-if="errorBusinessInfo" style="color: red"
        >Error: {{ errorBusinessInfo }}
      </span>
    </p>

    <button
      @click="
        createBusinessInformation(
          businessName,
          businessAddress,
          businessPhoneNumber,
          businessEmailAddress
        )
      "
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
      id="confirmButton"
    >
      <font size="3"><b>Confirm</b></font>
    </button>

    <div class="container">
      <h3 class="p-3 text-center">Your Business Information</h3>
      <table class="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Name</th>
            <th>Address</th>
            <th>PhoneNumber</th>
            <th>Email</th>
            <th style="color: red">Delete</th>
            <th style="color: #409eff">Active</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="businessInformation in businessInfos"
            :key="businessInformation.id"
          >
            <td>{{ businessInformation.name }}</td>
            <td>{{ businessInformation.address }}</td>
            <td>{{ businessInformation.phoneNumber }}</td>
            <td>{{ businessInformation.emailAddress }}</td>
            <td>
              <button
                @click="deleteBusinessInformation(businessInformation.name)"
                type="button"
                style="border-color: red; color: red"
                class="btn"
              >
                <font size="3"><b>Delete Business Information</b></font>
              </button>
            </td>
            <td>
              <button
                @click="
                  activateBusinessInformation(
                    businessInformation.name,
                    businessInformation.address,
                    businessInformation.phoneNumber,
                    businessInformation.emailAddress
                  )
                "
                type="button"
                style="border-color: #409eff; color: #409eff"
                class="btn"
              >
                <font size="3"><b>Activate Business Information</b></font>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button
      @click="getAllBusinessInformation()"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>Refresh Table</b></font>
    </button>
  </div>
</template>

<script src="./adminBusinessInfo.js">
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
}
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>