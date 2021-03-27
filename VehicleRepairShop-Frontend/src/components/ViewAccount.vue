<template>
  <div id="viewaccount">
      
    <h1 style="color:red">View Account </h1>
    <button v-bind:class="{'white': !clicked, 'blue': clicked}">Manage Account </button>
    <button>Delete Account</button>
    <button>Log off</button>
    <h2></h2>
    <button>Create Time Slot</button>
    <button>See Appointments</button>
    <table>
        <h3>Username: </h3>
        <h3>Name: </h3>
    </table>

  </div>
</template>

<script>

</script>

<style>
#viewaccount {
    margin-bottom: 0;
    margin-top: 0;
    /* background-color: #a9a9a9;
    color: #c30000; */
  }

</style>

