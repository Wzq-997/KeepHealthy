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
                <el-radio-group v-model="form.sex">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="生日">
                <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  style="width:350px ;"
                  placeholder="选择日期"
                ></el-date-picker>
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
      { rank: 'A', status: 0 },
      { rank: 'B', status: 1 },
      { rank: 'C', status: 0},
      { rank: 'D', status: 0},
    ]);

    // const hLight=function hightlight(row){
    //    if (row.status==1){
    //      return 1;
    //    }
    //  }
    function tableRowClassName({ row, rowIndex, store }) {
      // 根据 status 来设置不同的类名
      if (row.status == 0) {
        // console.log("status"+row.status);
        return 'status-0';
      } else if (row.status == 1 ) {
        console.log("status"+row.status);
        return 'status-1';
      }
    }
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
      sex: '',

      birthday: '',
      height: null,
      weight: null,
      targetweight: null,
      past_medical_history: '',

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

        myChartBmi.setOption(optionBmi);
        myChartCalories.setOption(optionCalories);

      }
      if (chartDom3){
        const myChartSleeps = echarts.init(chartDom3);
        drawChart(myChartSleeps, sleeps.value);
      }
    });
    const handleStatusMedal = () => {

      if (leaderboard.value[1].status == 1){

        console.log("BB男男女女");
      }
    }
    onMounted(() => {
      handleStatusMedal();
    })
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
