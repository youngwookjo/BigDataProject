
/* Drop Triggers */

DROP TRIGGER TRI_nownews_id;
DROP TRIGGER TRI_now_news_id;



/* Drop Tables */

DROP TABLE news_user CASCADE CONSTRAINTS;
DROP TABLE now_news CASCADE CONSTRAINTS;
DROP TABLE stock CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_nownews_id;
DROP SEQUENCE SEQ_now_news_id;




/* Create Sequences */

CREATE SEQUENCE SEQ_nownews_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_now_news_id INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE news_user
(
	user_id varchar2(20) NOT NULL,
	pw number(10) NOT NULL,
	stock_name varchar2(55) NOT NULL,
	user_name varchar2(20) NOT NULL,
	phone_no number(10) NOT NULL,
	PRIMARY KEY (user_id)
);


CREATE TABLE now_news
(
	id number(5) NOT NULL,
	news_title varchar2(300) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE stock
(
	stock_name varchar2(55) NOT NULL,
	price number(7,0) NOT NULL,
	net_updown varchar2(6),
	net number(6) NOT NULL,
	updown varchar2(15) NOT NULL,
	marketcap number(7,0) NOT NULL,
	PRIMARY KEY (stock_name)
);



/* Create Foreign Keys */

ALTER TABLE news_user
	ADD FOREIGN KEY (stock_name)
	REFERENCES stock (stock_name)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_nownews_id BEFORE INSERT ON nownews
FOR EACH ROW
BEGIN
	SELECT SEQ_nownews_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_now_news_id BEFORE INSERT ON now_news
FOR EACH ROW
BEGIN
	SELECT SEQ_now_news_id.nextval
	INTO :new.id
	FROM dual;
END;

/

insert into stock values('LG전자', 23000, '하락', 3000, '-12.32%', 1111111);
insert into stock values('없음', 0, '-', 0, '-', 0);
insert into stock values('LG생활건강', 11000, '상승', 8000, '+9.32%', 13231);
insert into stock values('삼성전자',25000,'상승', 2000,'+10.23%',2432342);
insert into news_user values('yyy2410',241023,'삼성전자','김히렁',01074793691);
insert into news_user values('yqq2234',222223,'LG전자','김영욱',01029293691);
insert into news_user values('abc2864',335423,'삼성전자','조영욱',01074793691);
insert into now_news (news_title) values ('삼성전자가 반도체 산업의 불황에 대비하여 앞서나가고 있는것이 밝혀져 그뒤에 어떻게할것인지 매우중요하다고 볼수있다');
insert into now_news (news_title) values ('LG전자가 경기 시작 냠 대비하여 앞서나가고 있는것이 밝혀져');
insert into now_news (news_title) values ('현대차가 새롭게 선보인 전기차');
insert into now_news (news_title) values ('[특징주] 슈프리마아이디, 상장 첫날 하락세');
insert into now_news (news_title) values ('[특징주] SM엔터, 주주서한 답변 내용 실망에 약세');
insert into now_news (news_title) values ('[공시]파인텍, 中 CSOT와 91억원 규모 OLED 본딩장비 공급 계약 체결');
insert into now_news (news_title) values ('[실적속보] 동원F&B(별도), 2019/2Q 영업이익 69.21억원');
insert into now_news (news_title) values ('[실적속보] 락앤락(연결), 2019/2Q 영업이익 39.12억원');
insert into now_news (news_title) values ('[출발]코스닥 하락..612.01(-1.65%)');
insert into now_news (news_title) values ('[출발]코스닥 상승..723.61(+0.34%)');
insert into now_news (news_title) values ('[출발]환율 상승..1196.0(+7.5원)');
purge recyclebin;
commit;


