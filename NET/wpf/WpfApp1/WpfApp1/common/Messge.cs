using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp1.common
{
    public class Messge : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        private string remsg = "消息:";
        private string sendmsg = "默认信息";
        public string Remsg
        {
            get
            {
                return remsg;
            }
            set
            {
                //if (remsg != value)           //如果当前的变量值不等于先前的文件名，说明需要更新文件名
                //{
                remsg = value; //更新文件名               
                //通知绑定此变量的textbox在前台更新
                if(PropertyChanged!=null)
                PropertyChanged(this, new PropertyChangedEventArgs("Remsg"));
                //  }
            }
        }
        public string Sendmsg
        {
            get
            {
                return sendmsg;
            }
            set
            {
                //if (sendmsg != value) //如果当前的变量值不等于先前的文件名，说明需要更新文件名
                //{
                sendmsg = value;//更新文件名
                                //通知绑定此变量的textbox在前台更新
                if (PropertyChanged != null)
                    PropertyChanged(this, new PropertyChangedEventArgs("Sendmsg"));
                // }
            }
        }
    }
}
