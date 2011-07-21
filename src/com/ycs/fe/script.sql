CREATE TABLE test(name varchar(20), bday timestamp);
create table test2(name varchar(20), bday date);
CREATE   TABLE  PROGRAMDETAILS(PROGNAME VARCHAR(20),PROGDESC VARCHAR(30), issuername varchar(20),COUNTRY_ISSUE VARCHAR(30), STATUS VARCHAR(30));

INSERT INTO TEST VALUES('sam','2010-12-12 00:00:00.000000')                        ;
INSERT INTO TEST2 VALUES('sam','2010-12-12')                                       ;
INSERT INTO TEST2 VALUES('sam sam','2011-02-14')                                   ;
INSERT INTO  PROGRAMDETAILS(PROGNAME, PROGDESC, ISSUERNAME, COUNTRY_ISSUE) VALUES
('NewProg', 'NwProg desc', NULL, 'SINGAPORE'),
('NewProg', 'NwProg desc', NULL, 'SINGAPORE'),
('NewProg', 'NwProg desc', NULL, 'SINGAPORE'),
('NewProg', 'NwProg desc', NULL, 'SINGAPORE'),
('NewCard', 'Some desc', NULL, 'AUSTRALIA'),
('TRACARD', 'Travel Card Program', NULL, 'INDIA'),
('NewCard', 'Some desc', NULL, 'AUSTRALIA'),
('LOYCARD', 'Loyalty Card Program', NULL, 'PHILIPPINES');



drop alias if exists TO_CHAR; 
create alias TO_CHAR as $$ 
import java.text.SimpleDateFormat;
import java.util.Date;
@CODE
  String toChar(String date, String pattern) throws Exception { 
	pattern = pattern.replaceAll("YY","yy");
	pattern = pattern.replaceAll("DD","dd");
	pattern = pattern.replaceAll("HH24|hh24","HH");
	pattern = pattern.replaceAll("HH?!24|hh?!24","KK");
	pattern = pattern.replaceAll("MON|mon","MMM");
	pattern = pattern.replaceAll("MM|mm","MM");
	pattern = pattern.replaceAll("MI|mi","mm");
	pattern = pattern.replaceAll("SS|ss","ss");
	pattern = pattern.replaceAll("AM|PM","aa");
	System.out.println(pattern);
	SimpleDateFormat sm = new SimpleDateFormat(pattern);
	java.util.Date dt; 
		if(date.length() > 10)dt = java.sql.Timestamp.valueOf(date);
		else
		 dt = java.sql.Date.valueOf(date);
		return sm.format(dt); 
 }
$$;


create table user_role_map(userid varchar2(20),roleid varchar2(20));
insert into user_role_map values('sam_admin','ADMIN');
insert into user_role_map values('sam_man','MANAGER');
insert into user_role_map values('sam_user','USER');
insert into user_role_map values('sam_man_user','USER');
insert into user_role_map values('sam_man_user','MANAGER');

create table country (country_code varchar2(10), country_name varchar2(30), currency_code varchar2(10),currency varchar2(30));

