<template>
  <div class="common-layout">
    <el-container>
      <el-main class="left" >
        <el-row>
          <el-col :span="12"    class="MyInform"  style=" border-radius: 3px; box-shadow: 0 0 15px 0 rgba(240,240,240,0.75);" >
            <el-form :model="form" class="Myform"size="small">
              <el-form-item label="姓名" style=" margin-top: 20px;" >
                <el-input v-model="form.name"  readonly style="width: 350px"/>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio-group v-model="form.sex" disabled >
                  <el-radio value="1" >男</el-radio>
                  <el-radio value="0" >女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="身高">
                <el-input v-model.number="form.height" type="number" readonly style="width: 350px"></el-input>
              </el-form-item>
              <el-form-item label="体重">
                <el-input v-model.number="form.weight" type="number" readonly style="width: 350px"></el-input>
              </el-form-item>
              <el-form-item label="目标体重">
                <el-input v-model.number="form.targetweight" type="number" readonly style="width: 330px"></el-input>
              </el-form-item>
              <el-form-item label="过往病史">
                <el-input v-model="form.past_medical_history" type="textarea" readonly style="width: 330px"/>
              </el-form-item>

            </el-form>
          </el-col>
          <el-col :span="12"  >

            <el-table :data="leaderboard" class="box" style="margin-left: 10px;height: 40vh;" :row-class-name="tableRowClassName" >
              <el-table-column prop="rank"  label="等级" width="200"  >
              </el-table-column>
              <el-table-column prop="status" label="勋章" width="200">
                <template #default="scope">
                  <img class="medal" v-if="scope.row.status === 1"  src="../img/icon/勋章-阳光勋章.png" alt="图片死掉了" />
                  <img class="medal"  v-else-if="scope.row.status === 0" src="../img/icon/勋章.png" alt="图片死掉了" />
                  <span v-else>{{ scope.row.status }}</span>
                </template>
              </el-table-column>

            </el-table>

          </el-col>
        </el-row>
        <el-row style="margin-top: 20px">
          <el-col :span="12" >
            <el-card style="margin-right: 20px">
              <div ref="chartRefBmi" style="width: 100%; height: 42vh;margin-left: 15px"></div>
            </el-card>
          </el-col>
          <el-col :span="12" >
            <el-card>
            <div ref="chartRefCalories" style="width: 100%; height: 42vh;margin-left: 15px"></div>
          </el-card></el-col>
        </el-row>
      </el-main>
      <el-aside class="right">
        <el-row style="width: 92%">
          <el-col >
            <div  class="box" style="margin-top: 20px;margin-left: 10px;height: 40vh">
              <br>

              <div class="title">当日消耗卡路里：</div>
              <div v-for="(item, index) in sports" :key="index" class="progress-container">
                <div   >{{ item.sport }}</div>
                <svg  width="400px" height="50" viewBox="0 0 100 50">
                  <rect
                    :x="-110"
                    :y="10"
                    width="300"
                    height="30"
                    rx="15"
                    ry="15"
                    fill="lightgray"/>
                  <rect
                    :x="-110"
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
          <el-col   style="margin-top: 5px;width: 9px;">
            <div ref="chartRefSleeps"class="box" style="width:92%;height:42vh;margin-top: 15px" ></div>
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
import {useUserStore} from "@/store";
import axios from "axios";
export default {
  components: {
    ElProgress,
  },
  setup() {
    const chartRefBmi = ref(null);
    const chartRefCalories = ref(null);
    const chartRefSleeps = ref(null);
    let myChartSleeps = null;
    const user = useUserStore().userInfo
    const userSleeps = ref()
    const userId = user.id
    const form = reactive({
      name: user.name,
      sex: user.sex,
      height: user.height,
      weight: user.weight,
      targetweight: user.targetweight,
      past_medical_history: user.past_medical_history,
    });

    const leaderboard = ref([
      {rank: 'A', status: 0},
      {rank: 'B', status: 0},
      {rank: 'C', status: 0},
      {rank: 'D', status: 0},
    ]);

    function tableRowClassName({row, rowIndex, store}) {
      // 根据 status 来设置不同的类名
      if (row.status == 0) {
        // console.log("status"+row.status);
        return 'status-0';
      } else if (row.status == 1) {
        console.log("status" + row.status);
        return 'status-1';
      }
    }

    // 存储bmi组
    const bmiData = ref([])


    const caloriesData = ref([])


    const sports = ref([]);
    //存储睡眠时间
    const sleeps = ref([])
    const healthInfo = ref([])
    const level = ref()
    onMounted(() => {
      fetchData();
      const chartDom = chartRefBmi.value; // 获取 DOM 元素
      const chartDom2 = chartRefCalories.value;
      const chartDom3 = chartRefSleeps.value;
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
            data: caloriesData.value,
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
            data: bmiData.value.map(item => item.date)
          },
          yAxis: {
            type: 'value',
            min: 18, // 假设 BMI 正常范围为 18.5-24.9
            max: 25,
            boundaryGap: [0, '30%']
          }
        };

        myChartBmi.setOption(optionBmi);
        myChartCalories.setOption(optionCalories);

      }
      if (chartDom3) {
        const myChartSleeps = echarts.init(chartDom3);
        drawChart(myChartSleeps, sleeps.value);
      }
    });

    const fetchData = async () => {
      try {
        // 获取用户所有的睡眠时间
        const sleepResponse = await  axios.get(`http://localhost:8081/sleep/MyInfo/${userId}`)
            .then(response => {
              // 处理响应数据
              console.log(response.data.data);
              userSleeps.value = response.data.data
              console.log("睡眠时间组:" + userSleeps.value)
              for (let i = 0; i < userSleeps.value.length; i++) {
                sleeps.value.push({
                  date: userSleeps.value[i].date,
                  sleepDuration: userSleeps.value[i].timeQuantume
                })
              }
              for (let i = 0; i < userSleeps.value.length; i++) {
                console.log("获取到的睡眠情况：" + sleeps.value[i].date + "," + sleeps.value[i].sleepDuration)
              }
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
        // 获取用户的健康分析基础信息

       const healthyResponse =await axios({
          method: 'get',
          url: 'http://localhost:8081//healthyanalysis/getHealthyDate',
          params: {
            userId: userId
          }
        }).then(respone => {
          if (respone.data.code == 1) {
            console.log("健康情况组：" + respone.data.data.length)
            healthInfo.value = respone.data.date
            for (let i = 0; i < respone.data.data.length; i++) {
              bmiData.value.push({
                date: respone.data.data[i].date,
                bmi: respone.data.data[i].bmi
              })
            }
            level.value = respone.data.data[respone.data.data.length - 1].healthLevel  //当前的健康等级
            //先把等级表默认为0
            for (let i = 0; i < 4; i++) {
              leaderboard.value[i].status = 0;
            }
            // 更新健康等级
            switch (level.value) {
              case 1:
                leaderboard.value[0].status = 1;
                break;
              case 2:
                leaderboard.value[1].status = 1;
                break;
              case 3:
                leaderboard.value[2].status = 1;
                break;
              case 4:
                leaderboard.value[3].status = 1;
                break;
            }
          } else {
            alert("数据获取失败!")
          }
        }).catch(error => {
          console.log(error)
        })
        // 获取用户当天的运动记录及消耗的卡路里
       const CaloriesResponse = await axios({
          method: 'get',
          url: 'http://localhost:8081/sport/getSportRecordOne',
          params: {
            userId: userId
          }
        }).then(response => {
          if (response.data.code == 1) {
            console.log("今天的运动记录：" + response.data.data)
            for (let i = 0; i < response.data.data.length; i++) {
              if (response.data.data[i].sportTypes == "S001") {
                sports.value.push({
                  sport: "跑步",
                  calories: response.data.data[i].calories,
                  progress: response.data.data[i].progress,
                  color: response.data.data[i].color
                })
              } else if (response.data.data[i].sportTypes == "S002") {
                sports.value.push(
                    {
                      sport: "瑜伽",
                      calories: response.data.data[i].calories,
                      progress: response.data.data[i].progress,
                      color: response.data.data[i].color
                    }
                )
              } else if (response.data.data[i].sportTypes == "S003") {
                sports.value.push(
                    {
                      sport: "游泳",
                      calories: response.data.data[i].calories,
                      progress: response.data.data[i].progress,
                      color: response.data.data[i].color
                    }
                )
              } else if (response.data.data[i].sportTypes == "S004") {
                sports.value.push(
                    {
                      sport: "骑行",
                      calories: response.data.data[i].calories,
                      progress: response.data.data[i].progress,
                      color: response.data.data[i].color
                    }
                )
              } else if (response.data.data[i].sportTypes == "S005") {
                sports.value.push(
                    {
                      sport: "散步",
                      calories: response.data.data[i].calories,
                      progress: response.data.data[i].progress,
                      color: response.data.data[i].color
                    }
                )
              } else if (response.data.data[i].sportTypes == "S006") {
                sports.value.push(
                    {
                      sport: "攀登",
                      calories: response.data.data[i].calories,
                      progress: response.data.data[i].progress,
                      color: response.data.data[i].color
                    }
                )
              }
            }
            // 获取每项运动的卡路里
            for (let i = 0; i < sports.length; i++) {
              caloriesData.value.push({
                name: sports.value[i].sport,
                value: sports.value[i].calories
              })
            }
          } else {
            alert("数据获取失败!")
          }
        }).catch(error => {
          console.log(error)
        })
      }catch (error ) {
        console.log(error)
      }
    }
    function updateChartSleepsData(newData) {
      if (myChartSleeps) {
        myChartSleeps.setOption({
          yAxis: [{
            data: newData.map(item => item.sleepDuration),
          }],
          xAxis: {
            data: newData.map(item => item.date),
          }
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
          type: 'value',
          data: data.map(item => item.sleepDuration),
        }
      });
    }
    watch(sleeps, (newSleeps) => {
      updateChartSleepsData(newSleeps);
    });

    return {
      chartRefBmi,
      leaderboard,
      form,
      tableRowClassName,
      chartRefCalories,
      sports,
      chartRefSleeps
    };
  }
};
</script>

<style>
.status-1{
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}

.MyInform{
  max-height:500px;
  height: 40vh;
  overflow: hidden; /* 隐藏溢出内容 */
  display: flex; /* 弹性布局，以便内部元素可以缩小 */
  flex-direction: column; /* 垂直排列内部元素 */

}
.medal{
  width: 20px;
  height: auto;
}
.Myform{
  background-color: #ffffff;
//width: 300px;
  margin-top: -10px;
  height: 67%;
  //justify-content: flex-end;


}

.left{
  width: 300px;

}

.right{

  width: 500px;
}

.box{
  background-color: #ffffff;
  box-shadow: 0 0 15px 0 rgba(240,240,240,0.75);
  border-radius: 3px;
}
</style>
