import { emptyStatement } from "babel-types"
import axios from 'axios'
//import { response } from "express"
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
        //this.OfferedServices = []
        // Initializing offeredServices from backend
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
            // {
        //   Id:"SERVICE1",
        //   name: "SERVICE1",
        //   duration: 10,
        //   price: 20.1,
        //   comments: "quick and easy",
        //   reminderDate: 10,
        //   reminderTime: "09:00:00"
            // }
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
        //   desc: ''
        },
        formLabelWidth: '120px',
        packData:[],
        delVisible: false,//删除提示弹框的状态
        msg:"",//记录每一条的信息，便于取id
        msg2:"",//index
        delarr:[],//存放删除的数据
        multipleSelection: [],//多选的数据
        modifyData:[],
        prevValue:{}
      }
    },

    // mounted(){
    //     this.getData()
    // },

    methods: {
        initializeTable(){
            console.log("this.tableData.length:" + this.tableData.length)
            var j
            var original_length = this.tableData.length
            console.log("this.tableData.length + 1 : " + (this.tableData.length+1))
                
            for (j=0;j<original_length;j++){
                    console.log("j:" + j)
                    this.tableData.splice(0,1)
                    
            }
           
            
            console.log("this.OfferedServices.length:" + this.OfferedServices.length)
            for (let os of this.OfferedServices){
                console.log(os.Id)
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
            //console.log(index, row);
            this.msg = row;
            this.msg2 = index;
        },

        
        Edit(Id,name,duration,price,description,reminderDate,reminderTime){
            // console.log("Id:" + Id)
            // console.log("name:" + name)
            // console.log("duration:" + duration)
            // console.log("price:" + price)
            // console.log("description:" + description)
            // console.log("reminderDate:" + reminderDate)
            // console.log("reminderTime:" + reminderTime)

            // var newValue2 = {
            //     Id: Id,
            //     name: name,
            //     duration: duration,
            //     price: price,
            //     comments: description,
            //     reminderDate: reminderDate,
            //     reminderTime: reminderTime
            // }
            // this.tableData[this.msg2].Id = Id
            // this.tableData[this.msg2].name = name
            // this.tableData[this.msg2].duration = duration
            // this.tableData[this.msg2].price = price
            // this.tableData[this.msg2].comments = description
            // this.tableData[this.msg2].reminderDate = reminderDate
            // this.tableData[this.msg2].reminderTime = reminderTime
            // this.tableData.splice(this.msg2, 1)
            // this.tableData.push(this.msg2, newValue2)
            // this.tableData.splice(this.msg2, 1)
            AXIOS.put('/updateOfferedService/'.concat(Id).concat('/')
                                                .concat(price).concat('/')
                                                .concat(name).concat('/')
                                                .concat(duration).concat('/')
                                                .concat(reminderTime).concat('/')
                                                .concat(reminderDate).concat('/')
                                                .concat(description), {},{})
                    .then(response =>{
                        //JSON responses are automatically parsed
                        //this.OfferedServices.push(response.data)
                        var newValue2 = {
                            Id: Id,
                            name: name,
                            duration: duration,
                            price: price,
                            comments: description,
                            reminderDate: reminderDate,
                            reminderTime: reminderTime
                        }
                        // this.tableData[this.msg2].Id = Id
                        // this.tableData[this.msg2].name = name
                        // this.tableData[this.msg2].duration = duration
                        // this.tableData[this.msg2].price = price
                        // this.tableData[this.msg2].comments = description
                        // this.tableData[this.msg2].reminderDate = reminderDate
                        // this.tableData[this.msg2].reminderTime = reminderTime
                        this.tableData.splice(this.msg2,1,newValue2)
                        // this.tableData.splice(this.msg2, 1)
                        // this.tableData.push(this.msg2, newValue2)
                        // this.tableData.splice(this.msg2, 1)

                        let i=0
                        for(let os of this.OfferedServices){
                            // console.log("boolean:" + (os.Id === this.msg.Id))
                            // console.log("os.Id:" + os.Id)
                            // console.log("os:" + os)
                            // console.log("this.msg.Id:" + this.msg.Id)
                            // console.log("this.msg2:"+this.msg2)
                            // console.log("service length: " + this.OfferedServices.length)
                            // console.log("i:" + i)
                            if ( os.offeredServiceId === this.msg.Id ){
                                this.OfferedServices.splice(i,1,response.data)
                            }
                            i++
                        }
                        this.errorOfferedService = ''
                        this.newOfferedService = ''
                        console.log("this.tableData.length:" + this.tableData.length)
                        var j
                        var original_length = this.tableData.length
                        console.log("this.tableData.length + 1 : " + (this.tableData.length+1))
                            
                        for (j=0;j<original_length;j++){
                                console.log("j:" + j)
                                this.tableData.splice(0,1)
                                
                        }
                    
                        
                        console.log("this.OfferedServices.length:" + this.OfferedServices.length)
                        for (let os of this.OfferedServices){
                            console.log(os.Id)
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
            //var o = new OfferedServiceDTO(Id,price,name,duration,reminderTime,reminderDate,description)    
            // this.OfferedServices.push(o)
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
                    this.msg= row;//每一条数据的记录
                    this.delarr.push(this.msg.Id);//把单行删除的每条数据的id添加进放删除数据的数组
                    this.delVisible = true;
        },

        deleteRow(){
            AXIOS.delete('/deleteOfferedServiceById/'.concat(this.msg.Id).concat('/'), {},{})
                    .then(response =>{
                        //JSON responses are automatically parsed
                        //this.OfferedServices.push(response.data)
                        this.tableData.splice(this.idx, 1)
                        let i=0
                        for(let os of this.OfferedServices){
                            console.log("boolean:" + (os.offeredServiceId === this.msg.Id))
                            console.log("os.Id:" + os.offeredServiceId)
                            console.log("os:" + os)
                            console.log("this.msg.Id:" + this.msg.Id)
                            console.log("this.msg2:"+this.msg2)
                            console.log("service length: " + this.OfferedServices.length)
                            console.log("i:" + i)
                            if ( os.offeredServiceId === this.msg.Id ){
                                this.OfferedServices.splice(i,1)
                                console.log("this.offeredServices.length after delete: " + this.OfferedServices.length)
                            }
                            i++
                        }
                        this.errorOfferedService = ''
                        this.newOfferedService = ''
                        console.log("this.tableData.length:" + this.tableData.length)
                        var j
                        var original_length = this.tableData.length
                        console.log("this.tableData.length + 1 : " + (this.tableData.length+1))
                            
                        for (j=0;j<original_length;j++){
                                console.log("j:" + j)
                                this.tableData.splice(0,1)
                                
                        }
                    
                        
                        console.log("this.OfferedServices.length:" + this.OfferedServices.length)
                        for (let os of this.OfferedServices){
                            console.log(os.Id)
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
            //console.log(reminderTime)
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


            // var o = new OfferedServiceDTO(offeredServiceId, price,name,duration,reminderTime,reminderDate,description)
            // this.OfferedServices.push(o)
            // this.newOfferedService = ''
        },
    },


    
  }
