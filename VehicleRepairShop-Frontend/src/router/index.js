import Vue from 'vue'
import Router from 'vue-router'
import Modal from "@burhanahmeed/vue-modal-2";
import Hello from '@/components/Hello'
import CalendarAdminAccount from '@/components/CalendarAdminAccount.vue'
import MainMenu from '@/components/MainMenu'
import SelectAccount from '@/components/SelectAccount'
import ViewAccount from '@/components/ViewAccount'
import EditAccount from '@/components/EditAccount'
import AdminAccountLogin from '@/components/AdminAccountLogin.vue'
import CustomerAccountLogin from '@/components/CustomerAccountLogin.vue'
import TechnicianAccountLogin from '@/components/TechnicianAccountLogin'
import SignUp from '@/components/SignUp'
import Test from '@/components/Test'

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
    
      path: '/signUp',
      name: 'SignUp',
      component: SignUp
    },
    {
    
      path: '/editAccount',
      name: 'EditAccount',
      component: EditAccount
    },
    {
      path: '/viewAccount',
      name: 'ViewAccount',
      component: ViewAccount
    },
    {
      path: '/test',
      name: 'Test',
      component: Test

    }
  ]
})
