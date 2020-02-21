using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using Swashbuckle.AspNetCore.Swagger;

namespace Core_api
{
    // 1.构造函数 Startup  Core的核心是依赖注入 所以要有构造函数进行注入
    // 2.承载注入实现的对象 IConfiguration
    // 3.添加服务的方法 ConfigureServices 比Configure 先调用
    // 4.配置HTTP请求管道的方法 Configure
    public class Startup
    {
        //环境信息
        private readonly IHostingEnvironment _env;
        //获取配置信息使用 GetSection
        private readonly IConfiguration _config;
        //Log记录接口
        private readonly ILoggerFactory _loggerFactory;
        public Startup(IHostingEnvironment env,IConfiguration config,ILoggerFactory loggerFactory)
        {
            _env = env;
            _config = config;
            _loggerFactory = loggerFactory;
        }
        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            //跨域配置
            string[] urls = _config.GetSection("AllowCors:AllowAllOrigin").Value.Split(',');
            // 设置允许的请求来源地址、头信息、请求类型,cookie
            //对于cookie，AJAX中配置xhrFields:{withCredentials: true},
            services.AddCors(Options=>
            {
                Options.AddPolicy("AllowAllOrigin", builder => {
                    builder.WithOrigins(urls)
                                 .AllowAnyHeader()
                                 .AllowAnyMethod()
                                 .AllowCredentials()
                                 .AllowAnyOrigin();  //允许所有来源主机
                });             
            });
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_1);
            #region Swagger
            services.AddSwaggerGen(_ =>
            {
                _.SwaggerDoc("v1", new Info
                {
                    Version = "v1.1.0",
                    Title = "Core_swagger",
                    Description = "框架集合",
                    TermsOfService = "None",
                   // Contact = new Swashbuckle.AspNetCore.Swagger.Contact { Name = "RayWang", Email = "2271272653@qq.com", Url = "http://www.cnblogs.com/RayWang" }
                });
                //添加读取注释服务
                var basePath = AppContext.BaseDirectory;
                var xmlPath = Path.Combine(basePath, "Core_api.xml");
                _.IncludeXmlComments(xmlPath);
            });

            //D:\ProgrameDemo\NET\Core_api\Core_api\Core_api.xml
            
            #endregion
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseHsts();
            }

            //app.UseCors(options => options
            //                      .WithOrigins("http://localhost:56594")
            //                      .AllowAnyHeader()
            //                      .AllowAnyMethod()
            //                      .AllowCredentials()
            //    );
            app.UseCors("AllowAllOrigin");
            //启用Mvc服务
            app.UseHttpsRedirection();
            app.UseMvc();
            #region Swagger
            app.UseSwagger();
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "ApiHelp V1");
            });
            app.UseDefaultFiles();
            //这个可以添加默认页
            DefaultFilesOptions options = new DefaultFilesOptions();
            //这里的清理可以不使用
            options.DefaultFileNames.Clear();
            options.DefaultFileNames.Add("error.html");
            app.UseDefaultFiles(options);
            #endregion
        }
    }
}
