<template>
  <div id="mainmenu">
      
    <h1 style="color:#c30000;">Main Menu </h1>


    <button @click="goToViewAccount" onclick="" type="button" style="background-color:#a9a9a9; color: white;" class="btn ">
                <font size="3"><b>View Account</b></font>
    </button>

  </div>
</template>

<script>
       export default{
           methods:{
               goToViewAccount(){
                   this.$router.push("/ViewAccount");
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

