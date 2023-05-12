# Kafka

---
## 参考网站
1. [Apache Kafka](https://kafka.apache.org/)
2. [Kafka流处理平台介绍-慕课网](https://www.imooc.com/learn/1043)
---
## 事件流平台
1. 发布和订阅事件流，包括从其它系统连续导入导出数据
2. 持久和可靠地存储事件流
3. 在事件流发生或事后处理事件流
---
## 特点
1. 分布式
    - 多分区
    - 多副本
    - 多订阅者
    - 基于 ZooKeeper 调度
2. 高性能
    - 高吞吐量
    - 低延迟
    - 高并发
    - 时间复杂度 O(1)
3. 持久性与扩展性
    - 数据可持久化
    - 容错性
    - 支持在线水平扩展
    - 消息自动平衡
---
## 应用场景
1. 消息队列
2. 行为跟踪
3. 元信息监控
4. 日志收集
5. 流处理
6. 事件源
7. 持久性日志
---
## 基本概念
- Producer: 消息和数据的生产者，向 Kafka 的一个 Topic 发布消息的进程/代码/服务
- Consumer: 消息和数据的消费者，订阅数据(Topic)并且处理其发布的消息的进程/代码/服务
- Consumer Group: 逻辑概念，对于同一个 Topic，会广播给不同的 Group，一个 Group 中，只有一个 Consumer 可以消费该消息
- Broker: 物理概念，Kafka 集群中的每个 Kafka 节点
- Topic: 逻辑概念，Kafka 消息的类别，对数据进行区分、隔离
- Partition: 物理概念，Kafka 下数据存储的基本单元。
    - 一个 Topic 数据，会被分散存储到多个 Partition，每一个 Partition 是有序的
    - 消费者数目少于或等于 Partition 的数目
    - Consumer Group 中仅有一个 Consumer 读取 Topic 的一个或多个 Partition，并且是唯一的 Consumer
    - Broker Group 中的每一个 Broker 保存 Topic 的一个或多个 Partition
- Replication: 同一个 Partition 可能会有多个 Replica，多个 Replica 之间数据是一样的
    - 当集群中有 Broker 挂掉的情况，系统可以主动地使 Replica 提供服务
    - 系统默认设置每一个 Topic 的 Replication 系数为1，可以在创建 Topic 时单独设置
    - 所有的读和写都从 Leader 进，Follower 只作为备份，Follower 必须能够及时复制 Leader 的数据
- Replication Leader: 一个 Partition 的多个 Replica 上，需要一个 Leader 辅助该 Partition 上与 Producer 和 Consumer 交互
- ReplicaManager: 负责管理当前 Broker 所有分区和副本的信息，处理 KafkaController 发起的一些请求，副本状态的切换、添加/读取消息等
---
## 基本结构
<img alt="Kafka 基本结构" src="https://img4.mukewang.com/60451bbd0001875819201080.jpg" width="640"/>
<img alt="Kafka 基本结构" src="https://img4.mukewang.com/60451cab000189d719201080.jpg" width="640"/>

---
## 消息结构
| Offset  | Length  |  CRC32  |  Magic  | Attributes | Timestamp | key Length | Key | Value Length | Value |
|:-------:|:-------:|:-------:|:-------:|:-----:|:---------:|:-----:|:---:|:------------:|:-----:|
| 4 bytes | 4 bytes | 4 bytes | 1 bytes |  1 bytes   |  8 bytes  |   4bytes   |     |    4bytes    |       |
---
## 安装和使用
1. [windows10安装zookeeper-3.6.2并生成zookeeper服务](https://www.cnblogs.com/fps2tao/p/13869428.html)
2. [ZooKeeper audit is disabled](https://blog.csdn.net/u011702673/article/details/109963726)
3. [windows下搭建kafka](https://www.cnblogs.com/seeall/p/14464516.html)
### Zookeeper
```
1. 配置文件：./conf/zoo.cfg
    1.1 dataDir=D:\dev\apache-zookeeper-3.6.2-bin\data
    1.2 dataLogDir=D:\dev\apache-zookeeper-3.6.2-bin\logs
    1.3 admin.serverPort=8888
    1.4 audit.enable=true
2. 添加环境
    2.1 添加 ZOOKEEPER_HOME=D:\dev\apache-zookeeper-3.6.2-bin
    2.2 PATCH 添加 %ZOOKEEPER_HOME%\bin
3. 启动和测试
    3.1 ./bin/zkServer.cmd
    3.2 ./bin/zkCli.cmd
```
### Kafka
1. [在Windows安装运行Kafka](https://blog.csdn.net/qq_42715450/article/details/114278321)
```
1. 配置文件：./config/server.properties
    1.1 log.dirs=D:\dev\kafka_2.13-2.7.0\logs
2. 启动
    2.1 启动 Zookeeper ./bin/zkServer.cmd
    2.2 启动 Kafka ./bin/windows/kafka-server-start.bat ./config/server.properties
3. 命令测试
    3.1 cd bin 或 cd bin/windows
    3.2 创建主题：kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic test-topic
    3.3 查看主题：kafka-topics.bat --list --zookeeper localhost:2181
    3.4 创建生产者：kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
    3.5 创建消费者：kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
    3.6 在生产者输入 first message，消费者可以看到 first message
```
---
## Kafka 高级特性
### [消息事务](https://www.imooc.com/video/17877)
- 数据传输的事务定义
    - 最多一次：消息不会被重复发送，最多被传输一次，但也有可能一次不传输
    - 最少一次：消息不会被漏发送，最少被传输一次，但也有肯能被重复传输
    - 精确的一次(Exactly Once)：不会漏传输也不会重复传输，每个消息都仅仅传输一次
- 事务保证
    - 内部重试问题：Procedure 幂等处理
    - 多分区原子写入
        - <img alt="多分区原子写入" src="https://img.mukewang.com/604e42340001d3d319201080.jpg" width="500"/>
    - 避免僵尸实例
        - 每个事务 Producer 分配一个 transactional.id，在进程重新启动时能够识别相同的 Producer 实例
        - Kafka 增加了一个与 transactional.id 相关的 epoch，存储每个 transactional.id 内部元数据
        - 一旦 epoch 被触发，任何具有相同的 transactional.id 和更旧的 epoch 的 Producer 被视为僵尸，Kafka 会拒绝来自这些 Procedure 的后续事务性写入
### [零拷贝](https://www.imooc.com/video/17878)
- 网络传输持久性
- Java Nio channel.transferTo()
- Linux sendfile 系统调用
<img alt="零拷贝" src="https://img.mukewang.com/604e41560001baea19201080.jpg" width="600"/>
- 文件传输到网络的公共数据路径
    - 操作系统将数据从磁盘读入到内核空间的页缓存
    - 应用程序将数据从内核空间读入到用户空间缓存
    - 应用程序将数据写回到内核空间的 socket 缓存
    - 操作系统将数据从 socket 缓冲区复制到网卡缓冲区，以便将数据经网络发出
- 零拷贝
    - 操作系统将数据从磁盘读入到内核空间的页缓存
    - 将数据的位置和长度的信息的描述符增加至内核空间（socket 缓存区）
    - 操作系统将数据从内核空间复制到网卡缓冲区，以便将数据经网络发出
---
