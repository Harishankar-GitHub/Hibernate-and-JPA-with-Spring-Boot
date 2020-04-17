--create table person
--(
--	id integer not null,
--	name varchar(255) not null,
--	location varchar(255),
--	birth_date timestamp,
--	primary key(id)
--);
-- Commenting create table script because, we have used Entity class.
-- This is directly initialized by Schema Update of Hibernate which is triggered by Spring Boot AutoConfiguration

insert into person (id, name, location, birth_date) values (10001, 'Harish', 'Chennai', sysdate());
insert into person (id, name, location, birth_date) values (10002, 'Jack', 'New York', sysdate());
insert into person (id, name, location, birth_date) values (10003, 'Jill', 'Canada', sysdate());
insert into person (id, name, location, birth_date) values (10004, 'Mark', 'UK', sysdate());
insert into person (id, name, location, birth_date) values (10005, 'Achu', 'Bangalore', sysdate());