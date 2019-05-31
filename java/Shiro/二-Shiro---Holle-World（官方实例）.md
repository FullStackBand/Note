#这是一个官方的案例
```
package com.atguigu.shiro.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单的快速入门应用程序显示如何使用Shiro的API。
 *
 * @since 0.9 RC2
 */
public class Quickstart {

	private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);

	public static void main(String[] args) {

		// 创建一个已配置的Shiro SecurityManager的最简单方法
		// 领域、用户、角色和权限使用简单的INI配置。
		// 我们将使用一个可以接收.ini文件的工厂来实现这一点
		// 返回一个SecurityManager实例:
		// 用衬衫。类路径根目录下的ini文件
		// (文件:和url:前缀分别从文件和url加载):
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();

		// 对于这个简单的示例quickstart，使用SecurityManager
		// 可以作为JVM单例访问。大多数应用程序不会这样做
		// 而是依赖于它们的容器配置或web.xml
		// webapps.这超出了这个简单快速入门的范围
		// 我们只做最简单的，这样您就可以继续体验了
		// 对的事情。
		SecurityUtils.setSecurityManager(securityManager);

		// 现在已经建立了一个简单的Shiro环境，让我们看看您能做些什么:
		// 获取当前正在执行的用户:
		// 获取当前的话题。调用SecurityUtils.getSubject ();
		Subject currentUser = SecurityUtils.getSubject();

		// 使用会话做一些事情(不需要web或EJB容器!!!)
		// 测试使用会话
		// 获取会话:话题# getSession()
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			log.info("---> 检索正确的值! [" + value + "]");
		}
		// 让我们登录当前用户，以便检查角色和权限:
		// 测试当前的用户是否已经被认证. 即是否已经登录.
		// 调动 Subject 的 isAuthenticated()
		if (!currentUser.isAuthenticated()) {
			// 把用户名和密码封装为 UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
			// rememberme
			token.setRememberMe(true);
			try {
				// 执行登录.
				currentUser.login(token);
			}
			// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
			catch (UnknownAccountException uae) {
				log.info("没有用户名为的用户" + token.getPrincipal());
				return;
			}
			// 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
			catch (IncorrectCredentialsException ice) {
				log.info("密码帐户 " + token.getPrincipal() + " 是不正确的!");
				return;
			}
			// 用户被锁定的异常 LockedAccountException
			catch (LockedAccountException lae) {
				log.info("用户名账户 " + token.getPrincipal() + "被锁定.  " + "请联系管理员解锁.");
			}
			// ……在这里捕获更多异常(可能是特定于您的系统的自定义异常)
			// 应用程序?
			// 所有认证时异常的父类.
			catch (AuthenticationException ae) {
				// 意想不到的条件?错误呢?
			}
		}

		// 说出他们是谁:
		// 打印其标识主体(在本例中为用户名):
		log.info("----> User [" + currentUser.getPrincipal() + "] logged in successfully.");

		// 测试是否有某一个角色. 调用 Subject 的 hasRole 方法.
		if (currentUser.hasRole("schwartz")) {
			log.info("----> May the Schwartz be with you!");
		} else {
			log.info("----> Hello, mere mortal.");
			return;
		}

		// 测试一个类型权限(不是实例级)
		// 测试用户是否具备某一个行为. 调用 Subject 的 isPermitted() 方法。
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("----> You may use a lightsaber ring.  Use it wisely.");
		} else {
			log.info("Sorry, lightsaber rings are for schwartz masters only.");
		}

		// 一个(非常强大)实例级权限:
		// 测试用户是否具备某一个行为.
		if (currentUser.isPermitted("user:delete:zhangsan")) {
			log.info("----> You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  "
					+ "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}

		// 执行登出. 调用 Subject 的 Logout() 方法.
		System.out.println("---->" + currentUser.isAuthenticated());

		currentUser.logout();

		System.out.println("---->" + currentUser.isAuthenticated());

		System.exit(0);
	}
}
```
1. 获取当前的Subject（Subject：指的当前登陆的用户）
`Subject currentUser = SecurityUtils.getSubject();`
2. 判断当前的用户是否登陆
`currentUser.isAuthenticated();`
3. 如果当前没有用户，创建一个用户
`UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");`
4. 记住登陆状态
 `token.setRememberMe(true);`
5. 执行登陆
`currentUser.login(token);`
6. 没有该用户异常
`UnknownAccountException`
7. 密码不正确异常
`IncorrectCredentialsException`
8.账户锁定异常
`LockedAccountException`
9. 获取当前用户名
`currentUser.getPrincipal()`
10. 验证角色
`currentUser.hasRole("schwartz")`
11. 测试用户具备的行为
`currentUser.isPermitted("lightsaber:weild")`

			
