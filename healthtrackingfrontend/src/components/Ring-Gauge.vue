<template>
  <div ref="container" style="width: 600px; height: 400px;" >
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';
import { defineProps } from 'vue';

const props = defineProps({
  xAxisData: {
    type: Array,
    default: () => [],
  },
  data1: {
    type: Array,
    default: () => [],
  },
  data2: {
    type: Array,
    default: () => [],
  }
});
for(let i=0;i<props.data1.length;i++)
{
  console.log(props.data1[i])
}

const container = ref(null);

let option = {
  legend: {
    data: ['昨天', '今天']
  },
  xAxis: {
    data: props.xAxisData, // 使用从 props 传递进来的数据
    splitLine: {
      show: false
    }
  },
  yAxis: {},
  series: [
    {
      name: '本周',
      type: 'bar',
      data: props.data1, // 使用从 props 传递进来的数据
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
      data: props.data2, // 使用从 props 传递进来的数据
      emphasis: {
        focus: 'series'
      },
      animationDelay: function(idx) {
        return idx * 10 + 100;
      }
    }
  ],
  // ... 其他配置保持不变
  animationEasing: 'elasticOut',
  animationDelayUpdate: function(idx) {
    return idx * 5;
  }
};

onMounted(() => {
  let eChartsType = echarts.init(container.value);
  eChartsType.setOption(option);
});
</script>

<style scoped>
/* 样式 */
</style>
