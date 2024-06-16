import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ExercisePrescriptionView from "../views/ExercisePrescriptionView.vue";
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    name: "Page1",
    path: "/Page1",
    component: ExercisePrescriptionView,

  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
