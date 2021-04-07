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
            customerAccounts: [],
            newCustomerAccount: {
                username: '',
                password: '',
                name: ''
            },
            selectedCustomerAccount: {
                username: '',
                password: '',
                name: ''
            },
            errorCustomerAccount: '',
            response: [],
            showPassword: false,
            showConfirmPassword: false

        }

    },
    methods: {
        createCustomerAccount: function (username, password, confirmPassword, name) {
            if (password === confirmPassword) {
                AXIOS.post('/createCustomerAccount/' + username + '/' + password + '/' + name).then(response => {
                    // JSON responses are automatically parsed.
                    this.customerAccounts.push(response.data)
                    this.newCustomerAccount.username = ''
                    this.newCustomerAccount.password = ''
                    this.newCustomerAccount.name = ''
                    this.errorCustomerAccount = ''
                    this.$currentUsername.value = username
                    this.$currentName.value = name
                    this.$router.push("/calendarCustomer")
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorCustomerAccount = errorMsg
                    })
            }
            else {
                this.errorCustomerAccount = "The passwords do not match."
            }
        },
        getAllCustomerAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/getAllCustomerAccounts')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.customerAccounts = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
                })
        },
        goToCustomerAccountLogin() {
            this.$router.push("/customerAccountLogin");
        }
    }

}