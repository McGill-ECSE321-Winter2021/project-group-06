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
            </center>
            
    </tr>
    <p>
        <span v-if="errorPerson" style="color:red">Error: {{errorPerson}}</span>
    </p>
  
    <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
        fixed
        prop="Id"
        label="Id"
        width="150">
        </el-table-column>
        <el-table-column
        prop="name"
        label="name"
        width="120">
        </el-table-column>
        <el-table-column
        prop="duration"
        label="duration"
        width="120">
        </el-table-column>
        <el-table-column
        prop="price"
        label="price"
        width="120">
        </el-table-column>
        <el-table-column
        prop="comments"
        label="comments"
        width="300">
        </el-table-column>
        <el-table-column
        prop="reminderDate"
        label="reminderDate"
        width="120">
        </el-table-column>
        <el-table-column
        fixed="reminderTime"
        label="reminderTime"
        width="100">
        <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
        </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      }
    },

    data() {
      return {
        tableData: [{
          OfferedServiceId: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1517 弄',
          zip: 200333
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1519 弄',
          zip: 200333
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        }]
      }
    }
  }
</script>


<style>
  #ElementUITable{
    text-align: center;
  }
</style>