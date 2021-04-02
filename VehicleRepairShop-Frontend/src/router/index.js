import Vue from 'vue'
import Router from 'vue-router'
import Modal from "@burhanahmeed/vue-modal-2"
import CalendarAdminAccount from '@/components/CalendarAdminAccount.vue'
import CalendarCustomerAccount from '@/components/CalendarCustomerAccount.vue'
import CalendarTechnicianAccount from '@/components/CalendarTechnicianAccount.vue'
import OfferedServiceTableAdmin from '@/components/OfferedServiceTableAdmin.vue'
import SelectAccount from '@/components/SelectAccount.vue'
import ViewAdminAccount from '@/components/ViewAdminAccount.vue'
import ViewCustomerAccount from '@/components/ViewCustomerAccount.vue'
import ViewTechnicianAccount from '@/components/ViewTechnicianAccount.vue'
import EditAdminAccount from '@/components/EditAdminAccount.vue'
import EditCustomerAccount from '@/components/EditCustomerAccount.vue'
import EditTechnicianAccount from '@/components/EditTechnicianAccount.vue'
import AdminAccountLogin from '@/components/AdminAccountLogin.vue'
import CustomerAccountLogin from '@/components/CustomerAccountLogin.vue'
import TechnicianAccountLogin from '@/components/TechnicianAccountLogin.vue'
import AdminAccountSignUp from '@/components/AdminAccountSignUp.vue'
import CustomerAccountSignUp from '@/components/CustomerAccountSignUp.vue'
import TechnicianAccountSignUp from '@/components/TechnicianAccountSignUp.vue'
import AdminHome from '@/components/AdminHome.vue'
import Car from '@/components/CustomerCreateCar.vue'

Vue.use(Router)
Vue.use(Modal);


export default new Router({
  routes: [
    {
      path: '/',
      name: 'SelectAccount',
      component: SelectAccount
    },
    {
      path:'/offeredServiceTableAdmin',
      name:'OfferedServiceTableAdmin',
      component: OfferedServiceTableAdmin
    },
    {

      path: '/calendarAdmin',
      name: 'CalendarAdminAccount',
      component: CalendarAdminAccount
    },
    {

      path: '/calendarTechnician',
      name: 'CalendarTechnicianAccount',
      component: CalendarTechnicianAccount
    },
    {

      path: '/calendarCustomer',
      name: 'CalendarCustomerAccount',
      component: CalendarCustomerAccount
    },
    {
    
      path: '/adminHome',
      name: 'AdminHome',
      component: AdminHome
    },
    {
    
      path: '/adminAccountLogin',
      name: 'AdminAccountLogin',
      component: AdminAccountLogin
    },
    {
    
      path: '/customerAccountLogin',
      name: 'CustomerAccountLogin',
      component: CustomerAccountLogin
    },
    {
      path: '/technicianAccountLogin',
      name: 'TechnicianAccountLogin',
      component: TechnicianAccountLogin
    },
    {

      path: '/adminAccountSignUp',
      name: 'AdminAccountSignUp',
      component: AdminAccountSignUp
    },
    {
      path: '/customerAccountSignUp',
      name: 'CustomerAccountSignUp',
      component: CustomerAccountSignUp
    },
    {
      path: '/technicianAccountSignUp',
      name: 'TechnicianAccountSignUp',
      component: TechnicianAccountSignUp
    },
    {
    
      path: '/editAdminAccount',
      name: 'EditAdminAccount',
      component: EditAdminAccount
    },
    {
    
      path: '/editCustomerAccount',
      name: 'EditCustomerAccount',
      component: EditCustomerAccount
    },
    {
    
      path: '/editTechnicianAccount',
      name: 'EditTechnicianAccount',
      component: EditTechnicianAccount
    },



    {
      path: '/viewAdminAccount',
      name: 'ViewAdminAccount',
      component: ViewAdminAccount
    },
    {
      path: '/viewCustomerAccount',
      name: 'ViewCustomerAccount',
      component: ViewCustomerAccount
    },
    {
      path: '/viewTechnicianAccount',
      name: 'ViewTechnicianAccount',
      component: ViewTechnicianAccount
    },
    {
      path: '/car',
      name: 'car',
      component: Car
    }
  ]
})
