alter table if exists "user"
    drop constraint if exists user_id_uindex cascade;
alter table if exists "user"
    drop constraint if exists user_usermail_uindex cascade;
alter table if exists "role"
    drop constraint if exists role_name_uk cascade;
alter table if exists "user_role"
    drop constraint if exists user_role_pk cascade;
alter table if exists "user_role"
    drop constraint if exists user_role_uk cascade;
alter table if exists "user_role"
    drop constraint if exists user_role_fk1 cascade;
alter table if exists "user_role"
    drop constraint if exists user_role_fk2 cascade;

drop table if exists "user" cascade;

drop table if exists "role" cascade;

drop table if exists "user_role" cascade;

create table if not exists "user"
(
    "id"        serial      not null,
    "username"  varchar(20) not null,
    "password"  varchar     not null,
    "user_img"  varchar,
    "usermail"  varchar(30) not null,
    "userphone" varchar(14),
    "country"   varchar(15),
    "city"      varchar(20),
    "company"   varchar(20),
    "position"  varchar(30),
    "roles"     varchar,
    "active"    bool,
    constraint user_pk
        primary key ("id")
);

alter table "user"
    add constraint user_id_uindex unique ("id");

alter table "user"
    add constraint user_usermail_uindex unique ("usermail");

create table if not exists "role"
(
    "id"   serial      not null,
    "name" varchar(45) not null,
    constraint role_pk
        primary key ("id")
);

--
-- dumping data for table "role"
--

alter table "role"
    add constraint role_name_uk unique ("name");

-- table structure for table "user_role"
--

insert into "role"("id", "name")
values (1, 'ROLE_USER');

create table "user_role"
(
    "id"      int not null,
    "user_id" int not null,
    "role_id" int not null
);
--
alter table user_role
    add constraint user_role_pk primary key (id);

alter table user_role
    add constraint user_role_uk unique (user_id, role_id);

alter table user_role
    add constraint user_role_fk1 foreign key (user_id)
        references "user" (id);

alter table user_role
    add constraint user_role_fk2 foreign key (role_id)
        references role (id);

INSERT INTO manluck."user" (id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active) VALUES (2, 'Vadim Volin', 'vadim12345', 'resources/manluck_data/user_img/vadimvolin.png', 'vadimvolin@mail.com', '0999999999', 'USA', 'Palo-Alto', 'Amazon', 'Software Developer', 'ROLE_USER', true);
INSERT INTO manluck."user" (id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active) VALUES (1, 'Vadym Volin', 'vadim12345', 'resources/manluck_data/user_img/vadymvolin.png', 'vadim@gmail.com', '+38088888878', 'France', 'San-Francisco', 'Amazon', 'Software Developer', 'ROLE_USER', true);
INSERT INTO manluck."user" (id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active) VALUES (3, 'Oleh Horbenko', 'oleh12345', 'resources/manluck_data/user_img/olehhorbenko.png', 'oleh@mail.com', '0999999999', 'USA', 'Palo-Alto', 'Amazon', 'Software Developer', 'ROLE_USER', false);
INSERT INTO manluck."user" (id, username, password, user_img, usermail, userphone, country, city, company, position, roles, active) VALUES (4, 'Alexander Lake', 'alexander12345', 'resources/manluck_data/user_img/alexanderlake.png', 'alexander@mail.com', '0999999999', 'USA', 'Palo-Alto', 'Google', 'Developer', 'ROLE_NOT', true);

insert into user_role(id, user_id, role_id)
values (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1);

select *
from "user" u;
