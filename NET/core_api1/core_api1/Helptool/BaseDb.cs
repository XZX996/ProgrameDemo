using SqlSugar;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Core_api.HelpTool
{
    public class BaseDb
    {
        public static SqlSugarClient GetClient()
        {
            SqlSugarClient db = new SqlSugarClient(
                new ConnectionConfig()
                {
                    ConnectionString = "Data Source=11.101.2.36:1521/orcl;Persist Security Info=True;User ID=gz_veh_drv_mgr;Password=scxdcj;",//BaseDBConfig.ConnectionString,
                    DbType = DbType.SqlServer,
                    IsAutoCloseConnection = true
                }
            );
            //输出sql 便于调试
            db.Aop.OnLogExecuting = (sql, pars) =>
            {
                Console.WriteLine(sql + "\r\n" + db.Utilities.SerializeObject(pars.ToDictionary(it => it.ParameterName, it => it.Value)));
                Console.WriteLine();
            };
            return db;
        }
    }
}
