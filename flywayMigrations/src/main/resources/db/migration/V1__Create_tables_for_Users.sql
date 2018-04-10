CREATE TABLE users (
    ID serial,
    username varchar(100) not null,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    admin boolean not null
);