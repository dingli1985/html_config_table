# html_config_table
可配置化页面表格【仿Excel】
基于springframework 4.3.9,整合Thymeleaf模版殷勤进行页面渲染
# html_config_table V1.0


## 鸣谢

##技术讨论

## V1.0更新日志

注:

##功能简介
  基于页面配置，HTML表格扎实，及编辑。  表格展示数据持久到数据库

##使用说明


###如何启动项目
  

###注意
建议本项目用jdk1.7

##所用框架
###前端
bootstrap
thymeleaf



###后端
mybatis
spring-jdbc
hicaricp数据库连接池


##项目包结构说明
```
├─main
│  │  
│  ├─java
│  │   │
│  │   ├─com.xhp.hrms----------------项目主代码
│  │   │          │
│  │   │          ├─core----------------项目运行的核心依靠(shiro权限检查等)
│  │   │          │
│  │   │          ├─config----------------项目配置代码(例如thymeleaf配置，数据库连接池配置等)
│  │   │          │
│  │   │          ├─entity----------------实体配置文件
│  │   │          │
│  │   │          ├─web----------------前台页面控制
│  │   │          │
│  │   │          ├─common----------------项目公用的部分(业务中经常调用的类,例如常量,异常,实体,注解,分页类,节点类)
│  │   │          │
│  │   │          └─Initializer类----------------用servlet容器启动配置类
│  │   │
│  │   └─generator----------------模版,Dao,Web,配置文件生成
│  │
│  ├─resources----------------项目资源文件
│  │     │
│  │     ├─gunsTemplate----------------guns代码生成模板
│  │     │ 
│  │     ├─jdbc.properties----------------springboot项目配置
│  │     │ 
│  │     └─Message.properties----------------模版页面中文描述
│  │
│  └─webapp
|        |
|        ├─bootstrap----------------bootstrap相关内容
|        |
|        ├─WEB-INF----------------bootstrap相关内容
|        |      |
|        |      |─templates----------------thymeleaf模版内容
```
注:SpringBoot项目默认不支持将静态资源和模板(web页面)放到webapp目录,但是个人感觉resources目录只放项目的配置更加简洁,所以就将web页面继续放到webapp目录了.

##项目特点

##基于javabean方式的spring配置


##业务日志记录原理

```
开发页面时，只需编写如下代码即可


##对js常用代码的封装

##极简的图片上传方法


##独创controller层，map+warpper返回方式


##常见问题答疑

##效果图



