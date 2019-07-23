# 1. 简介

1. webservice 即 web 服务 , 他是一种跨语言和跨操作系统平台的远程调用技术.
2. java中共有三种webservice规范
   1. JAX-WS (JAX-RPC) 
      * 通信
      * 数据 : `xml`
   2. JAXM&SAAJ
   3. JAX-RS
      * 通信 : `http`
      * 数据 : `xml` / `json`
3. webservice三要素
   1. soap
   2. wsdl
   3. uddi

# 2. 术语

## 2.1 webservice开发规范

java中共有3中webservice开发规范 , 分别是 JAXM&SAAJ / JAX-WS(JAX-RPC) / JAX-RS

1. JAX-WS (常用)

   JAX-WS (java API For XML-WebService) , JDK1.6自带的版本为JAX-WS2.1 , 其低层支持为JAXB.

   JAX-WS(JSR224)规范的API位于`javax.xml.ws.*`包 , 其中大部分都是注解 , 提供API操作Web服务 (通常在客户端使用的比较多 , 由于客户端可以借助SDK生成 , 因此这个包中的API我们较少会直接用到)

2. JAXM&SAAJ 

   * JAXM ( java API For XML Message ) 主要定义了包含了发送和接收消息所需的API , 相当与 Web 服务的服务端 , 器API位于 `javax.messaging.*`包 , 它是javaee 的可选包 , 因此你需要单独下载

   * SAAJ (SOAP With Attachent API For java , JSR 67) 是于JAXM搭配使用的API , 为构建 SOAP 包和解析SOAP包提供了重要的支持 , 支持附件传送 , 它在服务器端 、客户端都需要使用 . 

     这里还要提到SAAJ规范 , 其API位于 `javax.xml.soap.*`包

     JAXM&SAAJ与JAX-WS都是基于`SOAP`的Web服务 , 相比之下JAXM&SAAJ暴露了SOAP更多的底层细节 , 编码比较麻烦 , 而JAX-WS更加抽象 , 隐藏了更多的细节 , 更加面向对象 , 实现起来你基本上不需要关心SOAP的任何细节 . 如果你想控制更多的细节 , 可以使用JAXM&SAAJ.

3. JAX-RS (常用)

   JAX-RS 是 java 针对 REST( RepresentationState Transfer)风格制定的一套Web服务规范 , 由于推出的比较晚 , 该规范(JSR331 , 目前JAX-RS的版本为1.0) 并未随JDK1.6一起发行 , 你需要到CP上单独下载JAX-RS规范的接口 , 其API位于`javax.ws.rs.*`包.

## 2.2 SOAP 协议

1. SOAP 及简单对象访问协议 ( Simple Object Access Protocol ),它是用于交换XML编码信息的轻量级协议.
2. SOAP作为一个基于XML语言的协议用于传输数据.
3. SOAP = HTTP+XML
4. SOAP是基于HTTP的
5. SOAP的组成如下
   1. Envelope - 必须的部分. 以XML的根元素出现
   2. Headers - 可选的.
   3. Body - 必须的 . 在body部分 , 包含要执行的服务器方法 . 和发送到服务器的数据 . 

## 2.3 wsdl说明书

1. 通过wsdl说明书 , 可以描述webservice服务端对外发布的服务
2. wsdl说明书是基于XML文件的 , 通过XML语言描述整个服务
3. 在wsdl说明中, 描述了:
   1. 对外发布的服务名称(类)
   2. 接口方法名称(方法)
   3. 接口参数(方法参数)
   4. 服务放回的数据类型(方法返回值)

## 2.4 UDDI

Web服务提供商又如何将自己开发的Web服务公布到英特网上 , 这就需要使用到UDDI了 

UDDI是一个跨产业 , 跨平台的开放性架构 , 可以帮助Web服务提供商在互联网上发布Web服务信息 . 

UDDI是一种目录服务 , 企业可以通过UDDI来注册和搜索Web服务.

简单来说 , UDDI就是一个目录 , 只不过这个目录存放的是一些关于Web服务的信息而已 .

并且UDDI通过SOAP进行通讯 , 构建于.Net之上 .

UDDI 即 ( Universal Description , Discovery and Integration) , 也就是通用的描述 , 发现以及整合.

# 3. 应用场景

 WebService可以适用于应用程序继承,软件重用,跨防火墙通信等需求. 不同的业务要求不同

简单来说 , 如果一个功能 , 需要被多个系统使用可以使用WebService开发一个服务端接口 , 供不同的客户端应用 . 主要应用在企业内部系统之间的接口调用,面向公网的WebService服务

# 4. 优缺点

## 4.1 优点

1. 异构平台互通性
2. 更广发的软件服用
3. 成本低 \ 可读性强 \ 应用范围广
4. 迅捷的软件发行方式
5. 可以传对象

## 4.2 缺点

1. 效率低	
   * 由于soap是基于xml传输, 本身使用xml传输会传输一些无关内容从而影响效率, 随着soap协议的完善, soap协议增加了许多内容, 这样就导致了使用soap去完成简单的数据传输而携带的信息较多影响效率;
   * WebService作为web跨平台访问的标准技术, 很多公司都限定要求使用WebService, 但如果是简单的接口可以直接使用http传输自定义数据格式, 开发更加便捷

# 5.面向服务的架构SOA

SOA (Service Oriented Architecture) 面向服务架构是一种思想 , 它将应用程序的不同功能单元通过中立的契约 (独立于硬件平台 / 操作平台 / 编程语言) 联系起来 , 使得各种形式的功能单元更好的集成 . 

目前来说 WebService 是SOA的一种较好的实现方.

WebService 采用的是 HTTP 作为传送协议, SOAP作为传输消息的格式 . 但WebService并不是完全符合SOA的概念 , 因为SOAP协议是 WebService 的特有协议, 并未符合SOA的传输协议透明化的要求. SOAP是一种应用协议, 早期应用于RPC的实现, 传输协议可以依赖于HTTP / SMTP等.