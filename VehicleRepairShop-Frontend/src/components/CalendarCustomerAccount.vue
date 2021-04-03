<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#/calendarCustomer" style="color: black"
        >VRSS</b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item @click="goBack" style="color: white">Go Back</b-nav-item>
          <b-nav-item href="#/viewCustomerAccount" color="white"
            >Profile</b-nav-item
          >
          <b-nav-item href="#/customerCreateCar" color="white">Cars</b-nav-item>
          <b-nav-item href="#/calendarCustomer" color="white"
            >Calendar</b-nav-item
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
    <Fullcalendar ref="calendarCustomer" :options="calendarOptions" />
  </div>
</template>


<script>
import Fullcalendar from "@fullcalendar/vue";
import DayGridPlugin from "@fullcalendar/daygrid";
import TimeGridPlugin from "@fullcalendar/timegrid";
import InteractionPlugin from "@fullcalendar/interaction";
import axios from "axios";

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
  },
  computed: {},

  data() {
    return {
      isShowModal: false,

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
        //   droppable: true,
        eventOverlap: false,
        weekends: true,
        eventStartEditable: false,
        select: this.timeSlotSelected,
        //   eventClick: this.deleteOrUpdateAppointment,
        //   eventDrop: this.createTimeslot,
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
      currentSelectedTimeslotId: 1,
      currentSelectedOwnerUsername: 1,
      currentSelectedCarLicensePlate: 1,
      curreantSelectedServiceId: 1,
      currentSelectedGarageId: 1,
      currentSelectedTechnicianUsername: 1,
      currentSelectedAppointmentId: 1,
      selectedInfo: {},
      appointmentIdToDelete: 1,

      searchInput: "",
    };
  },
  mounted() {
    let calendarApi = this.$refs.calendarCustomer.getApi();
    console.log(this.$currentUsername);
    let currentCustomerAppointment = [];
    AXIOS.get("/getAppointmentByCustomer/username1").then((response) => {
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
        currentCustomerAppointment.push(response.data[i].appointmentId);
        let event = {
          id: response.data[i].appointmentId,
          title: response.data[i].offeredService.name,
          start: startStr,
          end: endStr,
        };
        console.log(event);
        calendarApi.addEvent(event);
      }
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
          if (
            currentCustomerAppointment.includes(
              response.data[0].appointmentId
            ) == false
          ) {
            let event = {
              id: response.data[0].appointmentId,
              title: "Booked timeslot",
              start: startStr,
              end: endStr,
              editable: false,
            };
            console.log(event);
            calendarApi.addEvent(event);
          }
        }
      });
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
        this.$router.push("/calendarCustomer");
      } else if (
        searchInput === "Profile" ||
        searchInput === "profile" ||
        searchInput === "Account" ||
        searchInput === "account"
      ) {
        this.$router.push("/viewCustomerAccount");
      } else if (searchInput === "Calendar" || searchInput === "calendar") {
        this.$router.push("/calendarCustomer");
      } else if (
        searchInput === "Edit" ||
        searchInput === "edit" ||
        searchInput === "Manage" ||
        searchInput === "manage"
      ) {
        this.$router.push("/editCustomerAccount");
      } else if (
        searchInput === "Car" ||
        searchInput === "car" ||
        searchInput == "Cars" ||
        search === "cars"
      ) {
        this.$router.push("/customerCreateCar");
      } else {
        this.searchInput = "";
        console.log("Not Found");
      }
    },

    // backend
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
    async getAllTimeSlot() {
      try {
        const response = await AXIOS.get("/getAllTimeSlots");
        console.log(response);
      } catch (error) {
        console.error(error);
      } finally {
        console.log("get all timeslot");
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
        if (confirm("Do you want to make an appointment?")) {
          // once selected a timeslot, show modal
          this.toggleShowModal();
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
          console.log(this.currentSelectedTimeslotId);
          this.createService();
        }
      } catch (error) {
        console.error(error);
      } finally {
        console.log("get all timeslot");
      }
    },
    async createOwner() {
      try {
        const response = await AXIOS.post(
          "/createCustomerAccount/username1/password/name1"
        );
        this.currentSelectedOwnerUsername = response.data.username;
        console.log(response.data.username);
        this.createGarage();
      } catch (error) {
        console.error(error);
      } finally {
        console.log("create owner: " + this.currentSelectedOwnerUsername);
      }
    },
    async createTechnician() {
      try {
        const response = await AXIOS.post(
          "/createTechnicianAccount/username1/password/name1"
        );
        this.currentSelectedTechnicianUsername = response.data.username;
        console.log(response.data.username);
        this.createCar();
      } catch (error) {
        console.error(error);
      } finally {
        console.log(
          "create technician: " + this.currentSelectedTechnicianUsername
        );
      }
    },
    async createCar() {
      try {
        const response = await AXIOS.post(
          "/createCar/licensePlate1/model1/" +
            2019 +
            "/Electric/" +
            this.currentSelectedOwnerUsername
        );
        this.currentSelectedCarLicensePlate = response.data.licensePlate;
        // console.log(this.currentSelectedCarLicensePlate);
        this.createAppointment();
      } catch (error) {
        console.error(error);
      } finally {
        console.log("create car: " + this.currentSelectedCarLicensePlate);
      }
    },
    async createService() {
      try {
        const response = await AXIOS.post(
          "/createOfferedService/offeredServiceId1" +
            "/" +
            20.0 +
            "/" +
            "name1/" +
            30 +
            "/10:00:00/" +
            30 +
            "/description1"
        );
        this.curreantSelectedServiceId = response.data.offeredServiceId;
        this.createOwner();
        this.createTechnician();
        // console.log(this.curreantSelectedServiceId );
      } catch (error) {
        console.error(error);
      } finally {
        console.log("create service: " + this.curreantSelectedServiceId);
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
    async createAppointment() {
      try {
        const response = await AXIOS.post(
          "/createAppointment/comment1/" +
            this.currentSelectedTimeslotId +
            "/" +
            this.currentSelectedCarLicensePlate +
            "/" +
            this.currentSelectedGarageId +
            "/" +
            this.curreantSelectedServiceId +
            "/" +
            this.currentSelectedTechnicianUsername
        );
        this.currentSelectedAppointmentId = response.data.appointmentId;
        // console.log(this.currentSelectedAppointmentId);
        // this.newlyAddedAppointment = response.data
        // console.log(this.newlyAddedAppointment)
        let calendarApi = this.selectedInfo.view.calendar;
        calendarApi.unselect();
        calendarApi.addEvent({
          id: response.data.appointmentId,
          title: response.data.offeredService.name,
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