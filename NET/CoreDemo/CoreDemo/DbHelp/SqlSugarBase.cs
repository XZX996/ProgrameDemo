using Microsoft.Extensions.Options;
using SqlSugar;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CoreDemo.DbHelp
{
    public  class SqlSugarBase
    {
        private SqlSugarBase()
        {

        }
        public static SqlSugarClient DB => GetInstance();
        public  string connectionString;
        static SqlSugarClient GetInstance()
        {
            //string connectionString = "Server=127.0.0.1;Database=SqlSugarDemo;Integrated Security=False;User ID=sa;Password=sa;";
            var db = new SqlSugarClient(
                new ConnectionConfig
                {
                    ConnectionString = "Data Source=11.101.2.36:1521/orcl;Persist Security Info=True;User ID=gz_veh_drv_mgr;Password=scxdcj;", //dbhelp.config.OraDbCon,
                    DbType = DbType.Oracle,
                    IsAutoCloseConnection=true, //自动释放数据务，如果存在事务，在事务结束后释放
                    InitKeyType =InitKeyType.SystemTable, //从实体特性中读取主键自增列信息
                    IsShardSameThread = true
                });
            //用来打印Sql方便调式    
            db.Aop.OnLogExecuting = (sql, pars) =>
            {
                Console.WriteLine(sql + "\r\n" +
                db.Utilities.SerializeObject(pars.ToDictionary(it => it.ParameterName, it => it.Value)));
                Console.WriteLine();
            };

            return db;
        }
    }
}
