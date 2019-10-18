using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Nancy;
using Nancy.Security;

namespace NancyDemo.Modules
{
    public class SecureModule:NancyModule
    {
        public SecureModule():base("/Secure")
        {
            //调用权限验证
            this.RequiresAuthentication();
            Get("/", args => "Hello " + this.Context.CurrentUser.Identity.Name);
        }
    }
}