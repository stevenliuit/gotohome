CREATE OR REPLACE PACKAGE TYPES AS
   TYPE RQ_REF_CURSOR IS REF CURSOR;
   -- procedure getData(	V_TEMP OUT TYPES.RQ_REF_CURSOR);
END;


CREATE OR REPLACE PROCEDURE RQ_TEST_CUR
(
	V_TEMP OUT TYPES.RQ_REF_CURSOR,
	PID IN VARCHAR
)
AS
BEGIN
	OPEN V_TEMP FOR SELECT NAME FROM TEST WHERE ID = PID;
END RQ_TEST_CUR;


-- Create table
create table TEST
(
  ID   VARCHAR2(10) not null,
  NAME VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table TEST
  add constraint TEST_PK unique (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


instr(’oracle traning’,’ra’,1,2)
.SUBSTR(string,start,count) 

if not v_b then

end if;
-------------------------
表类型变量table
语法如下：
    type 表类型定义的名称 is table of 类型 index by binary_integer;

declare
	type t_rd is record(id number,name varchar2(20));
	type t_tb is table of varchar2(20) index by binary_integer;  --不支持 number
	type t_tb2 is table of t_rd index by binary_integer;
	
	v_tb t_tb;
	v_tb2 t_tb2;
begin
	v_tb(100) := 'hello';
	v_tb(-1) := 'world';
	
	v_tb2(100).id := 1;
	v_tb2(100).name := 'hello';
	
	dbms_output.put_line(v_tb(100));
	dbms_output.put_line(v_tb(-1));
	--dbms_output.put_line(v_tb(1));    不能用没赋值
	dbms_output.put_line(v_tb2(100).id);
	dbms_output.put_line(v_tb2(100).name);
end;
/

-------------------------------------------------------------
--员工表  
create table t_employee(  
id number(10) primary key,  
name varchar2(20),  
age number(3),  
salary number(10),  
deptid number(10)  
);  
 
--部门表  
create table t_dept(  
id number(10) primary key,  
name varchar2(20)  
);  
  
--存储过程 package  
create or replace package mypackage AS TYPE mycursor IS REF CURSOR;  
  
procedure EMP_TJ( in_deptid number,  
                  in_age number,  
                  allemps out mypackage.mycursor);  
end mypackage;  
--存储过程package body   
create or replace package body mypackage is  
procedure EMP_TJ(  
             in_deptid number,  
             in_age number,  
             allemps out mypackage.mycursor)  
IS  
BEGIN  
 open allemps for  
   SELECT id,name,age,salary,deptid FROM T_EMPLOYEE WHERE DEPTID=IN_DEPTID AND AGE>IN_AGE;  
END EMP_TJ;  
end mypackage;  
