using System.Collections.Generic;
using Core_api.Helptool;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace Core_api.Controllers
{
    [Route("xzx/[controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        /// <summary>
        ///  GET api/values
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public string Get()
        {
            RefInfo<string> re = new RefInfo<string>();
            string ss = "hello"; //"{\"code\":0,\"msg\":\"hello,core\",\"data\":\"hello\"}";
            ss=JsonConvert.SerializeObject(re.success(ss));
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
            RefInfo<string> re = new RefInfo<string>();
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
            RefInfo<string> re = new RefInfo<string>();
            string ss = id.ToString(); //"{\"code\":0,\"msg\":\"hello,core\",\"data\":\"hello\"}";
            ss = JsonConvert.SerializeObject(re.success(ss));
            return ss;

        }

        // POST api/values
        [HttpPost]
        public string Post([FromBody] string value)
        {
            string ss = "hello,core";
            return JsonConvert.SerializeObject(ss);
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
