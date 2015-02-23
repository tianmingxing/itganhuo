/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.37-0ubuntu0.12.04.1 : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '作者主键',
  `title` varchar(500) NOT NULL COMMENT '文章标题',
  `content` text NOT NULL COMMENT '文章内容',
  `ymd` varchar(50) NOT NULL COMMENT '文章发布时间之年月日',
  `hms` varchar(50) NOT NULL COMMENT '文章发布时间之时分秒',
  `updateDate` varchar(50) DEFAULT NULL COMMENT '文章最后修改时间',
  `praiseNum` int(11) DEFAULT '0' COMMENT '文章被点赞次数',
  `trampleNum` int(11) DEFAULT '0' COMMENT '文章被点踩次数',
  `visitorNum` int(11) DEFAULT '0' COMMENT '文章被浏览次数（不去重复ip访问次数）',
  `answerNum` int(11) DEFAULT '0' COMMENT '文章主评论数量（不统计回复数量）',
  PRIMARY KEY (`id`),
  KEY `ymd` (`ymd`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存用户发表的文章';

/*Data for the table `t_article` */

insert  into `t_article`(`id`,`userId`,`title`,`content`,`ymd`,`hms`,`updateDate`,`praiseNum`,`trampleNum`,`visitorNum`,`answerNum`) values (1,1,'领先的 Web 技术教程 - 全部免费','在 w3school，你可以找到你所需要的所有的网站建设教程。\r\n从基础的 HTML 到 CSS，乃至进阶的XML、SQL、JS、PHP 和 ASP.NET。\r\n从左侧的菜单选择你需要的教程！\r\n完整的网站技术参考手册\r\n完整的网站技术参考手册\r\n我们的参考手册涵盖了网站技术的方方面面。\r\n其中包括W3C的标准技术：HTML、CSS、XML 。以及其他的技术，诸如JavaScript、PHP、SQL等等。\r\n在线实例测试工具\r\n在线实例测试工具\r\n在w3school，我们提供上千个实例。\r\n通过使用我们的在线编辑器，你可以编辑这些例子，并对代码进行实验。','2014-1-2','12:12:34','2015-1-1 12:21:21',56,45,35,35);

/*Table structure for table `t_article_label` */

DROP TABLE IF EXISTS `t_article_label`;

CREATE TABLE `t_article_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL COMMENT '文章主键',
  `labelId` int(11) NOT NULL COMMENT '标签主键',
  `userId` int(11) NOT NULL COMMENT '作者主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='保存文章和标签之间的关系';

/*Data for the table `t_article_label` */

insert  into `t_article_label`(`id`,`articleId`,`labelId`,`userId`) values (3,1,1,1);

/*Table structure for table `t_article_line` */

DROP TABLE IF EXISTS `t_article_line`;

CREATE TABLE `t_article_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '问题补充人主键',
  `articleId` int(11) NOT NULL COMMENT '被补充的文章主键',
  `content` text NOT NULL COMMENT '补充内容',
  `postDate` varchar(50) NOT NULL COMMENT '补充问题时间',
  `praiseNum` int(11) DEFAULT '0' COMMENT '问题被点赞同次数',
  `trampleNum` int(11) DEFAULT '0' COMMENT '问题被点踩次数',
  `isPass` int(1) DEFAULT '1' COMMENT '是否通过，1待审核2审核通过3审核不通过',
  `describe` varchar(500) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存文章补充说明的内容，这个数据是由非本文章作者添加的。';

/*Data for the table `t_article_line` */

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL COMMENT '操作类型，1文章评价2文章点赞3文章点踩',
  `articleId` int(11) DEFAULT NULL COMMENT '用来记录文章的主键，前提是对评论点赞或点踩，根据type来判断数据类型',
  `userId` int(11) NOT NULL COMMENT '评论发布人主键',
  `content` text NOT NULL COMMENT '评论内容',
  `postDate` varchar(50) NOT NULL COMMENT '评论时间',
  `praise` int(11) DEFAULT '0' COMMENT '记录针对本评论所赞数量，仅针对评论时有效',
  `trample` int(11) DEFAULT '0' COMMENT '记录针对本评论所踩数量，仅针对评论时有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存对文章评论的数据，注意赞和踩也是评价的一种。';

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`type`,`articleId`,`userId`,`content`,`postDate`,`praise`,`trample`) values (1,1,1,1,'楼上的你想干嘛','2015-1-23 12:34:32',3,4);

/*Table structure for table `t_dictionaries` */

DROP TABLE IF EXISTS `t_dictionaries`;

CREATE TABLE `t_dictionaries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '所属组主键',
  `attrName` varchar(500) NOT NULL DEFAULT 'null' COMMENT '属性名称，如果pid为0时则表示组名称',
  `attrValue` varchar(500) NOT NULL DEFAULT 'null' COMMENT '属性值',
  `description` varchar(500) DEFAULT NULL COMMENT '对属性组的描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数字字典，用来把一些高度重复的属性保存到数据库。';

/*Data for the table `t_dictionaries` */

insert  into `t_dictionaries`(`id`,`pid`,`attrName`,`attrValue`,`description`,`sort`) values (1,0,'sex','性别','属性组',0),(2,1,'male','男','属性值',1),(3,1,'female','女','属性值',2),(4,1,'secrecy','保密','属性值',3);

/*Table structure for table `t_label` */

DROP TABLE IF EXISTS `t_label`;

CREATE TABLE `t_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '标签添加人主键',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `description` text COMMENT '标签介绍',
  `postDate` varchar(50) NOT NULL COMMENT '标签添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存标签信息，标签是由用户发表文章时添加的。';

/*Data for the table `t_label` */