insert into country (country_code,country_name,currency_code,currency) values ('AFG','Afghanistan','AFA','Afghani');
insert into country (country_code,country_name,currency_code,currency) values ('ARG','Argentina','ARP','Peso');
insert into country (country_code,country_name,currency_code,currency) values ('AUS','Australia','AUD','Australian Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('BGD','Bangladesh','BDT','Taka');
insert into country (country_code,country_name,currency_code,currency) values ('BEL','Belgium','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('BTN','Bhutan','INR','Indian Rupee');
insert into country (country_code,country_name,currency_code,currency) values ('BRA','Brazil','BRR','Brazil');
insert into country (country_code,country_name,currency_code,currency) values ('CAN','Canada','CAD','Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('CHN','China','CNY','Yuan Renminbi');
insert into country (country_code,country_name,currency_code,currency) values ('COL','Colombia','COP','Peso');
insert into country (country_code,country_name,currency_code,currency) values ('CUB','Cuba','CUP','Cuban Peso');
insert into country (country_code,country_name,currency_code,currency) values ('DNK','Denmark','DKK','Danish Krone');
insert into country (country_code,country_name,currency_code,currency) values ('EGY','Egypt','EGP','Egyptian Pound');
insert into country (country_code,country_name,currency_code,currency) values ('FJI','Fiji','FJD','Fijian Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('FRA','France','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('DEU','Germany','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('HKG','Hong Kong','HKD','Hong Kong Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('IND','India','INR','Indian Rupee');
insert into country (country_code,country_name,currency_code,currency) values ('IDN','Indonesia','IDR','Indonesian Rupiah');
insert into country (country_code,country_name,currency_code,currency) values ('IRN','Iran ','IRR','Iranian Rial');
insert into country (country_code,country_name,currency_code,currency) values ('IRQ','Iraq','IQD','Iraqi Dinar');
insert into country (country_code,country_name,currency_code,currency) values ('IRL','Ireland','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('ISR','Israel','ILS','Shekel');
insert into country (country_code,country_name,currency_code,currency) values ('ITA','Italy','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('JPN','Japan','JPY','Yen');
insert into country (country_code,country_name,currency_code,currency) values ('KEN','Kenya','KES','Kenyan Shilling');
insert into country (country_code,country_name,currency_code,currency) values ('KWT','Kuwait','KWD','Kuwaiti Dinar');
insert into country (country_code,country_name,currency_code,currency) values ('MYS','Malaysia','MYR','Ringgit');
insert into country (country_code,country_name,currency_code,currency) values ('MDV','Maldives','MVR','Rufiyaa');
insert into country (country_code,country_name,currency_code,currency) values ('MEX','Mexico','MXP','Peso');
insert into country (country_code,country_name,currency_code,currency) values ('NPL','Nepal','NPR','Nepalese Rupee');
insert into country (country_code,country_name,currency_code,currency) values ('NLD','Netherlands','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('NZL','New Zealand','NZD','New Zealand Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('OMN','Oman','OMR','Sul Rial');
insert into country (country_code,country_name,currency_code,currency) values ('PAK','Pakistan','PKR','Rupee');
insert into country (country_code,country_name,currency_code,currency) values ('PHL','Philippines','PHP','Peso');
insert into country (country_code,country_name,currency_code,currency) values ('RUS','Russian Federation','RUR','Ruble');
insert into country (country_code,country_name,currency_code,currency) values ('SAU','Saudi Arabia','SAR','Riyal');
insert into country (country_code,country_name,currency_code,currency) values ('SGP','Singapore','SGD','Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('ZAF','South Africa','ZAR','Rand');
insert into country (country_code,country_name,currency_code,currency) values ('ESP','Spain','EUR','Euro');
insert into country (country_code,country_name,currency_code,currency) values ('LKA','Sri Lanka','LKR','Rupee');
insert into country (country_code,country_name,currency_code,currency) values ('SWE','Sweden','SEK','Krona');
insert into country (country_code,country_name,currency_code,currency) values ('CHE','Switzerland','CHF','Swiss Franc');
insert into country (country_code,country_name,currency_code,currency) values ('TWN','Taiwan','TWD','Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('THA','Thailand','THB','Baht');
insert into country (country_code,country_name,currency_code,currency) values ('GBR','United Kingdom','GBP','Pound Sterling');
insert into country (country_code,country_name,currency_code,currency) values ('USA','United States','USD','US Dollar');
insert into country (country_code,country_name,currency_code,currency) values ('VNM','Vietnam','VND','Dong');


create table program_setup(prog_code varchar2(10),prog_name varchar2(25),prog_desc varchar2(50),issuer_name varchar2(25), country varchar2(25),status varchar2(16),maker_id varchar2(25),maker_date date);
insert into program_setup (PROG_CODE,PROG_NAME,PROG_DESC,ISSUER_NAME,COUNTRY,STATUS,MAKER_ID,MAKER_DATE) values ('PM001','Loyalty card','loyalty card desc','HDFC','IND','','ram','2011-02-02');
insert into program_setup (PROG_CODE,PROG_NAME,PROG_DESC,ISSUER_NAME,COUNTRY,STATUS,MAKER_ID,MAKER_DATE) values ('PM002','Travel card','Travel card desc','DBS','SGP','','Srini','2011-02-14');
insert into program_setup (PROG_CODE,PROG_NAME,PROG_DESC,ISSUER_NAME,COUNTRY,STATUS,MAKER_ID,MAKER_DATE) values ('PM003','Gift card','Gift card desc','ICICI','IND','','Gokul','2011-02-06');
insert into program_setup (PROG_CODE,PROG_NAME,PROG_DESC,ISSUER_NAME,COUNTRY,STATUS,MAKER_ID,MAKER_DATE) values ('PM004','Salary card','Salary card desc','OCBC','USA','','Ravi','2011-02-22');


create table bin_group_details(bin_group_code varchar2(10),bin_group_name varchar2(25),bin_group_desc varchar2(50),bin_currency varchar2(25),settlement_currency varchar2(25), issuer_name varchar2(25),issue_country varchar2(25),
status varchar2(16),maker_id varchar2(25),maker_date date,bin_range_begin varchar2(6),bin_range_from number(9),bin_range_to number(9),total_num_cards number(10));
insert into BIN_GROUP_DETAILS (BIN_GROUP_NAME ,BIN_GROUP_DESC ,BIN_CURRENCY ,SETTLEMENT_CURRENCY ,issuer_name,ISSUE_COUNTRY ,STATUS ,MAKER_ID ,MAKER_DATE ,bin_range_begin ,BIN_RANGE_FROM ,BIN_RANGE_TO ,TOTAL_NUM_CARDS )values('BINGRP1','BINGRP1 desc','SGD','SGD','DBS BANK','SGP','','ram',sysdate,'111111',000000100,000002000,1900);
insert into BIN_GROUP_DETAILS (BIN_GROUP_NAME ,BIN_GROUP_DESC ,BIN_CURRENCY ,SETTLEMENT_CURRENCY ,issuer_name,ISSUE_COUNTRY ,STATUS ,MAKER_ID ,MAKER_DATE ,bin_range_begin,BIN_RANGE_FROM ,BIN_RANGE_TO ,TOTAL_NUM_CARDS )values('BINGRP2','BINGRP2 desc','INR','INR','HDFC BANK','IND','','Srini',sysdate,'222222',000000100,000000999,899);


create table role_rights_map (role varchar(20), menu_id varchar(20));
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab1menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab1menu2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu4');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu5');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu6');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu7');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab1menu3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu8');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu9');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu10');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab2menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu11');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu12');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu13');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab3menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu14');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu15');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu16');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab4');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab4menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu17');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu18');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu19');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab5');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','tab5menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu20');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu21');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('ADMIN','submenu22');

insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab1menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab1menu2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu4');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu5');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu6');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu7');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab1menu3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu8');

insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab2');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','tab2menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('MANAGER','submenu11');

insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab3');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab3menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','submenu14');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','submenu15');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','submenu16');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab4');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab4menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','submenu17');

insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab5');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','tab5menu1');
insert into ROLE_RIGHTS_MAP (ROLE ,MENU_ID ) values('USER','submenu20');




