<template>
  <div class="container">
    <div class="left">

      <el-carousel height="600px"

                   direction="vertical"
                   @change="carouselChange"
                   type="card"
                   :autoplay="true"
style="width: 70vh"
                   interval="2000"
      >
        <el-carousel-item v-for="(item, index) in carouselItems"  style="border-radius: 40px" :key="index">
          <!-- 使用 img 标签展示图片 -->
          <img :src="item.src" class="img" alt="Dish Image">
        </el-carousel-item>
      </el-carousel>
      <p class="text-center demonstration" style="color: #cac6c6;margin-right: 20px">图片仅供参考</p>
    </div>
    <div class="right">
      <div class="top" style=" background-color: #b7d9ac;">
        <h2 style="color: white; text-align: center;">{{names[currentIndex]}}</h2>
        <p
           class="text-center demonstration"
           v-for="(part, index) in splitMake"
           :key="index"
        >
          {{part}}
        </p>

      </div>
      <div class="bottom" style="height: 20px;color:   #b7d9ac;">
       <h2 style="line-height: 30px">
         <img src="../img/icon/注意.png" class="icons" />
         注意事项
       </h2>

        <p  class="text-center demonstration"
            v-for="(part, index) in splitnote"
            :key="index"
            style="margin-left: 20px"
        >{{part}}</p>
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
.img{
  width: 100%;
  height: 40vh;
  border-radius: 40px;
}
.right {
  flex: 1;
  display: flex;
  flex-direction: column;
  color: white;

}

.top, .bottom {
  flex: 1;
  padding: 20px;
  border-radius: 30px;
}

.bottom{
  margin-top: 20px;

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
  background-color: rgba(211, 220, 230, 0);

}


.box{
  background-color: #ffffff;
  box-shadow: 0 0 15px 0 rgba(240,240,240,0.75);
  border-radius: 40px;
  overflow: hidden;

}
.icons {
  width: 30px;
  height: 30px;
//position: absolute;
  margin-right: 8px;

}
</style>


<script setup>
import axios from "axios";
import {computed, onMounted, ref} from 'vue'
//菜名
const names = ref([])//'烧椒皮蛋','酱烧排骨','姜葱炒花甲','可乐鸡翅'
//绑定图片（也可以从数据库中拿）
const carouselItems = ref([
  { src: require('../img/diet/M001.jpg') },
  { src: require('../img/diet/M002.jpg') },
  { src: require('../img/diet/M003.jpg') },
  { src: require('../img/diet/M004.jpg') },
]);
//  所有菜的制作方法
const make = ref([])
  //   '将皮蛋切块，青红尖椒烧至虎皮状后放在皮蛋上。再加入由蒜末、生抽、陈醋、盐、白糖、味精、胡椒粉、油辣椒、红油调成的味汁即可',
  //   '排骨适量，洗干净，备用。排骨放入高压锅中，加入适量，盐，八角香叶，陈皮，大酱，酱油，老抽，盐，焖熟。焖熟排骨倒入锅中，大火收汁。排骨炖好，加入适量鸡精调味，出锅。',
  // '花甲用清水浸泡一早上，让其吐沙。姜葱蒜、辣椒、香菜切好待用。花甲冷水下锅，水煮开，花甲开壳即可捞出来滤干水分。姜蒜下锅炒香。加入花甲炒5分钟，倒入老抽翻炒，最后加葱及香菜即可出锅。',
  // '鸡翅冷水下锅焯一下水、去浮沫。洗净后控干水份。锅中倒少许油，下鸡翅煎至两面金黄。倒入料酒。小瓶可乐。姜、葱。来点老抽和生抽调味。煮开后中火焖25-30分钟。大火收汁至浓稠。好入味哦！'

//分割make
const splitMake=computed(()=>{
  if (make.value[currentIndex.value]) {
    console.log(make.value[currentIndex.value].split('。'));
    return make.value[currentIndex.value].split('。');
  }
  return [];
})

//  注意事项同理
const note = ref([])
  // '非常美味，但是注意不要摄入太多哦',
  // '吃饭不仅是满足生理需求的过程，还涉及到社交和文化活动。与家人、朋友一起分享美食是增进感情、传承文化的重要方式。',
  // '食物中的维生素、矿物质和其他微量营养素对于维持身体的正常生理功能至关重要。它们参与酶的活动、细胞结构的构建、神经传导、免疫防御等多种生命过程。',
  // '老抽和生抽看鸡翅多少适当增减;鸡翅大的可以划几刀让它更入味！'

//分割note
const splitnote=computed(()=>{
  if (note.value[currentIndex.value]) {
    console.log(note.value[currentIndex.value].split('。'));
    return note.value[currentIndex.value].split('。');
  }
  return [];
})

//  当前索引下标
const currentIndex = ref(0)
onMounted(()=>{
  axios({
    method:'post',
    url:'http://localhost:8081/diet/getMenuRecord'
  }).then(respone=>{
    let data = respone.data.data
    for(let i = 0;i<data.length;i++)
    {
      names.value.push(data[i].name)
      make.value.push(data[i].make)
      note.value.push(data[i].tips)
    }
    console.log(respone.data.data)
    console.log(make.value)
    console.log(note.value)
  })
})
//
// const carouselItems = ref([
//   { title:'幻灯片A' },
//   { title:'幻灯片B' },
//   { title:'幻灯片C' },
//   { title:'幻灯片D' },
// ])
const carouselChange =(index)=>{
  // 记录当前幻灯片下标
  currentIndex.value = index
}

</script>

