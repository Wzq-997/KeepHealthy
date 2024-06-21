<template>
  <div class="container">
    <div class="left">
      <p class="text-center demonstration">每日食谱</p>
      <el-carousel height="200px" direction="vertical" :autoplay="false">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
      <p class="text-center demonstration">图片仅供参考</p>
      <el-carousel height="400px" direction="vertical"
                   @change="carouselChange"
                   type="card"
                   :autoplay="true"
                   interval="3000"
      >
        <el-carousel-item v-for="(item,index) in carouselItems" :key="index">
          <h3>{{ item.title}}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="right">
      <div class="top">
        <h2>{{names[currentIndex]}}</h2>
        <p class="text-center demonstration"

        >{{make[currentIndex]}}</p>
        <!-- 在这里添加内容 -->
      </div>
      <div class="bottom">
        <p class="text-center demonstration">{{note[currentIndex]}}</p>
        <!-- 在这里添加内容 -->
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: 100vh;
}

.left {
  flex: 1;
  padding: 20px;
}

.right {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.top, .bottom {
  flex: 1;
  padding: 20px;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>


<script setup>
import axios from "axios";
import {onMounted, ref} from 'vue'
const names = ref([])
//  所有菜的制作方法
const make = ref([])
//  注意事项同理
const note = ref([])
//  当前索引下标
const currentIndex = ref(0)
// onMounted(()=>{
//   axios({
//     method:'post',
//     url:'http://localhost:8081/diet/getMenuRecord'
//   }).then(respone=>{
//     let data = respone.data.data
//     for(let i = 0;i<data.length;i++)
//     {
//       names.value.push(data[i].name)
//       make.value.push(data[i].make)
//       note.value.push(data[i].tips)
//     }
//     console.log(respone.data.data)
//     console.log(make.value)
//     console.log(note.value)
//   })
// })

const carouselItems = ref([
  { title:'幻灯片A' },
  { title:'幻灯片B' },
  { title:'幻灯片C' }
])
const carouselChange =(index)=>{
  // 记录当前幻灯片下标
  currentIndex.value = index
}
</script>

