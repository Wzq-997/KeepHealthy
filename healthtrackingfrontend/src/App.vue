<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script setup>
import {useUserStore} from "@/store";
import router from "@/router";
import {onBeforeMount, onMounted} from "vue";
import {jwtDecode} from "jwt-decode";

onBeforeMount(()=>{
  const token  = localStorage.getItem("token")
  console.log(token)
  if(token==null)
  {
    router.push("/login")
  }
  else {
    function isTokenExpired(token) {
      try {
        const decoded = jwtDecode(token); // 使用jwt-decode库或类似的库来解码JWT
        if (decoded.exp < Date.now() / 1000) {
          console.log("exp:"+decoded.exp)
          console.log("now:"+Date.now() / 1000)
          return true; // token已过期
        }
        return false; // token未过期
      } catch (err) {
        // 处理解码错误，可能是非法的token
        return true; // 或根据你的需求返回false
      }
    }
    if(isTokenExpired(token))
    {
      //   token过期
      console.log("token过期")
      router.push("/login")
    }
    else
    {
      const user = useUserStore()
      user.setToken(token)
      console.log(user.userInfo)
      router.push("/")
    }
  }
})



</script>
