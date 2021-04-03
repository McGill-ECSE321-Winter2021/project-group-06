<template>
  <div class="adminAccountLogin">
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#/" style="color: black"
            >VRSS</b-navbar-brand
          >

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item href="#/" style="color: white"
                >Go Back</b-nav-item
              >
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    <br><br/>

    	<h1 style="color:#409EFF;">Welcome to our Vehicle Repair Shop App</h1>
    	<h4 style="color:#000000;">Log into your account</h4>
        <br><br/>


      <table id="t01">
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
          <th>Username:</th>
          <td><input type="text" v-model="username"></td>
        <tr v-if="!showPassword">
          <th>Password:</th>
          <td><input v-bind:type="[showPassword ? 'text' : 'password']" v-model="password">
          <i @click="showPassword =!showPassword" class="fa fa-eye-slash fa_custom"></i>
          </td>
        </tr>
        <tr v-if="showPassword">
          <th>Password:</th>
          <td><input v-bind:type="[showPassword ? 'text' : 'password']" v-model="password">
          <i @click="showPassword =!showPassword" class="fa fa-eye fa_custom"></i>
          </td>
        </tr>
      </table>

      <p>
        <span v-if="errorAdminAccount" style="color: red">Error: {{ errorAdminAccount }}
        </span>
      </p>

		
  		<button @click="loginAdminAccount(username, password)" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>Log in</b></font>
        </button>	
        <br><br/>
        <b-link href="#/adminAccountSignUp">Do not have an account? Sign up!</b-link>
    </div>
</template>


<script src="./adminAccountLogin.js">
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
color: #409eff
}
</style>