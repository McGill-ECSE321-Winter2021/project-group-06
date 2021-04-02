import axios from 'axios'
import main from '../main'
// import { response } from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
    props: ['currentUsername'],
    data() {
        return {
            customerAccounts: [],
            newCustomerAccount: {
                username: '',
                password: '',
                name: ''
            },
            selectedCustomerAccount: {
                username: '',
                password: '',
                name: ''
            },
            errorCustomerAccount: '',
            response: [],
            searchInput:'',
        }

    },
    methods: {
        goBack() {
            this.$router.go(-1);

        },
        searchButton(searchInput) {
            this.searchInput = ''
            if (searchInput === "Services" || searchInput === "services" || searchInput === "Service" || searchInput === "service") {
                this.$router.push("/offeredServiceTableCustomer");
            }
            else if (searchInput === "Profile" || searchInput === "profile" || searchInput === "Account" || searchInput === "account") {
                this.$router.push("/viewCustomerAccount");
            }
            else if (searchInput === "Calendar" || searchInput === "calendar" || searchInput === "Home" || searchInput === "home") {
                this.$router.push("/calendarCustomer")
            }
            else if (searchInput === "Edit" || searchInput === "edit" || searchInput === "Manage" || searchInput === "manage") {
                this.$router.push("/editCustomerAccount")
            }
            else {
                this.searchInput = "";
                console.log("Not Found");
            }

        },
        deleteCustomerAccount: function () {
            AXIOS.put('/deleteCustomerAccount/' + this.$currentUsername.value).then(response => {
                this.customerAccounts = response.data
                this.errorCustomerAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorCustomerAccount = errorMsg
                })

        },
        logoutCustomerAccount: function () {
            AXIOS.put('/logoutCustomerAccount/' + this.$currentUsername.value).then(response => {
                this.selectedCustomerAccount = response.data
                this.errorCustomerAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorCustomerAccount = errorMsg
                })
        },
        getAllCustomerAccounts: function () {
            AXIOS.get('/getAllCustomerAccounts')
                .then(response => {
                    this.customerAccounts = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
                })
        },
        // getCustomerAccountByUsername: function () {
        //     AXIOS.get('/getCustomerAccountByUsername/' + this.$currentUsername.value)
        //         .then(response => {
        //             this.selectedCustomerAccount = response.data
        //         })
        //         .catch(e => {
        //             this.errorCustomerAccount = e
        //         })
        // },
        goToEditCustomerAccount: function () {
            this.$router.push("/editCustomerAccount");
        }
    }

}