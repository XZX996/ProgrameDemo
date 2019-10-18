using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Core_api.Helptool
{
    /// <summary>
    /// 消息返回参数
    /// </summary>
    public class RefInfo<T>
    {
        #region 返回状态类
        public class Meta {
            public  int code { get; set; }
            public  string messge { get; set; }
        }
        #endregion
        public  Meta meta;
        private List<T> data;

        public RefInfo()
        {
            meta = new Meta();
        }

        public RefInfo<T> success()
        {
            meta.code = (int)Emeta.SUCCESS;
            meta.messge= Emeta.SUCCESS.GetRemark();
            return this;
        }
        public RefInfo<T> success(List<T> data)
        {
           
            meta.code = (int)Emeta.SUCCESS;
            meta.messge = Emeta.SUCCESS.GetRemark();
           // EnumOprate.GetRemark(Meta.SUCCESS);
            this.data = data;
            return this;
        }

        public RefInfo<T> failure()
        {
            meta.code = (int)Emeta.Err400;
            meta.messge = Emeta.Err400.GetRemark();
            return this;
        }

        public RefInfo<T> failure(Emeta emeta)
        {
            meta.code = (int)emeta;
            meta.messge = emeta.GetRemark();
            return this;
        }

        public RefInfo<T> failure(Emeta emeta, List<T> data)
        {
            meta.code = (int)emeta;
            meta.messge = emeta.GetRemark();
            this.data = data;
            return this;
        }

        /// <summary>
        /// 返回状态码
        /// </summary>
        public enum Emeta
        {
            /**
             * 请求成功
             */
            [Remark("请求成功")]
            SUCCESS =200,

            /**
                * 请求错误
                */
            [Remark("请求错误")]
            Err400=400,
            [Remark("当前请求需要用户验证")]
            Err401=401,
            [Remark("请求失败，请求所希望得到的资源未被在服务器上发现")]
            Err404=404,

            /**
                * 服务器错误
                */
            [Remark("服务器遇到了一个未曾预料的状况")]
            Err500=500,
            [Remark("服务器不支持当前请求所需要的某个功能")]
            Err501 =501,
            [Remark("从上游服务器接收到无效的响应")]
            Err502=502,

            /**
                * 业务错误
                */
            [Remark("未登录！")]
            Err1=1,
            [Remark("权限不足")]
            Err3=3,
            [Remark("用户不存在！")]
            Err2=2,
            [Remark("异地登陆")]
            Err5=5,
            [Remark("密码错误")]
            Err4=4,
        };

    }
}
