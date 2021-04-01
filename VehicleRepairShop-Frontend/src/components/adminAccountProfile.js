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
            selectedAdminAccount: '',
            errorAdminAccount: '',
            response: []


        }

    },
    methods: {
        logoutAdminAccount: function () {
            AXIOS.put('/logoutAdminAccount/' + username).then(response => {
                this.adminAccounts = response.data
                main.username = ''
                main.accountType = ''
            })
                .catch(e => {
                    console.log(e)
                    this.errorAdminAccount = e
                })
        }
    }

}