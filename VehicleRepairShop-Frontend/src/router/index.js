import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import FirstCompoent from '@/components/FirstCompoent'
import OfferedService from '@/components/OfferedService'



Vue.use(Router)

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
      path: '/table',
      name: 'OfferedService',
      component: OfferedService
    },


  ]
})
