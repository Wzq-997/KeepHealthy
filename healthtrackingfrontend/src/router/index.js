import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ExercisePrescriptionView from "../views/ExercisePrescriptionView.vue";
import AutonomouSportsView from "../views/AutonomouSportsView.vue";
import DietRecordView from "../views/DietRecordView.vue";
import DailyDietView from "../views/DailyDietView.vue";

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

  },
  {
    name: "Page3",
    path: "/Page3",
    component: DietRecordView,

  },
  {
    name: "Page4",
    path: "/Page4",
    component: DailyDietView,

  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
