import Vue from 'vue'
import Router from 'vue-router'
import Modal from "@burhanahmeed/vue-modal-2";
import Hello from '@/components/Hello'

import FirstCompoent from '@/components/FirstCompoent'
import OfferedServiceTable from '@/components/OfferedServiceTable'
import CalendarAdminAccount from '@/components/CalendarAdminAccount.vue'
import MainMenu from '@/components/MainMenu'
import ViewAccount from '@/components/ViewAccount'
import EditAccount from '@/components/EditAccount'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Test from '@/components/Test'


Vue.use(Router)
Vue.use(Modal);


export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {

      path: '/app',
      name: 'FirstCompoent',
      component: FirstCompoent
    },
    {
      path:'/OfferedServiceTable',
      name:'OfferedServiceTable',
      component: OfferedServiceTable


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
    
      path: '/login',
      name: 'Login',
      component: Login
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
