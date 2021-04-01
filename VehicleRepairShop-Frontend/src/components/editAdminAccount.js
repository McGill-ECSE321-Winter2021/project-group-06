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
        updateAdminAccount: function (username, newPassword, newName) {
            AXIOS.put('/updateAdminAccount/' + username + '/' + newPassword + '/' + newName).then(response => {
                // JSON responses are automatically parsed.
                this.adminAccounts.push(response.data)
                this.newAdminAccount.username = ''
                this.newAdminAccount.password = '' //should this be .newPassword?
                this.newAdminAccount.name = ''
				this.errorAdminAccount = ''
				console.log(newAdminAccount.username + " " + newAdminAccount.name)
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorAdminAccount = errorMsg
                })
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
        goToViewAdminAccount(){
              this.$router.push("/viewAdminAccount");
            }
    }

}