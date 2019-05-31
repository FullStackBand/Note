#Spring MVC 支持使用原生的ServletAPI
* 支持如下的类型
 * HttpServletRequest 
 * HttpServletResponse 
 * HttpSession
 * java.security.Principal 
 * Locale InputStream 
 * OutputStream 
 * Reader 
 * Writer
* 只需写入形参，就可以得到当前请求的类型参数
* 代码如下
```
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request,HttpServletResponse response, Writer out) throws IOException {
		System.out.println("testServletAPI, " + request + ", " + response);
		out.write("hello springmvc");
//		return SUCCESS;
	}
```	
