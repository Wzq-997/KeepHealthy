<template>
    <div id="register">
      <transition appear name="opacitytrans">
        <div class="container" id="container">
          <div class="form-container">
            <form action="#register">
              <h1>创建新账户</h1>

              <input type="text" placeholder="用户名" v-model="username" required/>
              <input type="email" placeholder="邮箱地址" v-model="email" required/>
              <input type="password" placeholder="密码" v-model="password" required/>
              <input type="password" placeholder="确认密码" v-model="confirmPassword" required/>
              <div class="button" @click.prevent="register">注册</div>
            </form>
          </div>
          <div>
            <div>
              <div>
                <p>已有账户？<span style="color:  rgba(0, 0, 0, 0.22);" @click="$router.push('/login')">登录</span></p>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: "RegisterVue",
    data() {
      return {
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
      };
    },
    methods: {
      async register() {
        if (!this.username || !this.email || !this.password || !this.confirmPassword) {
          alert("请填写所有必填项");
          return;
        }
  
        if (this.password !== this.confirmPassword) {
          alert("两次输入的密码不一致");
          return;
        }
  
        try {
          const response = await axios.post('', {
            username: this.username,
            email: this.email,
            password: this.password,
          });
  
          if (response.status === 200) {
            alert('注册成功，请登录');
            this.$router.push('/login'); // 注册成功后跳转到登录页面
          } else {
            alert('注册失败，请重试');
          }
        } catch (error) {
          console.error('注册时发生错误:', error);
          alert('注册请求失败，请稍后再试');
        }
      },
    },
  };
  </script>

  
<style scoped>
#register {
  /* font-family: "Montserrat", sans-serif; */
  /* background: #f6f5f7; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  /* background-image: url(""); */
  /* background-size: cover; */
}

h1 {
  font-weight: bold;
  margin: 20;
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
  color: rgba(0, 0, 0, 0.22);
}

a {
  color: rgba(0, 0, 0, 0.22);
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


.form-container input {
  background: rgba(45, 52, 54, 1);
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.button {
  cursor: pointer;
  border-radius: 20px;
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
  font-weight: 200px;
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
  font-weight: 200px;
  padding: 10px 0;
  transition: border 0.5s;
  outline: none;
  color: #fff;
  font-weight: bold;
}
</style>
