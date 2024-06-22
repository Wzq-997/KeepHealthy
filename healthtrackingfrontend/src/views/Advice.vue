<template>
  <div class="container">
    <div class="input-group">
      <input type="text" v-model="question" placeholder="请输入您的需求" class="input-field">
      <el-button @click="ask" type="primary">获取建议</el-button>
      <el-button @click="reset" type="success">重置</el-button>
    </div>
    <div class="input-group">
      <div>建议: </div>
      <el-input
          type="textarea"
          :autosize="{ minRows: 4, maxRows: 8}"
          v-model="answer"
          v-loading="loading">
      </el-input>
    </div>
    <!-- 无历史记录时隐藏 -->
    <div class="history" v-if="history.length > 0">
      <h3>历史记录</h3>
      <div v-for="(record, index) in history" :key="index" class="history-record">
        <div><strong>问题:</strong> {{ record.question }}</div>
        <div><strong>建议:</strong> {{ record.answer }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElMessage, ElButton, ElInput } from 'element-plus';
import axios from 'axios';

const question = ref('');
const answer = ref('');
const loading = ref(false);
const history = ref([]);

const httpUrl = '';

const ask = async () => {
  if(question.value === '') {
    ElMessage.warning('请输入需求');
  } else {
    loading.value = true;
    try {
      const response = await axios.post(`${httpUrl}/test/ask?question=${question.value}`);
      answer.value = response.data.result;
      history.value.push({ question: question.value, answer: response.data.result });
    } catch (error) {
      console.error('Error:', error);
    } finally {
      loading.value = false;
    }
  }
};

const reset = () => {
  question.value = '';
  answer.value = '';
  history.value = [];
  loading.value = false;
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f5f5f5;
}

.container {
  margin: 20px;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.input-group {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.input-group input[type="text"] {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 16px;
  transition: border-color 0.3s ease;
}

.input-group button {
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
  margin-left: 5px;
}

.history {
  margin-top: 20px;
}

.history h3 {
  margin-bottom: 10px;
}

.history-record {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 3px;
  margin-bottom: 10px;
}

.history-record strong {
  color: #333;
}
</style>
