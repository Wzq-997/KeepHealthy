<template>
  <div class="common-layout">
    <el-container>
      <el-main class="left" >
        <el-row>
          <el-col :span="12"    class="MyInform">
            <el-form :model="form" class="Myform"  size="small">
              <el-form-item label="Activity name" style=" margin-top: 20px;" >
                <el-input v-model="form.name"  readonly style="width: 350px"/>
              </el-form-item>
              <el-form-item label="Activity zone"  >
                <el-select v-model="form.region" placeholder="please select your zone">
                  <el-optionBmi label="Zone one" value="shanghai" />
                  <el-optionBmi label="Zone two" value="beijing" />
                </el-select>
              </el-form-item>
              <el-form-item label="Activity time" >
                <el-col :span="11">
                  <el-date-picker
                    v-model="form.date1"
                    type="date"
                    placeholder="Pick a date" />
                </el-col>
                <el-col :span="2" class="text-center" >
                  <span class="text-gray-500">-</span>
                </el-col>
                <el-col :span="11">
                  <el-time-picker
                    v-model="form.date2"
                    placeholder="Pick a time"

                  />
                </el-col>
              </el-form-item>
              <el-form-item label="Instant delivery">
                <el-switch v-model="form.delivery" />
              </el-form-item>
              <el-form-item label="Activity type">
                <el-checkbox-group v-model="form.type">
                  <el-checkbox value="Online activities" name="type">
                    Online activities
                  </el-checkbox>
                  <el-checkbox value="Promotion activities" name="type">
                    Promotion activities
                  </el-checkbox>
                  <el-checkbox value="Offline activities" name="type">
                    Offline activities
                  </el-checkbox>
                  <el-checkbox value="Simple brand exposure" name="type">
                    Simple brand exposure
                  </el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="Resources">
                <el-radio-group v-model="form.resource">
                  <el-radio value="Sponsor">Sponsor</el-radio>
                  <el-radio value="Venue">Venue</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="Activity form">
                <el-input v-model="form.desc" type="textarea" readonly/>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">Create</el-button>
                <el-button>Cancel</el-button>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="12">

            <el-table :data="leaderboard" style="width: 100%">
              <el-table-column prop="rank" label="排名" width="100">
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="200">
              </el-table-column>
              <el-table-column prop="score" label="运动员" width="200"> <!-- 修正为"得分" -->
              </el-table-column>
            </el-table>

          </el-col>
        </el-row>
                  <el-row>
                    <el-col :span="12" >
                        <el-card style="margin-right: 20px">
                          <div ref="chartRefBmi" style="width: 100%; height: 200px;"></div>
                        </el-card>
                    </el-col>
                    <el-col :span="12" > <el-card>

                      <div ref="chartRefCalories" style="width: 100%; height: 200px;"></div>
                    </el-card></el-col>
                  </el-row>
      </el-main>
      <el-aside class="right">
        <el-row>
          <el-col  >
            <div>
              <div class="title">当日消耗卡路里：</div>
              <div v-for="(item, index) in sports" :key="index" class="progress-container">
                <div class="progress-title">{{ item.sport }}</div>
                <svg class="progress-bar" width="100" height="50" viewBox="0 0 100 50">
                  <rect
                    :x="0"
                    :y="10"
                    width="100"
                    height="30"
                    rx="15"
                    ry="15"
                    fill="lightgray"
                  />
                  <rect
                    :x="0"
                    :y="10"
                    :width="item.progress + '%'"
                    height="30"
                    rx="15"
                    ry="15"
                    :fill="item.color"
                  />
                </svg>
              </div>
            </div>
          </el-col>

        </el-row>
        <el-row>
          <el-col  >
                  <div ref="chartRefSleeps" style="width: 100%; height: 400px;" ></div>
          </el-col>

        </el-row>
      </el-aside>

    </el-container>
  </div>
</template>

