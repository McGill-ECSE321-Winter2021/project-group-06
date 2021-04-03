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
            cars: [],
            newCar: {
                licensePlate: '',
                model: '',
                year: '',
                motorType: '',
                username: ''
            },
            selectedCar: {
                licensePlate: '',
                model: '',
                year: '',
                motorType: '',
                username: ''
            },
            errorCar: '',
            response: [],         
        }
    },
    methods: {
        createCar: function (licensePlate, model, year, motorType, username) {
            AXIOS.post('/createCar/' + licensePlate + '/' + model + '/' + year + '/' + motorType + '/' + username).then(response => {
                // JSON responses are automatically parsed.
                this.cars.push(response.data)
                this.newCar.licensePlate = ''
                this.newCar.model = ''
                this.newCar.year = ''
                this.newCar.motorType = ''
                this.newCar.username = ''
                this.errorCar = ''
                this.$currentUsername.value = username
                this.$router.push("/customerViewCars");

                console.log(this.$currentUsername.value);

            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorCar = errorMsg
                })
        },
        goToCarCustomerView() {
            this.$router.push("/customerViewCars");
        }
    }
}