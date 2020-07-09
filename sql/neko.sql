CREATE DATABASE neko;

use neko;
CREATE TABLE users(
	phoneNumber	Varchar(50)	Not	NULL ,	
    username Varchar(50)	not	NULL,	
    password Varchar(50)	not	NULL,	
    avatar Varchar(50)	,
    sex	Varchar(50)	,
    sign Varchar(50) ,
    lastAddress	Varchar(50)	,	
	PRIMARY KEY(phoneNumber)
);

insert users values("15273299601","han","111",null,null,null,null);
insert users values("222","chen","222",null,null,null,null);

create table following(
     userPhoneNumber	Varchar(50)	not null,	
     followingPhoneNumber	Varchar(50)	not null,	
     PRIMARY KEY(userPhoneNumber,followingPhoneNumber),
	 Foreign key (userPhoneNumber) references users(phoneNumber),
     Foreign key (followingPhoneNumber) references users(phoneNumber)
);
insert following values("15273299601","222");

create table moment(
momentId	int	not null AUTO_INCREMENT,
phoneNumber	Varchar(50)	not null,	
text	Varchar(400),	
originality	Varchar(50)	not null,
powerTpye	Int 	not null,
PRIMARY KEY(momentId),
Foreign key (phoneNumber) references users(phoneNumber)
);

create table favour(
     momentId	int	not null,
     phoneNumber	Varchar(50)	not null,
	 PRIMARY KEY(momentId,phoneNumber),
	 Foreign key (momentId) references moment(momentId),
     Foreign key (phoneNumber) references users(phoneNumber)
);

create table powerUsers(
momentId	int	not null,
phoneNumber	Varchar(50)	not null,
PRIMARY KEY(momentId,phoneNumber),
	  Foreign key (momentId) references moment(momentId),
     Foreign key (phoneNumber) references users(phoneNumber)
);


create table originalityMoment(
momentId	int	not null,
address	Varchar(50) ,
PRIMARY KEY(momentId),
Foreign key (momentId) references moment(momentId)
);


create table forkMoment(
momentId	int	not null,
forkForm int  not null,
PRIMARY KEY(momentId),
Foreign key (momentId) references moment(momentId),
Foreign key (forkForm) references originalityMoment(momentId)
);



create table momentPicture(
pictureId	int	not null AUTO_INCREMENT,
url	varChar(50) not null,		
momentId int  not null,
PRIMARY KEY(pictureId),
Foreign key (momentId) references originalityMoment(momentId)
);



create table label(
labelId	int	not null AUTO_INCREMENT,
description	varChar(50) not null,		
PRIMARY KEY(labelId)
);



create table labelInclude(
momentId int  not null,
labelId	int	not null,	
PRIMARY KEY(momentId,labelId),
Foreign key (momentId) references originalityMoment(momentId),
Foreign key (labelId) references label(labelId)
);


create table session(
sessionId int  not null AUTO_INCREMENT,
more	varChar(50) not null,	
createTimestamp	timestamp	not null,	
PRIMARY KEY(sessionId)
);


create table sessionJoin(
sessionId int  not null,
phoneNumber	varChar(50) not null,	
PRIMARY KEY(sessionId,phoneNumber),
Foreign key (sessionId) references session(sessionId),
Foreign key (phoneNumber) references users(phoneNumber)
);


create table sessionMessage(
sessionId int  not null,
phoneNumber	varChar(50) not null,	
text	varChar(800) not null,	
time	timestamp	not null,	
PRIMARY KEY(sessionId,phoneNumber),
Foreign key (sessionId) references session(sessionId),
Foreign key (phoneNumber) references users(phoneNumber)
);


create table sessionPicture(
sessionId int  not null,
phoneNumber	varChar(50) not null,	
url	varChar(50) not null,	
time	timestamp	not null,	
PRIMARY KEY(sessionId,phoneNumber),
Foreign key (sessionId) references session(sessionId),
Foreign key (phoneNumber) references users(phoneNumber)
);

create table comments(
	commentId	int	Not	NULL AUTO_INCREMENT,	
    phoneNumber Varchar(50)	not	NULL,	
    momentId int   not	NULL,	
    replyCommentId Varchar(50)	not null,
    text varchar(100) not null,
	PRIMARY KEY(commentId),
	Foreign key (momentId) references moment(momentId),
    Foreign key (phoneNumber) references users(phoneNumber)

);