<script>
import { ref,reactive, onMounted, watch, onUnmounted  } from 'vue';
import * as echarts from 'echarts';
import { ElProgress } from 'element-plus';
export default {
  components: {
    ElProgress,
  },
  setup() {
    const chartRefBmi = ref(null);
    const chartRefCalories = ref(null);
    const chartRefSleeps=ref(null);
    let myChartSleeps  = null;
    const leaderboard = ref([
      { rank: 1, name: 'Alice', score: 100 },
      { rank: 2, name: 'Bob', score: 90 },
      { rank: 3, name: 'Charlie', score: 80 }

    ]);
    const bmiData = [
      { name: 'Jan', value: 22.5 },
      { name: 'Feb', value: 23.2 },
      { name: 'Mar', value: 22.8 }

    ];
    const caloriesData = [
      //  每日卡路里消耗数据
      { name: '早餐', value: 500 },
      { name: '午餐', value: 700 },
      { name: '晚餐', value: 600 },
      { name: '零食', value: 200 },
    ];

    const form = reactive({
      name: '',
      region: '',
      date1: '',
      date2: '',
      delivery: false,
      type: [],
      resource: '',
      desc: '',
    });
    const sports = ref([
      { sport: '跑步', calories: 200, progress: 40, color: 'green' },
      { sport: '游泳', calories: 150, progress: 60, color: 'blue' },
      { sport: '骑车', calories: 100, progress: 20, color: 'orange' },
    ]);
    const sleeps=ref([
      { date: '2023-01-01', sleepDuration: 7 },
      { date: '2023-02-01', sleepDuration: 9 },
      { date: '2023-03-01', sleepDuration: 88 }
    ]);
    const onSubmit = () => {
      console.log('submit!')
    }
    onMounted(() => {
      const chartDom = chartRefBmi.value; // 获取 DOM 元素
      const chartDom2 = chartRefCalories.value;
      const chartDom3 =chartRefSleeps.value;
      if (chartDom) {
        const myChartBmi = echarts.init(chartDom);
        const myChartCalories = echarts.init(chartDom2);


        const optionCalories = {
          title: {
            text: '每日卡路里消耗',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          series: [{
            name: '卡路里',
            type: 'pie',
            radius: '55%',
            data: caloriesData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }]
        };
        const optionBmi = {
          title: {
            text: 'BMI 曲线图',
            left: 'center'
          },
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: bmiData.map(item => item.name)
          },
          yAxis: {
            type: 'value',
            min: 18, // 假设 BMI 正常范围为 18.5-24.9
            max: 25,
            boundaryGap: [0, '30%']
          },
          series: [{
            data: bmiData.map(item => item.value),
            type: 'line',
            smooth: true
          }]
        };
        // const optionSleeps ={
        //   title: {
        //     text: '睡眠时间折线图'
        //   },
        //   tooltip: {
        //     trigger: 'axis'
        //   },
        //   legend: {
        //     data: ['睡眠时间']
        //   },
        //   grid: {
        //     left: '3%',
        //     right: '4%',
        //     bottom: '3%',
        //     containLabel: true
        //   },
        //   toolbox: {
        //     feature: {
        //       saveAsImage: {}
        //     }
        //   },
        //   xAxis: {
        //     type: 'category',
        //     boundaryGap: false,
        //     data: data.map(item => item.date)
        //   },
        //   yAxis: {
        //     type: 'value'
        //   },
        //   series: [
        //     {
        //       name: '睡眠时间',
        //       type: 'line',
        //       stack: '总量',
        //       areaStyle: {},
        //       data: data.map(item => item.sleepDuration)
        //     }
        //   ]
        //
        // };
        myChartBmi.setOption(optionBmi);
        myChartCalories.setOption(optionCalories);

      }
      if (chartDom3){
        const myChartSleeps = echarts.init(chartDom3);
        drawChart(myChartSleeps, sleeps.value);
      }
    });

    function updateChartSleepsData(newData) {
      if (myChartSleeps) {
        myChartSleeps.setOption({
          series: [{
            data: newData.map(item => item.sleepDuration),
          }],
          xAxis: {
            data: newData.map(item => item.date),
          },
        });
      }
    }
    function drawChart(chart, data) {
      chart.setOption({
        title: {
          text: '睡眠时间曲线图'
        },
        tooltip: {},
        xAxis: {
          data: data.map(item => item.date),
          type: 'category',
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: data.map(item => item.sleepDuration),
          type: 'line'
        }]
      });
    }
    watch(sleeps, (newSleeps) => {
      updateChartSleepsData(newSleeps);
    });

    return {
      chartRefBmi,
      leaderboard,
      form,
      onSubmit,
      chartRefCalories,
      sports,
      chartRefSleeps
    };
  }
};
</script>


<style>
.MyInform{
  max-height:500px;
  height: 60vh;
  overflow: hidden; /* 隐藏溢出内容 */
  display: flex; /* 弹性布局，以便内部元素可以缩小 */
  flex-direction: column; /* 垂直排列内部元素 */

}
.Myform{
  background-color: #ffffff;
  //width: 300px;
  height: 100%;
//overflow : hidden;

}
.left{
  width: 300px;

}
.right{

  width: 200px;
}

</style>



