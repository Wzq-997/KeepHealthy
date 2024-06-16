<template>
    <div class="Page1">
        <div>
            <div class="homePage" style="height: 300px; width: 600px; float: left;">
            <!-- ^ 在这里，我加入了 background-color: red -->
            <el-card >
                <template #header>Yummy hamburger</template>
                <img
                src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                style="width: 600px; height: 200px;"/>
            </el-card>
            </div>
            <div style="float: left;">
                <el-table :data="tableData" style="width: 400px; padding: 30px;">
                <el-table-column prop="item" label="item" />
                <el-table-column prop="name" label="Name"/>
                <el-table-column prop="sports" label="sports" />
                </el-table>
            </div>
            <div class="demo-progress" style="padding-left: 50px; padding-top: 50px;">
            <el-progress :text-inside="true" :stroke-width="26" :percentage="70" />
            <el-progress
            :text-inside="true"
            :stroke-width="24"
            :percentage="100"
            status="success"
             />
            <el-progress
                :text-inside="true"
                :stroke-width="22"
                :percentage="80"
                status="warning"
            />
            <el-progress
                :text-inside="true"
                :stroke-width="20"
                :percentage="50"
                status="exception"
            />
            </div>
        </div>
        <div style="clear: both;">
            <p style="color: cornflowerblue; text-align: left;">我的运动处方</p>
        </div>
       

    </div>
  
  </template>
  <script setup>
  const tableData = [
    {
      item: '1',
      name: 'Tom',
      sports: 'run',
    },
    {
      item: '2',
      name: 'Tom',
      sports: 'walk',
    },
    {
      item: '3',
      name: 'Tom',
      sports: 'run',
    },
    {
      item: '4',
      name: 'Tom',
      sports: 'run',
    },
    {
      item: '5',
      name: 'Tom',
      sports: 'run',
    },
    {
      item: '6',
      name: 'Tom',
      sports: 'run',
    },
  ]
  </script>
  
  <style scoped>
  .demo-progress .el-progress--line {
    margin-bottom: 15px;
    max-width: 600px;
  }
  </style>