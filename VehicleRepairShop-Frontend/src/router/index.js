import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import CalendarAdmin from '@/components/CalendarAdminAccount'
import MainMenu from '@/components/MainMenu'
import ViewAccount from '@/components/ViewAccount'
import Test from '@/components/Test'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {

      path: '/calendarAdmin',
      name: 'CalendarAdmin',
      component: CalendarAdmin
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

    }
  ]
})
