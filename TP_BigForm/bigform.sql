drop database if exists bigform;

CREATE database if not exists bigform;

use bigform;

create table employee (
	id INT primary key auto_increment,
    name varchar(30),
    age int,
    is_female boolean,
    address varchar(250),
    qualification varchar(5),
    bloodtype varchar(4),
    phone varchar(10),
    start_date varchar(10),
    photoPath varchar(250)
);
