create table if not exists manluck."user"
(
    id        integer     not null,
    username  varchar(20) not null,
    password  varchar(16) not null,
    usermail  varchar(30) not null,
    userphone varchar(14),
    country   varchar(15),
    city      varchar(20),
    company   varchar(20),
    position  varchar(15),
    constraint user_pk
        primary key (id)
);

alter table manluck."user"
    owner to postgres;

create unique index if not exists user_id_uindex
    on manluck."user" (id);

create unique index if not exists user_usermail_uindex
    on manluck."user" (usermail);

