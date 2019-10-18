using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CoreDemo.Dao;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;

namespace CoreDemo.Controllers
{
    public class BaseController:Controller
    {
        private readonly Context contents;

        public BaseController(IOptions<Context> option)
        {
            contents = option.Value;
        }
    
    }
}