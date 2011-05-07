create schema rahn;
create sequence DriverSEQ;
create table rahn.Driver (id bigint not null, firstname varchar(255), name varchar(255) not null, primary key (id));
create table rahn.Car (id varchar(255) not null, type varchar(255) not null, driver_id bigint not null, primary key (id));
alter table rahn.Car add constraint FK107B45ADEE2FE foreign key (driver_id) references rahn.Driver;
insert into rahn.Driver (firstname, name, id) values ('Martin', 'Rahn', next value for DriverSEQ);