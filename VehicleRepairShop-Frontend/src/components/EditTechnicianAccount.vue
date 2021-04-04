<template>
  <div class="editTechnicianAccount">
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
    <br /><br />
    <h1 style="color: #409eff">Manage Your Account</h1>
    <h4 style="color: #000000">Here you can change your name and password</h4>

    <table id="t01">
      <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
      />
      <tr>
        <th>Username:</th>
        <td>{{ this.$currentUsername.value }}</td>
      </tr>
      <tr>
        <th>Current Name:</th>
        <td>{{ this.$currentName.value }}</td>
      </tr>
      <th>New Name:</th>
      <td><input type="text" v-model="newName" /></td>
      <tr v-if="!showPassword">
        <th>New Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="newPassword"
          />
          <i
            @click="showPassword = !showPassword"
            class="fa fa-eye-slash fa_custom"
          ></i>
        </td>
      </tr>
      <tr v-if="showPassword">
        <th>New Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="newPassword"
          />
          <i
            @click="showPassword = !showPassword"
            class="fa fa-eye fa_custom"
          ></i>
        </td>
      </tr>

      <tr v-if="!showConfirmPassword">
        <th>Confirm Password:</th>
        <td>
          <input
            v-bind:type="[showConfirmassword ? 'text' : 'password']"
            v-model="confirmPassword"
          />
          <i
            @click="showConfirmPassword = !showConfirmPassword"
            class="fa fa-eye-slash fa_custom"
          ></i>
        </td>
      </tr>

      <tr v-if="showConfirmPassword">
        <th>Confirm Password:</th>
        <td>
          <input
            v-bind:type="[showConfirmPassword ? 'text' : 'password']"
            v-model="confirmPassword"
          />
          <i
            @click="showConfirmPassword = !showConfirmPassword"
            class="fa fa-eye fa_custom"
          ></i>
        </td>
      </tr>
    </table>
    <br />
    <br />
    <p>
      <span v-if="errorTechnicianAccount" style="color: red"
        >Error: {{ errorTechnicianAccount }}
      </span>
    </p>

    <button
      @click="goToViewTechnicianAccount()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>

    <button
      @click="updateTechnicianAccount(newPassword, confirmPassword, newName)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>Save</b></font>
    </button>
  </div>
</template>


<script  src="./editTechnicianAccount.js">
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
.fa_custom {
  color: #409eff;
}
</style>