package topic

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.command.ActiveMQTopic

import javax.jms.Session

def url = "tcp://192.168.31.145:61616"
def name = "topic-test"

// 创建ConnectionFactory
def connectionFactory = new ActiveMQConnectionFactory(url)

// 创建Connection
connection = connectionFactory.createConnection()

// 创建目的地
destination = new ActiveMQTopic(name)

// 创建会话
session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

// 创建生产者
producer = session.createProducer(destination)

// 文本消息
message = session.createTextMessage()

for (i in 1..100) {
    message.setText("This is message $i")
    println("Sending message: " + message.getText())
    producer.send(message)
}

// 关闭连接
connection.close()