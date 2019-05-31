
###请求处理方法返回值类型


##视图和视图解析器
* 请求处理方法执行完成后，最终返回一个odelAndView对象。对于那些返回 String，View 或 ModeMap 等类型的处理方法，Spring MVC 也会在内部将它们装配成一个ModelAndView 对象，它包含了逻辑名和模型对象的视图
* Spring MVC 借助视图解析器（ViewResolver）得到最 终的视图对象（View），最终的视图可以是 JSP ，也可能是Excel、JFreeChart 等各种表现形式的视图
* 对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生产模型数据的工作上，从而实现 MVC 的充分解耦

##视图
* 视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。
* 为了实现视图模型和具体实现技术的解耦，Spring 在org.springframework.web.servlet 包中定义了一个高度抽象的 View


* 视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题

##常用的视图实现类


##视图解析器

* SpringMVC 为逻辑视图名的解析提供了不同的策略，可以在 Spring WEB 上下文中配置一种或多种解析策略，并指定他们之间的先后顺序。每一种映射策略对应一个具体的视图解析器实现类。
* 视图解析器的作用比较单一：将逻辑视图解析为一个具体的视图对象。


##常用的视图解析器实现类



* 程序员可以选择一种视图解析器或混用多种视图解析器
* 每个视图解析器都实现了 Ordered 接口并开放出一个 order 属性，`可以通过 order 属性指定解析器的优先顺序，order 越小优先级越高。`
* SpringMVC 会按视图解析器顺序的优先顺序对逻辑视图名进行解析，直到解析成功并返回视图对象，否则将抛出 ServletException 异常

##最常见的视图解析器`InternalResourceViewResolver`
* JSP 是最常见的视图技术，可以使用InternalResourceViewResolver 作为视图解析器：
![微信截图_20180910092604.jpg](https://upload-images.jianshu.io/upload_images/13055171-aec9b190a1da0a97.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 若项目中使用了 JSTL，则 SpringMVC 会自动把视图由InternalResourceView 转为 JstlView

#### 国际化配置
* 若使用 JSTL 的 fmt 标签则需要在 SpringMVC 的配置文件中配置国际化资源文件

* 若希望直接响应通过 SpringMVC 渲染的页面，可以使用 mvc:view-controller 标签实现


####Excel 视图
* 若希望使用 • Excel 展示数据列表，仅需要扩展SpringMVC 提供的 `AbstractExcelView` 或AbstractJExcel View 即可。实现buildExcelDocument() 方法，在方法中使用模型数据对象构建 Excel 文档就可以了。
* `AbstractExcelView` 基于 `POI API`，而AbstractJExcelView 是基于 JExcelAPI 的。
*` 视图对象需要配置 IOC 容器中的一个 Bean，使用BeanNameViewResolver 作为视图解析器即可`
* 若希望直接在浏览器中直接下载 Excel 文档，则可以设置响应头 `Content-Disposition` 的值为
`attachment;filename=xxx.xls`
