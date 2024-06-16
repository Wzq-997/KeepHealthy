import { createRouter, createWebHistory ,RouteRecordRaw} from "vue-router";
import login from "../views/login.vue";
import system from "@/views/system.vue";
import sportwords from "../views/sportwords.vue"
const routes = [
  {
    path: "/login",
    name: "登录",
    component: login
  },


  {
    path: "/",
    name: "系统",
    component:system,
    children:[
      {
        path: "/sportwords",
        name: "运动处方",
        component:sportwords
      },
    ]
  },

  // {
  //   path: "/about",
  //   name: "about",
  //
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
