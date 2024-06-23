<template>
  <div ref="container" style="width: 600px; height: 400px;">

  </div>
</template>

<script setup>
import {ref, onMounted, watch, onBeforeUnmount,shallowRef} from 'vue';
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

});
const container = ref(null);
const chartInstance =shallowRef(null)
let option={
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
function initOption(){
  option = {
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
}
// 初始化图表
onMounted(() => {
  if (container.value) {
    chartInstance.value = echarts.init(container.value);
    initOption(); // 初始化 option 并设置到图表中
    chartInstance.value.setOption(option);
    console.log("运动时间表图表已初始化");
  }
});

// 监听其他可能改变图表配置的 props
watch([() => props.xAxisData_name, () => props.data1], ([xAxisData, data1]) => {
  if (chartInstance.value) {
    option.xAxis.data = xAxisData;
    option.series[0].data = data1; // 假设 data1 对应 series[0] 的 data
    chartInstance.value.setOption(option);
  }
}, { deep: true });

// 监听 data2 的变化并更新图表
watch(() => props.data2, (newValue) => {
  if (chartInstance.value) {
    // 更新 option 中的 data2
    console.log("更新 option 中的 data2"+newValue)
    option.series[1].data = newValue; // 假设 data2 对应 series[1] 的 data
    chartInstance.value.setOption(option);
  }
}, { deep: true });
onBeforeUnmount(() => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }
});
</script>

<style scoped>
/* 样式 */
</style>