using CoreDemo.Dao;
using CoreDemo.DbHelp;
using Microsoft.Extensions.Options;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CoreDemo.ServiceImp
{
    public class TestServiceImp
    {

        public TestServiceImp()
        {
        }
        public List<TEST> GetList()
        {
            using (var db = SqlSugarBase.DB)
            {
                return db.Queryable<TEST>().ToList();
            }
        }
    }
}
