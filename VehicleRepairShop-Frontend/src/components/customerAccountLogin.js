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
            showPassword: false


        }

    },

    methods: {
        loginCustomerAccount: function (username, password) {
            AXIOS.put('/loginCustomerAccount/' + username + '/' + password).then(response => {
                this.selectedCustomerAccount = response.data
                this.$currentUsername.value = username
                this.$currentName.value = this.selectedCustomerAccount.name
                console.log(this.$currentUsername.value)
                console.log(this.$currentName.value)
                this.$router.push("/calendarCustomer")
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorCustomerAccount = errorMsg
                })

        },
        getAllCustomerAccounts: function () {
            AXIOS.get('/getAllCustomerAccounts')
                .then(response => {
                    this.customerAccounts = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
                })
        },
        getCustomerAccountByUsername: function (username) {
            AXIOS.get('/getCustomerAccountByUsername/' + username)
                .then(response => {
                    this.selectedCustomerAccount = response.data
                })
                .catch(e => {
                    this.errorCustomerAccount = e
                })
        },

    }
}