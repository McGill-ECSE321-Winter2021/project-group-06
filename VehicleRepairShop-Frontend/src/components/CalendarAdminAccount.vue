<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#/adminHome" style="color: black">VRSS</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
          <b-nav-item href="#/adminHome" color="white">Home</b-nav-item>
          <b-nav-item href="#/viewAdminAccount" color="white"
            >Profile</b-nav-item
          >
          <b-nav-item href="#/calendarAdmin" color="white">Calendar</b-nav-item>

          <b-nav-item href="#/offeredServiceTableAdmin"
            >Services and Pricing</b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <tr>
              <td>
                <input type="text" v-model="searchInput" placeholder="Search" />
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
    <br /><br />
    <Fullcalendar ref="calendar" :options="calendarOptions" />
    
    <!-- eidting dialogue-->
    <el-dialog title="EIDTING" :visible.sync="dialogFormVisible">
      <el-form :model="form">
         <el-form-item label="technicain" :label-width="formLabelWidth">
          <el-select v-model="value1" placeholder="select technicain">
            <el-option
              v-for="item in TechAccountOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

          <el-form-item label="OfferedService" :label-width="formLabelWidth">
            <el-select v-model="value2" placeholder="select OfferedService">
              <el-option
                v-for="item in OfferedServiceOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Car" :label-width="formLabelWidth">
            <el-select v-model="value3" placeholder="select Car">
              <el-option
                v-for="item in CarOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Garage" :label-width="formLabelWidth">
            <el-select v-model="value4" placeholder="select Garage">
              <el-option
                v-for="item in GarageOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="comments" :label-width="formLabelWidth">
            <el-input v-model="value5" autocomplete="off"></el-input>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button
          type="primary"
          @click="
            (dialogFormVisible = false),
              createAppointment(
                value5, 
                this.currentSelectedTimeslotId, 
                value3, 
                value4, 
                value2, 
                value1
              )
          "
          >Comfirm</el-button
        >
      </div>
    </el-dialog>

    <!-- delete dialogue -->
    <el-dialog title="CAUTION" :visible.sync="createAppointmentVisible" width="295px" center>
      <div class="del-dialog-cnt">
        <center>Are you sure you want to delete this OfferedService?</center>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delVisible = false">Cancel</el-button>
        <el-button type="primary" @click="deleteRow()">Comfirm</el-button>
      </span>
    </el-dialog>

    <modal-window
      :visible="isShowModal"
      :close-on-escape="true"
      :close-on-outside-click="true"
      :show-x-mark="true"
      @close="isShowModal = false"
    >
    </modal-window>
  </div>
</template>



