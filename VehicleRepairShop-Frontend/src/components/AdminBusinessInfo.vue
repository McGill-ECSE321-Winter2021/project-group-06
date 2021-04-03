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
            <Fullcalendar ref="calendar" :options="calendarOptions" />

            <modal-window
            :visible="isShowModal"
            :close-on-escape="true"
            :close-on-outside-click="true"
            :show-x-mark="true"
            @close="isShowModal = false"
            >
            </modal-window>
        </div>
    </template>
    <br /><br />
    <h1 style="color: #409eff">Manage Your Business Information</h1>

    <table id="t01">
      <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
      />
      <tr>
        <th>Business Name:</th>
        <td><input type="text" v-model="businessName" /></td>
        <p>{{businessInfo.name}} aa</p>
      </tr>

      <tr>
        <th>Business Address:</th>
        <td><input type="text" v-model="address" /></td>
        <p>{{businessInfo.address}} aa</p>
      </tr>

      <tr>
        <th>Phone Number:</th>
        <td><input type="text" v-model="phoneNumber" /></td>
        <p>{{businessInfo.phoneNumber}} aa</p>
      </tr>
       
      <tr> 
        <th>Email:</th>
        <td><input type="text" v-model="email" /></td>
        <p>{{businessInfo.email}} aa</p>
      </tr>     
    </table>
    <br>
    <button
      @click="goToAdminHome()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>

    <button
      @click="deleteBusinessInformation(businessName)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
        <font size="3"><b>Save</b></font>
    </button>

    <button
      @click="createBusinessInformation(businessName, address, phoneNumber, email)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
      disabled='isDisabled'
      id=confirmButton
    >
        <font size="3"><b>Confirm</b></font>
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