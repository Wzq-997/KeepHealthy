<template>
    <img alt="Vue logo" src="./assets/logo.png" />
    <p>姓名：{{ name }}</p>
    <p>年龄：{{ age }}</p>
    <p>性别：{{ sex }}</p>
  </template>
  <script setup>
  import { ref } from "vue";
  import { useUserStore } from "../src/store/userStore";
  const store = useUserStore();
//   const name = ref<string>(store.name);
//   const age = ref<number>(store.age);
//   const sex = ref<string>(store.sex);
    const { name, age, sex } = store;
  </script>

  