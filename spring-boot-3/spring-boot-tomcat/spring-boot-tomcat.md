# spring-boot 项目使用 Tomcat 部署
1. pom.xml
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    ```
2. [Application.java](./src/main/java/com/ljh/TomcatApplication.java)
    - 启动类继承 SpringBootServletInitializer 并重写 configure()
---
