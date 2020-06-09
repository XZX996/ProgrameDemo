using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using Steeltoe.Discovery.Client;

namespace core_api1
{
    public class Startup
    {

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers().AddNewtonsoftJson(options =>
            {
                //����ʱ���ʽ
                options.SerializerSettings.DateFormatString = "yyyy-MM-dd HH:mm:ss";
                //����ѭ������
                // options.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
                //���ݸ�ʽ����ĸСд
                //options.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                //���ݸ�ʽ��ԭ�����
                // options.SerializerSettings.ContractResolver = new DefaultContractResolver();
                //���Կ�ֵ
                // options.SerializerSettings.NullValueHandling = NullValueHandling.Ignore;
            });
            /* services.AddSwaggerGen(c=> {
                 c.SwaggerDoc("v1", new OpenApiInfo { Title = "My API", Version = "v1" });
             });*/
            services.AddSwaggerGen(_ =>
            {
                _.SwaggerDoc("v1", new OpenApiInfo
                {
                    Version = "v1.1.0",
                    Title = "Core_swagger",
                    Description = "��ܼ���",
                    //TermsOfService = "None",
                    // Contact = new Swashbuckle.AspNetCore.Swagger.Contact { Name = "RayWang", Email = "2271272653@qq.com", Url = "http://www.cnblogs.com/RayWang" }
                });
                //��Ӷ�ȡע�ͷ���
                var basePath = AppContext.BaseDirectory;
                var xmlPath = Path.Combine(basePath, "core_api.xml");
                _.IncludeXmlComments(xmlPath);
            });
            //���ע����������
            services.AddDiscoveryClient(Configuration);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
            #region swagger
            //�����м����������Swagger��ΪJSON�ս��
            app.UseSwagger();
            //�����м�������swagger-ui��ָ��Swagger JSON�ս��
            /*app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "My API V1");
            });
            #region Swagger
            app.UseSwagger();*/
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "core_api");
            });
            //���ע����������
            app.UseDiscoveryClient();
            #endregion
        }
    }
}
