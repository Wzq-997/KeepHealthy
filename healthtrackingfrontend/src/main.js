// // import { createApp } from "vue";
// // import App from "./App.vue";
// // import router from "./router";
// // import store from "./store";

// import { createApp } from 'vue'
// import App from './App.vue'
// import router from './router'
// import mitt from 'mitt'
//
// const app=createApp(App)
// app.config.globalProperties.emitter = mitt()
//
// app.use(router)
// app.mount('#app')
// Vue 3 使用 createApp 和 ElementPlus
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import mitt from 'mitt'

const app = createApp(App)

app.config.globalProperties.emitter = mitt()
app.use(ElementPlus)
// 如果你的路由是 Vue Router 4，则也需要使用 app.use(router)
app.use(router)
app.use(store) // 如果你使用的是 Vuex 4

app.mount('#app')