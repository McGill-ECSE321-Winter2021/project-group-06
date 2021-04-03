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
            businessInfo: [],
            newBusinessInfo: {
                name: '',
                address: '',
                phoneNumber: '',
                email: '',
            },
            errorBusinessInfo: '',
            response: [],         
        }
    },
    methods: {
        createBusinessInfo: function (name, address, phoneNumber, email) {
            AXIOS.post('/createBusinessInformation/' + name + '/' + address + '/' + phoneNumber + '/' + email).then(response => {
                // JSON responses are automatically parsed.
                this.businessInfo.push(response.data)
                this.newBusinessInfo.name = ''
                this.newBusinessInfo.address = ''
                this.newBusinessInfo.phoneNumber = ''
                this.newBusinessInfo.email = ''
                this.errorBusinessInfo = ''
                this.$router.push("/adminHome");

                console.log(this.$currentUsername.value);

            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorBusinessInfo = errorMsg
                })
        },
        goToAdminHome() {
            this.$router.push("/adminHome");
        }
    }
}