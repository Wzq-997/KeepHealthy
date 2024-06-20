<script setup>
import {nextTick, onBeforeMount, onMounted, ref} from "vue";
import {useUserStore} from "@/store";
import * as echarts from 'echarts';
import axios from "axios";

const container = ref(null);
const userStore = useUserStore()
const user = userStore.userInfo
console.log(user)
console.log(user.name)
let userId = user.id
let date = new Date()
let datenow = date.toLocaleDateString()
let userTypes = ref([])
let data1 = ref([]);
let data2 = ref([]);
// 使用 ref 创建响应式数据
const tableData = ref([]);
const rankedUsers = ref([]);


// console.log(data2.value[0])
onMounted(async () => {
  try {
    const [response1, response2] = await Promise.all([
  axios({
    method: 'get',
    url: 'http://localhost:8081/sport/getSportRecordOne',
    params:{
      userId:userId
    }
  })
      .then(response => {
        // 请求成功时处理响应数据
        let array = response.data.data
        if(array.length==0)
        {
          console.log("没有1")
        }
        else {
          console.log(array[0].sportTypes);
          for (let i = 0; i < array.length; i++) {
            const sportId = array[i].sportId;
            const index = sportId.slice(1) - 1; // 假设 sportId 为 'S001', 'S002' 等，转换为索引 0, 1, ...
            if (index >= 0 && index < 7) { // 确保索引在有效范围内
              let pers = array[i].consumeCalories / 74 * 8;
              userTypes.value[index] = pers; // 更新 userTypes
              data1.value[index] = array[i].consumeCalories; // 更新 data2
              // 假设 data1 的值是 pers 的某种变换，这里直接赋值
              // data1.value[index] = pers;
            }
          }
        }
      })
      .catch(error => {
        // 请求失败时处理错误
        console.error('Error:', error);
      }),
  axios({
    method: 'get',
    url: 'http://localhost:8081/sport/getSportRecordYes',
    params:{
      userId:userId
    }
  }).then(
      response => {
        // 请求成功时处理响应数据
        let array = response.data.data
        // console.log(array[0].sportTypes);
        if(array.length==0)
        {
          console.log(array.length)
          console.log("没有2")
        }
        else {
          for (let i = 0; i < array.length; i++) {
            const sportId = array[i].sportId;
            const index = sportId.slice(1) - 1; // 假设 sportId 为 'S001', 'S002' 等，转换为索引 0, 1, ...
            if (index >= 0 && index < 7) { // 确保索引在有效范围内
              let pers = array[i].consumeCalories / 74 * 8;
              userTypes.value[index] = pers; // 更新 userTypes
              data2.value[index] = array[i].consumeCalories; // 更新 data2
              // 假设 data1 的值是 pers 的某种变换，这里直接赋值
              // data1.value[index] = pers;
            }
          }
        }
      }
  ).catch(error => {
    // 请求失败时处理错误
    console.error('Error:', error);
  }),
   axios({
            method: 'get',
            url: 'http://localhost:8081/sport/getFriends'
          })
       .then(response => {
                // 请求成功时处理响应数据
            tableData.value= response.data.data
         // 直接在 setup 函数中初始化 rankedUsers
         rankedUsers.value = tableData.value.map((user, index) => ({
           ...user, // 保留原始用户数据
           rank: index + 1 // 添加排名属性，从1开始
         }));
         console.log(response.data.data)
       })
       .catch(error => {
                // 请求失败时处理错误
                console.error('Error:', error);
              }),
  ]
  )}catch (error)
  {
    console.error("Error",error)
  }
  const chartDom = ref(null)
  console.log(container.value)
  let eChartsType = echarts.init(container.value);
  eChartsType.setOption(option);
  console.log(container.value)
})
let yoga =()=>{
  return "瑜伽"
}
let running =()=>{
  return "跑步"
}
let riding = ()=>{
  return "骑行"
}
let swim = ()=>{
  return "游泳"
}
let xAxisData = ["跑步","瑜伽","游泳","骑行","散步","攀登","打篮球"];


let option = {
  legend: {
    data: ['今天', '昨天']
  },
  xAxis: {
    data: xAxisData,
    splitLine: {
      show: false
    }
  },
  yAxis: {},
  series: [
    {
      name: '今天',
      type: 'bar',
      data: data1.value,
      emphasis: {
        focus: 'series'
      },
      animationDelay: function(idx) {
        return idx * 10;
      }
    },
    {
      name: '昨天',
      type: 'bar',
      data: data2.value,
      emphasis: {
        focus: 'series'
      },
      animationDelay: function(idx) {
        return idx * 10 + 100;
      }
    }
  ],
  animationEasing: 'elasticOut',
  animationDelayUpdate: function(idx) {
    return idx * 5;
  }
};




</script>

<template class="main">
  <!--  轮播图-->
  <div class="lbt">
    <span class="demonstration"> 运动剪影 </span>
    <el-carousel height="250px" width="200px">
      <el-carousel-item v-for="item in 4" :key="item">
        <h3 class="small justify-center" text="2xl">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>

  <div class="tablebox" style="margin-left: 470px; margin-top: 10px">
    <!--  <div class="tablebox"  style="float:right;" >-->
    <h5 style="color: #419fff; margin-top: 15px">| 好友排行榜：</h5>
    <el-table :data="rankedUsers" style="width: 100%" max-height="250">
      <el-table-column fixed prop="rank" label="排名" width="150" />
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="totalCalories" label="运动量" width="120" />
    </el-table>
  </div>
  <div class="tablebox2">
    <h5 style="color: #419fff; margin-top: 15px">| 消耗卡路里：</h5>

    <div class="demo-progress">

      <el-progress
          :format="running"
          :text-inside="true"
          :stroke-width="16"
          :percentage=userTypes[0] />
      <el-progress
          :format="yoga"
        :text-inside="true"
        :stroke-width="16"
        :percentage=userTypes[1]
        status="success"
      />
      <el-progress
          :format="swim"
        :text-inside="true"
        :stroke-width="16"
        :percentage=userTypes[2]
        status="warning"
          color="black"
      />
      <el-progress
          :format="riding"
        :text-inside="true"
        :stroke-width="16"
        :percentage=userTypes[3]
        status="exception"
      />
    </div>
  </div>
  <div class="sportTable" >
    <div class="container" ref="container" style="width: 100%; height: 400px;"></div>
  </div>

</template>

<style scoped>
.lbt {
  width: 400px;
  height: 290px;
  background-color: #c6e2ff;
  position: absolute;
  margin-left: 10px;
  //float: left;
  margin-top: 10px;
}
.demonstration {
  color: var(--el-text-color-secondary);
}
.tablebox {
  width: 350px;
  height: 290px;
  position: absolute;
  background-color: white;
  color: white;
}
.tablebox2 {
  width: 350px;
  height: 290px;
  position: absolute;
  margin-right: 25px;
  right: 10px;
  background-color: white;
  color: white;
}
.main {
  background-color: #59ce33;
}
.demo-progress .el-progress--line {
  margin-bottom: 25px;
  max-width: 300px;
  width: 300px;
}
.sportTable{
  position:fixed;
  margin-top: 300px;
  margin-left: 200px;
  width: 1000px;
  height: 500px;
  border:1px red;
}
</style>
