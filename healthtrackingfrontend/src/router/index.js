import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import login from "../views/login.vue";
import system from "@/views/system.vue";
import sports from "../views/sports.vue";
import sportwords from "../views/sportwords.vue";
import dietRecord from "../views/DietRecordView.vue";
import dailyDiet from "../views/DailyDietView.vue";
const routes = [
  {
    path: "/login",
    name: "登录",
    component: login,
  },

  {
    path: "/",
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
      {
        path: "/dietRecord",
        name: "饮食记录",
        component: dietRecord,
      },
      {
        path: "/dailyDiet",
        name: "健康食谱",
        component: dailyDiet,
      },
    ],
  },


];
//
const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;