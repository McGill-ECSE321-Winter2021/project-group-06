import Vue from 'vue'
import Router from 'vue-router'
import Modal from "@burhanahmeed/vue-modal-2";
import Hello from '@/components/Hello'
import MainMenu from '@/components/MainMenu'
import ViewAccount from '@/components/ViewAccount'
import Test from '@/components/Test'
import EditAccount from '@/components/EditAccount'

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
      name: 'MainMenu',
      component: MainMenu
    },
    {
      path: '/ViewAccount',
      name: 'ViewAccount',
      component: ViewAccount
    },
    {
      path: '/Test',
      name: 'Test',
      component: Test
    },
    {
      path: '/EditAccount',
      name: 'EditAccount',
      component: EditAccount
    }
  ]
})
