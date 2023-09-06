create sequence users_SEQ start with 1 increment by 50;
create table users (id bigint not null, password varchar(255), role varchar(255), username varchar(255), primary key (id));
