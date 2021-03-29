<template>
    <div style="width: 1000px">
        <h3><center>Offered Service</center></h3>
        <tr><center>
                <td>
                    <input type="text" v-model="Id" placeholder="OfferedServiceId">
                    <input type="text" v-model="name" placeholder="OfferedServiceName">
                    <input type="text" v-model="duration" placeholder="Duration">
                    <input type="text" v-model="price" placeholder="Price">
                    <input type="text" v-model="comments" placeholder="Comments">
                    <input type="text" v-model="reminderDate" placeholder="ReminderDate">
                    <el-time-select v-model="reminderTime" :picker-options="{ start: '08:30', step: '00:15', end: '18:30'}"
                            placeholder="Pick The ReminderTime">
                    </el-time-select>
                </td>
                <td>
                    <button  @click="createOfferedService(Id,price,name,duration,reminderTime,reminderDate,comments)">Create OfferedService</button>
                    
                </td>
                
                </center>
                
        </tr>
        <!-- <p>
            <span v-if="errorPerson" style="color:red">Error: {{errorPerson}}</span>
        </p>
    -->
        <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column
            fixed
            prop="Id"
            label="Id"
            sortable
            width="100">
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
            prop="reminderTime"
            label="reminderTime"
            width="120">
            </el-table-column>
            <el-table-column
            fixed="right"
            label="operation"
            width="100">
            <template slot-scope="scope">
                <el-button @click="handleDelete(scope.$index, scope.row)" type="danger" size="small">delete</el-button>
                <el-button @click="handleEdit(scope.$index, scope.row), dialogFormVisible = true" type="text" size="small">edit</el-button>
            </template>
            </el-table-column>
        </el-table>
    <!-- delete dialogue -->
        <el-dialog title="CAUTION" :visible.sync="delVisible" width="295px" center>

            <div class="del-dialog-cnt"><center>Are you sure you want to delete this OfferedService?</center></div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">Cancel</el-button>
                <el-button type="primary" @click="deleteRow()">Comfirm</el-button>
            </span>
        </el-dialog>
    <!-- eidting dialogue-->
        <el-dialog title="EIDTING" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="Id" :label-width="formLabelWidth">
                    <el-input v-model="form.Id" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="name" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="duration" :label-width="formLabelWidth">
                    <el-input v-model="form.duration" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="price" :label-width="formLabelWidth">
                    <el-input v-model="form.price" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="comments" :label-width="formLabelWidth">
                    <el-input v-model="form.comments" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="reminderDate" :label-width="formLabelWidth">
                    <el-input v-model="form.reminderDate" autocomplete="off"></el-input>
                </el-form-item>

                <!-- <el-form-item label="reminderTime" :label-width="formLabelWidth">
                    <el-input v-model="form.reminderTime" autocomplete="off"></el-input>
                </el-form-item> -->
                <el-form-item label="reminderTime" :label-width="formLabelWidth">
                    <el-time-select v-model="form.reminderTime" :picker-options="{ start: '08:30', step: '00:15', end: '18:30'}"
                                placeholder="Pick The ReminderTime">
                    </el-time-select>
                </el-form-item>
                <!-- <el-form-item label="name" :label-width="formLabelWidth">
                    <el-select v-model="form.name" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click="Edit(form.Id,form.name,form.duration,form.price,form.comments,form.reminderDate,form.reminderTime), dialogFormVisible = false">Comfirm</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script src="./elementUITable.js">
</script>


<style>
  #ElementUITable{
    text-align: center;
  }
</style>
