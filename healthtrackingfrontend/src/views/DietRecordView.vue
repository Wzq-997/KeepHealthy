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
    >
      <el-form :model="form" :rules="rules" label-width="80px" ref="formRef">
        <el-form-item label="水果名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="卡路里" prop="calories">
          <el-input v-model="form.calories" type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="primary" @click="submitForm">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const tableData = ref([
  { id: 'F0001', name: '苹果', calories: 89 },
  { id: 'F0002', name: '香蕉', calories: 43 },
  { id: 'F0003', name: '葡萄', calories: 47 },
  { id: 'F0004', name: '橙子', calories: 50 },
]);

const tableRowClassName = ({ rowIndex }) => {
  if (rowIndex === 1) return 'warning-row';
  if (rowIndex === 3) return 'success-row';
  return '';
};

const showDialog = ref(false);
const form = ref({
  name: '',
  calories: null,
});
const formRef = ref(null);

const rules = {
  name: [{ required: true, message: '请输入水果名', trigger: 'blur' }],
  calories: [
    { required: true, message: '请输入卡路里', trigger: 'blur' },
    { 
        type: 'number', message: '卡路里必须是数字', trigger: 'blur',
        validator: (rule, value, callback) => {
            const num = Number(value);
            if (isNaN(num)) {
                callback(new Error('卡路里必须是数字'));
            } else {
                callback();
            }
        }
    }
],
};

const closeDialog = () => {
  showDialog.value = false;
};

const submitForm = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      addTableRow();
    }
  });
};

const addTableRow = () => {
  const newId = `F${(tableData.value.length + 1).toString().padStart(4, '0')}`;
  const newRow = { id: newId, name: form.value.name, calories: form.value.calories };
  tableData.value.push(newRow);
  // 保留表单内容，不清除表单
  closeDialog();
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
