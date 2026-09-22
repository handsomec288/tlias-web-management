# Tlias智能辅助学习系统
SpringBoot实战项目，员工管理后台，用于培训机构员工信息管理。

## 技术栈
- 后端框架：SpringBoot 2.7.x
- ORM框架：MyBatis
- 数据库：MySQL 8.0
- 构建工具：Maven
- 工具：阿里云OSS文件存储

## 功能模块
1. 员工登录校验
2. 员工信息管理：新增、修改、删除、分页条件查询
3. 文件上传：上传头像至阿里云OSS
4. 全局异常处理器，统一异常响应
5. 统一返回结果封装，前后端数据交互标准化

## 项目亮点
1. **多环境配置分离**
公共配置写在`application.yml`；数据库账号密码、阿里云OSS密钥放在`application-dev.yml`，通过`.gitignore`忽略该文件，不上传到Git仓库，防止敏感信息泄露。面试可以讲这个企业开发规范。
2. MyBatis开启下划线转驼峰映射，简化实体类开发
3. 自定义全局异常处理器，捕获业务异常，返回友好提示
4. 统一响应结果，所有接口返回格式一致

## 项目启动步骤
1. 在本地MySQL创建`tlias`数据库，导入项目提供的sql脚本
2. 在`application-dev.yml`配置数据库连接信息、阿里云OSS信息（该文件不会提交到代码仓库）
3. 运行SpringBoot主启动类，启动项目
4. 访问接口测试（Postman/ApiPost）

## 项目结构
src
└── main
├── java/com/itheima
│   ├── controller  // 控制器层
│   ├── service     // 业务层
│   ├── mapper      // 数据访问层
│   ├── pojo        // 实体类
│   ├── utils       // 工具类
│   └── exception   // 全局异常
└── resources
└──  application.yml      // 公共配置（提交 github）
