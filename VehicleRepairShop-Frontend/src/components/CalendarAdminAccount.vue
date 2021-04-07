<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#/adminHome" style="color: black"
        >VRSS</b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
          <b-nav-item href="#/adminHome" color="white">Home</b-nav-item>
          <b-nav-item href="#/viewAdminAccount" color="white"
            >Profile</b-nav-item
          >
          <b-nav-item href="#/calendarAdmin" color="white">Calendar</b-nav-item>

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
    <br/>
    <h1 style="color: #409eff">Calendar</h1>
    <br/>
    <Fullcalendar ref="calendar" :options="calendarOptions" />

    <el-dialog title="EDITING" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="technicain" :label-width="formLabelWidth">
          <el-select v-model="form.value1" placeholder="select technicain">
            <el-option
              v-for="item in this.techAccountOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="OfferedService" :label-width="formLabelWidth">
          <el-select v-model="form.value2" placeholder="select OfferedService">
            <el-option
              v-for="item in this.offeredServiceOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Car" :label-width="formLabelWidth">
          <el-select v-model="form.value3" placeholder="select Car">
            <el-option
              v-for="item in this.carOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Garage" :label-width="formLabelWidth">
          <el-select v-model="form.value4" placeholder="select Garage">
            <el-option
              v-for="item in this.garageOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="comments" :label-width="formLabelWidth">
          <el-input v-model="form.value5" type="text"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button
          type="primary"
          @click="
            (dialogFormVisible = false),
              createAppointment(
                form.value5,
                currentSelectedTimeslotId,
                form.value3,
                form.value4,
                form.value2,
                form.value1
              )
          "
          >Confirm</el-button
        >
        <p>
          <span v-if="errorService" style="color: red"
            >Error: {{ errorService }}</span
          >
        </p>
      </div>
    </el-dialog>
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
      errorService: "",

      techAccountOptions: [],
      offeredServiceOptions: [],
      carOptions: [],
      garageOptions: [],
      dialogFormVisible: false,
      form: {
        value1: "",
        value2: "",
        value3: "",
        value4: "",
        value5: "",
      },

      allTechnicianAccounts: [],
      allOfferedServices: [],
      allCars: [],
      allGarages: [],

      formLabelWidth: "120px",

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
      selectedInfo: {},
      appointmentIdToDelete: 1,
      searchInput: "",
    };
  },

  // auto show existing appointments
  mounted() {
    let calendarApi = this.$refs.calendar.getApi();
    AXIOS.get("/getAllAppointment").then((response) => {
      console.log(response.data);
      for (var i = 0; i < response.data.length; i++) {
        let startStr =
          response.data[i].timeSlot.startDate +
          "T" +
          response.data[i].timeSlot.startTime;
        let endStr =
          response.data[i].timeSlot.endDate +
          "T" +
          response.data[i].timeSlot.endTime;
        let event = {
          id: response.data[i].appointmentId,
          title:
            "Service: " +
            response.data[i].offeredService.name + '\n'+
            "|| License Plate: " +
            response.data[i].car.licensePlate,
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
      if (
        searchInput === "Home" ||
        searchInput === "home" ||
        searchInput === "Main" ||
        searchInput === "main"
      ) {
        this.$router.push("/adminHome");
      } else if (
        searchInput === "Profile" ||
        searchInput === "profile" ||
        searchInput === "Account" ||
        searchInput === "account"
      ) {
        this.$router.push("/viewAdminAccount");
      } else if (searchInput === "Calendar" || searchInput === "calendar") {
        this.$router.push("/calendarAdmin");
      } else if (
        searchInput === "Edit" ||
        searchInput === "edit" ||
        searchInput === "Manage" ||
        searchInput === "manage"
      ) {
        this.$router.push("/editAdminAccount");
      } else if (
        searchInput === "Services" ||
        searchInput === "services" ||
        searchInput === "Service" ||
        searchInput === "service"
      ) {
        this.$router.push("/offeredServiceTableAdmin");
      } else if (
        searchInput === "Business" ||
        searchInput === "business" ||
        searchInput === "Info" ||
        search === "info"
      ) {
        this.$router.push("/adminBusinessInfo");
      } else {
        this.searchInput = "";
        console.log("Not Found");
      }
    },

    refresh() {
      var i;
      var numTechAccounts = this.techAccountOptions.length;
      var numOfferedServices = this.offeredServiceOptions.length;
      var numCars = this.carOptions.length;
      var numGarages = this.garageOptions.length;

      for (i = 0; i < numTechAccounts; i++) {
        this.techAccountOptions.splice(0, 1);
      }

      for (i = 0; i < numOfferedServices; i++) {
        this.offeredServiceOptions.splice(0, 1);
      }

      for (i = 0; i < numCars; i++) {
        this.carOptions.splice(0, 1);
      }

      for (i = 0; i < numGarages; i++) {
        this.garageOptions.splice(0, 1);
      }

      this.getAllTechnicianAccounts();
      this.getAllOfferedServices();
      this.getAllCars();
      this.getAllGarages();
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
        this.updateAppointmentTimeslot(appointmentId, response.data.timeslotId);
      }
    },

    async deleteOrUpdateAppointment(selectionInfo) {
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

    getAllTechnicianAccounts: function () {
      AXIOS.get("/getAllTechnicianAccounts")
        .then((response) => {
          for (let ta of response.data) {
            console.log("ta.username: ");
            console.log(ta.username);
            var temp = {
              value: ta.username,
              label: ta.username,
            };
            this.techAccountOptions.push(temp);
          }
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorService = errorMsg;
        });
    },

    getAllOfferedServices: function () {
      AXIOS.get("/getAllOfferedServices")
        .then((response) => {
          console.log("in getAllOfferedServices");
          for (let os of response.data) {
            console.log("os.Id: ");
            console.log(os.offeredServiceId);
            var temp = {
              value: os.offeredServiceId,
              label: os.offeredServiceId,
            };
            this.offeredServiceOptions.push(temp);
          }
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorService = errorMsg;
        });
    },

    getAllCars: function () {
      AXIOS.get("/getAllCars")
        .then((response) => {
          console.log("in getAllCar");
          console.log(response.data);
          for (let car of response.data) {
            console.log("car: ");
            console.log(car);
            console.log("licensePlate: ");
            console.log(car.licensePlate);
            var temp = {
              value: car.licensePlate,
              label: car.licensePlate,
            };
            console.log("all cars temp");
            console.log(temp);
            console.log("all cars: ");
            console.log(this.allCars);
            this.carOptions.push(temp);
          }
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorService = errorMsg;
        });
    },

    getAllGarages: function () {
      AXIOS.get("/getAllGarages")
        .then((response) => {
          console.log("in getAllGarages");
          for (let g of response.data) {
            console.log("ta.username: ");
            console.log(g.garageId);
            var temp = {
              value: g.garageId,
              label: g.garageId,
            };
            this.garageOptions.push(temp);
          }
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorService = errorMsg;
        });
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
        var appCreate = confirm("Do you want to make an appointment?");
        if (appCreate == true) {
          console.log("1");
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
          this.currentSelectedTimeslotId = response.data.timeslotId;
          console.log("timeId: " + this.currentSelectedTimeslotId);

          this.refresh();
          this.dialogFormVisible = true;
          //this.createService();
        }
      } catch (error) {
        console.log(error);
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

    async createAppointment(
      comment,
      timeslotId,
      licensePlate,
      garageId,
      offeredServiceId,
      technicianUsername
    ) {
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
        console.log("comment: ");
        console.log(comment);
        console.log("timeslotId: " + timeslotId);
        this.currentSelectedAppointmentId = response.data.appointmentId;
        let calendarApi = this.selectedInfo.view.calendar;
        calendarApi.unselect();
        calendarApi.addEvent({
          id: response.data.appointmentId,
          title:
            "service: " +
            response.data.offeredService.name +
            "licensePlate: " +
            response.data.car.licensePlate,
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