--liquibase formatted sql

--changeset v.dmitriev:001_create_cat_table
create sequence cats_seq
  start 1000
  increment by 50;

create table cats (
  id   bigint       not null constraint cats_pk primary key,
  name varchar(255) not null,
  age  int          not null
);
