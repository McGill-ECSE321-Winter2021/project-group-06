import Vue from 'vue'
import Router from 'vue-router'
import Modal from "@burhanahmeed/vue-modal-2"
import Hello from '@/components/Hello'
import CalendarAdminAccount from '@/components/CalendarAdminAccount.vue'
import OfferedServiceTable from '@/components/OfferedServiceTable'
import MainMenu from '@/components/MainMenu'
import SelectAccount from '@/components/SelectAccount.vue'
import ViewAdminAccount from '@/components/ViewAdminAccount.vue'
import EditAdminAccount from '@/components/EditAdminAccount.vue'
import AdminAccountLogin from '@/components/AdminAccountLogin.vue'
import CustomerAccountLogin from '@/components/CustomerAccountLogin.vue'
import TechnicianAccountLogin from '@/components/TechnicianAccountLogin.vue'
import AdminAccountSignUp from '@/components/AdminAccountSignUp.vue'
import Test from '@/components/Test.vue'

Vue.use(Router)
Vue.use(Modal);


export default new Router({
  routes: [
    {
      path: '/',
      name: 'SelectAccount',
      component: SelectAccount
    },
    // {

    //   path: '/app',
    //   name: 'FirstCompoent',
    //   component: FirstCompoent
    // },
    {
      path:'/OfferedServiceTable',
      name:'OfferedServiceTable',
      component: OfferedServiceTable
    },
    {

      path: '/calendarAdmin',
      name: 'CalendarAdminAccount',
      component: CalendarAdminAccount
    },
    {
    
      path: '/app',
      name: 'MainMenu',
      component: MainMenu
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
    
      path: '/editAdminAccount',
      name: 'EditAdminAccount',
      component: EditAdminAccount
    },

    {
      path: '/viewAdminAccount',
      name: 'ViewAdminAccount',
      component: ViewAdminAccount
    },
    {
      path: '/test',
      name: 'Test',
      component: Test

    }
  ]
})
