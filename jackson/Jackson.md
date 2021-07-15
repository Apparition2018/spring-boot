# Jackson

---
## 参考网站
1. [FasterXML/jackson](https://github.com/FasterXML/jackson#documentation)
2. [FasterXML/jackson-docs](https://github.com/FasterXML/jackson-docs)
3. [享学Jackson | YourBatman](https://fangshixiang.blog.csdn.net/article/details/103732359)
3. [Jackson 使用教程](https://blog.csdn.net/weixin_44747933/article/details/108301626)
4. [SpringBoot 整合 Jackson](https://blog.csdn.net/prcyang/article/details/107881761)
---
## [核心模块](https://github.com/FasterXML/jackson#core-modules)
1. jackson-core: streaming API
    - [JsonFactory](https://github.com/FasterXML/jackson-core/wiki/JsonFactory-Features): 更改读写 JSON
    - [JsonGenerator](https://github.com/FasterXML/jackson-core/wiki/JsonGenerator-Features): 更改流解析方式
    - [JsonParser](https://github.com/FasterXML/jackson-core/wiki/JsonParser-Features): 更改流解析方式
2. jackson-annotations: 包含标准 Jackson 注解
3. jackson-databind: 实现数据绑定和对象序列化（ObjectMapper 所在模块）
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
      # 允许出现单引号
      allow_single_quotes: true
```
---