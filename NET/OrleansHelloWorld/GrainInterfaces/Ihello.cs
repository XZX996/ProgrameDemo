using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace GrainInterfaces
{
   //grain接口的所有方法都必须返回一个 Task（对于void方法）、一个 Task<T>或一个 ValueTask<T>
    public interface Ihello : Orleans.IGrainWithIntegerKey
    {
        Task<String> sayHello(String geeting);

        Task NoReturn(String geeting);

        Task<String> AsayHello(String geeting);
    }
}
