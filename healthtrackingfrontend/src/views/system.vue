<template>
  <el-container class="home_container">
    <!--    plus-->
    <el-header class="home_header">
      <div class="home_title">个人健康管理系统</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
          <span class="el-dropdown-link home_userinfo">
            {{ admin.name }}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>


    <el-container>
      <el-aside class="home_aside" width="200px">
        <el-menu
            router
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
        >
          <el-submenu index="1">
            <el-menu-item><h3 class="menu-title">--运动</h3></el-menu-item>
            <!--            <el-menu-item-group title="运动">-->
            <el-menu-item
                index="/sportwords"
                :class="$route.path == '/sportwords' ? 'is-active' : ''"
            >
              <el-icon><List /></el-icon>运动处方
            </el-menu-item>

            <el-menu-item
                index="/sports"
                :class="$route.path == '/sports' ? 'is-active' : ''"
            >
              <el-icon><CopyDocument /></el-icon>自主运动
            </el-menu-item>

          </el-submenu>

          <el-submenu index="2">
            <el-menu-item><h3 class="menu-title">--饮食管理</h3></el-menu-item>

            <el-menu-item
                index="/dietRecord"
                :class="$route.path == '/dietRecord' ? 'is-active' : ''"
            >
              <el-icon><Bowl /></el-icon>饮食记录
            </el-menu-item>
            <el-menu-item
                index="/dailyDiet"
                :class="$route.path == '/dailyDiet' ? 'is-active' : ''"
            >
              <img src="../img/icon/食谱-copy.png" class="icons" />每日食谱
            </el-menu-item>

          </el-submenu>

          <el-submenu index="3">
            <el-menu-item><h3 class="menu-title">--健康评估</h3></el-menu-item>

            <el-menu-item
                index="/healthyStatus"
                :class="$route.path == '/healthyStatus' ? 'is-active' : ''"
            >
              <img src="../img/icon/健康状态.png" class="icons" />健康状态
            </el-menu-item>
            <el-menu-item
                index="/advice"
                :class="$route.path == '/advice' ? 'is-active' : ''"
            >
              <img src="../img/icon/健康建议.png" class="icons" />健康建议
            </el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <el-menu-item><h3 class="menu-title">--个人信息</h3></el-menu-item>

            <el-menu-item
                index="/mine"
                :class="$route.path == '/mine' ? 'is-active' : ''"
            >
              <img src="../img/icon/person.png" class="icons" />我的
            </el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <div class="main" style="margin-top: -20px">
            <el-breadcrumb separator="→">
              <el-breadcrumb-item
                  :to="{ path: '/' }"
                  style="margin-top: 20px; margin-left: 10px"
              >首页</el-breadcrumb-item
              >
              <el-breadcrumb-item
                  :to="{ name: $route.name }"
                  style="margin-top: 20px"
                  v-if="$route.name"
              >{{ $route.name }}</el-breadcrumb-item
              >

            </el-breadcrumb>

            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>

  </el-container>
</template>
<script>
import { List, Location } from "@element-plus/icons-vue";
import axios from "axios";

export default {
  components: { List, Location },
  methods: {
    logout() {
      let _this = this;
      this.$confirm("注销登录吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(function () {
        localStorage.removeItem("systemAdmin");
        _this.$router.replace({ path: "/login" });
      });
    },
    handleOpen(key, keyPath) {
      console.log("Menu opened:", key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log("Menu closed:", key, keyPath);
    },
  },

  data() {
    return {
      admin: "",
      percentage: 20,
    };
  },

  created() {
    //你们在用的时候数据用静态
    let admin={id:1,username:"admin1",password: "123123", name: "管理员1", telephone: "88132001"};
    // let admin = JSON.parse(window.localStorage.getItem("systemAdmin"));
    this.admin = admin;
  },
  mounted() {
  //   axios
  //     .get("http://localhost:8099/user/lists")
  //     .then(function (response) {
  //       // 处理响应数据
  //       console.log(response);
  //       console.log(response.data);
  //     })
  //     .catch(function (error) {
  //       // 处理错误
  //       console.log(error);
  //     });
  },
};
</script>
<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.home_header {
  background-color: #2b2b2b;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
  //width: 90%;
  //position: absolute;
  //top: 0;
  //right: 0;
}

.home_title {
  color: #c2c2c2;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}

.home_aside {
  background-color: #ffffff;
}

.menu-title {
  color: #529b2e;
}
.icons {
  width: 20px;
  height: 20px;
  //position: absolute;
  margin-right: 8px;
}
.main {
  background-color: white;
  width: 100%;
  height: 100%;
}
.demo-progress {
  max-width: 100px;
}
</style>
