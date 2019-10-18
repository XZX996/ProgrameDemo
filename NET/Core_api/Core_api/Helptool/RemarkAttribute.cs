using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Core_api.Helptool
{
    /// <summary>
    /// 枚举自定义属性
    /// </summary>
    public class RemarkAttribute:Attribute
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="remark"></param>
        public RemarkAttribute(string remark)
        {
            this.Remark = remark;
        }
        /// <summary>
        /// 备注
        /// </summary>
        public string Remark { get; set; }
    }
}
