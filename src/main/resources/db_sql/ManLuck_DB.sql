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

create table if not exists "user"
(
    "id"              serial      not null,
    "username"        varchar(20) not null,
    "password"        varchar     not null,
    "user_img"        varchar,
    "usermail"        varchar(30) not null,
    "userphone"       varchar(14),
    "country"         varchar(15),
    "city"            varchar(20),
    "company"         varchar(20),
    "position"        varchar(30),
    "roles"           varchar,
    "active"          bool,
    "user_tasks_json" varchar,
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


create table "country"
(
    "country_id" serial not null unique,
    "country"    varchar,
    primary key ("country_id")
) without oids;


create table "city"
(
    "city_id"    serial  not null unique,
    "city"       varchar,
    "country_id" integer not null,
    primary key ("city_id", "country_id")
) without oids;


create table "project"
(
    "project_id"   serial not null unique,
    "project_name" varchar,
    primary key ("project_id")
) without oids;


create table "project_files"
(
    "project_files_id" serial  not null unique,
    "file_path"        varchar,
    "project_id"       integer not null,
    primary key ("project_files_id")
) without oids;


create table "team_user"
(
    "team_user_id" serial  not null unique,
    "user_id"      integer not null,
    "team_role_id" integer not null,
    "project_id"   integer not null,
    primary key ("team_user_id", "user_id", "team_role_id", "project_id")
) without oids;


create table "team_roles"
(
    "team_role_id" serial not null unique,
    "team_role"    varchar,
    primary key ("team_role_id")
) without oids;

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

alter table "team_user"
    add constraint "user_has_one_role_in_on_team" foreign key ("team_role_id") references "team_roles" ("team_role_id") on update restrict on delete restrict;

--

insert into "role"("id", "name")
values (1, 'ROLE_USER');

INSERT INTO "user" (id, username, password, user_img, usermail, userphone, country, city, company, position,
                    roles, active, user_tasks_json)
VALUES (2, 'Vadim Volin', 'vadim12345', 'resources/manluck_data/user_img/vadimvolin.png', 'vadimvolin@mail.com',
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
