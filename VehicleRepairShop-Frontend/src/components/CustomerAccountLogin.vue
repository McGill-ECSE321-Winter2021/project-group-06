<template>
  <div class="customerAccountLogin">
    <br><br/>

    	<h1 style="color:#409EFF;">Welcome to our Vehicle Repair Shop App</h1>
    	<h4 style="color:#000000;">Log into your account</h4>
        <br><br/>
 
      <table id="t01">
          <th>Username:</th>
          <td><input type="text" v-model="username"></td>
        <tr>
          <th>Password:</th>
          <td><input type="password" v-model="password"></td>
        </tr>
      </table>

      <p>
        <span v-if="errorCustomerAccount" style="color: red">Error: {{ errorCustomerAccount }}
        </span>
      </p>

		
  		<button @click="loginCustomerAccount(username, password)" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>Log in</b></font>
        </button>	
        <br><br/>
        <b-link href="#/customerAccountSignUp">Do not have an account? Sign up!</b-link>
    </div>
</template>


<script src="./customerAccountLogin.js">
</script>

<style>
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}

</style>