<template>
    <div class="container">
      <div class="left">
        <p class="text-center demonstration">每日食谱</p>
        <el-carousel height="200px" direction="vertical" :autoplay="false">
          <el-carousel-item v-for="item in 4" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <p class="text-center demonstration">图片仅供参考</p>
        <el-carousel height="400px" direction="vertical" type="card" :autoplay="false">
          <el-carousel-item v-for="item in 4" :key="item">
            <h3>{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="right">
        <div class="top">
          <p class="text-center demonstration">制作方法</p>
          <!-- 在这里添加内容 -->
        </div>
        <div class="bottom">
          <p class="text-center demonstration">注意事项</p>
          <!-- 在这里添加内容 -->
        </div>
      </div>
    </div>
  </template>
  
  <style scoped>
  .container {
    display: flex;
    height: 100vh;
  }
  
  .left {
    flex: 1;
    padding: 20px;
  }
  
  .right {
    flex: 1;
    display: flex;
    flex-direction: column;
  }
  
  .top, .bottom {
    flex: 1;
    padding: 20px;
  }
  
  .el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }
  </style>
  