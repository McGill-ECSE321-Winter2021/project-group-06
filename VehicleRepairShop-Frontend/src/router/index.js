import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import ViewAccount from '@/components/ViewAccount'

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
      name: 'ViewAccount',
      component: ViewAccount
    }
  ]
})
