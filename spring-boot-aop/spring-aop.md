## AOP 简介
1. 是一种编程范式，不是编程语言
2. 解决特定问题，不是解决所有问题
3. 是 OOP 的补充，不是替代
---
## AOP 的初衷
- DRY: Don't Repeat Yourself
- SoC: Separation of Concerns
    - 水平分离：展示层 → 服务层 → 持久层
    - 垂直分离：模块划分（订单、库存等）
    - 切面分离：分离功能性需求与非功能性需求
---
## AOP 的好处
- 集中处理某一关注点/横切逻辑
- 可以很方便地添加/删除关注点
- 侵入性少，增强代码可读性及可维护性
---
## AOP 应用场景
- 权限控制：@PreAuthorize
- 缓存控制：@Cacheable
- 事务控制：@Transactional
- 审计日志
- 性能监控
- 分布式追踪
- 异常处理
---
## AOP 基本概念
||||
|:---|:---|:---|
|横切|cross-cutting|与对象核心功能无关的公共行为|
|关注点|concern|一块我们感兴趣的区域|
|切面|Aspect|横切面对象，对横切关注点的抽象(可以借助@Aspect声明)|
|连接点|JoinPoint|被拦截到的点，可以是方法，字段和构造器，Spring只支持拦截方法|
|切入点|Pointcut|对连接点进行拦截的定义  @Pointcut|
|通知|advice|拦截到连接点之后要执行的代码  1）前置@Before 2）后置@After 3）异常@AfterThrowing 4）最终@AfterReturing 5）环绕通知@Around|
|目标对象|target|代理的目标对象|
|织入|weave|将切面应用到目标对象并导致代理对象创建的过程|
|引入|introduction|为类动态地添加一些方法或字段|
## AOP 主要注解
- @Aspect
- @Pointcut
- @Advice
    - @Before: 前置通知
    - @After(finally): 后置通知，方法执行完之后
    - @AfterReturning: 返回通知，成功执行之后
    - @AfterThrowing: 异常通知，抛出异常之后
    - @Around: 环绕通知
---
## Pointcut Expression
- designators
    - 匹配方法：execution()
    ```
    格式：? 表示可省略
    execution(
        modifier-pattern?
        ret-type-pattern
        declaring-type-pattern?
        name-pattern(param-pattern)
        throws-pattern?
    )
    ```
    - 匹配注解：@target(), @args(), @within(), @annotation()
    - 匹配包/类型：within()
    - 匹配对象：this(), target(), bean()
    - 匹配参数：args(), execution()
- wildcards
    - \*: 匹配任意数量的字符下·
    - \+: 匹配指定类及其子类
    - ..: 一般用于匹配任意数的子包或参数
- operators
    - &&
    - ||
    - !
---
## AOP 原理
>### 织入时机
>1. 编译器(Aspect)
>2. 类加载时(AspectJ 5+)
>3. 运行时(Spring AOP)
>   - 静态代理：重用性不强
>   <p>
>       <img src="https://img3.mukewang.com/5e0db1c60001328619201080.jpg" alt="代理模式" width="400">
>   </p>
>   - 动态代理
>       - 基于接口代理：JDK
>       - 基于继承代理：Cglib
>---
>>#### JDK 与 Cglib 代理区别
>>- JDK 只能针对有接口的类的接口方法进行动态代理，由于接口不能有 private 方法，所以无法对 private 方法进行代理
>>- Cglib 基于继承来实现代理，无法对 static / final 类进行代理，无法对 private / static 方法进行代理
>>---
>### Spring 如何选择动态代理模式
>```
>@SuppressWarning("serial")
>public class DefaultAopProxyFactory implements AopProxyFactory, Serializable {
>   @Override
>   public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
>       if (!config.isOptimize() && !config.isProxyTargetClass() && !this.hasNoUserSuppliedProxyInterfaces(config)) {
>           return new JdkDynamicAopProxy(config);
>       } else {
>           Class<?> targetClass = config.getTargetClass();
>           if (targetClass == null) {
>               throw new AopConfigException("TargetSource cannot determine target class: Either an interface or a target is required for proxy creation.");
>           } else {
>               return (AopProxy)(!targetClass.isInterface() && !Proxy.isProxyClass(targetClass) ? new ObjenesisCglibAopProxy(config) : new JdkDynamicAopProxy(config));
>           }
>       }
>   }
>}
>```
>- 如果目标对象实现了接口，则采用 JDK 动态代理
>- 如果目标对象没有实现接口，则采用 Cglib 动态代理
>- 如果目标对象实现了接口，且强制使用 Cglib 代理，则使用 Cglib 代理
>   ```
>   @SpringBootApplication
>   // 强制使用 Cglib 代理
>   @EnableAspectAutoProxy(proxyTargetClass = true)
>   public class AopDemoApplication() { ... }
>   ```
>---
>### AOP 如何链式调用
><p>
>   <img src="https://img1.mukewang.com/5e0db65a0001456b19201080.jpg" alt="责任链模式" width="450"/>
></p>
>
>---
## 实战
>### 实战案例背景/目标
>- 商家产品管理系统
>- 记录产品修改的操作记录
>- 什么人在什么时间修改了哪些产品的哪些字段修改为什么值
>---
>### 实现思路
>- 利用 aspect 去拦截增删改方法
>- 利用反射获取对象的新旧值
>- 利用 @Around 去记录操作记录
>---
>### 领域模型
>![实战领域模型](./实战领域模型.png)
>---
## 使用 SpringAop 的注意事项
- 不宜把重要的业务逻辑放到 AOP 中处理
- 无法拦截 static/final/private 方法
- 无法拦截内部方法调用