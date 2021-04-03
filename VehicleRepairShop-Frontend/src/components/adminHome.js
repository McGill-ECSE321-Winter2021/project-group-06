import axios from 'axios'
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
            garages: [],
            adminAccounts: [],
            customerAccounts: [],
            technicianAccounts: [],
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
            errorGarage: '',
            errorAdminAccount: '',
            errorCustomerAccount: '',
            errorTechnicianAccount: '',
            response: [],
            searchInput: '',
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
                console.log("Not Found!");
            }

        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
        },
        refreshTables(){
            this.getAllAdminAccounts()
            this.getAllCustomerAccounts()
            this.getAllTechnicianAccounts()
        },
        // backend
        deleteAdminAccount: function () {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteAdminAccount/' + this.$currentUsername.value).then(response => {
                    this.adminAccounts = response.data
                    this.selectedAdminAccount.username = ''
                    this.errorAdminAccount = ''
                    this.$currentUsername.value = ''
                    this.$currentName.value = ''
                    this.$router.push("/adminAccountLogin");
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorAdminAccount = errorMsg
                    })
            }

        },
        getAllAdminAccounts: function () {
            AXIOS.get('/getAllAdminAccounts')
                .then(response => {
                    this.adminAccounts = response.data
                })
                .catch(e => {
                    this.errorAdminAccount = e
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
        getAllTechnicianAccounts: function () {
            AXIOS.get('/getAllTechnicianAccounts')
                .then(response => {
                    this.technicianAccounts = response.data
                })
                .catch(e => {
                    this.errorTechnicianAccount = e
                })
        },
        deleteAdminAccount: function (username) {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteAdminAccountFromAdmin/' + username).then(response => {
                    this.adminAccounts = response.data
                    this.errorAdminAccount = ''
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorAdminAccount = errorMsg
                    })
            }
        },
        deleteCustomerAccount: function (username) {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteCustomerAccountFromAdmin/' + username).then(response => {
                    this.customerAccounts = response.data
                    this.errorCustomerAccount = ''
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorCustomerAccount = errorMsg
                    })
            }
        },
        deleteTechnicianAccount: function (username) {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteTechnicianAccountFromAdmin/' + username).then(response => {
                    this.technicianAccounts = response.data
                    this.errorTechnicianAccount = ''
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTechnicianAccount = errorMsg
                    })
            }
        },
    }

}