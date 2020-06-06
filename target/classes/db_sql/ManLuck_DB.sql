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


alter table if exists "team_user"
    drop constraint if exists "user_can_be_at_many_teams";
alter table if exists "city"
    drop constraint if exists "one_county_can_have_many_cities_";
alter table if exists "project_files"
    drop constraint if exists "one_project_can_has_many_files";
alter table if exists "team_user"
    drop constraint if exists "project_has_structured_team";
alter table if exists "team_user"
    drop constraint if exists "user_has_one_role_in_on_team";

drop table if exists "user" cascade;
drop table if exists "role" cascade;
drop table if exists "user_role" cascade;
drop table if exists "project_files" cascade;
drop table if exists "team_roles" cascade;
drop table if exists "team_user" cascade;
drop table if exists "project" cascade;
drop table if exists "city" cascade;
drop table if exists "country" cascade;

create table if not exists manluck."user"
(
    id              serial      not null
        constraint user_pk
            primary key
        constraint user_id_uindex
            unique,
    username        varchar(20) not null,
    password        varchar     not null,
    user_img        varchar,
    usermail        varchar(30) not null
        constraint ukqkmfglbkrwo5c7712d5tefp8f
            unique
        constraint user_usermail_uindex
            unique,
    userphone       varchar(14),
    country         varchar(15),
    city            varchar(20),
    company         varchar(20),
    position        varchar(30),
    roles           varchar,
    active          boolean,
    user_tasks_json varchar
);

alter table manluck."user"
    owner to postgres;

create table if not exists manluck.role
(
    id   serial      not null
        constraint role_pk
            primary key,
    name varchar(45) not null
        constraint role_name_uk
            unique
);

alter table manluck.role
    owner to postgres;

create table if not exists manluck.country
(
    country_id serial not null
        constraint country_pkey
            primary key,
    country    varchar
);

alter table manluck.country
    owner to postgres;

create table if not exists manluck.city
(
    city_id    serial  not null
        constraint city_city_id_key
            unique,
    city       varchar,
    country_id integer not null
        constraint one_county_can_have_many_cities_
            references manluck.country
            on update restrict on delete restrict,
    constraint city_pkey
        primary key (city_id, country_id)
);

alter table manluck.city
    owner to postgres;

create table if not exists manluck.project
(
    project_id    serial not null
        constraint project_pkey
            primary key,
    project_name  varchar,
    project_tasks varchar
);

alter table manluck.project
    owner to postgres;

create table if not exists manluck.team_user
(
    team_user_id serial  not null
        constraint team_user_team_user_id_key
            unique,
    user_id      integer not null
        constraint user_can_be_at_many_teams
            references manluck."user"
            on update restrict on delete restrict,
    project_id   integer not null
        constraint project_has_structured_team
            references manluck.project
            on update restrict on delete restrict,
    constraint team_user_pkey
        primary key (team_user_id, user_id, project_id)
);

alter table manluck.team_user
    owner to postgres;

create table if not exists manluck.team_roles
(
    team_role_id serial not null
        constraint team_roles_pkey
            primary key,
    team_role    varchar
);

alter table manluck.team_roles
    owner to postgres;

create table if not exists manluck.project_files
(
    project_files_id   serial not null
        constraint project_files_pkey
            primary key,
    file_path          varchar,
    project_project_id integer
        constraint fkfapm3pfyvoqkynh0v6a3qnywu
            references manluck.project
);

alter table manluck.project_files
    owner to postgres;



/* create foreign keys */

-- alter table "role" add constraint "one_user_must_have_one_role_user_" foreign key ("user_id") references "user" ("user_id") on update restrict on delete restrict;

alter table "team_user"
    add constraint "user_can_be_at_many_teams" foreign key ("user_id") references "user" ("id") on update restrict on delete restrict;

alter table "city"
    add constraint "one_county_can_have_many_cities_" foreign key ("country_id") references "country" ("country_id") on update restrict on delete restrict;

-- alter table "user" add constraint "one_user_can_have_one_country_and_one_city_" foreign key ("city_id","country_id") references "city" ("city_id","country_id") on update restrict on delete restrict;

alter table "project_files"
    add constraint "one_project_can_has_many_files" foreign key ("project_id") references "project" ("project_id") on update restrict on delete restrict;

alter table "team_user"

    add constraint "project_has_structured_team" foreign key ("project_id") references "project" ("project_id") on update restrict on delete restrict;

-- alter table "team_user"
--     add constraint "user_has_one_role_in_on_team" foreign key ("team_role_id") references "team_roles" ("team_role_id") on update restrict on delete restrict;

--

insert into "role"("id", "name")
values (1, 'ROLE_USER');

INSERT INTO "user" (id, username, password, user_img, usermail, userphone, country, city, company, position,
                    roles, active, user_tasks_json)
VALUES (2, 'Vadim Volin', 'vadim12345', 'resources/manluck_data/user_img/ehorvitovt.png', 'vadimvolin@mail.com',
        '0999999999', 'USA', 'Palo-Alto', 'Amazon', 'Software Developer', 'ROLE_USER', true, '');
INSERT INTO "user" (id, username, password, user_img, usermail, userphone, country, city, company, position,
                    roles, active, user_tasks_json)
VALUES (1, 'Vadym Volin', 'vadim12345', 'resources/manluck_data/user_img/vadymvolin.png', 'vadim@gmail.com',
        '+38088888878', 'France', 'San-Francisco', 'Amazon', 'Software Developer', 'ROLE_USER', true, '');
INSERT INTO "user" (id, username, password, user_img, usermail, userphone, country, city, company, position,
                    roles, active, user_tasks_json)
VALUES (3, 'Oleh Horbenko', 'oleh12345', 'resources/manluck_data/user_img/olehhorbenko.png', 'oleh@mail.com',
        '0999999999', 'USA', 'Palo-Alto', 'Amazon', 'Software Developer', 'ROLE_USER', false, '');
INSERT INTO "user" (id, username, password, user_img, usermail, userphone, country, city, company, position,
                    roles, active, user_tasks_json)
VALUES (4, 'Alexander Lake', 'alexander12345', 'resources/manluck_data/user_img/alexanderlake.png',
        'alexander@mail.com', '0999999999', 'USA', 'Palo-Alto', 'Google', 'Developer', 'ROLE_NOT', true, '');


select *
from "user" u;