insert  into `t_label`(`id`,`userId`,`name`,`description`,`postDate`) values (1,1,'jQuery','Jquery是继prototype之后又一个优秀的Javascript库。它是轻量级的js库 ，它兼容CSS3，还兼容各种浏览器（IE 6.0+, FF 1.5+, Safari 2.0+, Opera 9.0+），jQuery2.0及后续版本将不再支持IE6/7/8浏览器。','2015-1-11 22:03:30');

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '本表主键作外键',
  `text` varchar(255) NOT NULL COMMENT '菜单名称',
  `state` varchar(50) NOT NULL DEFAULT 'open' COMMENT '菜单节点状态（''''open'''' 或 ''''closed''''，默认：''''open''''）',
  `checked` tinyint(1) DEFAULT NULL COMMENT '该节点是否被选中',
  `iconcls` varchar(255) DEFAULT NULL COMMENT '菜单图标路径',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单链接到页面的地址',
  `sort` int(11) DEFAULT NULL COMMENT '菜单自定义排序字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='保存运营后台菜单信息';

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`pid`,`text`,`state`,`checked`,`iconcls`,`url`,`sort`) values (1,0,'系统管理','open',NULL,NULL,'/admin/sys',0),(2,1,'参数配置','open',NULL,NULL,NULL,1);

/*Table structure for table `t_permissions` */

DROP TABLE IF EXISTS `t_permissions`;

CREATE TABLE `t_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL COMMENT '权限标示',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  `isAvailable` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效 0无效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_permissions` */

insert  into `t_permissions`(`id`,`permission`,`description`,`isAvailable`) values (1,'view:*','数据查看权限',1);

/*Table structure for table `t_reply` */

DROP TABLE IF EXISTS `t_reply`;

CREATE TABLE `t_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '对应本表的主键',
  `userId` int(11) NOT NULL COMMENT '回复人主键',
  `commentId` int(11) NOT NULL COMMENT '被回复评论主键',
  `content` text NOT NULL COMMENT '回复内容',
  `postDate` varchar(50) NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存对评论的回复信息';

/*Data for the table `t_reply` */

insert  into `t_reply`(`id`,`parentId`,`userId`,`commentId`,`content`,`postDate`) values (1,0,1,1,'你刚才说的那个问题我找到原因了，谢谢你啊！','2014-12-21 12:23:32');

/*Table structure for table `t_roles` */

DROP TABLE IF EXISTS `t_roles`;

CREATE TABLE `t_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL COMMENT '角色标识',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  `isAvailable` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效 0无效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_roles` */

insert  into `t_roles`(`id`,`role`,`description`,`isAvailable`) values (1,'系统管理员','拥有对系统完整的超级权限',1);

/*Table structure for table `t_template` */

DROP TABLE IF EXISTS `t_template`;

CREATE TABLE `t_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL COMMENT '模块类型（1邮件模板，2短信模板，3站内信模板）',
  `name` varchar(100) NOT NULL COMMENT '模板名称',
  `content` text NOT NULL COMMENT '模板内容，需要替换的变量用##包含起来。',
  `postDate` varchar(50) NOT NULL COMMENT '邮件模板发布时间',
  `isAvailable` int(1) NOT NULL DEFAULT '1' COMMENT '是否启用，0未启用1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_template` */

insert  into `t_template`(`id`,`type`,`name`,`content`,`postDate`,`isAvailable`) values (1,1,'用户注册成功通知','亲爱的童鞋：#account#，<br>\r\n感谢你在IT干货技术分享网注册，现在你已经是我们的一员，今后的日子我们一直进步。同时你也可以加入官方群（329232140）。','2015-2-12 20:48:40',1),(2,1,'用户邮箱地址认证通知','亲爱的童鞋：#account#<br>\r\n你刚才提交了邮箱地址认证请求，现在你可以单击这个地址#url#完成认证动作。','',1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '登录账户',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `salt` varchar(255) NOT NULL COMMENT '密码加盐字段',
  `isLock` int(1) NOT NULL DEFAULT '1' COMMENT '账号是否锁定，默认新注册账号是锁定状态（0不锁定，1锁定）',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1男，2女，3保密）',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `qq` int(11) DEFAULT NULL COMMENT 'qq',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `tel` varchar(50) DEFAULT NULL COMMENT '座机',
  `postDate` varchar(50) NOT NULL COMMENT '账号注册时间',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '账号类型（1会员，999管理员）',
  `lastLoginIp` varchar(50) DEFAULT NULL COMMENT '账号最后登录时的ip地址',
  `lastLoginDate` varchar(50) DEFAULT NULL COMMENT '账号最后一次登录时间',
  `isValidateEmail` int(1) NOT NULL DEFAULT '0' COMMENT '邮箱地址是否认证（0未认证，1已认证）',
  `emailValidateCode` varchar(255) DEFAULT NULL COMMENT '邮箱认证码',
  `emailValidateDate` varchar(50) DEFAULT NULL COMMENT '邮箱认证开始时间，如果认证成功则修改为验证成功时的时间。',
  `credits` double(10,2) DEFAULT '0.00' COMMENT '积分',
  PRIMARY KEY (`id`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='保存用户信息';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`account`,`password`,`salt`,`isLock`,`nickname`,`sex`,`email`,`qq`,`phone`,`tel`,`postDate`,`type`,`lastLoginIp`,`lastLoginDate`,`isValidateEmail`,`emailValidateCode`,`emailValidateDate`,`credits`) values (1,'admin','123...','2342434',1,'管理员',1,'mx.tian@qq.com',504487927,'15111111111','0755-12345678','2014-12-12 12:12:34',1,NULL,NULL,0,NULL,NULL,12.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
