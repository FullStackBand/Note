* 在页面上，如果要实现对某些文本、按钮等的控制，例如需要有什么角色或者权限才可以看见这个按钮，利用shiro自带的shiro标签能很容易就实现
# 一 . 引入标签
`<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro">`
#二 . shiro 标签
1. shiro:authenticated （表示已认证通过，但不包括remember me登录的）
2. shiro:guest （表示是游客身份，没有登录）
3. shiro:hasAnyRoles（表示拥有这些角色中其中一个）
4. shiro:hasPermission（表示拥有某一权限）
5. shiro:hashRole （表示拥有某一角色）
6. shiro:lacksPermission （表示不拥有某一角色）
7. shiro:lacksRole （表示不拥有某一角色）
8. shiro:notAuthenticated （表示没有通过验证）
9. shiro:principal （表示用户的身份）
10. shiro:user （表示已登录）
##1.shiro:authenticated （表示已认证通过，但不包括remember me登录的）
```
<shiro:authenticated>
    <label>用户身份验证已通过 </label>
</shiro:authenticated>
 ```
> 说明：只有已通过用户认证，但不是通过记住我（remember me）浏览才会看到标签内的内容

##2.shiro:guest （表示是游客身份，没有登录）
```
<shiro:guest>
    <label>您当前是游客，</label><a href="/login.jsp" >请登录</a>
</shiro:guest>
```
> 说明：只有是没有登录过，以游客的身份浏览才会看到标签内的内容

##3.shiro:hasAnyRoles（表示拥有这些角色中其中一个）
```
<shiro:hasAnyRoles name="admin,user">
    <label>这是拥有admin或者是user角色的用户</label>
</shiro:hasAnyRoles>
```
> 说明：只有成功登录后，且具有admin或者user角色的用户才会看到标签内的内容；name属性中可以填写多个角色名称，以逗号（,）分隔

##4.shiro:hasPermission（表示拥有某一权限）
```
<shiro:hasPermission name="admin:add">
    <label>这个用户拥有admin:add的权限</label>
</shiro:hasPermission>
```
> 说明：只有成功登录后，且具有admin:add权限的用户才可以看到标签内的内容，name属性中只能填写一个权限的名称

##5.shiro:hashRole （表示拥有某一角色）
```
<shiro:hasRole name="admin">
    <label>这个用户拥有的角色是admin</label>
</shiro:hasRole>
```
> 说明：只有成功登录后，且具有admin角色的用户才可以看到标签内的内容，name属性中只能填写一个角色的名称

##6.shiro:lacksPermission （表示不拥有某一权限）
```
<shiro:lacksPermission name="admin:delete">
    <label>这个用户不拥有admin:delete的权限</label>
</shiro:lacksPermission>
```
> 说明：只有成功登录后，且不具有admin:delete权限的用户才可以看到标签内的内容，name属性中只能填写一个权限的名称
##7.shiro:lacksRole （表示不拥有某一角色）
```
<shiro:lacksRole name="admin">
    <label>这个用户不拥有admin的角色</label>
</shiro:lacksRole>
```
> 说明：只有成功登录后，且不具有admin角色的用户才可以看到标签内的内容，name属性中只能填写一个角色的名称
##8.shiro:notAuthenticated （表示没有通过验证）
```
<shiro:notAuthenticated>
    <label>用户身份验证没有通过（包括通过记住我（remember me）登录的） </label>
</shiro:notAuthenticated>
```
> 说明：只有没有通过验证的才可以看到标签内的内容，包括通过记住我（remember me）登录的
##9.shiro:principal （表示用户的身份）
* 取值取的是你登录的时候，在Realm 实现类中的new SimpleAuthenticationInfo(第一个参数,....) 放的第一个参数：
```
....
return new SimpleAuthenticationInfo(user,user.getPswd(), getName());
```
######1）如果第一个放的是username或者是一个值 ，那么就可以直接用。
```
<!--取到username-->
<shiro: principal/>
```
######2）如果第一个参数放的是对象，比如放User 对象。那么如果要取其中某一个值，可以通过property属性来指定。
```
<!--需要指定property-->
<shiro:principal property="username"/>
```
##10.shiro:user （表示已登录）
```
<shiro:user>
    <label>欢迎[<shiro:principal/>]，</label><a href="/logout.jsp">退出</a>
</shiro:user>
```
说明：只有已经登录（包含通过记住我（remember me）登录的）的用户才可以看到标签内的内容；一般和标签shiro:principal一起用，来做显示用户的名称


-----------------------------------------------------------------------------

> 注意：
shiro的jsp标签可以嵌套使用，可以根据业务的具体场景进行使用。例如一个按钮需要排除不是admin或user角色的用户才可以显示，可以像如下这样实现：
```
<shiro:lacksRole name="admin">
    <shiro:lacksRole name="user"> 
        <label>这个用户不拥有admin或user的角色</label>
    </shiro:lacksRole>
</shiro:lacksRole>
```
