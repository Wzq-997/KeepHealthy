<script setup>
import axios from "axios";
import {ref} from "vue";
const menu = ref()
axios.post('http://localhost:8081/diet/getMenuRecord')
    .then(response=>{
      menu.value = response.data.data
      console.log(menu.value)
    })
</script>

<template>

</template>

<style scoped>

</style>