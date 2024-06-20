<template>
  <br>
  <div class="container">
    <div class="sportsType">
      <h2>请选择运动类型</h2>
      <el-radio-group v-model="selectedSport">
        <el-radio v-for="sport in sports" :key="sport.sport_id" :label="sport.sport_id">{{ sport.sport_name }}</el-radio>
      </el-radio-group>
    </div>
    <div class="button-group">
      <button class="big-button start-button" @click="startTimer">开始</button>
      <button v-if="started" class="big-button stop-button" @click="stopTimer">结束</button>
    </div>
    <div v-if="started" class="time-passed">
      <p>已经过去的时间: {{ passedTime.toFixed(2) }} 秒</p>
    </div>
  </div>
</template>


<script>
import { ref, onMounted, onUnmounted } from 'vue';

export default {
  setup() {
    const started = ref(false);
    const stopTime = ref(null);
    let currentTimer = null;
    let startTime = 0;
    const passedTime = ref(0);

    const startTimer = () => {
      if(!started.value) {
        started.value = true;
        startTime = Date.now();
        currentTimer = setInterval(() => {
          passedTime.value = (Date.now() - startTime) / 1000;
        }, 100);
      }
    };

    const stopTimer = () => {
      if(currentTimer) {
        clearInterval(currentTimer);
        currentTimer = null;
      }
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

    const selectedSport = ref('');
    const sports = ref([
      { sport_id: 'S001', sport_name: '跑步', single_calorie: 1.3 },
      { sport_id: 'S002', sport_name: '瑜伽', single_calorie: 2.3 },
      { sport_id: 'S003', sport_name: '游泳', single_calorie: 3.5 },
      { sport_id: 'S004', sport_name: '骑行', single_calorie: 2.6 },
      { sport_id: 'S005', sport_name: '散步', single_calorie: 0.3 },
      { sport_id: 'S006', sport_name: '攀登', single_calorie: 5 },
    ]);

    return {
      started,
      startTimer,
      stopTimer,
      passedTime,
      stopTime,
      selectedSport,
      sports,
    };
  },
};
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-size: 20px;

  //background-color: #f0f0f0;


}

.sportsType {
  margin-bottom: 0px;
  background-color: #cac6c6;

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
}

.stop-button {
  background-color: #f44336;
}

.time-passed {
  color: #555;
  font-weight: bold;
}
</style>