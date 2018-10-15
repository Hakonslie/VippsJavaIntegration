create table orders (
dbid bigserial,
vippsid varchar(15) not null,
amount int not null,
capturedamount int DEFAULT 0,
refundedamount int DEFAULT 0,
status varchar(50) DEFAULT 'INITIATE' not null,
mobilenumber int not null,
transactiontext varchar(100)
);