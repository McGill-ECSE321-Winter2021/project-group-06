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
            showPassword: false


        }

    },

    methods: {
        loginAdminAccount: function (username, password) {
            AXIOS.put('/loginAdminAccount/' + username + '/' + password).then(response => {
                this.selectedAdminAccount = response.data
                this.$currentUsername.value = username
                this.$currentName.value = this.selectedAdminAccount.name
                console.log(this.$currentUsername.value)
                console.log(this.$currentName.value)
                //this.$router.push("/calendarAdmin")
                this.$router.push("/adminHome")
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
        getAdminAccountByUsername: function (username) {
            AXIOS.get('/getAdminAccountByUsername/' + username)
                .then(response => {
                    this.selectedAdminAccount = response.data
                })
                .catch(e => {
                    this.errorAdminAccount = e
                })
        },

    }
}