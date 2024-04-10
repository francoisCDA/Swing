create database if not exists cruddbswing;

use cruddbswing;

create table contact (
	id INT primary key auto_increment,
    name varchar(255),
    number varchar(30)
);


select id from contact;

select name, number from contact where id = 1 ;

select * from contact;