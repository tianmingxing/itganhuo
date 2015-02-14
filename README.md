介绍
====
这是[IT干货技术分享网](http://www.itganhuo.cn)整站开源项目，欢迎更多的爱好者参与进来帮助我们一起成长。同时也欢迎你加入私塾在线学习社区群329232140。

网站
====
IT干货技术分享网 http://itganhuo.cn 是为开发人员提供一个工作经验交流和分享的社区平台，它的目标是降低大家准确获取信息的成本，在这个网站上面有别人分享的真正干货，其代码或解决方案拿来就可以使用。没有长篇大论的描述，因为你需要的是拿来即有，而不是在此刻详细了解，或许你之前有了解过，只是现在忘了具体的使用语法。社区的生成离不开你我他的参与，欢迎你也分享自己的工作经验，让更多的人受益。

下载
====
如果你想下载源码可以采用以下两种方式：
* 单击项目主页右边"Download ZIP"下载zip文件（这种方法并不会含有版本信息）；
* 使用Git Bash或Torties Git下载，此时你需要先下载其中一个工具（具体百度之），然后克隆本项目地址https://github.com/xiaoxing598/itganhuo.git （这个地址可以在项目主页右边"clone URL"找到）；

参与
====
如果你想实际参与本项目后续开发或者发现了项目bug需要参与进来，那么可以通过以下途径：
* 默认任何人都有克隆本项目权限，如果你发现了bug并修复了然后想提交到源码上，此时你可以通过在线Requst Pull提交自己的代码，项目负责人看到你的请求之后视情况给予审核；
* 如果你的贡献代码比较多那么此时可考虑申请由贡献者变成开发者，我们会添加你本地的私钥（KEY），后续你就可以自由的通过SSH通道下载和提交代码了。

配置
====
下载项目后要想成功运行起来请遵照下面的步骤：
* 以Maven项目导入到开发工具中（以Spring Tool Suite为例）：
File > Import > (Maven > Existring Maven Projects) > 选择项目所在路径即可。
修改数据库连接信息（如果你想使用我们提供的公有数据库则忽略此项）：
* 从`src/main/resources`中找到`itganhuo.sql`文件用来创建数据库；
* 从`src/main/resources`中找到`jdbc.properties`文件将相应信息修改成你自己的； 
* 注意：目前数据库连接信息是加密的，你可以通过`cn.itganhuo.app.common.TestAes.testEncrypt()`测试类来生成加密后的字符串。

启动
====
本项目采用Maven + Jetty构建而无需Tomcat就可运行（当然如果你将项目放到Tomcat里面运行也是可以的）：
* 直接在项目上运行`mav jetty:run`来运行，但如果你是在IDE工具里那可在项目上右击选择Run As > Maven build... > 在Goals框中输入`clean package jetty:run`并回车即可。

包结构说明
====
为帮助大家熟悉项目下面把主要的包和文件说明一下
```
+app
|  +src/main/java
|  |  +cn.itganhuo.app
|  |  |  +common
|  |  |  +dao
|  |  |  +entity
|  |  |  +exception
|  |  |  +service
|  |  |  +web
|  +src/main/resources
|  |  +cn
|  |  -config-pool.properties
|  |  -itganhuo.sql
|  |  -jdbc.properties
|  |  -log4j.xml
|  |  -log4j2.xml
|  |  -resources.properties
|  |  -root-context.xml
|  |  -servlet-context.xml
|  |  -spring-javamail.xml
|  |  -spring-shiro.xml
|  +src/test/java
|  |  +cn.itganhuo.app
|  +src/test/resources
|  +logs
|  +src
|  +target
|  -LICENSE
|  -pom.xml
|  -README.md
```

Thanks!
IT干货技术分享网:heart:开发团队