import axios from 'axios'
// import { response } from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


// const adminAccounts = AXIOS.post('/createAdminAccount').then(response => {
//     return response.data
// });

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
            response: []


        }
    },
    methods: {
        createAdminAccount: function (username, password, name) {
            AXIOS.post('/createAdminAccount/' + username + '/' + password + '/' + name).then(response => {
                // JSON responses are automatically parsed.
                this.adminAccounts.push(response.data)
                this.newAdminAccount.username = ''
                this.newAdminAccount.password = ''
                this.newAdminAccount.name = ''
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                })
        }
    }

}