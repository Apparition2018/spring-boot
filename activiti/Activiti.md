# Activiti

---
## Reference
1. [Activiti](https://www.activiti.org/)
2. [Activiti/Activiti](https://github.com/Activiti/Activiti)
3. [Business Process Management with Activiti 7 and Spring Boot](https://shaji-adr.medium.com/business-process-management-with-activiti-7-and-spring-boot-2-1-a5f6bf114476)
4. [Activiti 最全讲解_哔哩哔哩](https://www.bilibili.com/video/BV1t64y147v4)
---
## 术语
- BPM：Business Process Management，业务流程管理
- BPMN：Business Process Model and Notation，业务流程模型和符号
---
## 使用步骤
1. 部署 Activiti
2. 定义流程
3. 部署流程
4. 启动流程实例
5. 查询代办任务
6. 办理任务
7. 流程结束
---
## 整合 SpringBoot
1. [依赖](pom.xml)
2. [配置文件](src/main/resources/application.properties)
3. 整合 SpringSecurity
    1. [SecurityUtil](src/main/java/com/ljh/util/SecurityUtil.java)
    2. [SpringSecurityConfiguration](src/main/java/com/ljh/config/SpringSecurityConfiguration.java)
---