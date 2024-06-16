<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <el-button type="success">点击我</el-button>
    <div id="main" class="echarts" ref="echartsEl" style="width: 600px;height:400px;"></div> 
  </div>
</template>

<script setup>
  import { ref, onMounted } from 'vue' // 导入ref和onMounted
  import * as echarts from 'echarts';

  const echartsEl = ref(null) // 定义ref变量

  onMounted(() => { // 在`mounted`生命周期钩子中初始化图表
    // 通过ref获取到DOM元素
    var myChart = echarts.init(document.getElementById('main'));

    // 绘制图表
    myChart.setOption({
      title: {
        text: 'ECharts 入门示例'
      },
      tooltip: {},
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
      },
      yAxis: {},
      series: [
        {
          color: ['#123456', '#234567', '#345678', '#456789', '#567890', '#678901'], 
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }
      ]
    });
  });
</script>

<style scoped>

.echarts {
  width: 600px;
  height: 400px;
}
</style>