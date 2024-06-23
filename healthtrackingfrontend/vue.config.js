const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  // 是否在保存时进行代码lint检查
  lintOnSave: false,
});
