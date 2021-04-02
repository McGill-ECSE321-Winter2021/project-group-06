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
            showPassword: false


        }

    },

    methods: {
        loginTechnicianAccount: function (username, password) {
            AXIOS.put('/loginTechnicianAccount/' + username + '/' + password).then(response => {
                this.selectedTechnicianAccount = response.data
                this.$currentUsername.value = username
                this.$currentName.value = this.selectedTechnicianAccount.name
                console.log(this.$currentUsername.value)
                console.log(this.$currentName.value)
                this.$router.push("/viewTechnicianAccount")
            
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
        getTechnicianAccountByUsername: function (username) {
            AXIOS.get('/getTechnicianAccountByUsername/' + username)
                .then(response => {
                    this.selectedTechnicianAccount = response.data
                })
                .catch(e => {
                    this.errorTechnicianAccount = e
                })
        },

    }
}