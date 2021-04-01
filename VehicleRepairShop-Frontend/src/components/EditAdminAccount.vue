<template>
  <div class="editAdminAccount">
   		<br><br/>
    	<h1 style="color:#409EFF;">Manage Your Account</h1>
    	<h4 style="color:#000000;">Here you can change your name and password</h4>
        <!-- <br></br> -->
 
		<table id="t01">
		  <tr>
		    <th>Username:</th>
		    <td>{{ this.$currentUsername.value }}</td>
		  </tr>
		  <tr>
		    <th>Current Name:</th>
		    <td>{{ this.$currentName.value }}</td>
		  </tr>
		    <th>New Name:</th>
		    <td><input type="text" v-model="newName"></td>
		  <tr>
		  	<th>New Password:</th>
		  	<td><input type="text" v-model="newPassword"></td>
		  </tr>
		</table>
		<br> <br/>
		<p>
      		<span v-if="errorAdminAccount" style="color: red">Error: {{ errorAdminAccount }}
    		</span>
    	</p>

		<button @click="goToViewAdminAccount()" type="button" style="border-color:#909399; color: #909399;" class="btn ">
            <font size="3"><b>Cancel</b></font>
        </button>

  		<button @click="updateAdminAccount(newPassword, newName)" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>Save</b></font>
        </button>	
  </div>
</template>


<script  src="./editAdminAccount.js">
</script>

<style>

#t01 {
  width: 40%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}

</style>