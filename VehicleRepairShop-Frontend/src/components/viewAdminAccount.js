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
            response: []


        }

    },
        methods: {
        deleteAdminAccount: function (username) {
            AXIOS.put('/deleteAdminAccount/' + username).then(response => {
                this.adminAccounts = response.data
                this.selectedAdminAccount.username = ''
                this.errorAdminAccount = ''
                this.$router.push("/adminAccountLogin");
            })
                .catch(e => {
                    console.log(e)
                    this.errorAdminAccount = e
                })
                
        },
        logoutAdminAccount: function (username) {
            AXIOS.put('/logoutAdminAccount/' + username).then(response => {
                this.adminAccounts = response.data
                this.selectedAdminAccount.username = ''
                this.errorAdminAccount = ''
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
        goToEditAdminAccount: function (){
            this.$router.push("/editAdminAccount");
        }
    }

}