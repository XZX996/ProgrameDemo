using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Nancy;
using Nancy.Authentication.Basic;
using Nancy.Bootstrapper;
using Nancy.TinyIoc;
using NancyDemo.Modules;

namespace NancyDemo
{
    public class BasicBootstrapper:DefaultNancyBootstrapper
    {
        protected override void ApplicationStartup(TinyIoCContainer container, IPipelines pipelines)
        {
            base.ApplicationStartup(container, pipelines);
            pipelines.EnableBasicAuthentication(new BasicAuthenticationConfiguration(container.Resolve<UserValidator>(), "xzx"));
        }
    }
}