using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Nancy;
using Nancy.Routing;

namespace NancyDemo.Modules
{
    public class DocModule : NancyModule
    {
        private IRouteCacheProvider _routeCacheProvider;

        public DocModule(IRouteCacheProvider routeCacheProvider) : base("/docs")
        {
            this._routeCacheProvider = routeCacheProvider;
            int a = 1;
            string b = "11";
            var i = Tuple.Create<int, String>(a, b);
            Console.WriteLine($"{i.Item1}  {i.Item2} ");
            Get("/", _ =>
            {
                var routeDescriptionList = _routeCacheProvider
                                             .GetCache()
                                             .SelectMany(x => x.Value)
                                             .Select(x => x.Item2)
                                             .Where(x => !string.IsNullOrWhiteSpace(x.Name))
                                             .ToList();

                return Response.AsJson(routeDescriptionList);
            });
        }

    }
}