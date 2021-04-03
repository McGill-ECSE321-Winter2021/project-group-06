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
            businessInfo: [],
            newBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                email: '',
            },
            errorBusinessInfo: '',
            response: [],
            searchInput: ''         
        }
    },
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
        createBusinessInformation: function (name, address, phoneNumber, email) {
            AXIOS.post('/deleteAllBusinessInformation/').then(response => {
                // JSON responses are automatically parsed.
                this.businessInfo.push(response.data)
                this.newBusinessInfo.name = ''
                this.$root.businessName = 'Business Name'
                this.newBusinessInfo.address = ''
                this.$root.businessAddress = 'Business Address'
                this.newBusinessInfo.phoneNumber = ''
                this.$root.businessPhoneNumber = 'Business Phone Number'
                this.newBusinessInfo.email = ''
                this.$root.businessEmail = 'Business Email'
                this.errorBusinessInfo = ''

                console.log(this.$currentUsername.value)

            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorBusinessInfo = errorMsg
                })
            AXIOS.post('/createBusinessInformation/' + name + '/' + address + '/' + phoneNumber + '/' + email).then(response => {
                // JSON responses are automatically parsed.
                this.businessInfo.push(response.data)
                this.newBusinessInfo.name = ''
                this.$root.businessName = name
                this.newBusinessInfo.address = ''
                this.$root.businessAddress = address
                this.newBusinessInfo.phoneNumber = ''
                this.$root.businessPhoneNumber = phoneNumber
                this.newBusinessInfo.email = ''
                //this.$root.businessEmail = email
                this.errorBusinessInfo = ''

                console.log(this.$currentUsername.value)

            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorBusinessInfo = errorMsg
                })
        },
        goToAdminHome() {
            this.$router.push("/adminHome")
        }
    }
}