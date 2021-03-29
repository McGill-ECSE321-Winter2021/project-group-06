

import axios from 'axios'
import { response } from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })
  

    const appointments = AXIOS.get('/getAllAppointment').then(response => {
        return response.data
    });

export async function loadEvents(){
    const 
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
        INITIAL_EVENTS.push(event);
    }

    return INITIAL_EVENTS;
//    INITIAL_EVENTS.push({
//     id: 1,
//     title: "cheng",
//     start: "2021-04-01T10:00:00",
//     end: "2021-04-01T11:00:00"
// })
}
  



