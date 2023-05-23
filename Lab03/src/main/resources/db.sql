CREATE TABLE book (
                         id serial primary key not null ,
                         title varchar(100),
                         author varchar(50),
                         year_of_creation int,
                         genre varchar(50),
                         isbn varchar(50),
                         lang varchar(30)
);

CREATE TABLE journal (
                         id serial primary key not null ,
                         title varchar(30),
                         author varchar(30),
                         year_of_creation int,
                         number int,
                         editor_name varchar(50),
                         topic varchar(30)
);