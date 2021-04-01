<template>
  <div class="adminAccountSignUp">
    <br><br/>

    <h1 style="color: #409EFF">Sign Up</h1>
    <h4 style="color: #000000">Create your account</h4>
    <br><br/>

    <table id="t01">
      <tr>
        <th>Username:</th>
        <td><input type="text" v-model="username" /></td>
      </tr>

      <tr>
        <th>Name:</th>
        <td><input type="text" v-model="name" /></td>
      </tr>

      <tr>
        <th>Password:</th>
        <td><input type="password" v-model="password" /></td>
      </tr>
    </table>

    <!-- <br /><br />
    <h4>Select your account type</h4>
    <select v-model="selected">
      <option disabled value="">Select your account type</option>
      <option>Admin Account</option>
      <option>Customer Account</option>
      <option>Technician Account</option>
    </select> -->

    <br><br/>
    <p>
      <span v-if="errorAdminAccount" style="color: red">Error: {{ errorAdminAccount }}
    </span>
    </p>

    <button
      @click="goToAdminAccountLogin()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>
    <button
      @click="createAdminAccount(username, password, name)"
      type="button"
      style="background-color: #409EFF; color: white"
      class="btn"
    >
      <font size="3"><b>Sign Up</b></font>
    </button>

    <br><br/>
  </div>
</template>


<script src="./adminAccountSignUp.js">
// import axios from 'axios'

// var config = require('../../config')

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

// var AXIOS = axios.create({
//     baseURL: backendUrl,
//     headers: { 'Access-Control-Allow-Origin': frontendUrl }
//   })

//        export default{

//            methods:{
//             goToMainMenu(){
//               this.$router.push("/app");
//             },
//             goBack(){
//               this.$router.go(-1);
//             },
//             goToLogin() {
//               this.$router.push("/login");
//             },
//             checkPassword: function (password, confirmPassword){
//               if (password == confirmPassword) {
//                 console.log("the passwords match");
//               }
//               else {
//                 console.log("they dont");
//               }
//             }
//           }

//        }
</script>

<style>

#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>