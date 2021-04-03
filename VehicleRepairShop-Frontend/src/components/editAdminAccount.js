import axios from 'axios'
// import main from '../main'
// import { response } from 'express'
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
            adminAccounts: [],
            newAdminAccount: {
                username: '',
                password: '',
                name: ''
            },
            selectedAdminAccount: {
                username: '',
                password: '',
                name: ''
            },
            errorAdminAccount: '',
            response: [],
            showPassword: false,
            showConfirmPassword: false,
            searchInput: '',

        }

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
        // backend
        updateAdminAccount: function (newPassword, confirmPassword, newName) {
            if (newPassword === confirmPassword) {
                AXIOS.put('/updateAdminAccount/' + this.$currentUsername.value + '/' + newPassword + '/' + newName).then(response => {
                    this.selectedAdminAccount = response.data
                    this.$currentName.value = this.selectedAdminAccount.name
                    console.log(this.$currentUsername.value)
                    console.log(this.$currentName.value)
                    this.$router.push("/viewAdminAccount")
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorAdminAccount = errorMsg
                    })
            }
            else {
                this.errorAdminAccount = "The passwords do not match."
            }
        },
        getAllAdminAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/getAllAdminAccounts')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.adminAccounts = response.data
                })
                .catch(e => {
                    this.errorAdminAccount = e
                })
        },
        goToViewAdminAccount() {
            this.$router.push("/viewAdminAccount")
        }
    }

}