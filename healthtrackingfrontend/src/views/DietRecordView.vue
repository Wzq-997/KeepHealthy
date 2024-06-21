<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
    >
      <el-table-column prop="date" label="日期" width="180" />
      <el-table-column prop="name" label="食物" width="180" />
      <el-table-column prop="consumeCalories" label="摄入卡路里" />
    </el-table>
    <el-button @click="showDialog = true" type="primary" style="margin-top: 20px">记录今日饮食</el-button>

    <el-dialog
        v-model="showDialog"
        title="添加饮食记录"
        :close-on-click-modal="false"
        @close="clearForm"
    >

      <el-form :model="form" :rules="rules" label-width="80px" ref="form">
        <el-form-item label="食物" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="calories">
          <el-input v-model="form.consumeCalories"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取消</el-button>
          <el-button type="primary" @click="addTableRow">确认</el-button>
        </span>
    </el-dialog>
  </div>

</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from "axios";
const userId = ref('U0001')  //用户编号
const tableData = ref();
// onMounted(()=>{
//   axios({
//     method:'post',
//     url:'http://localhost:8081/user/userdietManager/getAlldiet',
//     params:{
//       userId:userId.value  //传参
//     }
//   }).then(respone=>{
//     tableData.value=respone.data.data  //绑定表格数据
//     console.log(tableData.value)
//   })
// })


const tableRowClassName = ({ rowIndex }) => {
  if (rowIndex === 1) {
    return 'warning-row';
  } else if (rowIndex === 3) {
    return 'success-row';
  }
  return '';
}

const showDialog = ref(false);
const form = ref({
  name: '',
  calories: '',
});
// 假设我们不需要复杂的验证规则，但你可以根据需要添加
const rules = {
  name: [
    { required: true, message: '请输入水果名', trigger: 'blur' },
  ],
  quantity: [
    { required: true, message: '请输入数量', trigger: 'blur' },
    { type: 'number', message: '卡路里必须是数字', trigger: 'blur' }
  ],
};

// 添加表格行的方法
const addTableRow = () => {
  // 你可以添加表单验证逻辑，这里为了简化省略了
  // 假设验证通过，则添加新行
  const newRow = {
    id: `F${tableData.value.length + 1}001`, // 假设ID生成逻辑
    name: form.value.name,
    calories: form.value.calories,
  };
  tableData.value.push(newRow);
  // 重置表单
  form.value = {
    name: '',
    consumeCalories: '',
  };
  // 关闭对话框
  showDialog.value = false;
};

// 清除表单的方法
const clearForm = () => {
  form.value = {
    name: '',
    calories: '',
  };
};

</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
