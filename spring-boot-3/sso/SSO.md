# SSO
Single Sign On

---
## Reference
1. [单点登录的三种实现方式](https://mp.weixin.qq.com/s/OOQzl2WXS72CZMXLmXbeRA)
2. [Java SSO 实战](https://www.bilibili.com/video/BV1hT4y157QF)
3. [SpringSecurity 实现 SSO](https://mp.weixin.qq.com/s/9MCsxdkkWF4i4M6xbbO_xA)
---
## 根据域名分类
1. 同域 SSO
2. 同父域 SSO：服务器返回 cookie 时，把 cookie 的 domain 设置为其父域
3. 跨域 SSO
    1. 设置专门的 SSO 服务器
        - 两个开源实现：①Apereo CAS；②XXL-SSO
    2. Local Storage 实现跨域
---
