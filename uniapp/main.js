import Vue from 'vue'
import App from './App'
import uView from 'uview-ui' //ui
Vue.use(uView);
Vue.config.productionTip = false
Vue.prototype.apiServer = 'http://192.168.42.62:1114/'
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
