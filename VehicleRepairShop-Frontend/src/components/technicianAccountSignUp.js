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
            showPassword: false,
            showConfirmPassword: false

        }

    },
    methods: {
        createTechnicianAccount: function (username, password, confirmPassword, name) {
            if (password === confirmPassword) {
                AXIOS.post('/createTechnicianAccount/' + username + '/' + password + '/' + name).then(response => {
                    // JSON responses are automatically parsed.
                    this.technicianAccounts.push(response.data)
                    this.newTechnicianAccount.username = ''
                    this.newTechnicianAccount.password = ''
                    this.newTechnicianAccount.name = ''
                    this.errorTechnicianAccount = ''
                    this.$currentUsername.value = username
                    this.$currentName.value = name
                    this.$router.push("/calendarTechnician")

                    console.log(this.$currentUsername.value);
                    console.log(this.$currentName.value);

                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTechnicianAccount = errorMsg
                    })
            }
            else {
                this.errorTechnicianAccount = "The passwords do not match."
            }

        },
        getAllTechnicianAccounts: function () {
            // Initializing persons from backend
            AXIOS.get('/getAllTechnicianAccounts')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.technicianAccounts = response.data
                })
                .catch(e => {
                    this.errorTechnicianAccount = e
                })
        },
        goToTechnicianAccountLogin() {
            this.$router.push("/technicianAccountLogin");
        }
    }

}