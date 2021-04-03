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
            searchInput: '',
        }

    },
    methods: {
        // navigation bar
        goBack() {
            this.$router.push("/");

        },
        goToEditAdminAccount: function () {
            this.$router.push("/editAdminAccount");
        },
        searchButton(searchInput) {
            this.searchInput = "";
            if (searchInput === "Home") {
                this.$router.push("/adminHome");
            } else if (
                searchInput === "Profile" ||
                searchInput === "profile" ||
                searchInput === "Account" ||
                searchInput === "account"
            ) {
                this.$router.push("/viewAdminAccount");
            } else if (searchInput === "Calendar" || searchInput === "calendar") {
                this.$router.push("/calendarAdmin");
            } else if (
                searchInput === "Edit" ||
                searchInput === "edit" ||
                searchInput === "Manage" ||
                searchInput === "manage"
            ) {
                this.$router.push("/editAdminAccount");
            } else if (searchInput === "Services" || searchInput === "services" || searchInput === "Service" || searchInput === "service") {
                this.$router.push("/offeredServiceTableAdmin");
            }
            else if (
                searchInput === "Business" ||
                searchInput === "business" ||
                searchInput === "Info" ||
                search === "info"
            ) {
                this.$router.push("/adminBusinessInfo");
            } else {
                this.searchInput = "";
                console.log("Not Found");
            }
        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
        },
        // backend
        deleteAdminAccount: function () {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteAdminAccount/' + this.$currentUsername.value).then(response => {
                    this.adminAccounts = response.data
                    this.errorAdminAccount = ''
                    this.$currentUsername.value = ''
                    this.$currentName.value = ''
                    this.$router.push("/");

                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorAdminAccount = errorMsg
                    })

            }
        },
        logoutAdminAccount: function () {
            AXIOS.put('/logoutAdminAccount/' + this.$currentUsername.value).then(response => {
                this.selectedAdminAccount = response.data
                this.errorAdminAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorAdminAccount = errorMsg
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
    }

}