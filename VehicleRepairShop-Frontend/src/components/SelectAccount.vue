<template>
  <div class="selectAccount">
      <br><br/>
    <h1>Select your account type</h1>
    <br><br/>
    	<button @click="goToAdminAccountLogin" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>I am an Admin</b></font>
        </button>	
        <button @click="goToCustomerAccountLogin" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>I am a Customer</b></font>
        </button>	
        <button @click="goToTechnicianAccountLogin" type="button" style="background-color:#409EFF; color: white;" class="btn ">
            <font size="3"><b>I am a Technician</b></font>
        </button>	
  </div>
</template>

<script>
 export default{
           methods:{
            goToAdminAccountLogin(){
              this.$router.push("/adminAccountLogin");
            },
            goToCustomerAccountLogin(){
              this.$router.push("/customerAccountLogin");
            },
            goToTechnicianAccountLogin(){
              this.$router.push("/technicianAccountLogin");
            }
          }
       }
</script>

