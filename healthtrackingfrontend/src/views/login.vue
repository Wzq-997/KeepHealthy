<template>
  <div id="login">
    <transition appear name="opacitytrans">
      <div class="container" id="container">
        <div class="form-container sign-in-container">
          <form @submit.prevent="login">
            <h1>健康追踪应用系统</h1>

            <span>Version 1.0.0</span>
            <input type="text" placeholder="请输入账号" v-model="account" />
            <input type="password" placeholder="请输入密码" v-model="password" />
            <div class="button" @click="login">登录</div>
<!--            <a class="" @click="$router.push('/register')">没有账号？立即注册</a>-->
          </form>
        </div>
        <div class="overlay-container">
          <div class="overlay">
            <div class="overlay-panel overlay-right">
              <img class="logo" src="" alt="" />  <!-- logo链接 -->
              <p>「 欢迎来到健康追踪应用系统！ 」</p>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import {useUserStore} from '@/store/index';
import {jwtDecode} from "jwt-decode";

// 定义响应式变量
const account = ref('');
const password = ref('');
const userStore = useUserStore()
// 导入路由实例
const router = useRouter();

// 定义登录方法
async function login() {
  if (!account.value || !password.value) {
    alert("请输入账号和密码");
    return;
  }

  try {
    const response = await axios.post('http://localhost:8081/main/login', {
      userId: account.value,
      password: password.value
    });

    if (response.data.code === 1) {
      // 登录成功，处理后续逻辑
      let token = response.data.data.token
      let str = jwtDecode(response.data.data.token)
      localStorage.setItem("token",response.data.data.token)
      // 设置token，保存用户信息
      userStore.setToken(token)
      alert('登录成功');
      // 跳转到主页面
      router.push('/');
    } else {
      // 登录失败，显示错误信息
      alert(response.data.message);
    }
  } catch (error) {
    if (axios.isAxiosError(error)) {
      if (error.code === 'ERR_NETWORK') {
        console.error('网络错误:', error.message);
        alert('登录过程中出现网络错误，请检查你的网络连接');
      } else {
        console.error('Axios 错误:', error.message);
        console.error('响应状态:', error.response?.status);
        console.error('响应数据:', error.response?.data);
        // 根据具体错误代码或状态码显示不同的错误信息给用户
        alert('登录过程中发生错误，请稍后再试');
      }
    } else {
      // 非 axios 错误，可能是其他 JavaScript 错误
      console.error('未知错误:', error);
      alert('登录过程中发生未知错误，请稍后再试');
    }
  }
}
</script>





<style scoped>
#login {
  font-family: "Montserrat", sans-serif;
  background: #f6f5f7;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;

  background-size: cover;
}
.logo {
  width: 160px;
  height: auto;
}
h1 {
  font-weight: bold;
  margin: 0;
  color: rgb(200, 208, 210);
}

p {
  font-size: 16px;
  font-weight: bold;
  line-height: 20px;
  letter-spacing: 0.5px;
  margin: 20px 0 30px;
  color: #000;
}

span {
  font-size: 12px;
  color: beige;
}

a {
  color: #fff;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

.container {
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  position: absolute;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  opacity: 0.8;
}

.form-container form {
  background: rgba(45, 52, 54, 1);
  display: flex;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #ddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}

.form-container input {
  background: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.button {
  cursor: pointer;
  border-radius: 20px;
  /* border: 1px solid #ff4b2b;
  background: #ff4b2b; */
  /* border: 1px solid #fa8817;
  background: #fa8817; */
  border: 1px solid #1bbfb4;
  background: #1bbfb4;
  color: #fff;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  margin-top: 20px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

input[type="text"] {
  width: 240px;
  text-align: center;
  background: transparent;
  border: none;
  border-bottom: 1px solid #fff;
  font-family: "PLay", sans-serif;
  font-size: 16px;
  font-weight: 200;
  padding: 10px 0;
  transition: border 0.5s;
  outline: none;
  color: #fff;
  font-weight: bold;
}

input[type="password"] {
  width: 240px;
  text-align: center;
  background: transparent;
  border: none;
  border-bottom: 1px solid #fff;
  font-family: "PLay", sans-serif;
  font-size: 16px;
  font-weight: bold;
  padding: 10px 0;
  transition: border 0.5s;
  outline: none;
  color: #fff;
}

input[type="email"] {
  width: 240px;
  text-align: center;
  background: transparent;
  border: none;
  border-bottom: 1px solid #fff;
  font-family: "PLay", sans-serif;
  font-size: 16px;
  font-weight: 200;
  padding: 10px 0;
  transition: border 0.5s;
  outline: none;
  color: #fff;
  font-weight: bold;
}

.button:active {
  transform: scale(0.95);
}

.button:focus {
  outline: none;
}

.button.ghost {
  background: transparent;

  /* border-color: #fa8817;
  background-color: #fa8817; */
  border-color: #1bbfb4;
  background-color: #1bbfb4;
  margin: 0;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.sign-up-container {
  left: 0;
  width: 50%;
  z-index: 1;
  opacity: 0;
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.overlay {
  background: transparent;
  background-image: linear-gradient(to right, #ff4b2b, #ff416c);
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: cover;
  color: #fff;
  position: absolute;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-panel {
  position: absolute;
  top: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0 40px;
  height: 100%;
  width: 50%;
  text-align: center;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}
</style>