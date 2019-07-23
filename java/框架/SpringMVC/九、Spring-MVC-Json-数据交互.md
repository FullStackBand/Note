#Json数据交互
* `json`数据格式在接口调用中、`html`页面中较常用，`json`格式比较简单，解析还比较方便。
* 比如：`webservice`接口，传输`json数`据
##Spring MVC 进行json交互
1. 请求json、输出json要求请求的是json，所以在前端页面中需要将请求的内容转成json，不太方便
2. 请求key/value、输出json，比较常用。
![微信截图_20180910092604.jpg](https://upload-images.jianshu.io/upload_images/13055171-4c892247c8657f27.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 环境准备
####1.引入支持包 ： 
* Spring MVC 默认用 Map平JacksonHttpMessageConIverter 对 json 数据进行转换 ，需要加入 Jackson包 

> Jackson-core-asl
> Jackson-mapper-asl

####2.配置转换器
```
<mvc:annotation-driven/>
```


