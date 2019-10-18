using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;

namespace CoreDemo
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateWebHostBuilder(args).Build().Run();
        }

        public static IWebHostBuilder CreateWebHostBuilder(string[] args) =>
            WebHost.CreateDefaultBuilder(args)           
                .UseStartup<Startup>();


        /*.ConfigureAppConfiguration((hostingContext,config)=>{
        var env = hostingContext.HostingEnvironment;
        config.AddJsonFile("appsettings.json", optional:true,reloadOnChange:true);
            })*/   //自定义配置
    }
}
