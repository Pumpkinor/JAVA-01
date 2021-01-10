### 学习笔记
####第1课作业实践
1. 自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后
  自己分析一下对应的字节码，有问题群里讨论。

    [作业1-字节码分析-查看链接](./code/homework/%E4%BD%9C%E4%B8%9A1-%E5%AD%97%E8%8A%82%E7%A0%81%E5%88%86%E6%9E%90.md)

2. 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内
  容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

    [作业2-自定义Classloader-查看链接](./code/homework/MyClassLoader.java)

3. 画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的
  关系。
  ![内存参数图](./code/homework/%E4%BD%9C%E4%B8%9A3-%E5%86%85%E5%AD%98%E5%8F%82%E6%95%B0%E5%85%B3%E7%B3%BB%E5%9B%BE.png)

4. 检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下
  详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
  注意：如果没有线上系统，可以自己 run 一个 web/java 项目。
    [作业4-系统JVM分析-查看链接](./code/homework/%E4%BD%9C%E4%B8%9A4-%E7%B3%BB%E7%BB%9FJVM%E5%88%86%E6%9E%90.md)
  //TODO 去公司分析现有的check项目

