create table if not exists user 
(
	id int primary key auto_increment,
	name varchar(200),
	password varchar(100),
	extend1 varchar(2000),
	flag char default '1'
);
create table if not exists task
(
	id int primary key auto_increment,
	code varchar(200),
	desc varchar(1000),
	email varchar(100),
	host varchar(100),
	pwd varchar(200),
	toSend varchar(2000),
	toCC varchar(2000),
	toBCC varchar(2000),
	jobType int default 0,
	jobTime varchar(100),
	creater int,
	createDate date,
	modifyDate date,
	extend1 varchar(2000),
	flag char default '1'
);
create table if not exists body
(
	id int primary key auto_increment,
	taskId int,
	bodyType varchar(1),--0 正文 1附件
	path varchar(2000),
	name varchar(2000), --附件名称
	modfiyDate date,
	extend1 varchar(1000),
	extend2 varchar(2000),
	flag char default '1'
);
create table if not exists rule
(
	id int primary key auto_increment,
	bodyId int,
	taskId int,
	ruleCode varchar(200),
	ruleType varchar(1000),
	ruleValue varChar(1000),
	extend1 varchar(1000),
	extend2 varchar(2000),
	flag char default '1'
);

commit;