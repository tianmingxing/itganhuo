/*
sqlyog ultimate v11.25 (64 bit)
mysql - 5.6.19 : database - itganhuo
*********************************************************************
*/


/*!40101 set names utf8 */;

/*!40101 set sql_mode=''*/;

/*!40014 set @old_unique_checks=@@unique_checks, unique_checks=0 */;
/*!40014 set @old_foreign_key_checks=@@foreign_key_checks, foreign_key_checks=0 */;
/*!40101 set @old_sql_mode=@@sql_mode, sql_mode='no_auto_value_on_zero' */;
/*!40111 set @old_sql_notes=@@sql_notes, sql_notes=0 */;
create database /*!32312 if not exists*/`itganhuo` /*!40100 default character set utf8 */;

use `itganhuo`;

/*table structure for table `t_article` */

drop table if exists `t_article`;

create table `t_article` (
  `id` int(11) not null auto_increment,
  `user_id` int(11) not null comment '作者主键',
  `title` varchar(500) not null comment '文章标题',
  `content` text not null comment '文章内容',
  `ymd` varchar(50) not null comment '文章发布时间之年月日',
  `hms` varchar(50) not null comment '文章发布时间之时分秒',
  `update_date` varchar(50) default null comment '文章最后修改时间',
  `praise_num` int(11) default '0' comment '文章被点赞次数',
  `trample_num` int(11) default '0' comment '文章被点踩次数',
  `visitorvol_ume` int(11) default null comment '文章被浏览次数（不去重复ip访问次数）',
  `answer_number` int(11) default '0' comment '文章主评论数量（不统计回复数量）',
  primary key (`id`),
  key `user_id` (`user_id`),
  key `ymd` (`ymd`),
  constraint `t_article_ibfk_1` foreign key (`user_id`) references `t_user` (`id`) on delete cascade
) engine=innodb default charset=utf8 comment='保存用户发表的文章';

/*table structure for table `t_article_label` */

drop table if exists `t_article_label`;

create table `t_article_label` (
  `id` int(11) not null auto_increment,
  `article_id` int(11) not null comment '文章主键',
  `label_id` int(11) not null comment '标签主键',
  `user_id` int(11) not null comment '作者主键',
  primary key (`id`),
  key `article_id` (`article_id`),
  key `label_id` (`label_id`),
  key `user_id` (`user_id`),
  constraint `t_article_label_ibfk_1` foreign key (`article_id`) references `t_article` (`id`) on delete cascade,
  constraint `t_article_label_ibfk_2` foreign key (`label_id`) references `t_label` (`id`),
  constraint `t_article_label_ibfk_3` foreign key (`user_id`) references `t_user` (`id`) on delete cascade
) engine=innodb default charset=utf8 comment='保存文章和标签之间的关系';

/*table structure for table `t_article_line` */

drop table if exists `t_article_line`;

create table `t_article_line` (
  `id` int(11) not null auto_increment,
  `user_id` int(11) not null comment '问题补充人主键',
  `article_id` int(11) not null comment '被补充的文章主键',
  `content` text not null comment '补充内容',
  `post_date` varchar(50) not null comment '补充问题时间',
  `praise_num` int(11) default '0' comment '问题被点赞同次数',
  `trample_num` int(11) default '0' comment '问题被点踩次数',
  `ispass` int(1) default '1' comment '是否通过，1待审核2审核通过3审核不通过',
  `describe` varchar(500) default null comment '审核意见',
  primary key (`id`),
  key `user_id` (`user_id`),
  key `article_id` (`article_id`),
  constraint `t_article_line_ibfk_1` foreign key (`user_id`) references `t_user` (`id`),
  constraint `t_article_line_ibfk_2` foreign key (`article_id`) references `t_article` (`id`) on delete cascade
) engine=innodb default charset=utf8 comment='保存文章补充说明的内容，这个数据是由非本文章作者添加的。';

/*table structure for table `t_comment` */

drop table if exists `t_comment`;

create table `t_comment` (
  `id` int(11) not null auto_increment,
  `type` int(1) not null comment '操作类型，1文章评价2文章点赞3文章点踩',
  `article_id` int(11) default null comment '用来记录文章的主键，前提是对评论点赞或点踩，根据type来判断数据类型',
  `user_id` int(11) not null comment '评论发布人主键',
  `content` text not null comment '评论内容',
  `post_date` varchar(50) not null comment '评论时间',
  `praise` int(11) default '0' comment '记录针对本评论所赞数量，仅针对评论时有效',
  `trample` int(11) default '0' comment '记录针对本评论所踩数量，仅针对评论时有效',
  primary key (`id`),
  key `user_id` (`user_id`),
  constraint `t_comment_ibfk_1` foreign key (`user_id`) references `t_user` (`id`) on delete cascade on update cascade
) engine=innodb default charset=utf8 comment='保存对文章评论的数据，注意赞和踩也是评价的一种。';

/*table structure for table `t_dictionaries` */

drop table if exists `t_dictionaries`;

