<template>
  <div id="viewaccount">
    
    <!-- <span class="go-back"> -->
    <button @click="goBack" style="position: absolute; left: 0px">Go Back</button>
    <button @click="goToMainMenu" style="position: absolute; right: 0px">Main Menu</button> 
    <h1 style="color:#c30000;">View Account </h1>

    <br></br>

    	<table id="t01">
		  <tr>
		    <th>Username:</th>
		    <td>Placeholder username</td>
		  </tr>
		  <tr>
		    <th>Name:</th>
		    <td>Placeholder Name</td>
        </tr>
		</table>
    <br></br>

    <button @click="goToTest" onclick="" type="button" style="border-color:#a9a9a9; color: #a9a9a9;" class="btn ">
                <font size="3"><b>Delete Account</b></font>
    </button>
    <button @click="goToTest" onclick="" type="button" style="border-color:#c30000; color: #c30000;" class="btn ">
                <font size="3"><b>Edit Account</b></font>
    </button>
    <br></br>
     <button @click="goToTest" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Log Out</b></font>
    </button>
    <br></br>
     <!-- <button @click="goToTest"  onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Create Time Slot</b></font>
    </button>
     <button @click="goToTest"  onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>See Appointments</b></font>
    </button> -->
    <!-- <br></br> -->
  
  </div>
</template>

<script>
       export default{
           methods:{
            goToMainMenu(){
              this.$router.push("/app");
            },
            goToTest(){
              this.$router.push("/Test");
            },
            goBack(){
              this.$router.go(-1);
            },
            goToEditAccount(){
              this.$router.push("/editAccount");
            },
          }
       }
</script>

<style>
#viewaccount {
    margin-bottom: 0;
    margin-top: 0;
    /* background-color: #a9a9a9;
    color: #c30000; */
  }
  #t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}

</style>

