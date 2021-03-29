<template>
  <div class="login">
   		<button @click="goBack" style="position: absolute; left: 0px">Go Back</button>
    	<button @click="goToMainMenu" style="position: absolute; right: 0px">Main Menu</button> 
    	
    	<h1 style="color:#c30000;">Sign Up</h1>
    	<h4 style="color:#000000;">Create your own account</h4>
        <br></br>
 
		<table id="t01">
		  <tr>
		   	<th>Username:</th>
		    <td><input type="text" id="username" name="username"></td>
		  <tr>

		    <th>Name:</th>
		    <td><input type="text" id="name" name="name"></td>
		  <tr>
		  	<th>Password:</th>
		  	<td><input type="text" id="password" name="password"></td>
		  </tr>
		  <tr>  
		    <th>Confirm Password:</th>
		    <td><input type="text" id="confirmPassword" name="confirmPassword"></td>
		  </tr>
		</table>

        <br></br>
        <h4>Select your account type</h4>
        <select v-model="selected">
            <option disabled value="">Select your account type</option>
            <option>Admin Account</option>
            <option>Customer Account</option>
            <option>Technician Account</option>
        </select>
        
        <br></br>
		
        <button @click="goToLogin" type="button" style="border-color:#a9a9a9; color: #a9a9a9;" class="btn ">
            <font size="3"><b>Cancel</b></font>
        </button>
  		<button @click="goToMainMenu" type="button" style="background-color:#c30000; color: white;" class="btn ">
            <font size="3"><b>Sign Up</b></font>
        </button>	
        <br></br>
  </div>
</template>


<script>
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

       export default{
           methods:{
            goToMainMenu(){
              this.$router.push("/app");
            },
            goBack(){
              this.$router.go(-1);
            },
            goToLogin() {
              this.$router.push("/login");
            }
          }


          
       }
</script>

<style>
#login {
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