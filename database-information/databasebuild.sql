CREATE table USERS (
username	VARCHAR2 (40) CONSTRAINT users_username_pk PRIMARY KEY, 
password      VARCHAR2 (40));  

CREATE table BUDGETPERCENTAGES (
username	VARCHAR2 (40) CONSTRAINT budgetpercentages_username_pk PRIMARY KEY, 
rent     NUMBER, 
groceries NUMBER,
clothing NUMBER,
transportation NUMBER,
education NUMBER,
entertainment NUMBER,
savings NUMBER,
other NUMBER );

CREATE table INCOMEVALUES (
username	VARCHAR2 (40) CONSTRAINT income_username_pk PRIMARY KEY, 
salary     NUMBER, 
investments NUMBER,
bonus NUMBER,
taxcredits NUMBER ); 

CREATE table EXPENSES (
expenseID NUMBER(4) CONSTRAINT expenses_expenseID_pk PRIMARY KEY,
dateOfPurchase DATE,
category VARCHAR2(40),
description VARCHAR2(80),
value NUMBER ); 

CREATE TABLE EXPENSEUSERS (
expenseID NUMBER(4), 
username	VARCHAR2 (40)); 

ALTER TABLE BUDGETPERCENTAGES
ADD CONSTRAINT budgetpercentages_username_FK FOREIGN KEY (username)
REFERENCES users (username);

ALTER TABLE INCOMEVALUES
ADD CONSTRAINT incomevalues_username_FK FOREIGN KEY (username)
REFERENCES users (username);

ALTER TABLE EXPENSEUSERS
ADD CONSTRAINT expenseusers_username_FK FOREIGN KEY (username)
REFERENCES users (username);

ALTER TABLE EXPENSEUSERS
ADD CONSTRAINT expenseusers_expenseid_FK FOREIGN KEY (expenseid)
REFERENCES expenses (expenseid);

ALTER TABLE EXPENSEUSERS
ADD CONSTRAINT expenseusers_exidusername_PK PRIMARY KEY (expenseid, username);


CREATE SEQUENCE EXPENSE_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 10000
NOCYCLE
CACHE 10;

--This is a temporary workaround, please change the student number to yours before execute.
CREATE OR REPLACE TRIGGER CHANGEDATEFORMAT 
AFTER LOGON ON N01132259.SCHEMA 
BEGIN
  EXECUTE IMMEDIATE
  'ALTER SESSION SET NLS_DATE_FORMAT = ''DD-MON-YYYY''';
END;