<template>
  <br>
  <div class="container">
    <div class="lbt" >
        <el-carousel style=" height: 600px"
                     motion-blur>
                  <el-carousel-item  class="carousel-item">
                    <img src="../img/sports/run.png" alt="image" class="carousel-image" >
                  </el-carousel-item>
                  <el-carousel-item class="carousel-item"  >
                  <img src="../img/sports/hulaHoop.png" alt="image" class="carousel-image">
                </el-carousel-item>
                  <el-carousel-item  class="carousel-item" >
                    <img src="../img/sports/ride.png" alt="image"class="carousel-image">
                  </el-carousel-item>
                  <el-carousel-item  class="carousel-item" >
                    <img src="../img/sports/skate.png" alt="image" class="carousel-image">
                  </el-carousel-item>

                  <el-carousel-item   class="carousel-item">
                    <img src="../img/sports/weightLifting.png" alt="image" class="carousel-image" >
                  </el-carousel-item>
                <el-carousel-item  class="carousel-item"  >
                    <img src="../img/sports/skipping.png" alt="image"  class="carousel-image" >
                  </el-carousel-item>
                </el-carousel>
    </div>

    <div class="sportsType" style="position: relative;background-color: rgba(161,175,189,0.9)">
    <h2 >请选择运动类型</h2>
    <el-radio-group v-model="selectedSport"  @change="handleChange">
      <el-radio v-for="sport in sports" :key="sport.sport_id" :label="sport.sport_name">{{ sport.sport_name }}</el-radio>
    </el-radio-group>
  </div>
    <div class="button-group" >
      <button class="big-button start-button"style="position: relative" @click="startTimer">开始</button>
      <button v-if="started" class="big-button stop-button"style="position: relative" @click="stopTimer">结束</button>
    </div>
    <div v-if="started" class="time-passed">
      <p>已经过去的时间: {{ passedTime.toFixed(2) }} 秒</p>
    </div>
    </div>
  <div style="position: relative;margin-left:810px">
      <RingGauge  :xAxisData_name="xAxisData_name"
                  :data1="data1"
                  :data2="data2"

                  />
  </div>

</template>

<script setup>
import {ref, onMounted, onUnmounted, reactive, computed} from 'vue';
import { useStore } from 'vuex'; // 注意这里通常我们使用 useStore 而不是直接导入 index
import * as echarts from 'echarts/core';
import { GaugeChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';
import RingGauge from "@/components/Ring-Gauge.vue";
import axios from "axios";
import {useUserStore} from "@/store";
const user = useUserStore().userInfo
const userId =  user.id
const userTypes = ref([])
echarts.use([GaugeChart, CanvasRenderer]);
const store = useStore();
const started = ref(false);
const stopTime = ref(null);
let currentTimer = null;
let startTime = 0;
const passedTime = ref(0);
//选择的运动
const selectedSport = ref('');
const sports = ref([
  { sport_id: 'S001', sport_name: '跑步', single_calorie: 1.3 },
  { sport_id: 'S002', sport_name: '瑜伽', single_calorie: 2.3 },
  { sport_id: 'S003', sport_name: '游泳', single_calorie: 3.5 },
  { sport_id: 'S004', sport_name: '骑行', single_calorie: 2.6 },
  { sport_id: 'S005', sport_name: '散步', single_calorie: 0.3 },
  { sport_id: 'S006', sport_name: '攀登', single_calorie: 5 },
]);


const xAxisData_name=computed(()=>{
  return sports.value.map(sport => sport.sport_name)

})
const data1=ref([0,0,0,0,0,0,0]);//绑定数据库各项运动的昨日运动时长
const data2= ref([0,0,0,0,0,0,0]);//绑定数据库各项运动的今日运动时长
const startTimer = () => {
  if(!started.value) {
    started.value = true;
    startTime = Date.now();
    currentTimer = setInterval(() => {
      passedTime.value = (Date.now() - startTime) / 1000;
    }, 100);

  }
};
//找到运动
const handleChange = (value)=>{
   selectedSport.value=value

}
//更新运动名对应的数据

const updateData=async ()=>{
  for(let i=0;i<sports.value.length;i++){
    if(sports.value[i].sport_name==selectedSport.value){
      // data1.value[i]+=passedTime.value;
      console.log("data2[i]-"+data1.value[i]+"passedTime"+passedTime.value)
      const response =  await  axios({
        method: 'post',
        url: 'http://localhost:8081/sport/insertSportRecord',
        params: {
          lastTime:passedTime.value/60,
          userId: userId,
          sportId: sports.value[i].sport_id
        },
      })
          .then(response => {
            // 请求成功时处理响应数据
            console.log(response.data);
          }).catch(error => {
            // 请求失败时处理错误
            console.error(error);
          });
    }
  }
}

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
                      data2.value[index] = array[i].consumeCalories; // 更新 data1
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
                      data1.value[index] = array[i].consumeCalories; // 更新 data2
                      // 假设 data1 的值是 pers 的某种变换，这里直接赋值
                      // data1.value[index] = pers;
                    }
                  }
                }
              }
          ).catch(error => {
            // 请求失败时处理错误
            console.error('Error:', error);
          })]
    )}catch (error)
  {
    console.error("Error",error)
  }
  updateData()
})

onMounted(()=>handleChange())
const stopTimer = () => {
  started.value=false;
  if(currentTimer) {
    clearInterval(currentTimer);
    currentTimer = null;
  }

  // axios.
  updateData();
};
onMounted(() => {
  updateData();
  stopTimer();

});
onUnmounted(() => {
  if (currentTimer) {
    clearInterval(currentTimer);
    currentTimer = null;
  }
});


</script>

<style scoped>
.lbt {
  width: 460px;
  height: 500px;
  //box-shadow: 20px 20px  10px 50px rgba(203, 22, 22, 0.5);

}
.carousel-image{
  width: 460px;
  height: 700px;
  overflow: hidden;

}
.carousel-item{
  width: 460px;
  height: 650px;
}

.container {
  display: flex;
  flex-direction: column;
  //justify-content: center;
  align-items: center;
  position: absolute;
  margin-left: 10px;
  height: 100vh;
  border-radius: 10px;
overflow: hidden;
  font-size: 20px;

}

.sportsType {
  margin-bottom: 0px;
  background-color: rgb(149, 198, 251);
  //opacity: 0.75;

}

.button-group {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 20px;

}

.big-button {
  height: 60px;
  width: 120px;
  border-radius: 30px;
  border: none;
  font-size: 20px;
  color: white;
  font-weight: bold;
}

.start-button {
  background-color: #4caf50;
  position: absolute;

}

.stop-button {
  background-color: #f44336;
}

.time-passed {
  color: #555;
  font-weight: bold;
}

</style>