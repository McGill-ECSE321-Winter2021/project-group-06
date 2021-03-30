<template>
<div>
    <Fullcalendar ref="calendar"
        :options="calendarOptions"
    />

    <modal-window
    :visible="isShowModal"
    :close-on-escape="true"
    :close-on-outside-click="true"
    :show-x-mark="true"
    @close="isShowModal=false"
>
    
    </modal-window>
</div>


</template>


<script>
import Fullcalendar from '@fullcalendar/vue'
import DayGridPlugin from '@fullcalendar/daygrid'
import TimeGridPlugin from '@fullcalendar/timegrid'
import InteractionPlugin from '@fullcalendar/interaction'
import axios from 'axios'
import DropdownMenu from '@innologica/vue-dropdown-menu'
import ModalWindow from "@vuesence/modal-window";

var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl}
  })
export default {
    components : {
        Fullcalendar,
        ModalWindow,
        DropdownMenu
    },
    computed: {
    
    },
   
    data() {
        return {
            isShowModal: false,
            
            calendarOptions :{
                plugins : [
                    DayGridPlugin,
                    TimeGridPlugin,
                    InteractionPlugin
                ],
                initialView: 'timeGridWeek',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'timeGridWeek,timeGridDay'
                  },
                 
                  editable: true,
                  selectable: true,
                  selectMirror: true,
                  dayMaxEvents: true,
                  weekends: true,
                  select: this.timeSlotSelected,
                  eventClick: this.deleteAppointment,
    
                
            },
            currentSelectedTimeslotId: 1,
            currentSelectedOwnerUsername: 1,
            currentSelectedCarLicensePlate: 1,
            curreantSelectedServiceId: 1,
            currentSelectedGarageId: 1,
            currentSelectedTechnicianUsername: 1,
            currentSelectedAppointmentId: 1,
            selectedInfo: {},
            appointmentIdToDelete: 1
            
           
        }
   
    },
    mounted (){
        let calendarApi = this.$refs.calendar.getApi()
      
        AXIOS.get('/getAllAppointment').then((response)=>{
            console.log(response.data)
        
            for (var i = 0;i < response.data.length; i++){
                let startStr = response.data[0].timeSlot.startDate+'T'+response.data[0].timeSlot.startTime;
                let endStr = response.data[0].timeSlot.endDate+'T'+response.data[0].timeSlot.endTime;
                let event = {
                    id: response.data[0].appointmentId,
                    title: response.data[0].offeredService.name,
                    start: startStr,
                    end: endStr
                }
                console.log(event)
                calendarApi.addEvent(event);
            }
        })
            
    },
    methods: {
        async deleteAppointment(selectionInfo){
             var c = confirm("are you sure you want to delete this appointment?")
                      if (c == true) {
                          let appointment = selectionInfo.event;
                          let appointmentId = selectionInfo.event.id;
                          this.appointmentIdToDelete = parseInt(appointmentId);
                        //   console.log(typeof(this.appointmentIdToDelete))
                          this.deleteAppointment;
                          appointment.remove();
                          const response = await AXIOS.delete('/deleteAppointmentById/'+this.appointmentIdToDelete);
                          console.log(response.data)
                      } 
            
        },

      toggleShowModal (){
        
        this.isShowModal = true;
        console.log(this.isShowModal);
      },
        async getAllTimeSlot(){
            try{
                const response = await AXIOS.get('/getAllTimeSlots');
                console.log(response);
            }catch(error){
                console.error(error)
            }finally{
                console.log('get all timeslot')
            }
        },
        async timeSlotSelected(selectionInfo){
            this.selectedInfo = selectionInfo;
            console.log(selectionInfo.view.calendar);
            
            try{
                
                let startTime = selectionInfo.start.getHours().toString()+':'+selectionInfo.start.getMinutes().toString()+':'+selectionInfo.start.getSeconds().toString();
                let endTime = selectionInfo.end.getHours().toString()+':'+selectionInfo.end.getMinutes().toString()+':'+selectionInfo.end.getSeconds().toString();
                let startDate = selectionInfo.start.getFullYear().toString()+'-'+(selectionInfo.start.getMonth()+1).toString()+'-'+selectionInfo.start.getDate().toString();
                let endDate = selectionInfo.end.getFullYear().toString()+'-'+(selectionInfo.end.getMonth()+1).toString()+'-'+selectionInfo.end.getDate().toString();
                if (confirm('Do you want to make an appointment?')){
                    this.toggleShowModal();
                    const response = await AXIOS.post('/createTimeSlot/'+startTime+'/'+endTime+'/'+startDate+'/'+endDate);
                    this.currentSelectedTimeslotId = response.data.timeslotId;
                    console.log(this.currentSelectedTimeslotId);
                    this.createService();
                }  
            }catch(error){
                console.error(error)
            }finally{
                console.log('get all timeslot')
            }
        },
        async createOwner(){
            try{
                const response = await AXIOS.post('/createCustomerAccount/username1/password/name1');
                this.currentSelectedOwnerUsername = response.data.username;
                console.log(response.data.username);
                this.createGarage();
            }catch(error){
                console.error(error)
            }finally{
                console.log('create owner: '+this.currentSelectedOwnerUsername)
            }
        },
        async createTechnician(){
            try{
                const response = await AXIOS.post('/createTechnicianAccount/username1/password/name1');
                this.currentSelectedTechnicianUsername = response.data.username;
                console.log(response.data.username);
                this.createCar();
            }catch(error){
                console.error(error)
            }finally{
                console.log('create technician: '+this.currentSelectedTechnicianUsername)
            }
        },
        async createCar(){
            try{
                const response = await AXIOS.post('/createCar/licensePlate1/model1/'+2019+'/Electric/'+this.currentSelectedOwnerUsername);
                this.currentSelectedCarLicensePlate = response.data.licensePlate
                // console.log(this.currentSelectedCarLicensePlate);
                this.createAppointment();
            }catch(error){
                console.error(error)
            }finally{
                console.log('create car: '+this.currentSelectedCarLicensePlate)
            }
        },
        async createService(){
            try{
                const response = await AXIOS.post('/createOfferedService/offeredServiceId1'+'/'+20.0+'/'+'name1/'+30+'/10:00:00/'+30+'/description1');
                this.curreantSelectedServiceId = response.data.offeredServiceId
                this.createOwner();
                this.createTechnician();
                // console.log(this.curreantSelectedServiceId );
            }catch(error){
                console.error(error)
            }finally{
                console.log('create service: '+this.curreantSelectedServiceId)
            }
        },
        async createGarage(){
            try{
                const response = await AXIOS.post('/createGarage/garageId1');
                this.currentSelectedGarageId = response.data.garageId
                // console.log(this.currentSelectedGarageId);
            }catch(error){
                console.error(error)
            }finally{
                console.log('create garage: '+this.currentSelectedGarageId)
            }
        },
        async createAppointment(){
            try{
                const response = await AXIOS.post('/createAppointment/comment1/'+this.currentSelectedTimeslotId+'/'+this.currentSelectedCarLicensePlate+'/'+this.currentSelectedGarageId+'/'+this.curreantSelectedServiceId+'/'+this.currentSelectedTechnicianUsername);
                this.currentSelectedAppointmentId = response.data.appointmentId
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
            }catch(error){
                console.error(error)
            }finally{
                console.log('create appointment: '+this.currentSelectedAppointmentId)
            }
        },
    }
  
}
</script>

<style>
</style>