# Jackson

---
## 参考网站
1. [FasterXML/jackson](https://github.com/FasterXML/jackson#documentation)
2. [FasterXML/jackson-docs](https://github.com/FasterXML/jackson-docs)
3. [A哥学 Jackson](https://blog.csdn.net/f641385712/category_10175312.html)
3. [Jackson 使用教程](https://blog.csdn.net/weixin_44747933/article/details/108301626)
4. [SpringBoot 整合 Jackson](https://blog.csdn.net/prcyang/article/details/107881761)
5. [JSON 之 Jackson - 知乎](https://zhuanlan.zhihu.com/p/155914407)
6. [Jackson 处理 json](https://blog.csdn.net/psh18513234633/article/details/88599509)
---
## [核心模块](https://github.com/FasterXML/jackson#core-modules)
1. jackson-core: low-level streaming API
   - [JsonFactory](https://github.com/FasterXML/jackson-core/wiki/JsonFactory-Features): 配置和构建 JsonGenerator 和 JsonParser；线程安全的
   - [JsonGenerator](https://github.com/FasterXML/jackson-core/wiki/JsonGenerator-Features): 生成 Json  
     <img alt="" src="https://img-blog.csdnimg.cn/20200716143504786.png#pic_center" width="450">
   - [JsonParser](https://github.com/FasterXML/jackson-core/wiki/JsonParser-Features): 读取 Json
2. jackson-annotations: 包含标准 Jackson 注解
3. jackson-databind: 实现数据绑定和对象序列化（high-level 所在模块，如：ObjectMapper）
---
## 基本概念
1. Low-Level API: 细节需要自己处理，灵活性高，使用相对复制
2. High-Level API: 屏蔽细节处理，使用相对简单
3. Streaming: IO 流，因此理论上是支持任何格式
4. Incremental Mode: 增量模式，表示每个部分一个一个地往上增加
5. JsonToken: Json 每一部分都是一个独立的 Token，最终拼凑起来就是一个 Json
6. ObjectCodec: 解码器，ObjectMapper 继承了它，实现了序列化和反序列化、树模型等操作
---
## 两大原则
1. 写：100%遵顼规范
2. 读：最大程度兼容
---
## 三种使用方法
1. Streaming API
2. [JsonMapper (ObjectMapper)](.\src\test\java\com\ljh\ObjectMapperTest.java)
3. [Tree Model (JsonNode)](.\src\test\java\com\ljh\JsonNodeTest.java)
---
## 配置
```yaml
spring:
  jackson:
    # 日期格式化
    date-format: yyyy-MM-dd HH:mm:ss
    # 设置空如何序列化
    default-property-inclusion: NON_EMPTY
    serialization:
      # 日期转 timestamps
      write_date_as_timestamps: false
      # 格式化输出
      indent_output: true
      # 忽略无法转换的对象
      fail_on_empty_beans: false
      # 允许出现特殊字符和转义符
      allow_unescaped_control_chars: true
    deserialization:
      # 允许对象忽略json中不存在的属性
      fail_on_unknown_properties: false
    parser:
      # 允许注释（/* */, //）
      allow_comments: true
      # 允许字段名支持单引号
      allow_single_quotes: true
```
---