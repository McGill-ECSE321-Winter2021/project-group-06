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
            searchInput: ''

        }
    },
    methods: {
        // navigation bar
    goBack() {
      this.$router.go(-1);
    },
    searchButton(searchInput) {
      this.searchInput = "";
      if (searchInput === "Home") {
        this.$router.push("/calendarCustomer");
      } else if (
        searchInput === "Services" ||
        searchInput === "services" ||
        searchInput === "Service" ||
        searchInput === "service"
      ) {
        this.$router.push("/offeredServiceTableCustomer");
      } else if (
        searchInput === "Profile" ||
        searchInput === "profile" ||
        searchInput === "Account" ||
        searchInput === "account"
      ) {
        this.$router.push("/viewCustomerAccount");
      } else if (searchInput === "Calendar" || searchInput === "calendar") {
        this.$router.push("/calendarCustomer");
      } else {
        this.searchInput = "";
        console.log("Not Found");
      }
    },

        createCar: function (licensePlate, model, year, motorType) {
            AXIOS.post('/createCar/' + licensePlate + '/' + model + '/' + year + '/' + motorType + '/' + this.$currentUsername.value).then(response => {
                // JSON responses are automatically parsed.
                this.cars.push(response.data)
                this.newCar.licensePlate = ''
                this.newCar.model = ''
                this.newCar.year = ''
                this.newCar.motorType = ''
                this.errorCar = ''


            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorCar = errorMsg
                })
        },
        goToCarCustomerView() {
            this.$router.push("/customerViewCars");
        },
        getAllCars: function (){
            AXIOS.post('/getCarsByOwner/' + this.$currentUsername.value).then(response => {
                // JSON responses are automatically parsed.
                    this.cars = response.data
                })
                .catch(e => {
                    this.errorCar = e
                })

        },
        deleteCar: function (licensePlate) {
            if (confirm("Do you want to delete this car?\nYou cannot undo this action")) {
                AXIOS.post('/deleteCar/' + licensePlate).then(response => {
                    // JSON responses are automatically parsed.
                    this.cars.push(response.data)
                    this.errorCar = ''

                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorCar = errorMsg
                    })
            }

        },

    }
}