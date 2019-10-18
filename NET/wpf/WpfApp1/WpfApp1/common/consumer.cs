using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace WpfApp1.common
{
    /**
     * 消费者
     * */
    public class consumer
    {
        public  string message = null;
        private Boolean contine = false;
        //连接rabbit
        IConnection conn = null;
        //通道Channel用于接收和发送消息
        IModel channel = null;
        int runIndex = 1;
        //连接rabbit服务
        public Boolean connect()
        {
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
                return true;
            }
            catch (Exception ex)
            {
                Console.Write(string.Format("RabbitMQ连接异常:{0}\n", ex.ToString()));
                return false;
            }
        }
        //关闭Rabbit服务
        public void close()
        {
            if (channel != null)
            {
                channel.Close();
                channel = null;
            }
            conn.Close();
        }
        #region 接受队列消息
        public string ReceiveMsg()
        {
            string message = null;
            if (!connect())
                return null;
            using (channel = conn.CreateModel())
            {
                #region 消息响应

                //输入1，那如果接收一个消息，但是没有应答，则客户端不会收到下一个消息
                channel.BasicQos(0, 1, false);
                Console.WriteLine("Listening...");
                bool durable = true;
                string queueName = "jiqun";
                //队列声明 此处的队列声明要与发送端一致
                // channel.QueueDeclare(queueName, true, false, true, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                var consumer = new QueueingBasicConsumer(channel); //在通道channel里添加消费者
                channel.BasicConsume(queueName, true, consumer); //消费者订阅队列 // 消息队列的名字 是否关闭消息响应 消费者的名字
                try
                {
                    //while (true)
                    //{
                        BasicDeliverEventArgs ea = (BasicDeliverEventArgs)consumer.Queue.Dequeue();
                        IBasicProperties properties = ea.BasicProperties;
                        byte[] body = ea.Body;
                        message = Encoding.UTF8.GetString(body);
                        Console.WriteLine("已接收:{0},{1}", message, runIndex);
                        //channel.BasicAck(ea.DeliveryTag, false);
                        return message;
                   // }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex);
                    return message;
                }
                finally
                {                   
                    close();           
                }
                #endregion
            }

        }

        public string ReceivefountMsg()
        {
            string message = null;
            if (!connect())
                return null;
            using (channel = conn.CreateModel())
            {
                #region 消息响应

                //输入1，那如果接收一个消息，但是没有应答，则客户端不会收到下一个消息
                channel.BasicQos(0, 1, false);
                Console.WriteLine("Listening...");
                bool durable = true;
                string queueName = "jiqun";
                string EXCHANGE_NAME = "logs";
                channel.ExchangeDeclare(EXCHANGE_NAME, "fanout");
                // 由RabbitMQ自行创建的临时队列,唯一且随消费者的中止而自动删除的队列
                String queueName1 = channel.QueueDeclare();
                //队列声明 此处的队列声明要与发送端一致
                // channel.QueueDeclare(queueName, true, false, true, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                channel.QueueBind(queueName, EXCHANGE_NAME, "");//不需要指定routing key，设置了fanout,指了也没有用.
                var consumer = new QueueingBasicConsumer(channel);  //在通道channel里添加消费者
               // channel.BasicQos(1,1,false);
                channel.BasicConsume(queueName, true, consumer); //消费者订阅队列 // 消息队列的名字 是否关闭消息响应 消费者的名字
                try
                {
                    //while (true)
                    //{
                    BasicDeliverEventArgs ea = (BasicDeliverEventArgs)consumer.Queue.Dequeue();
                    IBasicProperties properties = ea.BasicProperties;
                    byte[] body = ea.Body;
                    message = Encoding.UTF8.GetString(body);
                    Console.WriteLine("已接收:{0},{1}", message, runIndex);
                    //channel.BasicAck(ea.DeliveryTag, false);
                    return message;
                    // }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex);
                    return message;
                }
                finally
                {
                    close();
                }
                #endregion
            }

        }
        #endregion
        #region 接受交换机消息
        public void ReceiveMsg1()
        {            
            if (!connect())
                return;
            using (channel = conn.CreateModel())
            {
                #region 消息响应

                //输入1，那如果接收一个消息，但是没有应答，则客户端不会收到下一个消息
                channel.BasicQos(0, 1, false);
                Console.WriteLine("Listening...");
                bool durable = true;
                string queueName = "jiqun";
                string ExchangeName = "messge";
                //队列声明 此处的队列声明要与发送端一致
                // channel.QueueDeclare(queueName, true, false, true, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                channel.QueueBind(queueName, ExchangeName, "");
                var consumer = new EventingBasicConsumer(channel); //在通道channel里添加消费者
                channel.BasicConsume(queueName, false, consumer); //消费者订阅队列 // 消息队列的名字 是否关闭消息响应 消费者的名字
                consumer.Received+= consumer_Received;           
                while (!contine)
                {
                    Thread.Sleep(500);
                }

                #endregion
            }

        }
        public void consumer_Received(object sender,BasicDeliverEventArgs e)
        {
            try
            {
                //while (true)
                //{
                IBasicConsumer basicConsumer = sender as IBasicConsumer;
                var body = e.Body;
                message = Encoding.UTF8.GetString(body);
                Console.WriteLine("收到ExchangeDirect：" + message);
                if (basicConsumer != null) basicConsumer.Model.BasicAck(e.DeliveryTag, false);          
                // }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
            finally
            {
                close();
                contine = true;
            }
        }
        public string ReceivefountMsg1()
        {
            string message = null;
            if (!connect())
                return null;
            using (channel = conn.CreateModel())
            {
                #region 消息响应

                //输入1，那如果接收一个消息，但是没有应答，则客户端不会收到下一个消息
                channel.BasicQos(0, 1, false);
                Console.WriteLine("Listening...");
                bool durable = true;
                string queueName = "jiqun";
                string EXCHANGE_NAME = "logs";
                channel.ExchangeDeclare(EXCHANGE_NAME, "fanout");
                // 由RabbitMQ自行创建的临时队列,唯一且随消费者的中止而自动删除的队列
                String queueName1 = channel.QueueDeclare();
                //队列声明 此处的队列声明要与发送端一致
                // channel.QueueDeclare(queueName, true, false, true, null); //要发送的信息 是否持久化 是否私有的 连接关闭时是否删除队列 参数                                         
                channel.QueueBind(queueName, EXCHANGE_NAME, "");//不需要指定routing key，设置了fanout,指了也没有用.
                var consumer = new QueueingBasicConsumer(channel);  //在通道channel里添加消费者
                                                                    // channel.BasicQos(1,1,false);
                channel.BasicConsume(queueName, true, consumer); //消费者订阅队列 // 消息队列的名字 是否关闭消息响应 消费者的名字
                try
                {
                    //while (true)
                    //{
                    BasicDeliverEventArgs ea = (BasicDeliverEventArgs)consumer.Queue.Dequeue();
                    IBasicProperties properties = ea.BasicProperties;
                    byte[] body = ea.Body;
                    message = Encoding.UTF8.GetString(body);
                    Console.WriteLine("已接收:{0},{1}", message, runIndex);
                    //channel.BasicAck(ea.DeliveryTag, false);
                    return message;
                    // }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex);
                    return message;
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
