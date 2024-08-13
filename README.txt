图书管理系统
项目包含的模块有 
	图书列表 当前借阅图书信息 借阅记录 图书管理 用户管理 图书上传功能
使用到的主要资源
jdk版本11.0.11
maven版本3.8.4
tomcat9.0.58
mysql8.0

涉及的技术有jsp,Spring,SpringMVC,Mybatis,JQuery

部分解释
	resources/application-dao.xml 持久层配置文件  mybatis配置文件
	resources/application-service.xml services层配置文件
	resources/spring-mvc.xml  表现层配置文件  拦截器，处理器适配器
	以上三个文件交由前端控制器（配置在web.xml）处理
	xml中的配置文件的配置bean会由spring扫描并注入项目中

前端控制器拦截请求
查询处理器映射器，并交由处理器适配器处理（Handler）@Controller表现层
	表现层接收请求路径与参数进一步调用逻辑处理层@Service修饰类 
	逻辑处理层执行逻辑并调用持久层dao层由dao层查询数据库并返回结果
	将结果向上返回至表现层
前端控制器将处理结果交由视图解析器处理后将数据返回由前端页面进一步渲染

注：
仅供学习
	