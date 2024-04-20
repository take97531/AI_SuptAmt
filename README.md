1. Mysql DB server download
Mysql DB server 로컬pc에 다운로드

2. Mysql DB server setting
$ mysql -u[id] -p[pw]
$ create database PLAN_INFO;
$ create user 'aiproj'@'localhost' identified by 'a123456789';
$ grant all privileges on *.* to 'aiproj'@'localhost';

3. Create tables for AI project server running
-- 판매정보 테이블 생성
create table if not exists SALE_INFO (
	SALE_ID varchar(50) not null primary key,
	SUBSCRIPTION_ID varchar(50),
	SALE_DATETIME datetime not null,
	DEVICE_CODE varchar(100),
	DEVICE_NUMBER varchar(100),
	DEVICE_PRICE long,
	SUPPORT_AMOUNT long,
	SALE_AMOUNT long,
	CONTRACT_START_DATETIME datetime,
	CONTRACT_END_DATETIME datetime,
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

-- 고객정보 테이블 생성
create table if not exists SUBSCRIPTION_INFO (
	SUBSCRIPTION_ID varchar(50) not null primary key,
	MARKET_CODE varchar(10),
	SUBSCRIPTION_DATETIME datetime not null,
	SUBSCRIPTION_PHONE varchar(100),
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

-- 계약정보 테이블 생성
create table if not exists CONTRACT_INFO (
	CONTRACT_ID varchar(50) not null primary key,
	SUBSCRIPTION_ID varchar(50),
	DEVICE_CODE varchar(100),
	DEVICE_NUMBER varchar(100),
	PLAN_CODE varchar(100),
	CONTRACT_DATETIME datetime,
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

-- 기기정보 테이블 생성
create table if not exists DEVICE_INFO (
	DEVICE_CODE varchar(100) not null primary key,
	DEVICE_NAME varchar(200),
	DEVICE_PRICE long,
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

-- 기기재고정보 테이블 생성
create table if not exists DEVICE_INVENTORY (
	DEVICE_CODE varchar(100) not null,
	DEVICE_NUMBER varchar(100),
	DEVICE_USAGE varchar(10),
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime,
	primary key(DEVICE_CODE, DEVICE_NUMBER)
);

-- 기기보조금정책정보 테이블 생성
create table if not exists DEVICE_SUBSIDY_POLICY (
	DEVICE_SUBSIDY_POLICY_ID varchar(50) not null primary key,
	MARKET_CODE varchar(10),
	START_DATETIME datetime not null,
	END_DATETIME datetime not null,
	DISCOUNT_TYPE varchar(10),
	DEVICE_CODE varchar(100),
	DEVICE_AMOUNT double,
	SUPPORT_AMOUNT double,
	PLAN_CODE varchar(50),
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

-- 요금제정보 테이블 생성
create table if not exists PLAN_INFO (
	PLAN_CODE varchar(50) not null primary key,
	PLAN_NAME varchar(200),
	CREATED_BY varchar(100),
	CREATE_PROGRAM varchar(100),
	CREATE_DATETIME datetime,
	UPDATED_BY varchar(100),
	UPDATE_PROGRAM varchar(100),
	UPDATE_DATETIME datetime
);

4. Create initial data for server logic running
-- 초기 고객정보 생성 (010-1111-2222)
insert into SUBSCRIPTION_INFO(SUBSCRIPTION_ID, MARKET_CODE, SUBSCRIPTION_DATETIME, SUBSCRIPTION_PHONE, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values(replace(uuid(), '-', ''), 'LGT', now(), '01011112222', 'auth', 'patch', now(), 'auth', 'patch', now());

-- 초기 기기정보 생성
insert into DEVICE_INFO(device_code, device_name, device_price, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S911N','갤럭시 S23 256G',1500000, 'auth', 'patch', now(), 'auth', 'patch', now());

insert into DEVICE_INFO(device_code, device_name, device_price, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S916N','갤럭시 S23 512G',1700000, 'auth', 'patch', now(), 'auth', 'patch', now());

-- 초기 요금제정보 생성
insert into PLAN_INFO (PLAN_CODE, PLAN_NAME, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('LZP0001', '5G simple', 'auth', 'patch', now(), 'auth', 'patch', now());

insert into PLAN_INFO (PLAN_CODE, PLAN_NAME, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('LZP0002', 'LTE simple', 'auth', 'patch', now(), 'auth', 'patch', now());

-- 초기 기기재고정보 생성
insert into DEVICE_INVENTORY(DEVICE_CODE, DEVICE_NUMBER, DEVICE_USAGE, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S911N', lpad('1', 20, '0'), 'N', 'auth', 'patch', now(), 'auth', 'patch', now());

insert into DEVICE_INVENTORY(DEVICE_CODE, DEVICE_NUMBER, DEVICE_USAGE, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S911N', lpad('2', 20, '0'), 'N', 'auth', 'patch', now(), 'auth', 'patch', now());

insert into DEVICE_INVENTORY(DEVICE_CODE, DEVICE_NUMBER, DEVICE_USAGE, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S916N', lpad('1', 20, '0'), 'N', 'auth', 'patch', now(), 'auth', 'patch', now());

insert into DEVICE_INVENTORY(DEVICE_CODE, DEVICE_NUMBER, DEVICE_USAGE, CREATED_BY, CREATE_PROGRAM, CREATE_DATETIME, UPDATED_BY, UPDATE_PROGRAM, UPDATE_DATETIME)
values('SM-S916N', lpad('2', 20, '0'), 'N', 'auth', 'patch', now(), 'auth', 'patch', now());

5. Run Server
Spring 서버 기동

6. Enter Server
브라우저에서 "localhost:8080/main.html" 접속
