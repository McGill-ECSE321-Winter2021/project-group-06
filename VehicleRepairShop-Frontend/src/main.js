// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en'
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI ,{locale});
Vue.use(VueMaterial)
Vue.use(BootstrapVue)
Vue.config.productionTip = false

Vue.prototype.$currentUsername = {value: ""};
Vue.prototype.$currentName = {value: ""};

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  template: '<App/>',
  components: { App }
})
