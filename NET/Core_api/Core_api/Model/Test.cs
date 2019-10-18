using SqlSugar;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Core_api.Model
{
    #region 实体特性
    /*
        1.ColumnName 定义数据库表字段的真实名称，当一样的时候可以不定义该特性

        2.IsIgnore   查询 增加 删除 更新会过滤这一列，如果想让查询也有这一列可以用.Select("*")强制查所有列

        3.IsPrimaryKey 标识是否为主键，更新和插入的时候会根据主键值判段更新哪条，当InitKey为Attribute时一定要加该特性不然找不到主键，Systemtable加了没有效果

        4.IsIdentity 是否为自增长  ，更新和插入的时候会根据会有用，当InitKey为Attribute时一定要加该特性，Systemtable加了没有效果

        5.ColumnDescription 列描述，暂时未实现该功能

        6.Length 长度，生成表会用到

        7.IsNullable 是否可空，生成表会用到

        8.OldColumnName 修改列名，生成表会用到

        9.ColumnDataType 自定义生成的数据类型，生成表会用到
         
       */
    #endregion

    [SugarTable("TEST")]
    public class Test
    {
        public string NAME { get; set; }
        public decimal ZT { get; set; }

        [SugarColumn(IsPrimaryKey = true)]
        public int NID { get; set; }
    }
}
