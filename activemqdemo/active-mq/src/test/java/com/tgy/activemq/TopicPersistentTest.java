package com.tgy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * 消息持久化订阅消息测试
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-13 15:41
 **/

public class TopicPersistentTest {
    //编写消息发送方--生产者
    @Test
    public void testSender() throws JMSException {
        //创建连接工厂对象
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        //从工厂中获取一个连接对象
        Connection connection = connectionFactory.createConnection();
        //连接MQ服务
        connection.start();
        //获取session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session对象创建Topic
        Topic topic = session.createTopic("TGY's-Topc-Name");
        //通过session对象创建消息的发送者
        MessageProducer producer = session.createProducer(topic);
        //通过session创建消息对象
        TextMessage message = session.createTextMessage("Ping");
        //发送消息
        //producer.send(message);
        producer.send(message,DeliveryMode.PERSISTENT,1,1000*60*60*24);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    //编写消息的接收方--消费者
    @Test
    public void testRecive() throws JMSException {
        //创建连接工厂对象
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        //从工厂中获取一个连接对象
        Connection connection = connectionFactory.createConnection();
        //设置客户端ID
        connection.setClientID("client-001");
        //连接MQ服务
        connection.start();
        //获取session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //通过session对象创建Topic
        Topic topic = session.createTopic("TGY's-Topc-Name");
        //通过session对象创建消息的消费者
        //MessageConsumer consumer = session.createConsumer(topic);
        //客户端持久化订阅
        TopicSubscriber consumer = session.createDurableSubscriber(topic, "client1-sub");
        //指定消息的监听器
        consumer.setMessageListener(new MessageListener() {
            //当监听topic中存在消息，这个方法自动执行
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("消费者接收到了消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //消费者端不能关闭资源（连接方法不能关闭）此处是模拟的test方法，写一个死循环
        while (true){

        }
    }
}
