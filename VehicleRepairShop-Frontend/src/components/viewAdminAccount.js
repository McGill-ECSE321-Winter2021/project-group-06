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
            AXIOS.put('/deleteAdminAccount/' + this.$currentUsername.value).then(response => {
                this.adminAccounts = response.data
                this.errorAdminAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/adminAccountLogin");
            })
                .catch(e => {
                    console.log(e)
                    this.errorAdminAccount = e
                })

        },
        logoutAdminAccount: function () {
            AXIOS.put('/logoutAdminAccount/' + this.$currentUsername.value).then(response => {
                this.selectedAdminAccount = response.data
                this.errorAdminAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/adminAccountLogin");
            })
                .catch(e => {
                    console.log(e)
                    this.errorAdminAccount = e
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
        // getAdminAccountByUsername: function () {
        //     AXIOS.get('/getAdminAccountByUsername/' + this.$currentUsername.value)
        //         .then(response => {
        //             this.selectedAdminAccount = response.data
        //         })
        //         .catch(e => {
        //             this.errorAdminAccount = e
        //         })
        // },
        goToEditAdminAccount: function () {
            this.$router.push("/editAdminAccount");
        }
    }

}