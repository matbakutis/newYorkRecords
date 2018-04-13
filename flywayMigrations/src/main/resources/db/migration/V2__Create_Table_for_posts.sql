CREATE TABLE posts (
    ID serial,
    title varchar(100) not null,
    content text not null,
    userid int not null,
    username varchar(100) not null
);