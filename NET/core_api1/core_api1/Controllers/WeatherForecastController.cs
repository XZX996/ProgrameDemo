using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Core_api.Helptool;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;


namespace core_api1.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<WeatherForecastController> _logger;

        public WeatherForecastController(ILogger<WeatherForecastController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {
            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateTime.Now.AddDays(index),
                TemperatureC = rng.Next(-20, 55),
                Summary = Summaries[rng.Next(Summaries.Length)]
            })
            .ToArray();
        }
        [HttpGet("GetOne")]
        public string GetOne()
        {
            //{ "meta":{ "code":"200","message":"请求成功。"},"data":"查询结果为空"};
            ReslutStr<string> re = new ReslutStr<string>();
            string ss = "hello"; //"{\"code\":0,\"msg\":\"hello,core\",\"data\":\"hello\"}";
            ss = JsonConvert.SerializeObject(re.success(ss));
            return ss;
            //return new JsonResult(ss); //JsonConvert.SerializeObject(ss);
        }

        // GET api/values/5
        /// <summary>
        /// 获取单个
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("{code}")]
        public string Get(int id)
        {
            ReslutStr<string> re = new ReslutStr<string>();
            string ss = id.ToString(); //"{\"code\":0,\"msg\":\"hello,core\",\"data\":\"hello\"}";
            ss = JsonConvert.SerializeObject(re.success(ss));
            return ss;

        }
        // GET api/values/5
        /// <summary>
        /// 获取单个  当命名为code，会优先调用该方法。
        /// 条件的名称和路径不要采用同一个名称，它可以正常工作，但难以阅读。
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet("code")]
        public string Get1(int id)
        {
            ReslutStr<string> re = new ReslutStr<string>();
            string ss = id.ToString(); //"{\"code\":0,\"msg\":\"hello,core\",\"data\":\"hello\"}";
            ss = JsonConvert.SerializeObject(re.success(ss));
            return ss;

        }

        // POST api/values
        [HttpPost]
        public string Post([FromBody] string value)
        {
            ReslutStr<string> re = new ReslutStr<string>();
            string ss = "hello,core"+value;
            return JsonConvert.SerializeObject(re.success(ss));
        }


        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
