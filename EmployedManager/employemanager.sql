CREATE DATABASE IF NOT EXISTS employemanager;

use employemanager;

CREATE TABLE departement (
	id_dept INT primary key auto_increment,
    name varchar(30) unique not null
);


CREATE TABLE salarie (
	id_sal INT primary key auto_increment,
    firstname varchar(30),
    lastname varchar(30),
    role varchar(15),
	departement_id int,
    CONSTRAINT fk_dept_salari foreign key (departement_id) REFERENCES departement(id_dept)
);

insert into salarie  (firstname, lastname, role, departement_id ) values ( "test", "test","MANAGER" , 1);

drop table if exists salarie;

drop database if exists employemanager;


select * from departement;
select * from salarie;

SELECT d.name, d.id_dept, COUNT(s.id_sal) AS nb_salaries FROM departement d LEFT JOIN salarie s ON d.id_dept = s.departement_id GROUP BY d.name, d.id_dept;

SELECT * FROM salarie FULL JOIN departement ON salarie.departement_id = departement.id_dept ;
