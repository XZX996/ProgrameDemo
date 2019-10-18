using GrainInterfaces;
using Microsoft.Extensions.Logging;
using Orleans;
using Orleans.Configuration;
using System;
using System.Threading.Tasks;

namespace Client
{
    public class Program
    {
        /*该编程模型是我们分布式面向对象编程的核心概念的一部分。
        SiloHost首先启动。然后，OrleansClient程序启动。
        OrleansClient的Main方法调用启动客户端的方法StartClientWithRetries()，
        客户端被传递给DoClientWork()方法。
        ======================================================================
        此时，OrleansClient创建对IHello grain的引用，
        并通过其接口IHello，调用其SayHello（）方法。此调用激活silo中的grain。OrleansClient向激活的grain发送问候语。
        grain返回问候语作为对OrleansClient的响应，OrleansClient显示在控制台上。          
        */
        static int Main(string[] args)
        {
            return RunMainAsync().Result;
        }

        private static async Task<int> RunMainAsync()
        {
            try
            {
                using (var client = await ConnectClient())
                {
                    await DoClientWork(client);
                    Console.ReadKey();
                }

                return 0;
            }
            catch (Exception e)
            {
                Console.WriteLine($"\nException while trying to run client: {e.Message}");
                Console.WriteLine("Make sure the silo the client is trying to connect to is running.");
                Console.WriteLine("\nPress any key to exit.");
                Console.ReadKey();
                return 1;
            }
        }

        private static async Task<IClusterClient> ConnectClient()
        {
            IClusterClient client;
            client = new ClientBuilder()
                .UseLocalhostClustering()   //将client配置为连接到localhost上的silo。
                .Configure<ClusterOptions>(options =>
                {
                    options.ClusterId = "dev";
                    options.ServiceId = "OrleansBasics";
                })
                .ConfigureLogging(logging => logging.AddConsole())
                .Build();

            await client.Connect();
            Console.WriteLine("Client successfully connected to silo host \n");
            return client;
        }

        private static async Task DoClientWork(IClusterClient client)
        {
            // example of calling grains from the initialized client
            var friend = client.GetGrain<Ihello>(0);
            var response = await friend.sayHello("Good morning, HelloGrain!");
            Console.WriteLine("\n\n{0}\n\n", response);
        }

    }
}
