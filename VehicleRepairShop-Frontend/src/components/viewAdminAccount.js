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
            response: []


        }

    },
    methods: {
        goBack() {
            this.$router.go(-1);

        },
        deleteAdminAccount: function () {
            console.log(this.$currentUsername.value);
            AXIOS.put('/deleteAdminAccount/' + this.$currentUsername.value).then(response => {
                this.adminAccounts = response.data
                this.selectedAdminAccount.username = ''
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
                this.adminAccounts = response.data
                this.selectedAdminAccount.username = ''
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
        goToEditAdminAccount: function () {
            this.$router.push("/editAdminAccount");
        }
    }

}