#准备jar
* jsqlparser-0.9.5.jar
* pagehelper-5.1.2.jar
#配置文件
```
<!-- 引入分页插件,必须紧跟在在别名之后 -->
	<plugins>
		<plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
	</plugins>
```
#后台代码
* 启动Mapper的方法为MyBatis Generator 反向生成
####业务层代码
```
	/**查询所有的方法  所以给的 mapper.selectByExample(null); 的参数为null*/
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(null);
	}
```
####Action层代码
```
	/**
	 * 查询用户数据(分页查询)
	 * 
	 * @param没有传入参数pagenum时,给一个默认值>defaultValue="1"<
	 */
	@RequestMapping("users.do")
	public String getUsers(@RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,Model model) {
		//引入PagerHelper分页插件
		//在查询之前调用分页设置(第几页,每页大小)
		PageHelper.startPage(pagenum, 3);
		//startPage必须紧跟查询
		List<Users> users = biz.getAll();
		//使用PageInfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		//他封装了详细的分页信息,包括我们查询的信息,连续显示的页数为5
		PageInfo page = new PageInfo(users,5);
		model.addAttribute("pageInfo", page);
		return "userslist.jsp";
	}
```
**********************************************PageInfo*************************************************************
#关于PageInfo的内容
```
//当前页
private int pageNum;
//每页的数量
private int pageSize;
//当前页的数量
private int size;

//当前页面第一个元素在数据库中的行号
private int startRow;
//当前页面最后一个元素在数据库中的行号
private int endRow;
//总记录数
private long total;
//总页数
private int pages;
//结果集
private List<T> list;


//前一页
private int prePage;
//下一页
private int nextPage;


//是否为第一页
private boolean isFirstPage = false;
//是否为最后一页
private boolean isLastPage = false;
//是否有前一页
private boolean hasPreviousPage = false;
//是否有下一页
private boolean hasNextPage = false;
//导航页码数
private int navigatePages;
//所有导航页号
private int[] navigatepageNums;
//导航条上的第一页
private int navigateFirstPage;
 //导航条上的最后一页
private int navigateLastPage;
```
