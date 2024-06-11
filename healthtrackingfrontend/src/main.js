// // import { createApp } from "vue";
// // import App from "./App.vue";
// // import router from "./router";
// // import store from "./store";

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'

const app=createApp(App)
app.config.globalProperties.emitter = mitt()

app.use(router)
app.mount('#app')
