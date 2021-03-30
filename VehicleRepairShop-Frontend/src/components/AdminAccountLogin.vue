<template>
  <div class="adminAccountLogin">
    <br><br/>

    	<h1 style="color:#409EFF;">Welcome to our Vehicle Repair Shop App</h1>
    	<h4 style="color:#000000;">Log into your account</h4>
        <br></br>
 
		<table id="t01">
		    <th>Username:</th>
		    <td><input type="text" v-model="username" name="username"></td>
		  <tr>
		  	<th>Password:</th>
		  	<td><input type="text" v-model="password" name="password"></td>
		  </tr>
		</table>
        <br></br>
		
  		<button @click="loginAdminAccount(username, password)" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>Log in</b></font>
        </button>	
        <br></br>
        <b-link href="#/signUp">Do not have an account? Sign up!</b-link>
  </div>
</template>


<script src="./adminAccountLogin.js">
      //  export default{
      //      methods:{
      //       goToMainMenu(){
      //         this.$router.push("/app");
      //       },
      //       goBack(){
      //         this.$router.go(-1);
      //       },
      //       goToViewAccount() {
      //         this.$router.push("/ViewAccount");
      //       }
      //     }
      //  }
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