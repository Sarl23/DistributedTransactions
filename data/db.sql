create user 'user'@'localhost' identified by 'password';
create user 'user'@'%' identified by 'password';
grant all on *.* to 'user'@'localhost' identified by 'password';
grant all on *.* to 'user'@'%' identified by 'password';
flush privileges;
create database person_dist;
create table 'person'
(
  'doc'     varchar(12) not null primary key,
  'type'    varchar(12) not null,
  'name'    varchar(20) not null,
  'surname' varchar(20) not null,
  'birth'   date        not null
);
