import { defineStore } from "pinia";
// import jwtDecode from "jwt-decode"; // 使用jwt-decode库解析JWT令牌
// import axios from "axios";

export const useUserStore = defineStore({
  id: "user",
  // 定义store的初始状态
  state: () => ({
    token: null,        // 用于存储用户的JWT令牌
    expiration: null,   // 用于存储令牌的过期时间
    userInfo: null,     // 用于存储解码后的用户信息
    
  }),
  // 定义基于状态的计算属性
  getters: {
    // 判断用户的登录是否过期
    isLoginExpired() {
      if (this.expiration) {
        // 如果存在令牌的过期时间
        const currentTime = Date.now() / 1000; // 获取当前时间(单位是秒)
        return currentTime > this.expiration; // 如果当前时间大于令牌的过期时间，说明登录已过期
      }
      // 如果没有令牌的过期时间，也认为登录已过期（即用户还未登录）
      return true;
    },
  },
  // 定义改变state的方法
  actions: {
    // 设置用户的JWT令牌
    setToken(token) {
      this.token = token; // 在state中存储token
      const decoded = jwtDecode(token); // 解码token
      this.userInfo = decoded.user; // 在解码后的payload中获取用户信息，并存入state
      this.expiration = decoded.exp; // 在解码后的payload中获取令牌的过期时间，并存入state
    },
    // 断言用户的登录未过期，如果过期则登出
    assertNotExpired() {
      if (this.isLoginExpired) {
        // 如果登录已过期
        this.logout(); // 执行登出操作
      }
    },
    // 用户登录
    async login(loginPayload) {
      // 在这里使用axios向后端发送登录请求，'/api/login'需要根据实际的登录接口来修改

      // loginPayload是包含用户登陆信息（例如用户名和密码）的对象

      const response = await axios.post("/api/login", loginPayload);

      // 从服务器响应中获取新的JWT令牌，'token'需要根据服务器实际返回的字段名来修改
      const token = response.data.token;

      // 将新的令牌存入user store的state里
      this.setToken(token);

      // 启动一个定时器，每12小时执行一次
      setInterval(async () => {
        // 在此处加入发送获取新token的请求的代码，例如使用axios发送请求到后端的刷新令牌接口
        // 在请求成功的回调中，使用 this.setToken(newToken) 更新令牌
      }, 12 * 60 * 60 * 1000); // 12小时后执行
    },
    // 用户登出
    logout() {
      // 清空state
      this.token = null;
      this.userInfo = null;
      this.expiration = null;
    },
  },
});