create table `t_dictionaries` (
  `id` int(11) not null auto_increment,
  `pid` int(11) not null default '0' comment '所属组主键',
  `key` varchar(500) not null default 'null' comment '属性名称，如果pid为0时则表示组名称',
  `value` varchar(500) not null default 'null' comment '属性值',
  `description` varchar(500) default null comment '对属性组的描述',
  `sort` int(11) default null comment '排序字段',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='数字字典，用来把一些高度重复的属性保存到数据库。';

/*table structure for table `t_label` */

drop table if exists `t_label`;

create table `t_label` (
  `id` int(11) not null auto_increment,
  `user_id` int(11) not null comment '标签添加人主键',
  `name` varchar(100) not null comment '标签名称',
  `description` text comment '标签介绍',
  `post_date` varchar(50) not null comment '标签添加时间',
  primary key (`id`),
  key `user_id` (`user_id`),
  constraint `t_label_ibfk_1` foreign key (`user_id`) references `t_user` (`id`)
) engine=innodb default charset=utf8 comment='保存标签信息，标签是由用户发表文章时添加的。';

/*table structure for table `t_menu` */

drop table if exists `t_menu`;

create table `t_menu` (
  `id` int(11) not null auto_increment,
  `pid` int(11) not null default '0' comment '本表主键作外键',
  `text` varchar(255) not null comment '菜单名称',
  `state` varchar(50) not null default 'open' comment '菜单节点状态（''''open'''' 或 ''''closed''''，默认：''''open''''）',
  `checked` tinyint(1) default null comment '该节点是否被选中',
  `iconcls` varchar(255) default null comment '菜单图标路径',
  `url` varchar(255) default null comment '菜单链接到页面的地址',
  `sort` int(11) default null comment '菜单自定义排序字段',
  primary key (`id`)
) engine=innodb default charset=utf8 comment='保存运营后台菜单信息';

/*table structure for table `t_permissions` */

drop table if exists `t_permissions`;

create table `t_permissions` (
  `id` int(11) not null auto_increment,
  `permission` varchar(255) not null comment '权限标示',
  `description` varchar(255) not null comment '权限描述',
  `isavailable` int(1) not null default '1' comment '是否有效 0无效,1有效',
  primary key (`id`)
) engine=innodb default charset=utf8;

/*table structure for table `t_reply` */

drop table if exists `t_reply`;

create table `t_reply` (
  `id` int(11) not null auto_increment,
  `parent_id` int(11) not null default '0' comment '对应本表的主键',
  `user_id` int(11) not null comment '回复人主键',
  `comment_id` int(11) not null comment '被回复评论主键',
  `content` text not null comment '回复内容',
  `post_date` varchar(50) not null comment '回复时间',
  primary key (`id`),
  key `user_id` (`user_id`),
  key `comment_id` (`comment_id`),
  constraint `t_reply_ibfk_1` foreign key (`user_id`) references `t_user` (`id`),
  constraint `t_reply_ibfk_2` foreign key (`comment_id`) references `t_comment` (`id`) on delete cascade
) engine=innodb default charset=utf8 comment='保存对评论的回复信息';

/*table structure for table `t_roles` */

drop table if exists `t_roles`;

create table `t_roles` (
  `id` int(11) not null auto_increment,
  `role` varchar(255) not null comment '角色标识',
  `description` varchar(255) not null comment '角色描述',
  `isavailable` int(1) not null default '1' comment '是否有效 0无效,1有效',
  primary key (`id`)
) engine=innodb default charset=utf8;

/*table structure for table `t_template` */

drop table if exists `t_template`;

create table `t_template` (
  `id` int(11) not null auto_increment,
  `type` int(1) not null comment '模块类型（1邮件模板，2短信模板，3站内信模板）',
  `name` varchar(100) not null comment '模板名称',
  `content` text not null comment '模板内容，需要替换的变量用##包含起来。',
  `post_date` varchar(50) not null comment '邮件模板发布时间',
  `isavailable` int(1) not null default '1' comment '是否启用，0未启用1启用',
  primary key (`id`)
) engine=innodb auto_increment=3 default charset=utf8;

/*table structure for table `t_user` */

drop table if exists `t_user`;

create table `t_user` (
  `id` int(11) not null auto_increment,
  `account` varchar(255) not null comment '登录账户',
  `password` varchar(255) not null comment '登录密码',
  `salt` varchar(255) not null comment '密码加盐字段',
  `islock` int(1) not null default '1' comment '账号是否锁定，默认新注册账号是锁定状态（0不锁定，1锁定）',
  `nickname` varchar(255) default null comment '昵称',
  `sex` int(1) default null comment '性别（1男，2女，3保密）',
  `email` varchar(255) default null comment '邮箱地址',
  `qq` int(11) default null comment 'qq',
  `phone` varchar(11) default null comment '手机',
  `tel` varchar(50) default null comment '座机',
  `post_date` varchar(50) not null comment '账号注册时间',
  `type` int(11) not null default '1' comment '账号类型（1会员，999管理员）',
  `last_login_ip` varchar(50) default null comment '账号最后登录时的ip地址',
  `last_login_date` varchar(50) default null comment '账号最后一次登录时间',
  `is_validate_email` int(1) not null default '0' comment '邮箱地址是否认证（0未认证，1已认证）',
  `email_validate_code` varchar(255) default null comment '邮箱认证码',
  `email_validate_date` varchar(50) default null comment '邮箱认证开始时间，如果认证成功则修改为验证成功时的时间。',
  primary key (`id`,`account`)
) engine=innodb auto_increment=11 default charset=utf8 comment='保存用户信息';

/*!40101 set sql_mode=@old_sql_mode */;
/*!40014 set foreign_key_checks=@old_foreign_key_checks */;
/*!40014 set unique_checks=@old_unique_checks */;
/*!40111 set sql_notes=@old_sql_notes */;
