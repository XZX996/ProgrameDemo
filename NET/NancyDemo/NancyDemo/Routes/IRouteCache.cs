using Nancy.Routing;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NancyDemo.Routes
{
    //参数缓存  tuple 元组 存多种数据类型的数组。
    public interface IRouteCache : IDictionary<Type, List<Tuple<int, RouteDescription>>>, ICollection<KeyValuePair<Type, List<Tuple<int, RouteDescription>>>>, IEnumerable<KeyValuePair<Type, List<Tuple<int, RouteDescription>>>>, IEnumerable
    {
        
        bool IsEmpty();
    }
}