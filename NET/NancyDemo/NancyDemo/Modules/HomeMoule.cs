using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Nancy;
using Nancy.ModelBinding;

namespace NancyDemo.Modules
{
    public class HomeMoule : NancyModule
    {
        public HomeMoule()
        {
            string model = "ada";        
            Get("/h", _ =>"这是一段字符串!");
            //带参数
            Get("/h/{id:int}", _ =>"这是一段字符串!"+_.id);
            Get("/",_=> {
                
                return View["/Home",model];
            });   //home 大小写不区分
     
            Post("/PER",_=> {
                string name = Request.Query["name"];
                Person s = this.Bind<Person>();
                return View["/Home",s];
            });   //home 大小写不区分
            Get("/PER", _ => {
                string name = Request.Query["name"];
                return View["/Home", name];
            });   
        }

        public class Person
        {
            public string name { get; set; }
            public string age { get; set; }
        }
    }
}