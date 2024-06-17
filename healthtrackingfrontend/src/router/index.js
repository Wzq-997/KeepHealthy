import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ExercisePrescriptionView from "../views/ExercisePrescriptionView.vue";
import AutonomouSportsView from "../views/AutonomouSportsView.vue";

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

  },
  {
    name: "Page2",
    path: "/Page2",
    component: AutonomouSportsView,

  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
