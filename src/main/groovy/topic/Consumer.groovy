package topic

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.command.ActiveMQTopic

import javax.jms.Session
import javax.jms.TextMessage

def url = "tcp://192.168.31.145:61616"
def name = "topic-test"

// 创建ConnectionFactory
def connectionFactory = new ActiveMQConnectionFactory(url)

// 创建Connection
connection = connectionFactory.createConnection()
connection.start()

// 创建目的地
destination = new ActiveMQTopic(name)

// 创建会话
session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

// 创建消费者
consumer = session.createConsumer(destination)

consumer.setMessageListener({ TextMessage message ->
    println("Got message: " + message.getText())
})