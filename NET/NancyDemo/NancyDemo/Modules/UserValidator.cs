using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Security.Claims;
using System.Security.Principal;
using Nancy.Authentication.Basic;

namespace NancyDemo.Modules
{
    public class UserValidator: IUserValidator
    {

        public ClaimsPrincipal Validate(string username, string password)
        {
            if (username == "xzx" && password == "123")
            {
                return new ClaimsPrincipal(new GenericIdentity(username));
            }
            //没有认证=>匿名
            return null;
        }
    }

}