<template>
  <div class="mine-container">
    <el-card shadow="always" class="user-info-card">
      <div slot="header" class="clearfix">
        <h3>个人信息</h3>
      </div>
      <div v-if="!editing">
        <div>
          <p><strong>用户昵称：</strong>{{ user.name }}</p>
          <p><strong>密码：</strong>********</p>
          <p><strong>性别：</strong>{{ user.sex === 1 ? '男' : '女' }}</p>
          <p><strong>身高：</strong>{{ user.height }}cm</p>
          <p><strong>体重：</strong>{{ user.weight }}kg</p>
          <p><strong>目标体重：</strong>{{ user.targetweight }}kg</p>
          <p><strong>过往病史：</strong>{{ user.past_medical_history }}</p>
        </div>
        <el-button type="primary" size="small" @click="editUserInfo">编辑</el-button>
      </div>
      <el-form v-else ref="userInfoForm" :model="form" label-width="80px">
        <el-form-item label="用户昵称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="当前密码">
          <el-input v-model="form.currentPassword" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="form.newPassword" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="form.confirmNewPassword" show-password></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身高">
          <el-input v-model="form.height"></el-input>
        </el-form-item>
        <el-form-item label="体重">
          <el-input v-model="form.weight"></el-input>
        </el-form-item>
        <el-form-item label="目标体重">
          <el-input v-model="form.targetweight"></el-input>
        </el-form-item>
        <el-form-item label="过往病史">
          <el-input v-model="form.past_medical_history"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveUserInfo">保存</el-button>
          <el-button @click="cancelEdit">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-button type="danger" @click="logout" class="logout-btn">退出登录</el-button>
  </div>
</template>

<script setup>
  import {ref, reactive, computed, onMounted} from 'vue';
  import axios from 'axios';
  import {useRouter} from 'vue-router';
  import {useUserStore} from "@/store";
  const user = useUserStore().userInfo

  console.log(user.age+"===")
  const router = useRouter();

  const editing = ref(false);

  const form = reactive({
    name: "test",
    sex: 1,
    height: "",
    weight: "",
    age:user.age,
    targetweight: "",
    past_medical_history: "",
    currentPassword: "", // 当前密码
    newPassword: "", // 新密码
    confirmNewPassword: "", // 再次输入新密码以确认
  });


  const editUserInfo = () => {
    editing.value = true;
    form.name = user.name;
    form.sex = user.sex;
    form.height = user.height;
    form.age = user.age
    form.weight = user.weight;
    form.targetweight = user.targetweight;
    form.past_medical_history = user.past_medical_history;
  };

  // 取消编辑
  const cancelEdit = () => {
    editing.value = false;
  };

  // 退出登录
  const logout = () => {
    // 清除本地存储的token（如果有的话）,然后返回login页面

    router.push('/login');
  };

  // 保存用户信息
  const saveUserInfo = async () => {
    // 新密码与确认密码一致性验证
    if (form.newPassword !== form.confirmNewPassword) {
      alert('新密码与确认密码不一致，请重新输入！');
      return;
    }

    // 新密码格式验证
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if (!passwordRegex.test(form.newPassword)) {
      alert('新密码必须至少8个字符，包含大小写字母和数字！');
      return;
    }

    // 旧密码验证逻辑
    const isOldPasswordValid = () => {
      if (form.currentPassword === user.password) {
        return true;
      } else
        return false;
    };
    if (!isOldPasswordValid) {
      alert('当前密码错误，请重新输入！');
      return;
    }
    try {
      const userDataToUpdate = {
        id: user.id,
        name: form.name,
        password: form.newPassword,
        sex: form.sex,
        age: form.age,
        height: form.height,
        weight: form.weight,
        targetweight: form.targetweight,
        past_medical_history: form.past_medical_history,
      };

      console.log("年龄："+user.age)
      try {
        const response = await axios.post('http://localhost:8081/updateById', {
          id: user.id,
          name: form.name,
          password: form.newPassword,
          sex: form.sex,
          age: user.age,
          height: form.height,
          weight: form.weight,
          targetweight: form.targetweight,
          past_medical_history: form.past_medical_history,
        });
        if (response.status === 200) {
          console.log('用户信息更新成功');
          // 可以在此处处理成功的逻辑，比如刷新页面或显示成功消息
        } else {
          throw new Error(`更新用户信息失败，服务器返回${response.status}状态码`);
        }
      } catch (error) {
        console.error('保存用户信息失败', error);
      } finally {
        // 清空密码字段
      }
      editing.value = false;
      form.currentPassword = '';
      form.newPassword = '';
      form.confirmNewPassword = '';
    }catch (err)
    {
      console.log(err)
    }
  }
  // 使用token从服务器获取用户信息
  onMounted(async () => {
    // 初始化表单数据
    form.name = user.name;
    form.age  = user.age;
    form.sex = user.sex;
    form.height = user.height;
    form.weight = user.weight;
    form.targetweight = user.targetweight;
    form.past_medical_history = user.past_medical_history;

  })
    //   } else {
    //     console.error('获取用户信息失败，服务器返回非200状态码');
    //   }
    // } catch (error) {
    //   console.error('获取用户信息失败', error);
    // }
    //   }
</script>

<style scoped>
.mine-container {
  padding: 10px;
  margin-top: 6px;
}

.user-info-card {
  margin-bottom: 20px;
}

.logout-btn {
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 4px;
  cursor: pointer;
}
</style>