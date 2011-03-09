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