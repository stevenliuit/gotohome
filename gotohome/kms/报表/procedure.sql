create or replace procedure say_hello(v_name varchar2)
as
  v_str varchar2(20) := 'hello ';
begin
  dbms_output.put_line(v_str || v_name);
  dbms_output.put_line(1/0);
exception
  when others then
    dbms_output.put_line('o(0_0)o~');
end;


begin
	say_hello('zhangxue');
  str_hello('',)
--say_hello(v_name=>'zx');
end;


create or replace procedure say_hello(v_name in varchar2,v_content out varchar2)
as
begin
	v_content := 'hello ' || v_name;
end;


----test '1000,1001-2000,2001-3000,3001-4000'
/*
instr(¡¯oracle traning¡¯,¡¯ra¡¯,1,2)
SUBSTR(string,start,count) 
*/





create or replace procedure str_hello	(V_TEMP OUT TYPES.RQ_REF_CURSOR,v_content in varchar2)
as
charnum number:=1; 
tempchar varchar2(20):='';
startnum number:=0;
endnum number:=0;
idx number:=1;
begin
  charnum:= length(v_content) -length(regexp_replace(v_content,',',''))+1 ;
  OPEN V_TEMP FOR
  ( for v_i in 1..charnum loop    
  -- dbms_output.put_line(substr(v_content,idx,instr(v_content,',',1,v_i))); 
  --1¡¢½âÎö
    --dbms_output.put(v_i||' '||idx||' - '||instr(v_content,',',1,v_i)); 
     if  v_i < charnum then
       tempchar:= substr(v_content,idx,instr(v_content,',',1,v_i)-idx);      
    else 
     tempchar:= substr(v_content,instr(v_content,',',1,v_i-1)+1,length(v_content));       
     end if;    
     idx:=instr(v_content,',',1,v_i);
     tempchar := regexp_replace(tempchar,',','');    
     dbms_output.put_line(':'||tempchar);
   --2¡¢
     if(v_i=1) then
     startnum := to_number(tempchar);    
     /*  */ 
    -- select 0 start, startnum end ,10 num from dual;
    elsif(v_i=charnum) then
       endnum := to_number(tempchar);
       /*  */ 
     -- select endnum start, 1000000 end ,12 num from dual;
     else 
        --dbms_output.put_line(startnum);
        startnum := to_number(substr(tempchar,1,instr(tempchar,',',1,1)));
        endnum := to_number(substr(tempchar,instr(tempchar,',',1,1)+1,length(tempchar)));
       -- select startnum start, endnum end ,13 num from dual;
        /*  */ 
     end if;     
    end loop;)
    dbms_output.put_line('1/0');
    end;

begin
  str_hello('1000,1001-2000,2001-3000,3001-4000');
end;

http://blog.sina.com.cn/s/blog_8333cf8f01012tvm.html
http://www.cnblogs.com/hoojo/archive/2011/05/03/2035388.html
http://www.iteye.com/problems/34895

