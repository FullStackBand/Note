* 一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理
* 如果返回的字符串中带 `forward:` 或 `redirect`: • 前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和redirect: 当成指示符，其后的字符串作为 URL 来处理
	* redirect:success.jsp：会完成一个到success.jsp 的重定向的操作
	* forward:success.jsp：会完成一个到 success.jsp 的转发操作
```
@RequestMapping(path = "/files/{path}", method = RequestMethod.POST)
public String upload(...) {
    // ...
    return "redirect:files/{path}";
}
```
