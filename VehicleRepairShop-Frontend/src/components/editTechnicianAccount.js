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
            technicianAccounts: [],
            newTechnicianAccount: {
                username: '',
                password: '',
                name: ''
            },
            selectedTechnicianAccount: {
                username: '',
                password: '',
                name: ''
            },
            errorTechnicianAccount: '',
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
            if (searchInput === "Home" || searchInput === "home" || searchInput === "Main" || searchInput === "main" ) {
              this.$router.push("/calendarTechnician");
            } else if (
              searchInput === "Profile" ||
              searchInput === "profile" ||
              searchInput === "Account" ||
              searchInput === "account"
            ) {
              this.$router.push("/viewTechnicianAccount");
            } else if (searchInput === "Calendar" || searchInput === "calendar") {
              this.$router.push("/calendarTechnician");
            } else if (
              searchInput === "Edit" ||
              searchInput === "edit" ||
              searchInput === "Manage" ||
              searchInput === "manage"
            ) {
              this.$router.push("/editTechnicianAccount");
            } else {
              this.searchInput = "";
              console.log("Not Found");
            }
          },
        // backend
        updateTechnicianAccount: function (confirmPassword, newName) {
            if (newPassword === confirmPassword) {
                AXIOS.put('/updateTechnicianAccount/' + this.$currentUsername.value + '/' + newPassword + '/' + newName).then(response => {
                    this.selectedTechnicianAccount = response.data
                    this.$currentName.value = this.selectedTechnicianAccount.name
                    console.log(this.$currentUsername.value)
                    console.log(this.$currentName.value)
                    this.$router.push("/viewTechnicianAccount")
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTechnicianAccount = errorMsg
                    })
            }
            else {
                this.errorTechnicianAccount = "The passwords do not match."
            }
        },
        getAllTechnicianAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/getAllTechnicianAccounts')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.TechnicianAccounts = response.data
                })
                .catch(e => {
                    this.errorTechnicianAccount = e
                })
        },
        goToViewTechnicianAccount() {
            this.$router.push("/viewTechnicianAccount")
        }
    }

}