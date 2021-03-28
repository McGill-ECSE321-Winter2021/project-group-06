import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import CalendarAdmin from '@/components/CalendarAdminAccount'

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
    }
  ]
})
