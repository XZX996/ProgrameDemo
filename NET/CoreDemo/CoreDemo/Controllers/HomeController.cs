using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using CoreDemo.Models;
using CoreDemo.ServiceImp;
using CoreDemo.Dao;
using Microsoft.Extensions.Options;

namespace CoreDemo.Controllers
{
    public class HomeController : Controller
    {
        private TestServiceImp testServiceImp=new TestServiceImp();
        public IOptions<Context> option1;

        [HttpGet]
        public IActionResult Index()
        {
           // ViewBag.Result = contents;
             var result = testServiceImp.GetList();
            Dictionary<int, Context> dic = new Dictionary<int, Context>();
            ViewBag.Result = result;
            
            return View();
        }

        public IActionResult About()
        {
            ViewData["Message"] = "Your application description page.";

            return View();
        }

        public IActionResult Contact()
        {
            ViewData["Message"] = "Your contact page.";

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

     
    }
}
