<template>
  <div style="width: 1000px"
  id = "eventregistration">
    <h3><center>Offered Service</center></h3>
    <tr><center>
            <td>
                <input type="text" v-model="Id" placeholder="OfferedServiceId">
                <input type="text" v-model="name" placeholder="OfferedServiceName">
                <input type="text" v-model="duration" placeholder="Duration">
                <input type="text" v-model="price" placeholder="Price">
                <input type="text" v-model="comments" placeholder="Comments">
                <input type="text" v-model="reminderDate" placeholder="ReminderDate">
                <input type="text" v-model="reminderTime" placeholder="ReminderTime">
            </td>
            <td>
                <button  @click="createOfferedService(Id,price,name,duration,reminderTime,reminderDate,comments)">Create OfferedService</button>
                
            </td>
            <td> 
                <button  @click="deleteOfferedService()">Select Delete</button>
            </td>
            </center>
            
    </tr>
  
    <vue-table-dynamic 
      :params="params" 
      ref="table"
    >
    </vue-table-dynamic>
    <p>
        <span v-if="errorPerson" style="color:red">Error: {{errorPerson}}</span>
    </p>
  </div>

</template>

<script>


</script>

<script src="./createOfferedService.js" >
</script>

<style>
  #OfferedService{
    text-align: center;
  }
</style>