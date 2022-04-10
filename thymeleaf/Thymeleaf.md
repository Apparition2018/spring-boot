# Thymeleaf
```xml
<html xmlns:th="http://www.thymeleaf.org"/>
```
---
## Reference
1. [Thymeleaf](https://www.thymeleaf.org)
2. [Thymeleaf 教程](https://fanlychie.github.io/post/thymeleaf.html)
3. [Thymeleaf 视频教程](https://www.bilibili.com/video/BV1cq4y1E79S)
---
## [标准表示语法](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax)
### 简单表达式
- @see SimpleExpressionSyntaxController

| 语法                    | 名称                             | 描述      | 作用                         |
|:----------------------|:-------------------------------|:--------|:---------------------------|
| ${...} <br/> ${{...}} | Variable Expressions           | 变量表达式   | 取出上下文变量的值<br/>自定义转换        |
| *{...} <br/> *{{...}} | Selection Variable Expressions | 选择变量表达式 | 取出对象的属性值<br/>自定义转换         |
| #{...}                | Message Expressions            | 消息表达式   | 国际化 (@see MyLocalResolver) |
| @{...}                | Link URL Expressions           | 链接表达式   | 各种超链接地址                    |
| ~{...}                | Fragment Expressions           | 片段表达式   | 引用代码片段                     |
### 表达式基本对象
- [表达式基本对象1](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-basic-objects)
- [表达式基本对象2](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-a-expression-basic-objects)
### 表达式使用程序对象
- [表达式使用程序对象2](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-utility-objects)
- [表达式使用程序对象2](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects)
### 字面量
| 名称               | 描述       | 说明                   |
|:-----------------|:---------|:---------------------|
| Text literals    | 文本字面量    | 单引号之间的字符串，可使用\'转义单引号 |
| Number literals  | 数字字面量    | 字面量令牌的特殊情况           |
| Boolean literals | 布尔字面量    | 字面量令牌的特殊情况           |
| Null literal     | null 字面量 | 字面量令牌的特殊情况           |
| Literal tokens   | 字面量令牌    | 不使用单引号，但不能有空格和逗号     |
### 操作和运算
- @see OperatorsController
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
## 设置标签文本内容
1. `th:text`：转义
2. `th:utext`：不转义
---
## [设置属性值](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
| 语法             | 作用        | 示例                                                             |
|:---------------|:----------|:---------------------------------------------------------------|
| th:attr        | 设置任意属性的值  | &lt;form th:attr="action=${action},method='post'"&gt;          |
| th:*           | 为特定属性设置值  | &lt;form th:action="${action}" th:method="post"&gt;            |
| data-th-*      | HTML5 友好  | &lt;form data-th-action="${action}" data-th-method="post"&gt;  |
| th:\*-\*       | 一次设置多个属性值 | &lt;img th:src="@{/img/bird.jpg}" th:alt-title="bird"&gt;      |
|                | 布尔属性      | &lt;input type="checkbox" th:checked="${selected}"&gt;         |
| th:attrappend  | 属性值追加     | &lt;div class="b" th:attrappend="class=' c'"&gt;               |
| th:attrprepend | 属性值前置     | &lt;div class="b" th:attrprepend="class='a '"&gt;              |
| th:classappend | 类追加       | &lt;div class="b" th:classappend=" c"&gt;                      |
| th:styleappend | 样式追加      | &lt;div style="width: 5px;" th:styleappend="'height: 5px'"&gt; |
---
## [迭代](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#iteration)
- 语法：`th:each="e,stat : ${collection}"`
- 可迭代值：List, Iterable, Enumeration, Iterator, Map, Map.Entry, 数组 等
- 迭代状态：默认状态变量名称是自定义的元素名称后面加 Stat

| 属性      | 类型      | 描述            |
|:--------|:--------|:--------------|
| index   | int     | 索引，从0开始       |
| count   | int     | 计数，从1开始       |
| size    | int     | 元素总数          |
| current | int     | 当前元素          |
| even    | boolean | 当前计数是否为偶数     |
| odd     | boolean | 当前计数是否为奇数     |
| first   | boolean | 当前元素是否为第一个元素  |
| last    | boolean | 当前元素是否为最后一个元素 |
---
## [条件判断](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#conditional-evaluation)
1. `th:if` & `th:unless`

| 类型        | false                  |
|:----------|:-----------------------|
| boolean   | false                  |
| number    | zero                   |
| character | zero                   |
| string    | "false", "off" or "no" |
2. `th:switch` & `th:case`
---
## [局部变量](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#local-variables)
- `th:with`
```html
<div th:with="name='mary', age=${age}">
    <span th:text="${name}"></span>
    <span th:text="${age}"></span>
</div>
```
---
## [模板布局](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#conditional-evaluation)
1. `th:fragment`
    - `th:insert="~{...}"`：插入代码片段到标签体内
    - `th:replace="~{...}"`：用代码片段替换标签体内容
    - `th:include="~{...}"`：插入代码片段的内容到标签体内

| 语法                                | 描述                                   |
|:----------------------------------|:-------------------------------------|
| ~{templatename::selector}         | 引用整个模板文件的代码片段                        |
| ~{templatename}                   | selector 可以是 th:fragment 指定的名称或其他选择器 |
| ~{::selector} 或 ~{this::selector} | 引用当前模板定义的代码片段                        |
2. `th:remove`

| 值             | 描述                    |
|:--------------|:----------------------|
| all           | 删除包含标签及其所有子标签         |
| body          | 删除其所有子标签              |
| tag           | 删除包含标签，不删除子标签         |
| all-but-first | 删除包含标签及其所有子标签，除了第一个标签 |
| none          | 什么都不做                 |
3. 片段参数
```html
<div th:fragment="layout (title, content)">
   <h1 th:replace="${title}">Title</h1>
   <div th:replace="${content}">
      <p>Layout content</p>
   </div>
   <footer>Layout footer</footer>
</div>
```
4. 动态布局
5. 布局继承
---
## [属性优先级](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#attribute-precedence)
| Order | Feature                         | Attributes                             |
|:------|:--------------------------------|:---------------------------------------|
| 1     | Fragment inclusion              | th:insert, th:replace                  |
| 2     | Fragment iteration              | th:each                                |
| 3     | Conditional evaluation          | th:if, th:unless, th:switch, th:case   |
| 4     | Local variable definition       | th:object, th:with                     |
| 5     | General attribute modification  | th:attr, th:attrprepend, th:attrappend |
| 6     | Specific attribute modification | th:value, th:href, th:src ...          |
| 7     | Text (tag body modification)    | th:text, th:utext                      |
| 8     | Fragment specification          | th:fragment                            |
| 9     | Fragment removal                | th:remove                              |
---
## [注释和块](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#comments-and-blocks)
1. 注释

| comment                    | 中文    | 浏览器访问 | Thymeleaf 模板引擎解析 |
|:---------------------------|:------|:------|:-----------------|
| &lt;!-- ... --&gt;         | 标准注释  | 显示注释  | 显示注释             |
| &lt;!--/* ... */--&gt;     | 解析器注释 | 显示注释  | 移除注释，适合开发人员使用    |
| &lt;!--/\*/ ... /\*/--&gt; | 原型注释  | 显示注释  | 显示注释内容，适合设计人员使用  |
2. `th:block`
    - Thymeleaf 唯一元素处理器
    - 只是一个属性容器，执行完属性后消失
---
## [内联](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#inlining)
1. `[[...]]`：相当于 `th:text`
2. `[(...)]`：相当于 `th:utext`
3. `th:inline`

| 值          | 描述                                        |
|:-----------|:------------------------------------------|
| none       | 禁用内联，原样输出 [[]] 和 [()] 字符串                 |
| text       | 文本内联，可以使用 th:each 等高级语法                   |
| css        | 样式内联，&lt;style th:inline="css"&gt;        |
| javascript | 脚本内联，&lt;style th:inline="javascript"&gt; |
---
## [文本模板模式](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#textual-template-modes)
| 语法                                        | 说明                     |
|:------------------------------------------|:-----------------------|
| [#th:block]...[/th:block] <br/> [#]...[/] | 文本语法                   |
| /\*[+...+]\*/                             | 文本原型注释块，添加代码           |
| /\*[- \*/.../\* -]\*/ <br/> /\*[-...-]\*/ | 文本解析器注释块，删除代码          |
| /\* [#] \*/ ... /\* [/] \*/               | 自然 JavaScript 和 CSS 模板 |
---
## [解耦模板和逻辑](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#decoupled-template-logic)
- 将 *.html Thymeleaf 文件解耦为：①*.html 模板文件；②*.th.html 逻辑文件
---
## [标记选择器语法](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#multivalued-class-matching)

---