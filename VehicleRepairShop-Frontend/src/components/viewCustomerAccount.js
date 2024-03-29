import axios from 'axios'
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
            if (searchInput === "Home" || searchInput === "home" || searchInput === "Main" || searchInput === "main" ) {
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
        goToEditCustomerAccount: function () {
            this.$router.push("/editCustomerAccount");
        },
        // backend
        deleteCustomerAccount: function () {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteCustomerAccount/' + this.$currentUsername.value).then(response => {
                    this.customerAccounts = response.data
                    this.errorCustomerAccount = ''
                    this.$currentUsername.value = ''
                    this.$currentName.value = ''
                    this.$router.push("/");
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorCustomerAccount = errorMsg
                    })
            }

        },
        logoutCustomerAccount: function () {
            AXIOS.put('/logoutCustomerAccount/' + this.$currentUsername.value).then(response => {
                this.selectedCustomerAccount = response.data
                this.errorCustomerAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/");
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
        }
    }

}