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
            response: []

        }

    },
    methods: {
        updateAdminAccount: function (newPassword, newName) {
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
              this.$router.push("/viewAdminAccount")
            }
    }

}