<script>
import Fullcalendar from "@fullcalendar/vue";
import DayGridPlugin from "@fullcalendar/daygrid";
import TimeGridPlugin from "@fullcalendar/timegrid";
import InteractionPlugin from "@fullcalendar/interaction";
import axios from "axios";
import DropdownMenu from "@innologica/vue-dropdown-menu";
import ModalWindow from "@vuesence/modal-window";
var config = require("../../config");
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl },
});
export default {
  components: {
    Fullcalendar,
    ModalWindow,
    DropdownMenu,
  },
  computed: {},
  data() {
    return {
      isShowModal: false,

      TechAccountOptions: [],
      OfferedServiceOptions: [],
      CarOptions: [],
      GarageOptions: [],
      dialogFormVisible: false,
      
      value: '',


      allTechnicianAccounts: [],
      allOfferedServices: [],
      allCars: [],
      allGarages: [],



      calendarOptions: {
        plugins: [DayGridPlugin, TimeGridPlugin, InteractionPlugin],
        initialView: "timeGridWeek",
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "timeGridWeek,timeGridDay",
        },
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        droppable: true,
        eventOverlap: false,
        weekends: true,
        select: this.timeSlotSelected,
        eventClick: this.deleteOrUpdateAppointment,
        eventDrop: this.createTimeslot,
        allDaySlot: false,
        businessHours: [
          {
            daysOfWeek: [1, 2, 3, 4, 5],
            startTime: "09:00",
            endTime: "17:00",
          },
        ],
        selectConstraint: "businessHours",
        eventConstraint: "businessHours",
      },
      

      currentSelectedTimeslotId: "",
      // currentSelectedOwnerUsername: 1,
      // currentSelectedCarLicensePlate: 1,
      // curreantSelectedServiceId: 1,
      // currentSelectedGarageId: 1,
      // currentSelectedTechnicianUsername: 1,
      // currentSelectedAppointmentId: 1,
      selectedInfo: {},
      appointmentIdToDelete: 1,
      searchInput:'',
    };
  },

  // auto show existing appointments
  mounted() {
    let calendarApi = this.$refs.calendar.getApi();
    AXIOS.get("/getAllAppointment").then((response) => {
      console.log(response.data);
      for (var i = 0; i < response.data.length; i++) {
        let startStr =
          response.data[0].timeSlot.startDate +
          "T" +
          response.data[0].timeSlot.startTime;
        let endStr =
          response.data[0].timeSlot.endDate +
          "T" +
          response.data[0].timeSlot.endTime;
        let event = {
          id: response.data[0].appointmentId,
          title: response.data[0].offeredService.name,
          start: startStr,
          end: endStr,
        };
        console.log(event);
        calendarApi.addEvent(event);
      }
    });
  },


  methods: {
    // navigation bar
    goBack() {
      this.$router.go(-1);
    },
    searchButton(searchInput) {
      this.searchInput = "";
      if (searchInput === "Home") {
        this.$router.push("/adminHome");
      } else if (
        searchInput === "Services" ||
        searchInput === "services" ||
        searchInput === "Service" ||
        searchInput === "service"
      ) {
        this.$router.push("/offeredServiceTableAdmin");
      } else if (
        searchInput === "Profile" ||
        searchInput === "profile" ||
        searchInput === "Account" ||
        searchInput === "account"
      ) {
        this.$router.push("/viewAdminAccount");
      } else if (searchInput === "Calendar" || searchInput === "calendar") {
        this.$router.push("/calendarAdmin");
      } else {
        this.searchInput = "";
        console.log("Not Found");
      }
    },


    refresh(){
      var i
      var numTechAccounts = this.TechAccountOptions.length
      var numOfferedServices = this.OfferedServiceOptions.length
      var numCars = this.CarOptions.length
      var numGarages = this.GarageOptions.length

      for (i=0;i<numTechAccounts;i++){
        this.TechAccountOptions.splice(0, 1)
      }

      for (i=0; i<numOfferedServices;i++){
        this.OfferedServiceOptions.splce(0, 1)
      }

      for (i=0; i<numCars;i++){
        this.CarOptions.splce(0, 1)
      }

      for (i=0; i<numGarages;i++){
        this.GarageOptions.splce(0, 1)
      }
      
      for (let ta in this.allTechnicianAccounts){
        if (ta.username !== emptyStatement) {
            var temp = {
                value: ta.username,
                label: ta.username,
            }
            this.techAccountOptions.push(temp)
        }
      }

      for (let os in this.allOfferedServices){
        if (os.Id !== emptyStatement) {
            var temp = {
                value: os.Id,
                label: os.Id,
            }
            this.OfferedServiceOptions.push(temp)
        }
      }

      for (let car in this.allCars){
        if (car.licensePlate !== emptyStatement) {
            var temp = {
              value: car.licensePlate,
              label: car.licensePlate,
            }
            this.CarOptions.push(temp)
        }
      }

      for (let g in this.allGarages){
        if (g.Id !== emptyStatement) {
            var temp = {
              value: g.Id,
              label: g.Id,
            }
            this.GarageOptions.push(temp)
        }
      }

    },

    // backend
    async updateAppointmentTimeslot(appointmentId, timeslotId) {
      console.log("update timeslot");
      const response = await AXIOS.put(
        "/updateAppointmentTimeSlot/" +
          parseInt(appointmentId) +
          "/" +
          parseInt(timeslotId)
      );
      console.log(response.data);
    },

    async createTimeslot(info) {
      let startTime = info.event.startStr.substring(11, 19);
      let startDate = info.event.startStr.substring(0, 10);
      let endTime = info.event.endStr.substring(11, 19);
      let endDate = info.event.endStr.substring(0, 10);
      let appointmentId = info.event.id;
      var c = confirm(
        "are you sure you want to change the timeslot of the appointment?"
      );
      if (c == true) {
        const response = await AXIOS.post(
          "/createTimeSlot/" +
            startTime +
            "/" +
            endTime +
            "/" +
            startDate +
            "/" +
            endDate
        );
        // console.log(response.data)
        this.updateAppointmentTimeslot(appointmentId, response.data.timeslotId);
      }
    },


    async deleteOrUpdateAppointment(selectionInfo) {
      var choice = prompt("do you want to update or delete appointment?");
      var c = confirm("are you sure you want to delete this appointment?");
      if (c == true) {
        let appointment = selectionInfo.event;
        let appointmentId = selectionInfo.event.id;
        this.appointmentIdToDelete = parseInt(appointmentId);
        this.deleteAppointment;
        appointment.remove();
        const response = await AXIOS.delete(
          "/deleteAppointmentById/" + this.appointmentIdToDelete
        );
        console.log(response.data);
      }
    },

    toggleShowModal() {
      this.isShowModal = true;
      console.log(this.isShowModal);
    },




    async getAllTechnicianAccounts(){
      try{
        this.allTechnicianAccounts = await AXIOS.get("/getAllTechnicianAccounts");
      }catch (error) {
        console.error(error);
      }finally{
        console.log("get all Technicain accounts");
      }
    },


    async getAllOfferedService(){
      try{
        this.allOfferdServices = await AXIOS.get("/getAllOfferedServices");
      }catch (error) {
        console.error(error);
      }finally{
        console.log("get all OfferedServices");
      }
    },

    async getAllgetAllCars(){
      try{
        this.allCars = await AXIOS.get("/getAllCars");
      }catch (error) {
        console.error(error);
      }finally{
        console.log("get all Cars");
      }
    },

    async getAllGarages(){
      try{
        this.allGarages = await AXIOS.get("/getAllGarages");
      }catch (error) {
        console.error(error);
      }finally{
        console.log("get all garages");
      }
    },


    async timeSlotSelected(selectionInfo) {
      this.selectedInfo = selectionInfo;
      console.log(selectionInfo.view.calendar);
      try {
        let startTime =
          selectionInfo.start.getHours().toString() +
          ":" +
          selectionInfo.start.getMinutes().toString() +
          ":" +
          selectionInfo.start.getSeconds().toString();
        let endTime =
          selectionInfo.end.getHours().toString() +
          ":" +
          selectionInfo.end.getMinutes().toString() +
          ":" +
          selectionInfo.end.getSeconds().toString();
        let startDate =
          selectionInfo.start.getFullYear().toString() +
          "-" +
          (selectionInfo.start.getMonth() + 1).toString() +
          "-" +
          selectionInfo.start.getDate().toString();
        let endDate =
          selectionInfo.end.getFullYear().toString() +
          "-" +
          (selectionInfo.end.getMonth() + 1).toString() +
          "-" +
          selectionInfo.end.getDate().toString();
        var appCreate = confirm("Do you want to make an appointment?")
        if (appCreate == true) {
          console.log("1");
          // once selected a timeslot, show modal
          // this.toggleShowModal();
          this.refresh()
          this.dialogFormVisible = true
          const response = await AXIOS.post(
            "/createTimeSlot/" +
              startTime +
              "/" +
              endTime +
              "/" +
              startDate +
              "/" +
              endDate
          )
          this.currentSelectedTimeslotId = response.data.timeslotId;
          console.log(this.currentSelectedTimeslotId);
          //this.createService();
        }
      } catch (error) {
        console.error(error);
      } finally {
        console.log("get all timeslot");
      }
      
    },

    async createGarage() {
      try {
        const response = await AXIOS.post("/createGarage/garageId1");
        this.currentSelectedGarageId = response.data.garageId;
        // console.log(this.currentSelectedGarageId);
      } catch (error) {
        console.error(error);
      } finally {
        console.log("create garage: " + this.currentSelectedGarageId);
      }
    },


    async createAppointment(comment, timeslotId, licensePlate, garageId, offeredServiceId, technicianUsername) {
      try {
        const response = await AXIOS.post(
          "/createAppointment/" +
            comment + 
            "/" +
            timeslotId +
            "/" +
            licensePlate +
            "/" +
            garageId +
            "/" +
            offeredServiceId +
            "/" +
            technicianUsername
        );

        this.currentSelectedAppointmentId = response.data.appointmentId;
        let calendarApi = this.selectedInfo.view.calendar;
        calendarApi.unselect();
        calendarApi.addEvent({
          id: response.data.appointmentId,
          title: "service: " + response.data.offeredService.name + "licensePlate: " + response.data.car.licensePlate,
          start: this.selectedInfo.startStr,
          end: this.selectedInfo.endStr,
          allDay: this.selectedInfo.allDay,
        });
      } catch (error) {
        console.error(error);
      } finally {
        console.log("create appointment: " + this.currentSelectedAppointmentId);
      }
    },



    // async createAppointment() {
    //   try {
    //     const response = await AXIOS.post(
    //       "/createAppointment/comment1/" +
    //         this.currentSelectedTimeslotId +
    //         "/" +
    //         this.currentSelectedCarLicensePlate +
    //         "/" +
    //         this.currentSelectedGarageId +
    //         "/" +
    //         this.curreantSelectedServiceId +
    //         "/" +
    //         this.currentSelectedTechnicianUsername
    //     );
    //     this.currentSelectedAppointmentId = response.data.appointmentId;
    //     // console.log(this.currentSelectedAppointmentId);
    //     // this.newlyAddedAppointment = response.data
    //     // console.log(this.newlyAddedAppointment)
    //     let calendarApi = this.selectedInfo.view.calendar;
    //     calendarApi.unselect();
    //     calendarApi.addEvent({
    //       id: response.data.appointmentId,
    //       title: response.data.offeredService.name,
    //       start: this.selectedInfo.startStr,
    //       end: this.selectedInfo.endStr,
    //       allDay: this.selectedInfo.allDay,
    //     });
    //   } catch (error) {
    //     console.error(error);
    //   } finally {
    //     console.log("create appointment: " + this.currentSelectedAppointmentId);
    //   }
    // },
  },
};
</script>

<style>
.navbar.navbar-dark.bg-dark {
  background-color: #409eff !important;
}
nav .navbar-nav li a {
  color: white !important;
}
</style>