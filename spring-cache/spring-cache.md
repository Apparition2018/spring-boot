# Spring Cache

## 参考网站
1. [第四篇：SpringBoot中Cache缓存的使用](https://blog.csdn.net/weixin_36279318/article/details/82820880)
2. [史上最全的Spring Boot Cache使用与整合](https://blog.csdn.net/qq_32448349/article/details/101696892)
3. [Caching Data with Spring](https://spring.io/guides/gs/caching/)
4. [keyGenerator](https://www.cnblogs.com/guchunchao/p/10112412.html)
---
## 核心接口
<img alt="" src="https://img-blog.csdn.net/20180923131228786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNjI3OTMxOA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" width="500"/><br/>
1. CachingProvider: 定义创建、配置、获取、管理和控制多个 CacheManager
2. CacheManager: 定义创建、配置、获取、管理和控制多个唯一命名的 Cache
3. Cache: 一个类似 Map 的数据结构并临时存储以 key 为索引的值
4. Entry: 一个存储再 Cache 的 key-value 对
5. Expiry: Entry 的有效期
---
## 注解
|注解|解释|
|:---|:---|
|@EnableCaching|开启缓存注解的支持|
|@CacheConfig|统一制定一些配置参数|
|@Cacheable|已有缓存数据直接返回，否则执行方法并缓存结果|
|@CachePut|执行方法并缓存结果|
|@CacheEvict|清楚缓存|
|@Caching|组合多个注解一起使用|
>### 注解主要参数
>|名称|注解|解释|示例|
>|:---|:---|:---|:---|
>|value<br/>cacheNames| |缓存的名称|@Cacheable("cache")<br/>@Cacheable(value={"cache1", "cache2"})|
>|key| |缓存的键，缺省则按照方法的所有参数进行组合|@Cacheable(value="cache", key="#name")|
>|condition| |缓存的条件，一般对参数进行判断|@Cacheable(value="cache", condition="#name.length()>2")|
>|unless|@Cacheable<br/>@CachePut|缓存的条件，可以对结果进行判断|@Cacheable(value="cache", unless="#result==null")|
>|allEntries|@CacheEvict|清空缓存下所有内容|@CacheEvict(value="cache", allEntries="true")|
>|beforeInvocation|@CacheEvict|在方法执行前清空|@CacheEvict(value="cache", beforeInvocation="true")| 
>### SpEL
>|名称|位置|描述|示例|
>|:---|:---|:---|:---|
>|methodName|root 对象|当前被调用的方法名|#root.methodname|
>|method|root 对象|当前被调用的方法|#root.method.name|
>|target|root 对象|当前被调用的目标对象实例|#root.target|
>|targetClass|root 对象|当前被调用的目标对象的类|#root.targetClass|
>|args|root 对象|当前被调用的方法的参数列表|#root.args[0]|
>|caches|root 对象|当前方法调用使用的缓存列表|#root.caches[0].name|
>|argument name|执行上下文|当前方法的参数|#user.name|
>|result|执行上下文|方法执行后的返回值|#result|
>- 注1：可省略 #root，因为 spring 默认使用 root 对象的属性，如：@Cacheable("methodName")
>- 注2：使用方法参数时，可直接使用 #参数名 或 #p索引，如：@Cacheable("#name") @Cacheable("p0")
>### [SpEl 运算符](https://blog.csdn.net/yuhui123999/article/details/84288177)
---
## 使用
1. @EnableCaching
```java
@SpringBootApplication
@EnableCaching
public class SpringCacheApplication {}
```