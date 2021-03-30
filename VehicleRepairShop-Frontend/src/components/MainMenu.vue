<template>
  <div id="mainmenu">
      
    <h1 style="color:#409EFF;">Main Menu </h1>


    <button @click="goToViewAccount" onclick="" type="button" style="background-color:#909399; color: white;" class="btn ">
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
    /* background-color: #909399;
    color: #409EFF; */
  }

</style>

