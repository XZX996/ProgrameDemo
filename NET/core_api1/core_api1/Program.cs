using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;


namespace core_api1
{
    public class Program
    {
        public static async Task Main(string[] args)
        {
            IHost webHost = CreateHostBuilder(args).Build();

           /* using (var client = new HttpClient())
            {
                client.DefaultRequestHeaders.Add("serversSecurityServerName", "Kettle");
                client.DefaultRequestHeaders.Add("serversSecurityAccessToken", "2d09c9ec-e0d7-11e8-9c84-0235d2b38928");
                var values = new List<KeyValuePair<string, string>>();
             
                var content = new FormUrlEncodedContent(values);

                var response = await client.PostAsync("http://11.101.2.16:9090/AdminServer/sys/security/server/serverRegist", content);

                var responseString = await response.Content.ReadAsStringAsync();
                Console.WriteLine("====================>"+responseString);
            };*/
            await webHost.RunAsync();

        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}
