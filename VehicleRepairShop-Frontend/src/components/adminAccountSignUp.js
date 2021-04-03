import axios from 'axios'
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
            showPassword: false,
            showConfirmPassword: false

        }

    },
    methods: {
        createAdminAccount: function (username, password, confirmPassword, name) {
            if (password === confirmPassword) {
                AXIOS.post('/createAdminAccount/' + username + '/' + password + '/' + name).then(response => {
                    // JSON responses are automatically parsed.
                    this.adminAccounts.push(response.data)
                    this.newAdminAccount.username = ''
                    this.newAdminAccount.password = ''
                    this.newAdminAccount.name = ''
                    this.errorAdminAccount = ''
                    this.$currentUsername.value = username
                    this.$currentName.value = name
                    this.$router.push("/adminHome");

                    console.log(this.$currentUsername.value);
                    console.log(this.$currentName.value);

                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorAdminAccount = errorMsg
                    })
            }
            else {
                this.errorAdminAccount = "The passwords do not match."
            }
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
        goToAdminAccountLogin() {
            this.$router.push("/adminAccountLogin");
        }
    }

}