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
            errorAdminAccount: '',
            errorCustomerAccount: '',
            errorTechnicianAccount: '',
            response: [],
            searchInput:'',
        }

    },
    methods: {
        goBack() {
            this.$router.go(-1);

        },
        // Add all the pages
        searchButton(searchInput){
                this.searchInput = ''
                if (searchInput === "Home"){
                    this.$router.push("/app");
                }
                else if (searchInput === "Services" || searchInput === "services" || searchInput === "Service" || searchInput === "service"){
                    this.$router.push("/offeredServiceTable");
                }
                else if (searchInput === "Profile" || searchInput === "profile" || searchInput === "Account" || searchInput === "account"){
                    this.$router.push("/viewAdminAccount");
                }
                else{
                    this.searchInput = "";
                    console.log("Not Found");
                }
                
        },
        deleteAdminAccount: function () {
            console.log(this.$currentUsername.value);
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
        }
    }

}