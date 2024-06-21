
import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "./App.vue";
import router from "./router";
import mitt from "mitt";
import * as echarts from 'echarts';
import { createPinia } from 'pinia';
// 手动导入并注册你需要的图标
import {
  Close,
  Search,
  List,
  CopyDocument,
  FolderRemove,
  Bowl,
  Coordinate,
} from "@element-plus/icons-vue";

const app = createApp(App);
app.config.globalProperties.emitter = mitt();
// 全局注册图标
app.component("Close", Close);
app.component("Search", Search);
app.component("List", List);
app.component("CopyDocument", CopyDocument);
app.component("FolderRemove", FolderRemove);
app.component("Bowl", Bowl);
app.component("Coordinate", Coordinate);
app.component("Echarts",echarts)
app.use(ElementPlus);
app.use(router);

const pinia = createPinia();
app.use(pinia);
app.mount("#app");

