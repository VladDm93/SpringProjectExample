create database cat_database encoding 'UTF8';
create user cat with encrypted password 'cat_pass';
grant all privileges on database cat_database to cat;
