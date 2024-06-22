<template>
<!--  <div class="common-layout">-->
<!--    <el-container>-->
<!--      <el-main class="left" >-->
<!--        <el-row>-->
<!--          <el-col :span="12"    class="MyInform">-->
<!--            <el-form :model="form" class="Myform"  size="small">-->
<!--              <el-form-item label="Activity name" style=" margin-top: 20px;" >-->
<!--                <el-input v-model="form.name"  readonly style="width: 350px"/>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Activity zone"  >-->
<!--                <el-select v-model="form.region" placeholder="please select your zone">-->
<!--                  <el-optionBmi label="Zone one" value="shanghai" />-->
<!--                  <el-optionBmi label="Zone two" value="beijing" />-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Activity time" >-->
<!--                <el-col :span="11">-->
<!--                  <el-date-picker-->
<!--                    v-model="form.date1"-->
<!--                    type="date"-->
<!--                    placeholder="Pick a date" />-->
<!--                </el-col>-->
<!--                <el-col :span="2" class="text-center" >-->
<!--                  <span class="text-gray-500">-</span>-->
<!--                </el-col>-->
<!--                <el-col :span="11">-->
<!--                  <el-time-picker-->
<!--                    v-model="form.date2"-->
<!--                    placeholder="Pick a time"-->

<!--                  />-->
<!--                </el-col>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Instant delivery">-->
<!--                <el-switch v-model="form.delivery" />-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Activity type">-->
<!--                <el-checkbox-group v-model="form.type">-->
<!--                  <el-checkbox value="Online activities" name="type">-->
<!--                    Online activities-->
<!--                  </el-checkbox>-->
<!--                  <el-checkbox value="Promotion activities" name="type">-->
<!--                    Promotion activities-->
<!--                  </el-checkbox>-->
<!--                  <el-checkbox value="Offline activities" name="type">-->
<!--                    Offline activities-->
<!--                  </el-checkbox>-->
<!--                  <el-checkbox value="Simple brand exposure" name="type">-->
<!--                    Simple brand exposure-->
<!--                  </el-checkbox>-->
<!--                </el-checkbox-group>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Resources">-->
<!--                <el-radio-group v-model="form.resource">-->
<!--                  <el-radio value="Sponsor">Sponsor</el-radio>-->
<!--                  <el-radio value="Venue">Venue</el-radio>-->
<!--                </el-radio-group>-->
<!--              </el-form-item>-->
<!--              <el-form-item label="Activity form">-->
<!--                <el-input v-model="form.desc" type="textarea" readonly/>-->
<!--              </el-form-item>-->
<!--              <el-form-item>-->
<!--                <el-button type="primary" @click="onSubmit">Create</el-button>-->
<!--                <el-button>Cancel</el-button>-->
<!--              </el-form-item>-->
<!--            </el-form>-->
<!--          </el-col>-->
<!--          <el-col :span="12">-->

<!--            <el-table :data="leaderboard" style="width: 100%">-->
<!--              <el-table-column prop="rank" label="等级" width="100">-->
<!--              </el-table-column>-->
<!--              <el-table-column prop="status" label="勋章" width="200">-->
<!--              </el-table-column>-->
<!--            </el-table>-->

<!--          </el-col>-->
<!--        </el-row>-->
<!--                  <el-row>-->
<!--                    <el-col :span="12" >-->
<!--                        <el-card style="margin-right: 20px">-->
<!--                          <div ref="chartRefBmi" style="width: 100%; height: 200px;"></div>-->
<!--                        </el-card>-->
<!--                    </el-col>-->
<!--                    <el-col :span="12" > <el-card>-->
<!--                      <div ref="chartRefCalories" style="width: 100%; height: 200px;"></div>-->
<!--                    </el-card></el-col>-->
<!--                  </el-row>-->
<!--      </el-main>-->
<!--      <el-aside class="right">-->
<!--        <el-row>-->
<!--          <el-col  >-->
<!--            <div>-->
<!--              <div class="title">当日消耗卡路里：</div>-->
<!--              <div v-for="(item, index) in sports" :key="index" class="progress-container">-->
<!--                <div class="progress-title">{{ item.sport }}</div>-->
<!--                <svg class="progress-bar" width="100" height="50" viewBox="0 0 100 50">-->
<!--                  <rect-->
<!--                    :x="0"-->
<!--                    :y="10"-->
<!--                    width="100"-->
<!--                    height="30"-->
<!--                    rx="15"-->
<!--                    ry="15"-->
<!--                    fill="lightgray"-->
<!--                  />-->
<!--                  <rect-->
<!--                    :x="0"-->
<!--                    :y="10"-->
<!--                    :width="item.progress + '%'"-->
<!--                    height="30"-->
<!--                    rx="15"-->
<!--                    ry="15"-->
<!--                    :fill="item.color"-->
<!--                  />-->
<!--                </svg>-->
<!--              </div>-->
<!--            </div>-->
<!--          </el-col>-->

