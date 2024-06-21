<script setup>
import {onMounted, ref} from "vue";
import * as echarts from 'echarts';
const container = ref(null);


let xAxisData = ["Mon.","Tues.","Wed.","Thur.","Fir.","Sat.","Sun."];
let data1 = [];
let data2 = [];

for (let i = 0; i < 15; i++) {
  data1.push(i+7);
  data2.push(i+Math.random()*10+1);
}
let option = {
  legend: {
    data: ['本周', '上周']
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
      name: '本周',
      type: 'bar',
      data: data1,
      emphasis: {
        focus: 'series'
      },
      animationDelay: function(idx) {
        return idx * 10;
      }
    },
    {
      name: '上周',
      type: 'bar',
      data: data2,
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

onMounted(()=>{
  console.log(container.value)
  let eChartsType = echarts.init(container.value);
  eChartsType.setOption(option);
  console.log(container.value)
})
// 使用 ref 创建响应式数据
const tableData = ref([
  {
    id: "1",
    name: "张三",
    last_time: "100",
  },
  {
    id: "2",
    name: "李四",
    last_time: "90",
  },
  {
    id: "3",
    name: "王五",
    last_time: "80",
  },
  {
    id: "4",
    name: "赵六",
    last_time: "70",
  },
]);
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
    <h5 style="color: #419fff; margin-top: 15px">| 好友排行榜：</h5>
    <el-table :data="tableData" style="width: 100%" max-height="250">
      <el-table-column fixed prop="id" label="排名" width="150" />
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="last_time" label="运动量" width="120" />
    </el-table>
  </div>
  <div class="tablebox2">
    <h5 style="color: #419fff; margin-top: 15px">| 消耗卡路里：</h5>
    <div class="demo-progress">
      <el-progress :text-inside="true" :stroke-width="16" :percentage="70" />
      <el-progress
        :text-inside="true"
        :stroke-width="16"
        :percentage="100"
        status="success"
      />
      <el-progress
        :text-inside="true"
        :stroke-width="16"
        :percentage="80"
        status="warning"
      />
      <el-progress
        :text-inside="true"
        :stroke-width="16"
        :percentage="50"
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
