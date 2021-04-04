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
    <br /><br />
    <Fullcalendar ref="calendarCustomer" :options="calendarOptions" />
    
    <!-- eidting dialogue-->
    <el-dialog title="EIDTING" :visible.sync="dialogFormVisible">
      <el-form :model="form">
         <el-form-item label="technicain" :label-width="formLabelWidth">
          <el-select v-model="form.value1" placeholder="select technicain">
            <el-option
              v-for="item in this.techAccountOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

          <el-form-item label="OfferedService" :label-width="formLabelWidth">
            <el-select v-model="form.value2" placeholder="select OfferedService">
              <el-option
                v-for="item in this.offeredServiceOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Car" :label-width="formLabelWidth">
            <el-select v-model="form.value3" placeholder="select Car">
              <el-option
                v-for="item in this.carOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Garage" :label-width="formLabelWidth">
            <el-select v-model="form.value4" placeholder="select Garage">
              <el-option
                v-for="item in this.garageOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
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
                form.value1,
              )
          "
          >Comfirm</el-button
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
// import { emptyStatement } from "babel-types";

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
      errorService: '',

      techAccountOptions: [],
      offeredServiceOptions: [],
      carOptions: [],
      garageOptions: [],

      customerApps: [],
      dialogFormVisible: false,
      form:{
          value1:"",
          value2:"",
          value3:"",
          value4:"",
          value5:"",

      },
      
      allTechnicianAccounts: [],
      allOfferedServices: [],
      allCars: [],
      allGarages: [],

      formLabelWidth: '120px',


      calendarOptions: {
        plugins: [DayGridPlugin , TimeGridPlugin , InteractionPlugin],
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
      
      
      searchInput:'',
    };
  },

  // auto show existing appointments
  mounted() {
    let calendarApi = this.$refs.calendarCustomer.getApi();
    console.log(this.$currentUsername);
    let currentCustomerAppointment = [];
    
    AXIOS.get("/getAppointmentByCustomer/" + this.$currentUsername.value).then((response) => {
      console.log(response.data);
      for (var i = 0; i < response.data.length; i++) {
        this.customerApps.push(response.data[i].appointmentId)
        console.log("customerApps1: " + this.customerApps)
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
          title: "service: " + response.data[i].offeredService.name + "licensePlate: " + response.data[i].car.licensePlate,
          start: startStr,
          end: endStr,
        };
        console.log(event);
        calendarApi.addEvent(event);
      }
    });

    AXIOS.get("/getAllAppointment").then((response) => {
        console.log(response.data);
        for (var i = 0; i < response.data.length; i++) {
          console.log("customerApps2: " + this.customerApps)
          console.log("response.data[i].appointmentId: "+response.data[i].appointmentId)
          console.log("BOOLEAN: " + this.customerApps.includes(response.data[i].appointmentId))
          if (!this.customerApps.includes(response.data[i].appointmentId)){
            let startStr =
            response.data[i].timeSlot.startDate +
            "T" +
            response.data[i].timeSlot.startTime;
            let endStr =
              response.data[i].timeSlot.endDate +
              "T" +
              response.data[i].timeSlot.endTime;
            if (
              currentCustomerAppointment.includes(
                response.data[i].appointmentId
              ) == false
            ) {
              let event = {
                id: response.data[i].appointmentId,
                title: "Booked timeslot",
                start: startStr,
                end: endStr,
                editable: false,
              };
              console.log(event);
              calendarApi.addEvent(event);
            }
          }
          
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
      var numTechAccounts = this.techAccountOptions.length
      var numOfferedServices = this.offeredServiceOptions.length
      var numCars = this.carOptions.length
      var numGarages = this.garageOptions.length

      for (i=0;i<numTechAccounts;i++){
        this.techAccountOptions.splice(0, 1)
      }

      for (i=0; i<numOfferedServices;i++){
        this.offeredServiceOptions.splice(0, 1)
      }

      for (i=0; i<numCars;i++){
        this.carOptions.splice(0, 1)
      }

      for (i=0; i<numGarages;i++){
        this.garageOptions.splice(0, 1)
      }
      
      this.getAllTechnicianAccounts()
      this.getAllOfferedServices()
      this.getAllCars()
      this.getAllGarages()

    },

    // // backend
    // async updateAppointmentTimeslot(appointmentId, timeslotId) {
    //   console.log("update timeslot");
    //   const response = await AXIOS.put(
    //     "/updateAppointmentTimeSlot/" +
    //       parseInt(appointmentId) +
    //       "/" +
    //       parseInt(timeslotId)
    //   );
    //   console.log(response.data);
    // },

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


 

    // toggleShowModal() {
    //   this.isShowModal = true;
    //   console.log(this.isShowModal);
    // },



    getAllTechnicianAccounts: function(){
      AXIOS.get("/getAllTechnicianAccounts").then(response => {
        console.log("in getAllTechnicianAccounts")
        // console.log(response.data)
            for (let ta of response.data){
              // console.log("car: ")
              // console.log(car)
              console.log("ta.username: ")
              console.log(ta.username)
              var temp = {
                value: ta.username,
                label: ta.username,
              }
              // console.log("all cars temp")
              // console.log(temp)
              // console.log("all cars: ")
              // console.log(this.allCars)
              this.techAccountOptions.push(temp)
            }
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorService = errorMsg
        })
    },


    // async getAllTechnicianAccounts(){
    //   try{
    //     this.allTechnicianAccounts = await AXIOS.get("/getAllTechnicianAccounts");
    //     for (let ta of this.allTechnicianAccounts){
    //       if (ta.username !== emptyStatement) {
    //           var temp = {
    //               value: ta.username,
    //               label: ta.username,
    //           }
    //           this.techAccountOptions.push(temp)
    //       }
    //     }
    //   }catch (error) {
    //     console.log(error);
    //   }finally{
    //     console.log("get all Technicain accounts");
    //   }
      
    // },

    getAllOfferedServices: function(){
      AXIOS.get("/getAllOfferedServices").then(response => {
        console.log("in getAllOfferedServices")
        // console.log(response.data)
            for (let os of response.data){
              // console.log("car: ")
              // console.log(car)
              console.log("os.Id: ")
              console.log(os.offeredServiceId)
              var temp = {
                value: os.offeredServiceId,
                label: os.offeredServiceId,
              }
              // console.log("all cars temp")
              // console.log(temp)
              // console.log("all cars: ")
              // console.log(this.allCars)
              this.offeredServiceOptions.push(temp)
            }
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorService = errorMsg
        })
    },

    // async getAllOfferedServices(){
    //   try{
    //     this.allOfferdServices = await AXIOS.get("/getAllOfferedServices");
    //     for (let os of this.allOfferedServices){
    //     if (os.Id !== emptyStatement) {
    //         var temp = {
    //             value: os.Id,
    //             label: os.Id,
    //         }
    //         this.offeredServiceOptions.push(temp)
    //     }
    //   }
    //   }catch (error) {
    //     console.log(error);
    //   }finally{
    //     console.log("get all OfferedServices");
    //   }
    // },

    getAllCars: function(){
      AXIOS.get("/getAllCars").then(response => {
        console.log("in getAllCar")
        console.log(response.data)
            for (let car of response.data){
              console.log("car: ")
              console.log(car)
              console.log("licensePlate: ")
              console.log(car.licensePlate)
              var temp = {
                value: car.licensePlate,
                label: car.licensePlate,
              }
              console.log("all cars temp")
              console.log(temp)
              console.log("all cars: ")
              console.log(this.allCars)
              this.carOptions.push(temp)
            }
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorService = errorMsg
        })
    },


    getAllGarages: function(){
      AXIOS.get("/getAllGarages").then(response => {
        console.log("in getAllGarages")
        // console.log(response.data)
            for (let g of response.data){
              // console.log("car: ")
              // console.log(car)
              console.log("ta.username: ")
              console.log(g.garageId)
              var temp = {
                value: g.garageId,
                label: g.garageId,
              }
              // console.log("all cars temp")
              // console.log(temp)
              // console.log("all cars: ")
              // console.log(this.allCars)
              this.garageOptions.push(temp)
            }
        })
        .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorService = errorMsg
        })
    },

    // async getAllGarages(){
    //   try{
    //     this.allGarages = await AXIOS.get("/getAllGarages");
    //     for (let g of this.allGarages){
    //     if (g.Id !== emptyStatement) {
    //         var temp = {
    //           value: g.Id,
    //           label: g.Id,
    //         }
    //         this.garageOptions.push(temp)
    //     }
    //   }
    //   }catch (error) {
    //     console.log(error);
    //   }finally{
    //     console.log("get all garages");
    //   }
    // },


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
          console.log("timeId: " + this.currentSelectedTimeslotId);

          this.refresh()
          this.dialogFormVisible = true
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
        console.log("comment: ")
        console.log(comment)
        console.log("timeslotId: " + timeslotId)
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