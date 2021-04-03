<template>
  <div id="customerCreateCar">

    <template>
        <div>
            <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="#/adminHome" style="color: black">VRSS</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
                <b-nav-item href="#/viewCustomerAccount" color="white"
                    >Profile</b-nav-item
                >
                <b-nav-item href="#/calendarCustomer" color="white">Calendar</b-nav-item>

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
            <!-- <Fullcalendar ref="calendar" :options="calendarOptions" />

            <modal-window
            :visible="isShowModal"
            :close-on-escape="true"
            :close-on-outside-click="true"
            :show-x-mark="true"
            @close="isShowModal = false"
            >
            </modal-window> -->
        </div>
    </template>

    <br><br />
    <h1 style="color: #409eff">Create New Car</h1>

    <table id="t01">
      <tr>
        <th>License Plate Number:</th>
        <td><input type="text" v-model="licensePlate" /></td>
      </tr>

      <tr>
        <th>Model:</th>
        <td><input type="text" v-model="model" /></td>
      </tr>

      <tr>
        <th>Year:</th>
        <td><input type="text" v-model="year" /></td>
      </tr>
       
      <tr> 
        <th>Motor Type:</th>
        <td>
            <select v-model="motorType">
            <option disabled value="">Please select one</option>
            <option>Electric</option>
            <option>Hybrid</option>
            <option>Gas</option>
            <option>Diesel</option>
            </select>
        </td>
      </tr>     
    </table>
    <br><br/>
        <p>
      <span v-if="errorCar" style="color: red"
        >Error: {{ errorCar }}
      </span>
    </p>
    <!-- <button
      @click="goToViewCustomerCar()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button> -->

    <button
      @click="createCar(licensePlate, model, year, motorType)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
        <font size="3"><b>Create</b></font>
    </button>
    
    <div class="container">
        <h3 class="p-3 text-center">Your Cars</h3>
        <table
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
                      <button
                  @click="getAllCars()"
                  type="button"
                  style="background-color: #409eff; color: white"
                  class="btn"
                > 
                  <font size="3"><b>Refresh Table</b></font>
                </button>
  

    
    </div>
</template>

<script src="./customerCreateCar.js">
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
t01 {
  width: 25%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>