import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
// import store from "./store"; 移除这行
import { createPinia } from "pinia";

// 使用 Pinia
const pinia = createPinia();

createApp(App).use(router).use(pinia).mount("#app");