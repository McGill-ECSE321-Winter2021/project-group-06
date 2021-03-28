<template>
  <div id="viewaccount">
    
    <!-- <span class="go-back"> -->
    <button @click="goBack" style="position: absolute; left: 0px">Go Back</button>
    <button @click="goToMainMenu" style="position: absolute; right: 0px">Main Menu</button> 
    
    <h1 style="color:#c30000;">Your Account</h1>

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
    <button @click="goToEditAccount" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Manage Account</b></font>
    </button>
     <button @click="goToTest" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Delete Account</b></font>
    </button>
     <button @click="goToTest" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Log Off</b></font>
    </button>
    <h2></h2>
     <button @click="goToTest"  onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>Create Time Slot</b></font>
    </button>
     <button @click="goToTest"  onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>View Appointments</b></font>
    </button>
    <h3></h3>
    <button @click="open">Delete</button>
    <vue-modal-2 name="modal-1" @on-close="close"> Are you sure you want to delete this account?
    </vue-modal-2>
    

  </div>
</template>

<script>
       export default{
           methods:{
            goToMainMenu(){
              this.$router.push("/app");
            },
            goToEditAccount(){
              this.$router.push("/EditAccount");
            },
            goToTest(){
              this.$router.push("/Test");
            },
            goBack(){
              this.$router.go(-1);
            },
            open () {
              this.$vm2.open('modal-1')
            },
            close () {
              this.$vm2.close('modal-1')
            }
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
  width: 30%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}

</style>

