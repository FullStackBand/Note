#例子
```xml
        <property name="filterChainDefinitions">
            <value>
                /login.jsp = anon
                /shiro/login = anon
                /shiro/logout = logout
                
                /user.jsp = roles[user]
                /admin.jsp = roles[admin]
                
                # everything else requires authentication:
                /** = authc
            </value>
        </property>
```
#一 . url 匹配模式
* 支持Ant风格模式
1. `?` : 匹配一个字符
2. `*` : 匹配零个或多个字符串
3. `**` : 匹配零个或者多个路径
#二 . url权限匹配顺序
* 采用**第一次匹配优先的方式**
   即从开始使用第一个匹配的url模式对应的拦截器链
   如:
```
   - /bb/**=filter1
   - /bb/aa=filter2
   - /**=filter3
```
* 如果请求的url是"/bb/aa",那么将使用filter1进行拦截(没错我没有写错)
#三 . 过滤器的意义
1. 无参
* `/admin/** = anon` ：无参，表示可匿名访问
* `/admin/user/** = authc` ：无参，表示需要认证才能访问
* `/admin/user/** = authcBasic` ：无参，表示需要httpBasic认证才能访问
* `/admin/user/** = ssl` ：无参，表示需要安全的URL请求，协议为https
* `/home = user` ：表示用户不一定需要通过认证，只要曾被 Shiro 记住过登录状态就可以正常发起 /home 请求
2. 必须认证
* `/edit = authc,perms[admin:edit]` ：表示用户必需已通过认证，并拥有 admin:edit 权限才可以正常发起 /edit 请求
* `/admin = authc,roles[admin]` ：表示用户必需已通过认证，并拥有 admin 角色才可以正常发起 /admin 请求
* `/admin/user/** = port[8081]` ：当请求的URL端口不是8081时，跳转到`schemal://serverName:8081?queryString`
* `/admin/user/** = rest[user]` ：根据请求方式来识别，相当于 /admins/user/**=perms[user:get]或perms[user:post] 等等
* `/admin** = roles["admin,guest"]` ：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于hasAllRoles()
* `/admin** = perms["user:add:*,user:del:*"]`：允许多个参数（逗号分隔），此时要全部通过才算通过，相当于isPermitedAll()
3. 退出登录
* `/logout = logout`

> 默认过滤器通过枚举声明。
> ```
> enum DefaultFilter`
> anon
> authc
> authcBasic
> logout
> noSessionCreation
> perms
> port
> rest
> roles
> ssl
> user
> ``` 
