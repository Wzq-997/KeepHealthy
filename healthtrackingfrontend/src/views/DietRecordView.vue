<template>
  <div style="width: 100%">
    <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
    >
      <el-table-column prop="date" label="日期" width="300px" />
      <el-table-column prop="name" label="食物" width="300px" />
      <el-table-column prop="consumeCalories" label="摄入卡路里" />
    </el-table>
    <el-button @click="showDialog = true" type="primary" style="margin-top: 20px">记录今日饮食</el-button>

    <el-dialog
        v-model="showDialog"
        title="添加饮食记录"
        :close-on-click-modal="false"
        @close="clearForm"
    >

      <el-form :model="form" :rules="rules" label-width="80px" ref="formRef">
        <el-form-item label="食物" prop="name">
          <!--          修改下拉选择食物-->
          <el-select
              v-model="selectedFood"
              placeholder="点击选择食物"
              clearable
          >
            <el-option style="border-radius: 40px"
                v-for="item in foods"
                       :key="item.id"
                       :label="item.name"
                       :value="item.id" />
<!--            <el-option label="苹果" value="食物二" />-->
          </el-select>

        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity"></el-input>
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
import {useUserStore} from "@/store";
const user = useUserStore().userInfo

const userId = user.id  //用户编号
const tableData = ref([]);
onMounted(()=>{
  axios({
    method:'post',
    url:'http://localhost:8081/user/userdietManager/getAlldiet',
    params:{
      userId:userId  //传参
    }
  }).then(respone=>{
    tableData.value=respone.data.data  //绑定表格数据
    console.log(tableData.value)
  }),
      axios({
        method:'get',
        url:'http://localhost:8081/Food/getFood',
      }).then(respone=>{
        foods.value=respone.data.data  //绑定表格数据
        console.log(tableData.value)
      })
})

const foods=ref([])
  // {id:'F0001',name:'油炸',calories:120},
  // {id:'F0002',name:'水果',calories:20},
  // {id:'F0003',name:'蔬菜',calories:10},
  // {id:'F0004',name:'海鲜',calories:15},
  // {id:'F0005',name:'烧烤',calories:50}

let selectedFood=ref()
const formRef=ref([])

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
  quantity:'',
  calories: '',
});
// 假设我们不需要复杂的验证规则，但你可以根据需要添加
const rules = {
  name: [
    { required: true, message: '请输入水果名', trigger: 'blur' },
  ],
  quantity: [
    { required: true, message: '请输入数量', trigger: 'blur' },
    { type: 'number', message: '数量必须是数字', trigger: 'blur' }
  ],
};

function addTableRow() {
  const calories = calculateCalories(selectedFood.value, form.value.quantity);
  const newData = {
    id:foods.value.find(item=>item.id===selectedFood.value)?.id || '',
    quantity:foods.value.find(item=>item.id===selectedFood.value)?.quantity || '',
    date: new Date().toISOString().slice(0, 10), // 示例日期
    name: foods.value.find(item => item.id === selectedFood.value)?.name || '', // 获取食物名称
    consumeCalories: calories,
  };
  tableData.value.push(newData);
  axios({
    method:'post',
    url:'http://localhost:8081/user/userdietManager/insertDiet',
    params:{
      userId:userId,
      foodId:foods.value.find(item=>item.id===selectedFood.value)?.id || '',
      quantity:form.value.quantity,
    }
  }).then(respone=>{
    console.log(respone.data.data)
  })
  clearForm(); // 添加后清除表单
  showDialog.value = false; // 关闭对话框
}
//计算卡路里
function calculateCalories(foodId, quantity) {
  // 根据 foodId 从 foods 数组中找到对应的卡路里，并乘以数量返回
  const food = foods.value.find(item => item.id === foodId);
  if (food) {
    return food.calories * quantity;
  }
  return 0; // 如果没有找到对应的食物，返回0卡路里
}

// 清除表单的方法
const clearForm = () => {
  form.value = {
    name: '',
    quantity:'',
    calories: '',
  };
  selectedFood.value='';
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
