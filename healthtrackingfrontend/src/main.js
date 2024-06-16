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

// 手动导入并注册你需要的图标
import { Close, Search,List,CopyDocument,FolderRemove,Bowl,Coordinate} from '@element-plus/icons-vue'

const app = createApp(App)
app.config.globalProperties.emitter = mitt()
// 全局注册图标
app.component('Close', Close)
app.component('Search', Search)
app.component('List', List)
app.component('CopyDocument', CopyDocument)
app.component('FolderRemove', FolderRemove)
app.component('Bowl', Bowl)
app.component('Coordinate', Coordinate)

app.use(ElementPlus)
app.use(router)
app.use(store)
app.mount('#app')
