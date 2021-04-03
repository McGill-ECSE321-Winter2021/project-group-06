import { ScrollResponder } from '@fullcalendar/common'
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
    data() {
        return {
            businessInfos: [],
            newBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                emailAddress: ''
            },
            selectedBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                emailAddress: ''
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
            this.searchInput = "";
            if (searchInput === "Home") {
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
            }             else if (searchInput === "Services" || searchInput === "services" || searchInput === "Service" || searchInput === "service") {
                this.$router.push("/offeredServiceTableAdmin");
            }
            else if (
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
        //backend
        deleteBusinessInformation: function (name){
            if (confirm("Do you want to delete this Business Information?\nYou cannot undo this action")) {
                this.toggleShowModal();
                console.log("THERE" + name + "a")
                AXIOS.put('/deleteBusinessInformation/' + name).then(response => {
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

        createBusinessInformation: function (name, address, phoneNumber, emailAddress) {

            AXIOS.post('/createBusinessInformation/'.concat(name).concat('/').concat(address).concat('/').concat(phoneNumber).concat('/').concat(emailAddress)).then(response => {
            // JSON responses are automatically parsed.
                this.businessInfos.push(response.data)
                this.newBusinessInfo.name = ''
                this.$root.businessName = name
                

                this.newBusinessInfo.address = ''
                this.$root.businessAddress = address
                this.newBusinessInfo.phoneNumber = ''
                this.$root.businessPhoneNumber = phoneNumber
                this.newBusinessInfo.emailAddress = ''
                this.$root.businessEmailAddress = emailAddress
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
        activateBusinessInformation: function (name, address, phoneNumber, emailAddress){
            this.$root.businessName = name
            this.$root.businessAddress = address
            this.$root.businessPhoneNumber = phoneNumber
            this.$root.businessEmailAddress = emailAddress
        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
        }
    }
}