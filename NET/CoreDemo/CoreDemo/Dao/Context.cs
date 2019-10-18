using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CoreDemo.Dao
{
    public class Context
    {
        //6.0新特性
        public int Id { get; set; } = 1;
        public string title { get; set; }
        public string content { get; set; }
        public string status { get; set; }
        public string add_time { get; set; }
        public string modify_time { get; set; }
    }
}
