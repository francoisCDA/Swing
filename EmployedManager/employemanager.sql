CREATE DATABASE IF NOT EXISTS employemanager;

use employemanager;

CREATE TABLE departement (
	id_dept INT primary key auto_increment,
    name varchar(30)
);


CREATE TABLE salarie (
	id_sal INT primary key auto_increment,
    firstname varchar(30),
    lastname varchar(30),
    role varchar(15),
	departement_id int,
    CONSTRAINT fk_dept_salari foreign key (departement_id) REFERENCES departement(id_dept)
);

drop table if exists salarie;

drop database if exists employemanager;
