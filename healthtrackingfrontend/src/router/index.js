import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import login from "../views/login.vue";
import system from "@/views/system.vue";
import sports from "../views/sports.vue";
import sportwords from "../views/sportwords.vue";
import healthyStatus from "../views/healthyStatus (2).vue"
import Mine from "../views/Mine.vue"
import Advice from "@/views/Advice.vue";
import DailyDietView from "@/views/DailyDietView.vue";
import DietRecordView from "@/views/DietRecordView.vue";
import {jwtDecode} from "jwt-decode";
import {useUserStore} from "@/store";

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
        path: "/advice",
        name: "运动建议",
        component: Advice,
        meta: {
          requiresAuth: true, // 添加此属性，表示此页面需要登录才能访问
        },
        beforeEnter: (to, from, next) => {
          const token = localStorage.getItem('token');
          if (token) {
            // 如果token存在，则继续导航到目标页面
            next();
          } else {
            // 如果token不存在，则重定向到登录页面
            next('/login');
          }

        }
        },
  {
    path: "/dietRecord",
    name: "饮食记录",
    component: DietRecordView,
  },
  {
    path: "/dailyDiet",
    name: "健康食谱",
    component: DailyDietView,
  },
  {
    path: "/healthyStatus",
    name: "健康状态",
    component: healthyStatus,
  },{
      path: "/mine",
      name: "我的",
      component: Mine,
      meta: {
        requiresAuth: true, // 添加此属性，表示此页面需要登录才能访问
      },
      beforeEnter: (to, from, next) => {
        const token = localStorage.getItem('token');
        if (token) {
          // 如果token存在，则继续导航到目标页面
          next();
        } else {
          // 如果token不存在，则重定向到登录页面
      }
  }
      }
  ]
  }];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const isLoggedIn = !!localStorage.getItem('token'); // 确认登录状态

  if (to.meta.requiresAuth && !isLoggedIn) {
    // 用户未登录且访问需要鉴权的页面时，重定向到登录页
    if (from.path !== '/login') {
      next('/login');
    } else {
      // 防止从登录页到登录页的无限循环
      next({ name: 'someDefaultPage' }); // 或者使用next(false)阻止导航
    }
  } else {
    next(); // 其他情况正常放行
  }
});


// 假定的辅助函数，用于解码和验证JWT
async function validateToken(token) {
  if (!token) return false;

  try {
    const decoded = jwtDecode(token);
    // 检查token是否过期
    if (decoded.exp * 1000 < Date.now()) {
      return false;
    }
    // 这里可以根据需要添加更多验证逻辑
    return true;
  } catch (err) {
    console.error("Token解码错误:", err);
    return false;
  }
}

// 开启路由守卫
// router.beforeEach((to,from,next)=> {
//   // const isAuthenticated = localStorage.getItem('token')
//   const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
//   const token = localStorage.getItem("token")
//   if (token == null) {
//     router.push("/")
//   } else {
//     try {
//       const decoded = jwtDecode(token); // 使用jwt-decode库或类似的库来解码JWT
//       if (decoded.exp < Date.now() / 1000) {
//         console.log("exp:" + decoded.exp)
//         console.log("now:" + Date.now() / 1000)
//         router.push("/login")
//       } else {
//         const user = useUserStore()
//         user.setToken(token)
//         console.log(user.userInfo.name)
//         next()
//       }
//     } catch (err) {
//       console.log(err)
//     }
//   }
// })
export default router;
