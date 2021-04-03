<template>
  <div id="customerViewCars">

    <template>
        <div>
            <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#/adminHome" style="color: black">VRSS</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
                <b-nav-item href="#/adminHome" color="white">Home</b-nav-item>
                <b-nav-item href="#/viewAdminAccount" color="white"
                    >Profile</b-nav-item
                >
                <b-nav-item href="#/calendarAdmin" color="white">Calendar</b-nav-item>

                <b-nav-item href="#/offeredServiceTableAdmin"
                    >Services and Pricing</b-nav-item
                >
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                <b-nav-form>
                    <tr>
                    <td>
                        <input type="text" v-model="searchInput" placeholder="Search" />
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
            <br /><br />
            
        </div>
    </template>
    <br /><br />

    <div class="container">
        <h3 class="p-3 text-center">Your Cars</h3>
        <table
          v-on="getAllCars()"
          class="table table-striped table-bordered"
        >
          <thead>
            <tr>
              <th>License Plate</th>
              <th>Model</th>
              <th>Year</th>
              <th>Motor Type</th>
              <th style="color: red">Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="car in cars" :key="car.id">
              <td>{{ car.licensePlate }}</td>
              <td>{{ car.model }}</td>
              <td>{{ car.year }}</td>
              <td>{{ car.motorType }}</td>
              <td>
                <button
                  @click="deleteCar(car.licensePlate)"
                  onclick=""
                  type="button"
                  style="border-color: red; color: red"
                  class="btn"
                >
                  <font size="3"><b>Delete Car</b></font>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    <h1 style="color: #409eff">Your Cars</h1>
    <br><br/>

    <button
      @click="goToCustomerCreateCar()"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
        <font size="3"><b>Add Car</b></font>
    </button>
    
    </div>
</template>

<script src="./customerViewCars.js">
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
  width: 40%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>