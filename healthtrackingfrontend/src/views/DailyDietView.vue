<template>
  <div class="container">
    <div class="left">
      <p class="title">每日食谱</p>
      <el-carousel height="200px" direction="vertical" :autoplay="false" class="carousel">
        <el-carousel-item v-for="item in recipes" :key="item.id">
          <img :src="item.image" alt="Recipe Image" class="carousel-image" />
        </el-carousel-item>
      </el-carousel>
      <p class="title">图片仅供参考</p>
      <el-carousel height="400px" direction="vertical" type="card" :autoplay="false" class="carousel">
        <el-carousel-item v-for="item in references" :key="item.id">
          <img :src="item.image" alt="Reference Image" class="carousel-image" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="right">
      <div class="top">
        <p class="title">制作方法</p>
        <div class="content">
          虾的天花板吃法!
          <br>做法1: 锅内铺姜和葱，放上虾，淋两圈料酒，盖锅盖中火焖6分钟，过冷水剥壳。
          <br>做法2: 调个酱汁:葱、蒜、小米辣、生抽2勺、陈醋1勺、香油1勺、蚝油1勺、凉白开适量，拌匀即可。原汁原味很鲜甜。
          <br><br>蒜香鸡翅
          <br>步骤1: 洗净的鸡翅对半切开，加葱 几片姜。
          <br>步骤2: 一勺料酒。
          <br>步骤3: 少许胡椒粉。
          <br>步骤4: 2勺生抽。
          <br>步骤5: 2勺奥尔良腌料。
          <br>步骤6: 抓匀腌制30/40分钟。
          <br>步骤7: 六成油温，一个个下鸡翅入锅炸(中小火炸), 炸至金黄色捞出。
        </div>
      </div>
      <div class="bottom">
        <p class="title">注意事项</p>
        <div class="content">
          <ul>
            <li>处理虾时要注意去除虾线，这样能减少腥味。</li>
            <li>煮虾的时间不要太长，否则会影响口感。</li>
            <li>腌制鸡翅时，可以适量增加腌制时间以让调料更入味。</li>
            <li>炸鸡翅时要注意油温，防止油温过高导致外焦里生。</li>
            <li>烹饪时注意厨房安全，防止热油溅出。</li>
            <li>在处理生食材后，一定要彻底清洗双手和用具，以防交叉污染。</li>
            <li>对于初次尝试的新菜谱，可以先做小份量，熟悉流程后再大规模制作。</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 定义图片数组
const recipes = ref([
  { id: 1, image: require('@/assets/Dishes1.png') },
  { id: 2, image: require('@/assets/Dishes2.png') },
  { id: 3, image: require('@/assets/Dishes3.png') },
  { id: 4, image: require('@/assets/Dishes4.png') },
]);

const references = ref([
  { id: 1, image: require('@/assets/Dishes1.png') },
  { id: 2, image: require('@/assets/Dishes2.png') },
  { id: 3, image: require('@/assets/Dishes3.png') },
  { id: 4, image: require('@/assets/Dishes4.png') },
]);
</script>

<style scoped>
.container {
  display: flex;
  height: 100vh;
  background-color: #f0f0f0;
  padding: 20px;
  box-sizing: border-box;
}

.left {
  flex: 1;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-right: 10px;
  display: flex;
  flex-direction: column;
}

.right {
  flex: 1;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

.top, .bottom {
  margin-bottom: 20px;
}

.title {
  font-size: 1.5em;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.content {
  font-size: 1.2em;
  line-height: 1.6;
  color: #666;
  padding: 10px;
  background-color: #fafafa;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.carousel {
  margin-bottom: 20px;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

ul {
  list-style-type: disc;
  padding-left: 20px;
}

ul li {
  margin-bottom: 10px;
}
</style>
