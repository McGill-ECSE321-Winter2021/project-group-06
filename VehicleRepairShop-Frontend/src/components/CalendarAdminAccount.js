import Fullcalendar from '@fullcalendar/vue'
import DayGridPlugin from '@fullcalendar/daygrid'
import TimeGridPlugin from '@fullcalendar/timegrid'
import InteractionPlugin from '@fullcalendar/interaction'
import axios from 'axios'
import ModalWindow from "@vuesence/modal-window";

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })



export default {
    components : {
        Fullcalendar,
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
                customButtons: {
                  creat_app: {
                    text: 'create appointment',
                    click: this.toggleShowModal
                  }
                },
                
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'creat_app,timeGridWeek,timeGridDay'
                  },

                 

                  editable: true,
                  selectable: true,
                  selectMirror: true,
                  dayMaxEvents: true,
                  weekends: true,
                  select: this.timeSlotSelected,
                  events: this.getAllAppointment,
                
            },
            timeslots: this.getAllTimeSlot

           
        }
   
    },

    methods: {
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
            try{
                this.toggleShowModal();
                let startTime = selectionInfo.start.getHours().toString()+':'+selectionInfo.start.getMinutes().toString()+':'+selectionInfo.start.getSeconds().toString();
                let endTime = selectionInfo.end.getHours().toString()+':'+selectionInfo.end.getMinutes().toString()+':'+selectionInfo.end.getSeconds().toString();
                let startDate = selectionInfo.start.getFullYear().toString()+'-'+(selectionInfo.start.getMonth()+1).toString()+'-'+selectionInfo.start.getDate().toString();
                let endDate = selectionInfo.end.getFullYear().toString()+'-'+(selectionInfo.end.getMonth()+1).toString()+'-'+selectionInfo.end.getDate().toString();
                if (confirm('Do you want to make an appointment?')){
                    const response = await AXIOS.post('/createTimeSlot/'+startTime+'/'+endTime+'/'+startDate+'/'+endDate);
                    console.log(response);
                }
                
            }catch(error){
                console.error(error)
            }finally{
                console.log('get all timeslot')
            }
        }
        // async creatOwner(){
        //     try{
        //         const response = await AXIOS.post('/createAdminAccount/username1/password1/name1');
        //         console.log(response);
        //     }catch(error){
        //         console.error(error)
        //     }finally{
        //         console.log('create owner')
        //     }
        // },
        // async creatCar(){
        //     try{
        //         const response = await AXIOS.post('/createCar/licensePlate1/model1/year1/Electric/username1');
        //         console.log(response);
        //     }catch(error){
        //         console.error(error)
        //     }finally{
        //         console.log('create car')
        //     }
        // },
        // async creatService(){
        //     try{
        //         const response = await AXIOS.post('/createOfferedService/offeredServiceId1/price1/name1/30/10:00:00/30/description1');
        //         console.log(response);
        //     }catch(error){
        //         console.error(error)
        //     }finally{
        //         console.log('create service')
        //     }
        // },
        // async creatCar(){
        //     try{
        //         const response = await AXIOS.post('/createCar/licensePlate1/model1/year1/Electric/username1');
        //         console.log(response);
        //     }catch(error){
        //         console.error(error)
        //     }finally{
        //         console.log('create car')
        //     }
        // },
        // async getAllAppointment(){
        //     try{
        //         const response = await AXIOS.get('/getAllAppointment');
        //         console.log(response);
        //     }catch(error){
        //         console.error(error)
        //     }finally{
        //         console.log('get all app')
        //     }
        // }
    }
  
}