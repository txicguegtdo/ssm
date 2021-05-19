insert into user(name,password,extend1) values('root','123456','测试用');
insert into task(code,email,pwd,host,toSend,creater) values('测试','zhangpw@asiainfo.com','yx20161018@','mail.asiainfo.com','1046846122@qq.com',1);
insert into rule(taskId,ruleCode,ruleType) values(1,'#{date-1}','MM');
insert into rule(taskId,ruleCode,ruleType) values(1,'#{date-2}','MM-yy');

commit;