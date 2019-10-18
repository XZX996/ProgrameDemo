using GrainInterfaces;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Grains
{
    /*grain是Orleans应用程序的构建块
     * 它们是隔离的、分布式的、持久性的原子单元。
     * grain是表示应用程序实体的对象。就像在经典的面向对象编程中一样，
     * grain封装了实体的状态，并在代码逻辑中对其行为进行编码。Grains可以保持彼此的引用，
     * 并通过调用彼此通过接口公开的方法，来进行交互。
     * =========================================================================================
     * 
     * 单个grain是grain类型（类）的唯一可寻址实例。
     * 每种grain在其类型中具有唯一的身份，也称为grain键。
     * 其类型中的grain标识可以是长整数、GUID、字符串或long + string或GUID + string的组合。
     * */


    public class HelloGrain : Orleans.Grain, Ihello
    {
        private readonly ILogger ilogger;

        public HelloGrain(ILogger<HelloGrain> logger)
        {
            this.ilogger = logger;
        }

        //有返回值
        public Task<string> sayHello(string greeting)
        {
            ilogger.LogInformation($"\n 接收到客户端消息: '{greeting}'");
            return Task.FromResult($"\n客户端消息: '{greeting}', so HelloGrain says: Hello!");
        }
   
        //异步有返回值
        public async Task<string> AsayHello(string greeting)
        {
            ilogger.LogInformation($"\n 接收到客户端消息: '{greeting}'");
            return "\n客户端消息: '{greeting}', so HelloGrain says: Hello!";
        }

        //无返回值
        public Task NoReturn(string greeting)
        {
            ilogger.LogInformation($"\n 接收到客户端消息: '{greeting}'");
            return Task.CompletedTask;
        }

        //异步无返回值
        public async Task AnoReturn(string greeting)
        {
            ilogger.LogInformation($"\n 接收到客户端消息: '{greeting}'");
            return ;
        }

    }
}
