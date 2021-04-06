<template>
  <div>
    <div>
      <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#/adminHome" style="color: black"
          >VRSS</b-navbar-brand
        >

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item @click="goBack" style="color: white"
              >Go Back</b-nav-item
            >
            <b-nav-item href="#/adminHome" color="white">Home</b-nav-item>
            <b-nav-item href="#/viewAdminAccount" color="white"
              >Profile</b-nav-item
            >
            <b-nav-item href="#/calendarAdmin" color="white"
              >Calendar</b-nav-item
            >

            <b-nav-item href="#/adminBusinessInfo" color="white"
              >Business Information</b-nav-item
            >

            <b-nav-item href="#/offeredServiceTableAdmin"
              >Services and Pricing</b-nav-item
            >
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-form>
              <tr>
                <td>
                  <input
                    type="text"
                    v-model="searchInput"
                    placeholder="Search"
                  />
                </td>
              </tr>
              <b-button
                @click="searchButton(searchInput)"
                size="sm"
                class="my-2 my-sm-0"
                type="submit"
                style="background-color: #909399; color: white"
                >Search</b-button
              >
            </b-nav-form>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <br /><br />
    <h1><center style="color: #409eff">Offered Service</center></h1>
    <tr>
      <center>
        <td>
          <input type="text" v-model="Id" placeholder="OfferedServiceId" />
          <input type="text" v-model="name" placeholder="OfferedServiceName" />
          <input type="text" v-model="duration" placeholder="Duration" />
          <input type="text" v-model="price" placeholder="Price" />
          <input type="text" v-model="comments" placeholder="Comments" />
          <input
            type="text"
            v-model="reminderDate"
            placeholder="ReminderDate"
          />
          <el-time-select
            v-model="reminderTime"
            :picker-options="{ start: '08:30', end: '18:30' }"
            placeholder="Pick The ReminderTime"
          >
          </el-time-select>
        </td>
        <td>
          <button
            @click="initializeTable()"
            type="button"
            style="border-color: #909399; color: #909399; font-size: 10%"
            class="btn"
          >
            <font size="3"><b>Initialize OfferedService</b></font>
          </button>
        </td>
        <td>
          <button
            @click="
              createOfferedService(
                Id,
                price,
                name,
                duration,
                reminderTime + ':00',
                reminderDate,
                comments
              )
            "
            type="button"
            style="background-color: #409eff; color: white"
            class="btn"
          >
            <font size="3"><b> Create OfferedService</b></font>
          </button>
        </td>
      </center>
    </tr>
    <p>
      <span v-if="errorOfferedService" style="color: red"
        >Error: {{ errorOfferedService }}</span
      >
    </p>
    <center>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column fixed prop="Id" label="Id" sortable style="width: 12%">
        </el-table-column>
        <el-table-column prop="name" label="Name" style="width: 12%">
        </el-table-column>
        <el-table-column prop="duration" label="Duration" style="width: 12%">
        </el-table-column>
        <el-table-column prop="price" label="Price" style="width: 12%">
        </el-table-column>
        <el-table-column prop="comments" label="Comments" style="width: 12%">
        </el-table-column>
        <el-table-column
          prop="reminderDate"
          label="Reminder Date"
          style="width: 12%"
        >
        </el-table-column>
        <el-table-column
          prop="reminderTime"
          label="Reminder Time"
          style="width: 12%"
        >
        </el-table-column>
        <el-table-column fixed="right" label="Operation" style="width: 12%">
          <template slot-scope="scope">
            <el-button
              @click="handleDelete(scope.$index, scope.row)"
              onclick=""
              type="button"
              style="border-color: red; color: red"
              class="btn"
            >
              <font size="3"><b>Delete</b></font></el-button
            >
            <el-button
              @click="
                handleEdit(scope.$index, scope.row), (dialogFormVisible = true)
              "
              type="text"
              size="small"
              >edit</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </center>
    <!-- delete dialogue -->
    <el-dialog title="CAUTION" :visible.sync="delVisible" width="295px" center>
      <div class="del-dialog-cnt">
        <center>Are you sure you want to delete this OfferedService?</center>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">Cancel</el-button>
        <el-button type="primary" @click="deleteRow()">Confirm</el-button>
      </span>
    </el-dialog>
    <!-- eidting dialogue-->
    <el-dialog :visible.sync="dialogFormVisible">
      <h4>Editing</h4>
      <h6 style="color: red">Please, fill all the boxes!</h6>
      <br />
      <el-form :model="form">
        <el-form-item label="Id" :label-width="formLabelWidth">
          <el-input
            v-model="form.Id"
            autocomplete="off"
            readonly="true"
          ></el-input>
        </el-form-item>

        <el-form-item label="Name" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="Duration" :label-width="formLabelWidth">
          <el-input v-model="form.duration" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="Price" :label-width="formLabelWidth">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="Comments" :label-width="formLabelWidth">
          <el-input v-model="form.comments" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="Reminder Date" :label-width="formLabelWidth">
          <el-input v-model="form.reminderDate" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="Reminder Time" :label-width="formLabelWidth">
          <el-time-select
            v-model="form.reminderTime"
            :picker-options="{ start: '08:30', end: '18:30' }"
            placeholder="Pick The ReminderTime"
            format="HH:mm:ss"
          >
          </el-time-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button
          type="primary"
          @click="
            (dialogFormVisible = false),
              Edit(
                form.Id,
                form.name,
                form.duration,
                form.price,
                form.comments,
                form.reminderDate,
                form.reminderTime + ':00'
              )
          "
          >Confirm</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script src="./elementUITableAdmin.js">
</script>


<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
#ElementUITable {
  text-align: center;
}
</style>