<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col  >-->
<!--                  <div ref="chartRefSleeps" style="width: 100%; height: 400px;" ></div>-->
<!--          </el-col>-->

<!--        </el-row>-->
<!--      </el-aside>-->

<!--    </el-container>-->
<!--  </div>-->
</template>
<script setup>
import axios from "axios";
import {useUserStore} from "@/store";
import {ref} from 'vue'
const sleeps= ref([])
const user = useUserStore().userInfo
const userSleeps = ref(null)
const userId = user.id
axios.get(`http://localhost:8081/sleep/MyInfo/${userId}`)
    .then(response => {
      // 处理响应数据
      console.log(response.data.data);
      userSleeps.value = response.data.data
      for (let i =0;i<userSleeps.value.length;i++)
      {
        sleeps.value.push({
                date:userSleeps.value[i].date,
                sleepDuration:userSleeps.value[i].timeQuantume
            })
      }
    })
    .catch(error => {
      // 处理错误
      console.error(error);
    });
// 获取用户的健康分析基础信息
const healthInfo = ref()
const level =ref()
axios({
  method: 'get',
  url: 'http://localhost:8081//healthyanalysis/getHealthyDate',
  params:{
    userId:userId
  }
}).then(respone=>{
      console.log(respone.data.data)
      healthInfo.value=respone.data.date
      level.value = healthInfo.value.healthLevel
}).catch(err)
{
  console.log(err)
}
// 获取用户的基本信息，从pinia存储中获取


// 获取用户当天的运动记录
let userTypes = ref([])
let yoga =()=>{
/
let running =()=>{
  return "跑步"
}
let riding = ()=>{
  return "骑行"
}
let swim = ()=>{
  return "游泳"
}

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
          }
        }
      }
    })
    .catch(error => {
      // 请求失败时处理错误
      console.error('Error:', error);
    })
