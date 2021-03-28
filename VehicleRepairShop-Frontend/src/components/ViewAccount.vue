<template>
  <div id="viewaccount">
      
    <h1 style="color:#c30000;">View Account </h1>


    <button @click="goToTest" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
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
                <font size="3"><b>See Appointments</b></font>
    </button>
    <table>

        <h3 style="color:#c30000;" >Username: </h3>
        <h3 style="color:#c30000;">Name: </h3>
    </table>

  </div>
</template>

<script>
       export default{
           methods:{
               goToTest(){
                   this.$router.push("/Test");
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

</style>

