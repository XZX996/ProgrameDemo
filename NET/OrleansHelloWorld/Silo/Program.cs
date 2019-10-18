using Grains;
using Orleans;
using Orleans.Configuration;
using Orleans.Hosting;
using System;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;
using System.Net;

namespace Silo
{
    public class Program
    {
        public static int Main(string[] args)
        {
            return RunMainAsync().Result;
        }

        private static async Task<int> RunMainAsync()
        {
            try
            {
                var host = await StartSilo();
                Console.WriteLine("\n\n Press Enter to terminate...\n\n");
                Console.ReadLine();

                await host.StopAsync();

                return 0;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                return 1;
            }
        }

        private static async Task<ISiloHost> StartSilo()
        {
            // define the cluster configuration
            var builder = new SiloHostBuilder()
                .UseLocalhostClustering()    //将client配置为连接到localhost上的silo。
                .Configure<ClusterOptions>(options =>     
                {
                    options.ClusterId = "dev"; //ClusterId是Orleans集群的名称，对于silo和client必须相同，因此它们可以相互通信。ServiceId是用于应用程序的ID，不得在部署之间进行更改
                    options.ServiceId = "OrleansBasics";
                })
                .Configure<EndpointOptions>(options => options.AdvertisedIPAddress = IPAddress.Loopback)//这告诉silo在哪里监听。这里监听loopback地址。
                .ConfigureApplicationParts(parts => parts.AddApplicationPart(typeof(HelloGrain).Assembly).WithReferences())  //将grain类和接口程序集作为应用程序部件添加到orleans应用程序中。
                .ConfigureLogging(logging => logging.AddConsole());

            var host = builder.Build();
            await host.StartAsync();
            return host;
        }
    }
}
