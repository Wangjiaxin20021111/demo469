create table testpaper(
  testnum varchar(20) primary key,
  testname varchar(20)
);--建立试卷表
 create table QuestionBank(
    num int primary key auto_increment,
    tittle varchar(20),
    contet varchar(300)
  );--建立题库表
--分析：一张试卷，多道题目，一道题目，可以同时被多张试卷采用，因此试卷与题目的关系是多对多
  create table connect(
      testnum varchar(20),
      questionnum int,
     priamry key(testnum,questionnum),--建立联合主键
     foreign key(testnum) references testpaper(testnum),
     foreign key(questionnum) references QuestionBank(num)
    );--建立试卷与题库的中间表
  --分析：一张试卷，可以同时被多个考场使用，每个考场同一时间只能有一张试题，因此试卷与考场的关系是一对多
  create table classroom(--建立考场表
      roomnum varchar(20) primary key,
      testnum varchar(20),
      foreign key(testnum) references testpaper(testnum)--关联试卷主键
      );
