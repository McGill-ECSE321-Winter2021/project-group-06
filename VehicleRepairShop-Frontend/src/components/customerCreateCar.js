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
            searchInput: '',

        }
    },
    methods: {
        // navigation bar
        goBack() {
            this.$router.go(-1);
        },
        searchButton(searchInput) {
            this.searchInput = "";
            if (searchInput === "Home" || searchInput === "home" || searchInput === "Main" || searchInput === "main") {
                this.$router.push("/calendarCustomer");
            } else if (
                searchInput === "Profile" ||
                searchInput === "profile" ||
                searchInput === "Account" ||
                searchInput === "account"
            ) {
                this.$router.push("/viewCustomerAccount");
            } else if (searchInput === "Calendar" || searchInput === "calendar") {
                this.$router.push("/calendarCustomer");
            } else if (
                searchInput === "Edit" ||
                searchInput === "edit" ||
                searchInput === "Manage" ||
                searchInput === "manage"
            ) {
                this.$router.push("/editCustomerAccount");
            } else if (
                searchInput === "Car" ||
                searchInput === "car" ||
                searchInput == "Cars" ||
                search === "cars"
            ) {
                this.$router.push("/customerCreateCar");
            } else {
                this.searchInput = "";
                console.log("Not Found");
            }
        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
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
                this.getAllCars()


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
        getAllCars: function () {
            console.log("OK")
            AXIOS.get('/getCarsByOwner/' + this.$currentUsername.value).then(response => {
                // JSON responses are automatically parsed.
                this.cars = response.data
            })
                .catch(e => {
                    this.errorCar = e
                })

        },
        deleteCar: function (licensePlate) {

            if (confirm("Do you want to delete this car?\nYou cannot undo this action")) {
                console.log(licensePlate)
                this.toggleShowModal();
                console.log("SHOW1")
                AXIOS.put('/deleteCar/' + licensePlate).then(response => {
                    // JSON responses are automatically parsed.
                    console.log("HERE")
                    this.selectedCar = response.data
                    this.errorCar = ''
                    this.getAllCars()

                })
                    .catch(e => {
                        console.log(e)
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorCar = errorMsg
                    })
            }
            else {
                console.log("else")
            }

        },

    }
}