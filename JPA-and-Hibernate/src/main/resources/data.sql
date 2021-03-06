insert into course (id, name, last_updated_date, created_date, is_deleted) values (10001, 'JPA in 50 Steps', sysdate(), sysdate(), false);
insert into course (id, name, last_updated_date, created_date, is_deleted) values (10002, 'Java in 100 Steps', sysdate(), sysdate(), false);
insert into course (id, name, last_updated_date, created_date, is_deleted) values (10003, 'Angular in 50 Steps', sysdate(), sysdate(), false);
--insert into course (id, name, last_updated_date, created_date) values (10004, 'Dummy1', sysdate(), sysdate());
--insert into course (id, name, last_updated_date, created_date) values (10005, 'Dummy2', sysdate(), sysdate());
--insert into course (id, name, last_updated_date, created_date) values (10006, 'Dummy3', sysdate(), sysdate());
--insert into course (id, name, last_updated_date, created_date) values (10007, 'Dummy4', sysdate(), sysdate());
--insert into course (id, name, last_updated_date, created_date) values (10008, 'Dummy5', sysdate(), sysdate());
-- Inserted Dummy data for the sake of pagination.

insert into passport (id, number) values (40001, 'A12345');
insert into passport (id, number) values (40002, 'B12345');
insert into passport (id, number) values (40003, 'C12345');

-- Creating Passport table before Student table because, Passport Id is a Foreign Key in Student Table.

insert into student (id, name, passport_id) values (20001, 'Ranga', 40001);
insert into student (id, name, passport_id) values (20002, 'Adam', 40002);
insert into student (id, name, passport_id) values (20003, 'Jane', 40003);

insert into review (id, rating, description, course_id) values (50001, 'FIVE', 'Great Course', 10001);
insert into review (id, rating, description, course_id) values (50002, 'FOUR', 'Wonderful Course', 10001);
insert into review (id, rating, description, course_id) values (50003, 'FIVE', 'Awesome Course', 10003);

insert into student_course (student_id, course_id) values (20001,10001);
insert into student_course (student_id, course_id) values (20002,10001);
insert into student_course (student_id, course_id) values (20003,10001);
insert into student_course (student_id, course_id) values (20001,10003);