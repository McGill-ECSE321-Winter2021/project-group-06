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
            selectedAdminAccount: '',
            errorAdminAccount: '',
            response: []


        }

    },
    methods: {
        loginAdminAccount: function (username, password) {
            AXIOS.put('/loginAdminAccount/' + username + '/' + password).then(response => {
                this.adminAccounts = response.data
                // main.username = username
                // main.accountType = 'Admin'
            })
                .catch(e => {
                    console.log(e)
                    this.errorAdminAccount = e
                })
                this.$router.push("/calendarAdmin");
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
    }

}