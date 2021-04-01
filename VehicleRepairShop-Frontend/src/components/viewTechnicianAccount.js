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
                    this.$router.push("/viewTechnicianAccount");
                }
                else{
                    this.searchInput = "";
                    console.log("Not Found");
                }
                
        },
        deleteTechnicianAccount: function () {
            AXIOS.put('/deleteTechnicianAccount/' + this.$currentUsername.value).then(response => {
                this.technicianAccounts = response.data
                this.errorTechnicianAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/technicianAccountLogin");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTechnicianAccount = errorMsg
                })

        },
        logoutTechnicianAccount: function () {
            AXIOS.put('/logoutTechnicianAccount/' + this.$currentUsername.value).then(response => {
                this.selectedTechnicianAccount = response.data
                this.errorTechnicianAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/technicianAccountLogin");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTechnicianAccount = errorMsg
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
        // getTechnicianAccountByUsername: function () {
        //     AXIOS.get('/getTechnicianAccountByUsername/' + this.$currentUsername.value)
        //         .then(response => {
        //             this.selectedTechnicianAccount = response.data
        //         })
        //         .catch(e => {
        //             this.errorTechnicianAccount = e
        //         })
        // },
        goToEditTechnicianAccount: function () {
            this.$router.push("/editTechnicianAccount");
        }
    }

}