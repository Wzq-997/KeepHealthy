<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
      >
        <el-table-column prop="id" label="编号" width="180" />
        <el-table-column prop="name" label="水果名" width="180" />
        <el-table-column prop="calories" label="卡路里" />
      </el-table>
      <el-button @click="showDialog = true" type="primary" style="margin-top: 20px">记录今日饮食</el-button>
  
      <el-dialog
        v-model="showDialog"
        title="添加饮食记录"
        :close-on-click-modal="false"
        @close="clearForm"
      >
        <el-form :model="form" :rules="rules" label-width="80px" ref="form">
          <el-form-item label="水果名" prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="卡路里" prop="calories">
            <el-input v-model="form.calories"></el-input>
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
  import { ref } from 'vue';
  
  const tableData = ref([
    {
      id: 'F0001',
      name: '苹果',
      calories: '89',
    },
    {
      id: 'F0002',
      name: '香蕉',
      calories: '43',
    },
    {
      id: 'F0003',
      name: '葡萄',
      calories: '47',
    },
    {
      id: 'F0004',
      name: '橙子',
      calories: '50',
    },
  ]);
  
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
  calories: [  
    { required: true, message: '请输入卡路里', trigger: 'blur' },  
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
    calories: '',  
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
  