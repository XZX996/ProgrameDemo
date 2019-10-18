using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System;
using System.Text;

namespace WpfApp1.common
{
    /**
     * 生产者
     * */
    public class producer
    {
        //连接rabbit
        IConnection conn = null;
        //通道Channel用于接收和发送消息
        IModel channel = null;
        int runIndex = 1;
        //连接rabbit服务
        public void connect() {
            try
            {
                ConnectionFactory factory = new ConnectionFactory();
                factory.UserName = "xzx";
                factory.Password = "123qwe";
                factory.HostName = "127.0.0.1";
                factory.VirtualHost = "/";//默认情况可省略此行
                factory.Port = 5672;//此处默认即为5672端口,可省略此行
                factory.AutomaticRecoveryEnabled = true;   //自动的错误恢复机制
                conn = factory.CreateConnection();
                Console.Write("---------------RabbitMQ连接成功---------------\n");
            }
            catch (Exception ex)
            {
                Console.Write(string.Format("RabbitMQ连接异常:{0}\n", ex.ToString()));
            }
        }
        //关闭Rabbit服务
        public void close()
        {
            if (channel != null) { 
                channel.Close();
                channel = null;
            }
            conn.Close();
        }
        #region 发送到队列
        public void senddirectmsg(string msg)
        {
            connect();
            using (channel = conn.CreateModel())
            {
                #region 消息响应
                bool durable = true;
                string queueName = "jiqun";
                //队列声明 此处的队列声明要与发送端一致
                channel.QueueDeclare(queueName, true, false, false, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                var consumer = new QueueingBasicConsumer(channel); //在通道channel里添加消费者
               // channel.BasicConsume(queueName, false, consumer); //消费者订阅队列 // 消息队列的名字 是否关闭消息响应 消费者的名字
                try
                {                 
                    var properties = channel.CreateBasicProperties();
                    properties.SetPersistent(true);
                    var body = Encoding.UTF8.GetBytes(msg);
                    channel.BasicPublish("", queueName, properties, body);
                    Console.WriteLine(" set {0}", msg);
                }
                catch (Exception ex)
                {
                }
                finally
                {
                    close();
                }
                #endregion
            }

        }

        public void sendFountmsg(string msg)
        {
            connect();
            using (channel = conn.CreateModel())
            {
                #region 消息响应
                bool durable = true;
                string queueName = "jiqun";
                //交换机申明
                 string EXCHANGE_NAME = "logs";
                 string ROUTING_KEY = queueName;
                //channel.ExchangeDeclare(EXCHANGE_NAME, "topic", true, false);   //topic
                channel.ExchangeDeclare(EXCHANGE_NAME, "fanout");//广播

                var consumer = new QueueingBasicConsumer(channel); //在通道channel里添加消费者
                try
                {
                    var properties = channel.CreateBasicProperties();
                    properties.SetPersistent(true);
                    var body = Encoding.UTF8.GetBytes(msg);
                    channel.BasicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, body);//不需要指定routing key，设置了fanout,指了也没有用.
                    Console.WriteLine(" set {0}", msg);
                }
                catch (Exception ex)
                {
                }
                finally
                {                  
                    close();
                }
                #endregion
            }

        }
        #endregion

        #region 发送到交换机
        public void senddirectmsg1(string msg)
        {
            connect();
            using (channel = conn.CreateModel())
            {
                #region 消息响应
                bool durable = true;
                string ExchangeName = "messge";
                //队列声明 此处的队列声明要与发送端一致
                channel.ExchangeDeclare(ExchangeName, "direct", true, false, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                try
                {
                    var properties = channel.CreateBasicProperties();
                    properties.SetPersistent(true);
                    var body = Encoding.UTF8.GetBytes(msg);
                    channel.BasicPublish(ExchangeName, "", properties, body);
                    Console.WriteLine(" 发送消息到交换机 {0}", msg);
                }
                catch (Exception ex)
                {
                }
                finally
                {
                    close();
                }
                #endregion
            }

        }

        public void sendFountmsg1(string msg)
        {
            connect();
            using (channel = conn.CreateModel())
            {
                #region 消息响应
                bool durable = true;
                string queueName = "jiqun";
                //交换机申明
                string EXCHANGE_NAME = "logs";
                string ROUTING_KEY = queueName;
                //channel.ExchangeDeclare(EXCHANGE_NAME, "topic", true, false);   //topic
                channel.ExchangeDeclare(EXCHANGE_NAME, "fanout");//广播

                var consumer = new QueueingBasicConsumer(channel); //在通道channel里添加消费者
                try
                {
                    var properties = channel.CreateBasicProperties();
                    properties.SetPersistent(true);
                    var body = Encoding.UTF8.GetBytes(msg);
                    channel.BasicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, body);//不需要指定routing key，设置了fanout,指了也没有用.
                    Console.WriteLine(" set {0}", msg);
                }
                catch (Exception ex)
                {
                }
                finally
                {
                    close();
                }
                #endregion
            }

        }
        #endregion
    }
}