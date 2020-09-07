# java

java学习项目

## maven创建web项目

### 环境配置

1. 配置maven环境
2. 配置eclispe maven 配置
3. 创建项目
4. 配置tomcat

### servlet处理http请求

1. maven添加servlet依赖
2. servlet可以通过xml配置或者注解配置，项目中使用注解配置

jsp开发

jsp会被编译成一个servlet，无需XML或者注解配置，方便html返回，可以实现动态网页

通过jsp实现登陆功能

- index.jsp显示登陆信息，检查session判断是否登陆，不存在session则认为未登陆，跳转到login.jsp
- jsp处理登陆，登陆成功存储session跳转到index.jsp界面

通过登陆功能对jsp初步了解：

- jsp会被编译成servlet
- jsp可以实现动态网页， 可以编写和访问java代码

mvc实现

### jdbc

jdbc是一种规范， 不同数据实现这种规范就可以使用相同的代码操作数据库,jdbc定义接口，具体的实现是各数据库提供的jabc驱动实现的，这里使用的是`sqlite-jdbc`

orm实现
