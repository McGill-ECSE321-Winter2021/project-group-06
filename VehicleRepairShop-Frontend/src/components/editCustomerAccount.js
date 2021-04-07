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
      showConfirmPassword: false,
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
    // backend
    updateCustomerAccount: function (newPassword, confirmPassword, newName) {
      if (newPassword === confirmPassword) {
        AXIOS.put('/updateCustomerAccount/' + this.$currentUsername.value + '/' + newPassword + '/' + newName).then(response => {
          this.selectedCustomerAccount = response.data
          this.$currentName.value = this.selectedCustomerAccount.name
          console.log(this.$currentUsername.value)
          console.log(this.$currentName.value)
          this.$router.push("/viewCustomerAccount")
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
          this.CustomerAccounts = response.data
        })
        .catch(e => {
          this.errorCustomerAccount = e
        })
    },
    goToViewCustomerAccount() {
      this.$router.push("/viewCustomerAccount")
    }
  }

}