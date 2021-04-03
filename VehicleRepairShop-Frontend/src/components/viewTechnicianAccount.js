import axios from 'axios'
import main from '../main'
// import { response } from 'express'
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
            if (searchInput === "Home"|| searchInput === "home") {
                this.$router.push("/calendarTechnician");
            } else if (
                searchInput === "Profile" ||
                searchInput === "profile" ||
                searchInput === "Account" ||
                searchInput === "account"
            ) {
                this.$router.push("/viewTechnicianAccount");
            } else if (searchInput === "Calendar" || searchInput === "calendar") {
                this.$router.push("/calendarTechnician");
            } else if (
                searchInput === "Edit" ||
                searchInput === "edit" ||
                searchInput === "Manage" ||
                searchInput === "manage"
            ) {
                this.$router.push("/editTechnicianAccount");
            } else {
                this.searchInput = "";
                console.log("Not Found");
            }
        },
        toggleShowModal() {
            this.isShowModal = true;
            console.log(this.isShowModal);
        },
        goToEditTechnicianAccount: function () {
            this.$router.push("/editTechnicianAccount");
        },
        // backend
        deleteTechnicianAccount: function () {
            if (confirm("Do you want to delete this account?\nYou cannot undo this action")) {
                this.toggleShowModal();
                AXIOS.put('/deleteTechnicianAccount/' + this.$currentUsername.value).then(response => {
                    this.technicianAccounts = response.data
                    this.errorTechnicianAccount = ''
                    this.$currentUsername.value = ''
                    this.$currentName.value = ''
                    this.$router.push("/");
                })
                    .catch(e => {
                        var errorMsg = e.response.data.message
                        console.log(errorMsg)
                        this.errorTechnicianAccount = errorMsg
                    })
            }

        },
        logoutTechnicianAccount: function () {
            AXIOS.put('/logoutTechnicianAccount/' + this.$currentUsername.value).then(response => {
                this.selectedTechnicianAccount = response.data
                this.errorTechnicianAccount = ''
                this.$currentUsername.value = ''
                this.$currentName.value = ''
                this.$router.push("/");
            })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorTechnicianAccount = errorMsg
                })
        },
        getAllTechnicianAccounts: function () {
            AXIOS.get('/getAllTechnicianAccounts')
                .then(response => {
                    this.technicianAccounts = response.data
                })
                .catch(e => {
                    this.errorTechnicianAccount = e
                })
        },
    }

}