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
const data1=ref([20,11,30,44,66,55,0]);//绑定数据库各项运动的昨日运动时长
const data2= ref([11,44,33,55,66,0,99]);//绑定数据库各项运动的今日运动时长
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
const updateData=()=>{
  for(let i=0;i<sports.value.length;i++){
    if(sports.value[i].sport_name==selectedSport.value){
      data2.value[i]+=passedTime.value;
      console.log("data2[i]-"+data2.value[i]+"passedTime"+passedTime.value)
    }
  }
}
onMounted(()=>handleChange())
const stopTimer = () => {
  if(currentTimer) {
    clearInterval(currentTimer);
    currentTimer = null;
  }
  updateData();
};
onMounted(() => {
  stopTimer();

});
onUnmounted(() => {
  if (currentTimer) {
    clearInterval(currentTimer);
    currentTimer = null;
  }
});

// 注册子组件
defineExpose({

  // 如果父组件需要向子组件暴露数据或方法，可以在这里定义
});

</script>

<style scoped>
.lbt {
  width: 460px;
  height: 500px;


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