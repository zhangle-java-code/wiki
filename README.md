https://www.toyaml.com/index.html
* 有参构造和无参构造
* 分页
* lombook

#### 区别
``` 
final StringBuffer sb = new StringBuffer("ResponseDto{");
StringBuilder sb = new StringBuilder();

toString区别
```

```text
# 获取环境变量
public static void main(String[] args) {
    SpringApplication app = new SpringApplication(WikiApplication.class);
    //! 环境变量
    //! [知识点] 获取环境变量
    Environment env = app.run(args).getEnvironment();
    LOG.info("启动成功！！");
    // 读取配置信息
    LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
}
```

# 代码生成器问题修复
这里不要使用管理员root账号，UserMapper生成代码有问题了;