</script>
<!--<script>-->
<!--import { ref,reactive, onMounted, watch, onUnmounted  } from 'vue';-->
<!--import * as echarts from 'echarts';-->
<!--import { ElProgress } from 'element-plus';-->
<!--export default {-->
<!--  components: {-->
<!--    ElProgress,-->
<!--  },-->
<!--  setup() {-->
<!--    const chartRefBmi = ref(null);-->
<!--    const chartRefCalories = ref(null);-->
<!--    const chartRefSleeps=ref(null);-->



<!--    let myChartSleeps  = null;-->
<!--    const leaderboard = ref([-->
<!--      {rank:'A',status:0,img:''},-->
<!--      {rank:'B',status:0,img:''},-->
<!--      {rank:'B',status:0,img:''}-->
<!--    ]);-->
<!--    const bmiData = [-->
<!--      { name: 'Jan', value: 22.5 },-->
<!--      { name: 'Feb', value: 23.2 },-->
<!--      { name: 'Mar', value: 22.8 }-->

<!--    ];-->
<!--    const caloriesData = [-->
<!--      //  每日卡路里消耗数据-->
<!--      { name: '早餐', value: 500 },-->
<!--      { name: '午餐', value: 700 },-->
<!--      { name: '晚餐', value: 600 },-->
<!--      { name: '零食', value: 200 },-->
<!--    ];-->

<!--    const form = reactive({-->
<!--      name: '',-->
<!--      region: '',-->
<!--      date1: '',-->
<!--      date2: '',-->
<!--      delivery: false,-->
<!--      type: [],-->
<!--      resource: '',-->
<!--      desc: '',-->
<!--    });-->
<!--    const sports = ref([-->
<!--      { sport: '跑步', calories: 200, progress: 40, color: 'green' },-->
<!--      { sport: '游泳', calories: 150, progress: 60, color: 'blue' },-->
<!--      { sport: '骑车', calories: 100, progress: 20, color: 'orange' },-->
<!--    ]);-->
<!--    const sleeps=ref([-->
<!--      { date: '2023-01-01', sleepDuration: 7 },-->
<!--      { date: '2023-02-01', sleepDuration: 9 },-->
<!--      { date: '2023-03-01', sleepDuration: 88 }-->
<!--    ]);-->
<!--    const onSubmit = () => {-->
<!--      console.log('submit!')-->
<!--    }-->
<!--    onMounted(() => {-->




<!--      const chartDom = chartRefBmi.value; // 获取 DOM 元素-->
<!--      const chartDom2 = chartRefCalories.value;-->
<!--      const chartDom3 =chartRefSleeps.value;-->
<!--      if (chartDom) {-->
<!--        const myChartBmi = echarts.init(chartDom);-->
<!--        const myChartCalories = echarts.init(chartDom2);-->


<!--        const optionCalories = {-->
<!--          title: {-->
<!--            text: '每日卡路里消耗',-->
<!--            left: 'center'-->
<!--          },-->
<!--          tooltip: {-->
<!--            trigger: 'item'-->
<!--          },-->
<!--          series: [{-->
<!--            name: '卡路里',-->
<!--            type: 'pie',-->
<!--            radius: '55%',-->
<!--            data: caloriesData,-->
<!--            emphasis: {-->
<!--              itemStyle: {-->
<!--                shadowBlur: 10,-->
<!--                shadowOffsetX: 0,-->
<!--                shadowColor: 'rgba(0, 0, 0, 0.5)'-->
<!--              }-->
<!--            }-->
<!--          }]-->
<!--        };-->
<!--        const optionBmi = {-->
<!--          title: {-->
<!--            text: 'BMI 曲线图',-->
<!--            left: 'center'-->
<!--          },-->
<!--          tooltip: {-->
<!--            trigger: 'axis'-->
<!--          },-->
<!--          xAxis: {-->
<!--            type: 'category',-->
<!--            data: bmiData.map(item => item.name)-->
<!--          },-->
<!--          yAxis: {-->
<!--            type: 'value',-->
<!--            min: 18, // 假设 BMI 正常范围为 18.5-24.9-->
<!--            max: 25,-->
<!--            boundaryGap: [0, '30%']-->
<!--          },-->
<!--          series: [{-->
<!--            data: bmiData.map(item => item.value),-->
<!--            type: 'line',-->
<!--            smooth: true-->
<!--          }]-->
<!--        };-->
<!--        myChartBmi.setOption(optionBmi);-->
<!--        myChartCalories.setOption(optionCalories);-->

<!--      }-->
<!--      if (chartDom3){-->
<!--        const myChartSleeps = echarts.init(chartDom3);-->
<!--        drawChart(myChartSleeps, sleeps.value);-->
<!--      }-->
<!--    });-->

<!--    function updateChartSleepsData(newData) {-->
<!--      if (myChartSleeps) {-->
<!--        myChartSleeps.setOption({-->
<!--          series: [{-->
<!--            data: newData.map(item => item.sleepDuration),-->
<!--          }],-->
<!--          xAxis: {-->
<!--            data: newData.map(item => item.date),-->
<!--          },-->
<!--        });-->
<!--      }-->
<!--    }-->
<!--    function drawChart(chart, data) {-->
<!--      chart.setOption({-->
<!--        title: {-->
<!--          text: '睡眠时间曲线图'-->
<!--        },-->
<!--        tooltip: {},-->
<!--        xAxis: {-->
<!--          data: data.map(item => item.date),-->
<!--          type: 'category',-->
<!--        },-->
<!--        yAxis: {-->
<!--          type: 'value'-->
<!--        },-->
<!--        series: [{-->
<!--          data: data.map(item => item.sleepDuration),-->
<!--          type: 'line'-->
<!--        }]-->
<!--      });-->
<!--    }-->
<!--    watch(sleeps, (newSleeps) => {-->
<!--      updateChartSleepsData(newSleeps);-->
<!--    });-->
<!--    const chartBmi = ref(null)-->

<!--    return {-->
<!--      chartRefBmi,-->
<!--      leaderboard,-->
<!--      form,-->
<!--      onSubmit,-->
<!--      chartRefCalories,-->
<!--      sports,-->
<!--      chartRefSleeps-->
<!--    };-->
<!--  }-->
<!--};-->
<!--</script>-->



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



