CREATE TABLE users (
    ID serial primary key,
    username varchar(100) not null unique,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    admin boolean not null
);