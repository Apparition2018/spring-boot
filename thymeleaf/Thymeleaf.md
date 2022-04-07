# Thymeleaf

---
## Reference
1. [Thymeleaf](https://www.thymeleaf.org)
2. [Thymeleaf 教程](https://fanlychie.github.io/post/thymeleaf.html)
3. [Thymeleaf 视频教程](https://www.bilibili.com/video/BV1cq4y1E79S)
---
## [标准表示语法](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax)
### 简单表达式
- @see SimpleExpressionSyntaxController.java

| 语法     | 名称                             | 描述      | 作用                         |
|:-------|:-------------------------------|:--------|:---------------------------|
| ${...} | Variable Expressions           | 变量表达式   | 取出上下文变量的值                  |
| *{...} | Selection Variable Expressions | 选择变量表达式 | 取出对象的属性值                   |
| #{...} | Message Expressions            | 消息表达式   | 国际化 (@see MyLocalResolver) |
| @{...} | Link URL Expressions           | 链接表达式   | 各种超链接地址                    |
| ~{...} | Fragment Expressions           | 片段表达式   | 引用代码片段                     |
### 字面量
| 名称               | 描述       | 说明                   |
|:-----------------|:---------|:---------------------|
| Text literals    | 文本字面量    | 单引号之间的字符串，可使用\'转义单引号 |
| Number literals  | 数字字面量    | 字面量令牌的特殊情况           |
| Boolean literals | 布尔字面量    | 字面量令牌的特殊情况           |
| Null literal     | null 字面量 | 字面量令牌的特殊情况           |
| Literal tokens   | 字面量令牌    | 不使用单引号，但不能有空格和逗号     |
### 操作和运算
- @see OperatorsController.java
1. 文本操作
    1. 字符串连接：`+`
        - `<span th:text="'The name is ' + ${name}">`
    2. 字面量替换：`|`
        - `<span th:text="|The name is ${name}|">`
2. 算术运算
    1. 二元运算符：`, -, *, / (div), % (mod) `
    2. 一元运算符：`-`
    - 这些运算符也可以应用于 OGNL 变量表达式本身，此时将由 OGNL 而不是 Thymeleaf 标准表达式引擎执行
3. 布尔运算
    1. 二元运算符：`and, or`
    2. 一元运算符：`!, not`
4. 比较和相等
    1. 比较器：`>, <, >=, <= (gt, lt, ge, le)`
    2. 等式运算符：`==, != (eq, ne)`
5. 条件运算符
    1. if-then：`(if) ? (then)`
    2. if-then-else：`(if) ? (then) : (else)`
    3. Default：`(value) ?: (defaultvalue)`
6. 无操作 (No-Operation) ???
    - `_`：允许使用原标签的内容作为默认值
        - `<p th:text="${isLogin} ?: _">未登录</p>`
---