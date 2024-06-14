import { createRouter, createWebHashHistory } from "vue-router";
import loginVue from "@/login/login.vue";
const routes = [
  {
    path: "/",
    redirect: "/login", //默认重定向到login界面
  },
  {
    path: "/login",
    name: "login",
    component: loginVue,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
