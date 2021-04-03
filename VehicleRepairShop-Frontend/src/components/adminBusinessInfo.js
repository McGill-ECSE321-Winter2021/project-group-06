import { ScrollResponder } from '@fullcalendar/common'
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function businessInformationDTO(name, address, phoneNumber, email) {

    this.name = name
    this.address = address
    this.phoneNumber = phoneNumber
    this.email = email
}

export default {
    data() {
        return {
            businessInfos: [],
            newBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                email: ''
            },
            selectedBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                email: ''
            },
            errorBusinessInfo: '',
            response: [],
            searchInput: ''         
        }
    },
    /*created: function () {
        AXIOS.get('/getAllBusinessInformation')
        .then(response => {
        // JSON responses are automatically parsed.
            this.businessInfos = response.data
        })
        .catch(e => {
            this.errorBusinessInfo = e
        })

      },*/
    methods: {
        // navigation bar
        goBack() {
            this.$router.go(-1);

        },
        searchButton(searchInput) {
            this.searchInput = ''
            if (searchInput === "Home" || searchInput === "home") {
                this.$router.push("/adminHome");
            }
            else if (searchInput === "Services" || searchInput === "services" || searchInput === "Service" || searchInput === "service") {
                this.$router.push("/offeredServiceTableAdmin");
            }
            else if (searchInput === "Profile" || searchInput === "profile" || searchInput === "Account" || searchInput === "account") {
                this.$router.push("/viewAdminAccount");
            }
            else if (searchInput === "Calendar" || searchInput === "calendar") {
                this.$router.push("/calendarAdmin")
            }
            else if (searchInput === "Edit" || searchInput === "edit" || searchInput === "Manage" || searchInput === "manage") {
                this.$router.push("/editAdminAccount")
            }
            else {
                this.searchInput = "";
                console.log("Not Found");
            }

        },
        //backend
        deleteBusinessInformation: function (name){
            if (confirm("Do you want to delete this Business Information?\nYou cannot undo this action")) {
                this.toggleShowModal();
                console.log("THERE")
                AXIOS.put('/deleteBusinessInformation/'.concat(name)).then(response => {
                    // JSON responses are automatically parsed.
                    /*this.businessInfo.push(response.data)
                    this.newBusinessInfo.name = ''
                    this.$root.businessName = 'Business Name2'
                    this.newBusinessInfo.address = ''
                    this.$root.businessAddress = 'Business Address'
                    this.newBusinessInfo.phoneNumber = ''
                    this.$root.businessPhoneNumber = 'Business Phone Number'
                    this.newBusinessInfo.email = ''
                    this.$root.businessEmail = 'Business Emails'
                    this.errorBusinessInfo = ''

                    console.log(this.$currentUsername.value)*/
                    console.log("HERE")
                    this.selectedBusinessInfo = response.data
                    this.errorBusinessInfo = ''
                    this.getAllBusinessInformation()

                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorBusinessInfo = errorMsg
                    })
                //confirmButton.disabled=false
            }else{
                console.log("else")
            }
        },

        createBusinessInformation: function (name, address, phoneNumber, email) {

            AXIOS.post('/createBusinessInformation/'.concat(name).concat('/').concat(address).concat('/').concat(phoneNumber).concat('/').concat(email)).then(response => {
            // JSON responses are automatically parsed.
                this.businessInfos.push(response.data)
                this.newBusinessInfo.name = ''
                this.$root.businessName = name
                

                this.newBusinessInfo.address = ''
                this.$root.businessAddress = address
                this.newBusinessInfo.phoneNumber = ''
                this.$root.businessPhoneNumber = phoneNumber
                this.newBusinessInfo.email = ''
                this.$root.businessEmail = email
                this.errorBusinessInfo = ''

                this.getAllBusinessInformation()
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorBusinessInfo = errorMsg
                })
            //confirmButton.disabled=true
        },
        getAllBusinessInformation: function (){
            console.log("OK")
            AXIOS.get('/getAllBusinessInformation/').then(response => {
                // JSON responses are automatically parsed.
                    this.businessInfos = response.data
                })
                .catch(e => {
                    this.errorCar = e
                })

        },
        activateBusinessInformation: function (){

        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
        }
    }
}