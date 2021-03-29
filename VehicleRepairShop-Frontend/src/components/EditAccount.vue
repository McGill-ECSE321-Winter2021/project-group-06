<template>
  <div class="editaccount">
   		<button @click="goBack" style="position: absolute; left: 0px">Go Back</button>
    	<button @click="goToMainMenu" style="position: absolute; right: 0px">Main Menu</button> 
    	
    	<h1 style="color:#c30000;">Manage Your Account</h1>
    	<h4 style="color:#000000;">Here you can change your name and password</h4>
        <br></br>
 
		<table id="t01">
		  <tr>
		    <th>Username:</th>
		    <td>Placeholder username</td>
		  </tr>
		  <tr>
		    <th>Current Name:</th>
		    <td>Placeholder Name</td>
		  </tr>
		    <th>New Name:</th>
		    <td><input type="text" id="newName" name="newName"></td>
		  <tr>
		  	<th>New Password:</th>
		  	<td><input type="text" id="newPassword" name="newPassword"></td>
		  </tr>
		  <tr>  
		    <th>Confirm Password:</th>
		    <td><input type="text" id="confirmPassword" name="confirmPassword"></td>
		  </tr>
		</table>
        <br></br>
		
		<button @click="goToViewAccount" type="button" style="border-color:#a9a9a9; color: #a9a9a9;" class="btn ">
            <font size="3"><b>Cancel</b></font>
        </button>
  		<button @click="goToViewAccount" type="button" style="background-color:#c30000; color: white;" class="btn ">
            <font size="3"><b>Save</b></font>
        </button>	
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
#editaccount {
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