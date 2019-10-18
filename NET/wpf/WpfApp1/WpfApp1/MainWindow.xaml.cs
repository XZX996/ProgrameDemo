using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using WpfApp1.common;

namespace WpfApp1
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        #region 内部类
        #endregion
        Messge m;
        static bool sucess = false;
        public MainWindow()
        {
            InitializeComponent();
            m = new Messge() { Sendmsg = "默认消息"+new DateTime().ToString(),Remsg="消息:" };         
            Console.WriteLine(m.Remsg + m.Sendmsg);
            //text.DataContext = m.Remsg;
            //send.DataContext = m.Sendmsg;
            // 简写
            send.SetBinding(TextBox.TextProperty, new Binding("Sendmsg")
            {
                Source = m,
                Mode = BindingMode.TwoWay
            });
            text.SetBinding(TextBlock.TextProperty, new Binding("Remsg")
            {
                Source = m,
                Mode = BindingMode.TwoWay
            });
        }      
        //接受消息
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Thread t = new Thread(()=>{
                consumer con = new consumer();
                con.ReceiveMsg1();
                m.Remsg += con.message;
                try
                {
                    System.Threading.Thread.CurrentThread.Abort();
                }
                catch (Exception)
                {

                    throw;
                }

            });
            t.IsBackground = true;
            t.Start();
            //this.Dispatcher.BeginInvoke(new Action(() =>
            //{
                
            //}));
        }
        //发送单一消息
        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
           producer pro = new producer();
           pro.senddirectmsg1(m.Sendmsg);                  

        }
        //发送广播
        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            m = new Messge();
            m.Sendmsg = "sdadsd";
            producer pro = new producer();
            pro.sendFountmsg(m.Sendmsg);
        }

        //广播订阅
        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            Thread t = new Thread(() => {
                consumer con = new consumer();
                string msg = con.ReceivefountMsg();
                m.Remsg += msg;
                try
                {
                    System.Threading.Thread.CurrentThread.Abort();
                }
                catch (Exception)
                {

                    throw;
                }
            });
            t.IsBackground = true;
            t.Start();
        }
    }
}
