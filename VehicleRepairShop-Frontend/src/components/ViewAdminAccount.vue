<template>
  <div id="viewAdminAccount">
        <template>
      <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#">VRSS</b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item @click="goBack" style="color: white"
                >Go Back</b-nav-item
              >
              <b-nav-item href="#/app" color="white">Home</b-nav-item>
			  <b-nav-item href="#/viewAdminAccount" color="white">Profile</b-nav-item>
              <b-nav-item href="#" disabled>Services and Pricing</b-nav-item>
              <b-nav-item href="#" disabled>Information</b-nav-item>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
              <b-nav-form>
                <b-form-input
                  size="sm"
                  class="mr-sm-2"
                  placeholder="Search"
                ></b-form-input>
                <b-button
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
                <!-- <b-dropdown-item href="#/viewAdminAccount" style="background-color: #909399">Profile</b-dropdown-item> -->
                <!-- <b-dropdown-item href="#" style="background-color: #909399">>Sign Out</b-dropdown-item> -->
              <!-- </b-nav-item-dropdown>  -->
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    </template> 

    <br><br/>
    <h1 style="color:#409EFF;">View Account </h1>

    <br><br/>

    	<table id="t01">
		  <tr>
		    <th>Username:</th>
		    <td>{{ username }}</td>
		  </tr>
		  <tr>
		    <th>Name:</th>
		    <td>{{ name }}</td>
        </tr>
		</table>
    <br><br/>

    <button @click="deleteAdminAccount(username)" onclick="" type="button" style="border-color:#909399; color: #909399;" class="btn ">
                <font size="3"><b>Delete Account</b></font>
    </button>
    <button @click="goToEditAdminAccount" onclick="" type="button" style="border-color:#409EFF; color: #409EFF;" class="btn ">
                <font size="3"><b>Manage Account</b></font>
    </button>
    <br><br/>
     <button @click="logoutAdminAccount(username)" type="button" style="background-color:#909399; color: white;" class="btn ">
                <font size="3"><b>Log Out</b></font>
    </button>
    <br><br/>

  </div>
</template>

<script src="./viewAdminAccount.js">

</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409EFF!important;
}
nav .navbar-nav li a {
  color: white !important;
}
#viewaccount {
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

