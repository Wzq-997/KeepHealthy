import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import login from "../views/login.vue";
import system from "@/views/system.vue";
import sports from "../views/sports.vue";
import sportwords from "../views/sportwords.vue";
import {jwtDecode} from "jwt-decode";
import {useUserStore} from "@/store";
import healthyStatus from "../views/healthyStatus.vue"

const routes = [
  {
    path: "/login",
    name: "登录",
    component: login,
  },
  {
    path: "/",
    name: "健康状态",
    component: healthyStatus,
  },
  {
    path: "/main",
    name: "系统",
    component: system,
    redirect: "/sportwords",
    children: [
      {
        path: "/sportwords",
        name: "运动处方",
        component: sportwords,
      },
      {
        path: "/sports",
        name: "自主运动",
        component: sports,
      },

    ],
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
  routes,
});
// 开启路由守卫
router.beforeEach((to,from,next)=> {
  // const isAuthenticated = localStorage.getItem('token')
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  const token = localStorage.getItem("token")
  if (token == null) {
    router.push("/")
  } else {
    try {
      const decoded = jwtDecode(token); // 使用jwt-decode库或类似的库来解码JWT
      if (decoded.exp < Date.now() / 1000) {
        console.log("exp:" + decoded.exp)
        console.log("now:" + Date.now() / 1000)
        router.push("/login")
      } else {
        const user = useUserStore()
        user.setToken(token)
        console.log(user.userInfo.name)
        next()
      }
    } catch (err) {
      console.log(err)
    }
  }
})
export default router;
