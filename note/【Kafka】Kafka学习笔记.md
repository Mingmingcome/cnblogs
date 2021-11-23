`2021年8月30日14:41:04`

# 【Kafka】Kafka学习笔记

## 快速开始

[快速开始](https://kafka.apachecn.org/quickstart.html)

里面有基础命令，如创建topic，创建partition和副本等。

## 客户端连接

本机连不上虚拟机的kafka：

https://blog.csdn.net/fuck487/article/details/107562890

伪集群：
``` bash
# 启动
bin/kafka-server-start.sh config/server.properties &
bin/kafka-server-start.sh config/server-1.properties &
bin/kafka-server-start.sh config/server-2.properties &
# 停止
bin/kafka-server-stop.sh config/server.properties &
bin/kafka-server-stop.sh config/server-1.properties &
bin/kafka-server-stop.sh config/server-2.properties &
```
消费者：
```
bin/kafka-console-consumer.sh --bootstrap-server 192.168.128.199:9092,192.168.128.199:9093,192.168.128.199:9094 --from-beginning --topic test
```

生产者：
```
bin/kafka-console-producer.sh --broker-list 192.168.128.199:9092,192.168.128.199:9093,192.168.128.199:9094 --topic test
```


acks参数含义：

- acks=0：只要生产者把消息通过网络发送出去，就会返回成功，即使消息没有写入kafka。也就是说如果当中出现了错误，导致broker没有收到消息，那么生产者是无从得知的，消息也就丢失了。不过，因为生产者不需要等待服务器的响应，从而可以以网络可以支持的最大的速度来发送消息，使得系统能够达到很高的吞吐量。
- acks=1：只要集群的首领节点收到消息，生产者就会收到来自服务器成功的响应。如果消息不能够被首领节点接收（比如说首领节点崩溃，而新的首领尚未选出来），这时候生产者会收到一个错误响应，为了避免数据的丢失，生产者应重发消息。这个时候的吞吐量取决于使用的同步还是异步发送。如果让发送客户端（生产者）等待服务器的响应（通过调用Future对象的get()方法），显然会增加延迟（一次发送和响应的会话延迟）。如果客户端（生产者）使用回调，延迟问题就可以的到解决了，不过吞吐量还是会收到发送中消息数量的限制（比如生产者在收到服务器响应之前可以发送多少个消息）。
- acks=all 或者 -1：只有在集群中的跟随副本都接收消息后，生产者才会收到一个来自服务器的成功响应。这种模式是最安全的，他可以保证集群中不止一个服务器收到消息，就算有服务器崩溃了，这个集群还是能够正常运行。不过他比acks=1的延迟性更高，因为生产者要等待的所有参与复制消息的节点接收到消息。


The number of acknowledgments the producer requires the leader to have received before considering a request complete. This controls the 
 durability of records that are sent. The following settings are allowed: 

- `acks=0` If set to zero then the producer will not wait for any acknowledgment from the server at all. The record will be immediately added to the socket buffer and considered sent. No guarantee can be made that the server has received the record in this case, and the `retries` configuration will not take effect (as the client won't generally know of any failures). The offset given back for each record will always be set to `-1`.
- `acks=1` This will mean the leader will write the record to its local log but will respond without awaiting full acknowledgement from all followers. In this case should the leader fail immediately after acknowledging the record but before the followers have replicated it then the record will be lost.
- `acks=all` This means the leader will wait for the full set of in-sync replicas to acknowledge the record. This guarantees that the record will not be lost as long as at least one in-sync replica remains alive. This is the strongest available guarantee. This is equivalent to the acks=-1 setting.

在认为请求完成之前，生产者要求领导者收到的确认数。这控制了发送的记录的持久性。允许以下设置：
acks=0如果设置为零，那么生产者根本不会等待来自服务器的任何确认。该记录将立即添加到套接字缓冲区并视为已发送。这种情况下retries不能保证服务端已经收到记录，配置不会生效（因为客户端一般不会知道任何故障）。为每个记录返回的偏移量将始终设置为-1。
acks=1这意味着领导者会将记录写入其本地日志，但会在不等待所有追随者的完全确认的情况下做出响应。在这种情况下，如果领导者在确认记录后立即失败，但在追随者复制它之前，则记录将丢失。
acks=all这意味着领导者将等待完整的同步副本集来确认记录。这保证了只要至少一个同步副本保持活动状态，记录就不会丢失。这是最强的可用保证。这相当于 acks=-1 设置。

## 代码演示

### 生产者

### 消费者

#### 指定分区消费

#### 提交

- 自动提交
- 手动提交
 - 同步提交
 - 异步提交

