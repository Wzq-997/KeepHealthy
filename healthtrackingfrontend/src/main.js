import { createApp } from "vue";
import App from "./App.vue";
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'  // 引入 Element Plus 样式文件
import router from "./router";
// import store from "./store"; 移除这行
import { createPinia } from "pinia";

// 使用 Pinia
const pinia = createPinia();

createApp(App).use(router).use(pinia).use(ElementPlus).mount("#app");