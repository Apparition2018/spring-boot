# RabbitMQ
![RabbitMQ 模型](https://img1.mukewang.com/645db3940001eb0e19201080-500-284.jpg)

---
## Reference
1. [Messaging that just works — RabbitMQ](https://www.rabbitmq.com)
2. [Spring AMQP](https://docs.spring.io/spring-amqp/reference/html/)
3. [rabbitmq-server | Github](https://github.com/rabbitmq/rabbitmq-server)
4. [rabbitmq-tutorials | Github](https://github.com/rabbitmq/rabbitmq-tutorials)
---
## 课程
1. [RabbitMQ 消息中间件极速入门与实战-慕课网](https://www.imooc.com/learn/1042)
2. [基于云服务器整合RabbitMQ中间件-慕课网](https://www.imooc.com/video/23816)
---
## AMQP
- Advanced Message Queuing Protocol 高级消息队列协议
<img alt="AMQP 协议模型" src="https://upload-images.jianshu.io/upload_images/11247052-3484249edc3746bb.jpg" width="500"/>

---
## 安装
1. 域名：`vim /etc/hostname` → `rabbitmq81`
2. 集群配置：`vim /etc/hosts`
    ```
    192.168.11.81 rabbitmq81
    192.168.11.82 rabbitmq82
    192.168.11.83 rabbitmq83
    ```
3. 防火墙设置
---
## 核心概念
<img alt="整体架构" src="https://img1.mukewang.com/6077b3da0001049719201080.jpg" width="500"/>
<br/>
<img alt="消息如何流转" src="https://img1.mukewang.com/6077f01a0001cc0619201080.jpg" width="500"/>

1. Server: 又称 Broker，接受客户端的连接，实现 AMQP 实体服务
2. Connection: 连接，应用程序与 Broker 的网络连接
3. Channel: 网络通道，几乎所有的操作都在 Channel 中进行，Channel 是进行消息读写的通道。客户端可建立多个 Channel，每个 Channel 代表一个会话任务
4. Message: 消息，服务器和应用程序之间传送的数据，由 Properties 和 Body 组成
    - Properties: 对消息进行修饰，比如消息的优先级、延迟等高级特性
    - Body: 消息体内容
5. Virtual Host: 虚拟地址，用于进行逻辑隔离，是最上层的消息路由。一个 Virtual Host 里面可以有若干个 Exchange 和 Queue，但名称不能相同
6. Exchange: 交换机，接收消息，根据路由键转发消息到绑定的队列
7. Building: Exchange 和 Queue 之间的虚拟连接，Building 中可以包含 Routing Key
8. Routing Key: 一个路由规则，虚拟机可用它来确定如何路由一个特定消息
9. Queue: 也称为 Message Queue，消息队列，保存消息并将它们转发给消费者
---
## 基本使用
### Producer
1. [application.properties](rabbitmq-producer/src/main/resources/application.properties)
    ```properties
    spring.rabbitmq.addresses=127.0.0.1:5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    spring.rabbitmq.connection-timeout=15000
    spring.rabbitmq.publisher-confirm-type=CORRELATED
    spring.rabbitmq.publisher-returns=true
    spring.rabbitmq.template.mandatory=true
    ```
2. 发送：`rabbitTemplate.convertAndSend(exchange, routingKey, object, corrleationData)`
### Consumer
1. [application.properties](rabbitmq-consumer/src/main/resources/application.properties)
    ```properties
    spring.rabbitmq.addresses=127.0.0.1:5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    spring.rabbitmq.listener.simple.acknowledge-mode=MANUAL
    ```
2. 接收，@see OrderReceiver
    ```java
    public class Receiver {
        @RabbitListener(bindings =
        @QueueBinding(value = @Queue(value = "order-queue", durable = "true"),
                exchange = @Exchange(value = "order-exchange", type = "topic"),
                key = "order.*"))
        @RabbitHandler
        public void onMessage(@Payload Object object, @Headers Map<String, Object> headers, Channel channel) throws IOException {
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
        }
    }
    ```
---
## 消息可靠性投递
<img alt="消息100%投递成功方案" src="https://img3.mukewang.com/607a7e6800017ed319201080.jpg" width="500"/>

- 消息日志表 broker_message_log @see BrokerMessageLog
    ```
    message_id        消息ID
    message           消息内容
    try_count         重试次数
    status            投递状态（0-投递中，1-投递成功，2-投递失败）
    next_retry_time   下一次投递时间
    ```
1. insert broker_message_log (status = 0，try_count = 0，next_retry_time = now() + 10sec)
2. send
3. confirm
4. success → update broker_message_log (status = 1)
5. timed task @see RetryMessageTask
    - select broker_message_log (status = 0，next_retry_time <= now())
        - try_count >= 3 → update broker_message_log (status = 2)
        - other → retry send
---
