一 . 继承org.apache.shiro.realm.activedirectory.ActiveDirectoryRealm
```
public class ShiroRealm extends AuthorizingRealm { 
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        方法...
        }
}
```
二 . 重写protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token);方法
```
public class ShiroRealm extends AuthorizingRealm { 
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        ......
        SimpleAuthenticationInfo info = SimpleAuthenticationInfo(
                Object principal,            //认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
                Object hashedCredentials,    //密码
                ByteSource credentialsSalt,  //当前 realm 对象的 name. 调用父类的 getName() 方法即可  (getName();)
                String realmName             //ByteSource.Util.bytes(username);
                );			
        ......
        
```
三 . Spring 配置 bean
----未完待续
