<template>
  <div class="login">
   		<button @click="goBack" style="position: absolute; left: 0px">Go Back</button>
    	<button @click="goToMainMenu" style="position: absolute; right: 0px">Main Menu</button> 
    	
    	<h1 style="color:#c30000;">Welcome to our Vehicle Repair Shop App</h1>
    	<h4 style="color:#000000;">Log into your account</h4>
        <br></br>
 
		<table id="t01">
		    <th>Username:</th>
		    <td><input type="text" id="username" name="username"></td>
		  <tr>
		  	<th>Password:</th>
		  	<td><input type="text" id="password" name="password"></td>
		  </tr>
		</table>
        <br></br>
		
  		<button @click="goToMainMenu" type="button" style="background-color:#c30000; color: white;" class="btn ">
            <font size="3"><b>Log in</b></font>
        </button>	
        <br></br>
        <b-link href="#/Test">Do not have an account? Sign up!</b-link>
  </div>
</template>


<script>
       export default{
           methods:{
            goToMainMenu(){
              this.$router.push("/app");
            },
            goBack(){
              this.$router.go(-1);
            },
            goToViewAccount() {
              this.$router.push("/ViewAccount");
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