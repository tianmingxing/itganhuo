/* 数据库基础数据，包含系统运行所必需的信息。 */
/*Data for the table `t_template` */
insert  into `t_template`(`id`,`type`,`enName`,`chName`,`content`,`isAvailable`) values (1,1,'registrationSuccessful','用户注册成功通知','亲爱的童鞋：#account#，<br>\r\n感谢你在IT干货技术分享网注册，现在你已经是我们的一员，今后的日子我们一直进步。同时你也可以加入官方群（329232140）。',1),(2,1,'certifiedMail','用户邮箱地址认证通知','亲爱的童鞋：#account#<br>\r\n你刚才提交了邮箱地址认证请求，现在你可以单击这个地址<a href=\"#url#\">#url#</a>完成认证动作。',1);

