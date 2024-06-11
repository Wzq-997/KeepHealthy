import { createPinia } from "pinia";
import { createApp } from 'vue';
import App from './App.vue';

// 使用 Pinia
const pinia = createPinia();

// 创建 Vue 应用
const app = createApp(App);

// 使用 Pinia
app.use(pinia);

// 挂载 Vue 应用
app.mount('#app');