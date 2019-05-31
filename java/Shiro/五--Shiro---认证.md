#1. 获取当前的Subject,调用SecurityUtils.getSubject();
```java
Subject currentUser = SecurityUtils.getSubject();
```
#2. 测试当前用户是否已经被认证,即是否已经登录,调用
```java
Subject.isAuthenticated();
```
#3. 若没有被认证,把用户名和密码封装为UsernamePasswordToken对象
```
UsernamePasswordToken token = new UsernamePasswordToken(username, password);
```
#4. 调用方法执行登陆,Subject.login(AuthenticationToken)方法
```java
currentUser.login(token);
```
#5. 自定义Realm的方法,从数据库中获取对应的记录,返回给Shiro
##  5.1 实际上需要继承 org.apache.shiro.realm.AuthenticatingRealm 类
* 其中有一个抽象方法
##  5.2 实现 doGetAuthenticationInfo(AuthenticationToken) 方法. 
1. 把AutenticationToken转换为UsernamePasswordToken
```
UsernamePasswordToken upToken = (UsernamePasswordToken) token;	
```
2. 从UsernamePasswordToken中来获取Username
```
String username = upToken.getUsername();	
```
3. 调用数据库的方法,从数据库中查询username对应的用户记录
4. 若用户不存在,则可以抛出异常UnknownAccountException
```
if("unknown".equals(username)){
    throw new UnknownAccountException("用户不存在!");
}
```
5. 根据用户信息的情况决定是否要抛出其他的异常
```
if("monster".equals(username)){
    throw new LockedAccountException("用户被锁定");
}
```
6. 根据用户的情况,来构建AuthenticationInfo对象并返回
7. 由 shiro 完成对密码的比对. 
```java
public class SecondRealm extends AuthenticatingRealm {
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SecondReaml] doGetAuthenticationInfo");	
/***********************验证登陆名*******************************************************/
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;	
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();	
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在!");
		}
		//5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常. 
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁定");
		}
/***********************验证密码*******************************************************/
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
		Object principal = username;
		//2). credentials: 密码. 
		Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
		if("admin".equals(username)){
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else if("user".equals(username)){
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值. 
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo("secondRealmName", credentials, credentialsSalt, realmName);
		return info;
	}
}
```
> 1. 此例子是先行查询了用户名,认证账号的状态
> 2. 如果用户名可以存在,并且可以使用,则再去验证密码的正确性

