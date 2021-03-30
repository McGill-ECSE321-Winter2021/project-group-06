import { emptyStatement } from "babel-types"
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host +':'+config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':'+config.dev.backendPort

var AXIOS = axios.create({
    baseURL : backendUrl,
    headers : {
        'Access-Control-Allow-Origin': frontendUrl
    }
})

function OfferedServiceDTO (offeredServiceId, price,name,duration,reminderTime,reminderDate,description){
    this.Id = offeredServiceId
    this.name = name
    this.duration = duration,
    this.price = price,
    this.comments = description,
    this.reminderDate = reminderDate,
    this.reminderTime = reminderTime
}

export default {
    created: function(){

        AXIOS.get('/getAllOfferedServices').then(response =>{
            //JSON responses are automatically parsed.
            this.OfferedServices = response.data
        })
        .catch(e => {
            this.errorOfferedService = e
        })
      },

    data() {
      return {
        OfferedServices: [],
        newOfferedService: '',
        errorOfferedService: '',
        response: [],

        tableData: [
 
        ],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          Id: '',
          name: '',
          duration: 0,
          price: 0.0,
          comments: '',
          reminderDate: 0,
          reminderTime: '',

        },
        formLabelWidth: '120px',
        packData:[],
        delVisible: false,
        msg:"",
        msg2:"",
        delarr:[],
        modifyData:[],
        prevValue:{}
      }
    },

    methods: {
        initializeTable(){
            var j
            var original_length = this.tableData.length
                
            for (j=0;j<original_length;j++){
                    this.tableData.splice(0,1)
                    
            }
           
            
            for (let os of this.OfferedServices){
                if (os.Id !== emptyStatement){
                    var temp = {
                        Id: os.offeredServiceId,
                        name: os.name,
                        duration: os.duration,
                        price: os.price,
                        comments: os.description,
                        reminderDate: os.reminderDate,
                        reminderTime: os.reminderTime
                    }
                    this.tableData.push(temp)
                }
            }
                
        },

        handleEdit(index, row) {
            this.msg = row;
            this.msg2 = index;
            console.log("msg.Id: " + this.msg.Id)
            this.form.Id = this.msg.Id
        },

        
        Edit(Id,name,duration,price,description,reminderDate,reminderTime){
            AXIOS.put('/updateOfferedService/'.concat(Id).concat('/')
                                                .concat(price).concat('/')
                                                .concat(name).concat('/')
                                                .concat(duration).concat('/')
                                                .concat(reminderTime).concat('/')
                                                .concat(reminderDate).concat('/')
                                                .concat(description), {},{})
                    .then(response =>{
                        var newValue2 = {
                            Id: Id,
                            name: name,
                            duration: duration,
                            price: price,
                            comments: description,
                            reminderDate: reminderDate,
                            reminderTime: reminderTime
                        }
                        
                        this.tableData.splice(this.msg2,1,newValue2)

                        let i=0
                        for(let os of this.OfferedServices){
                            if ( os.offeredServiceId === this.msg.Id ){
                                this.OfferedServices.splice(i,1,response.data)
                            }
                            i++
                        }
                        
                        this.errorOfferedService = ''
                        this.newOfferedService = ''
                        var j
                        var original_length = this.tableData.length
                            
                        for (j=0;j<original_length;j++){
                                this.tableData.splice(0,1)
                                
                        }
                    

                        for (let os of this.OfferedServices){
                            if (os.Id !== emptyStatement){
                                var temp = {
                                    Id: os.offeredServiceId,
                                    name: os.name,
                                    duration: os.duration,
                                    price: os.price,
                                    comments: os.description,
                                    reminderDate: os.reminderDate,
                                    reminderTime: os.reminderTime
                                }
                                this.tableData.push(temp)
                            }
                        }
                    })
                    
                    .catch(e =>{
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorOfferedService = errorMsg
                    })
            this.delVisible = false;
            this.dialogFormVisible = false;
        },

        getPackData() {
                this.$axios.post('/api/selectPackPageMade.do').then((res) => {
                    this.packData = res.data;
                }).catch(function(error){
                    console.log(error);
                })
            },

        handleDelete(index, row) {
                    this.idx = index;
                    this.msg= row;
                    this.delarr.push(this.msg.Id);
                    this.delVisible = true;
        },

        deleteRow(){
            AXIOS.delete('/deleteOfferedServiceById/'.concat(this.msg.Id).concat('/'), {},{})
                    .then(response =>{
                        //JSON responses are automatically parsed
                        this.tableData.splice(this.idx, 1)
                        let i=0
                        for(let os of this.OfferedServices){
                            if ( os.offeredServiceId === this.msg.Id ){
                                this.OfferedServices.splice(i,1)
                            }
                            i++
                        }
                        this.errorOfferedService = ''
                        this.newOfferedService = ''
                        var j
                        var original_length = this.tableData.length
                        
                            
                        for (j=0;j<original_length;j++){
                                console.log("j:" + j)
                                this.tableData.splice(0,1)
                                
                        }
                    
                        
                        
                        for (let os of this.OfferedServices){
                            
                            if (os.Id !== emptyStatement){
                                var temp = {
                                    Id: os.offeredServiceId,
                                    name: os.name,
                                    duration: os.duration,
                                    price: os.price,
                                    comments: os.description,
                                    reminderDate: os.reminderDate,
                                    reminderTime: os.reminderTime
                                }
                                this.tableData.push(temp)
                            }
                        }
                    })
                    .catch(e =>{
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorOfferedService = errorMsg
                    })
            
            this.delVisible = false;
        },


        

        createOfferedService: function (offeredServiceId, price,name,duration,reminderTime,reminderDate,description) {
            AXIOS.post('/createOfferedService/'.concat(offeredServiceId).concat('/')
                                                .concat(price).concat('/')
                                                .concat(name).concat('/')
                                                .concat(duration).concat('/')
                                                .concat(reminderTime).concat('/')
                                                .concat(reminderDate).concat('/')
                                                .concat(description), {},{})
                    .then(response =>{
                        var newValue = {
                            Id: offeredServiceId,
                            name: name,
                            duration: duration,
                            price: price,
                            comments: description,
                            reminderDate: reminderDate,
                            reminderTime: reminderTime
                        }
                        //JSON responses are automatically parsed
                        this.OfferedServices.push(response.data)
                        this.tableData.push(newValue)
                        this.errorOfferedService = ''
                        this.newOfferedService = ''
                    })
                    .catch(e =>{
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorOfferedService = errorMsg
                    })


            
        },
    },


    
  }
