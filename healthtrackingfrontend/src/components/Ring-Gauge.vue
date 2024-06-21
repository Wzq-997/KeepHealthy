<template>
  <div ref="container" style="width: 600px; height: 400px;">

  </div>
</template>

<script setup>
import { ref, onMounted,watch } from 'vue';
import * as echarts from 'echarts';
import { defineProps } from 'vue';

const props = defineProps({
  xAxisData_name: {
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
  },
  // passedTime:{
  //   type:Number,
  //   default:0,
  // }

});
const container = ref(null);

let option = {
  legend: {
    data: ['昨天', '今天']
  },
  xAxis: {
    data: props.xAxisData_name, // 使用从 props 传递进来的数据
    splitLine: {
      show: false
    }
  },
  yAxis: {},
  series: [
    {
      name: '昨天',
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
      name: '今天',
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

// 监听 data2 的变化
watch(
    () => props.options,
    (newVal, oldVal) => {
      if (newVal !== oldVal) {
        eChartsType.setOption(newVal);
      }
    },
    { deep: true } // 如果 options 是一个复杂对象，需要深度监听
);
onMounted(() => {
  const eChartsType = echarts.init(container.value);
  eChartsType.setOption(option);
  console.log("运动时间表")
});
</script>

<style scoped>
/* 样式 */
</style>