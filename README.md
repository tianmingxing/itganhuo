介绍
====
这是IT干货技术分享网开源项目，我们欢迎更多的人参与进来开发。
项目线上访问地址http://www.itganhuo.cn



下载
====
你可以采用以下三种方式
1、直接下载项目.zip文件（这种方法不会含有版本信息）；
2、采用Git Bash或Torties Git通过下载项目；
3、如果你申请由贡献者成为开发者后，我们会添加你的KEY，届时你可以通过下载项目。



配置
====
将项目以Maven项目方式导入到开发工具中，这里以Spring Tool Suite为例：
File > Import > (Maven > Existring Maven Projects) > 选择项目路径即可

修改数据库连接信息（如果你想使用我们提供的公有数据库则忽略此项）；
1、从`src/main/resources`中找到`sql.sql`文件用来创建数据库； 
2、修改数据库配置文件，从`src/main/resources`中找到`resources.properties`文件将相应信息修改成你自己的； 



启动
====
由于本项目采用Maven+jetty构建，无需tomcat就可以运行项目（如果你将项目放到tomcat里面运行当然也是可以的。），你可以直接在项目上运行`mav jetty:run`来运行，如果你是在IDE工具里那在项目上右击选择Run As > Maven build... > 在Goals框中输入`jetty:run`并回车即可。