using Microsoft.Extensions.Options;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CoreDemo.DbHelp
{
    public  class dbhelp
    {
        public static DbConSet config;

        public  dbhelp(IOptions<DbConSet> redisOptions)
        {
           config = redisOptions.Value;
        }
    }
}
