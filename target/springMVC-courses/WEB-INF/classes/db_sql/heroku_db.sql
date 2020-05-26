drop schema if exists manluck;

drop table if exists "user" cascade;
drop table if exists "role" cascade;
drop table if exists "user_role" cascade;
drop table if exists "project_files" cascade;
drop table if exists "team_roles" cascade;
drop table if exists "team_user" cascade;
drop table if exists "project" cascade;
drop table if exists "city" cascade;
drop table if exists "country" cascade;

create schema if not exists manluck;

create sequence hibernate_sequence;

create table if not exists "user"
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

create table if not exists role
(
    id   serial      not null
        constraint role_pk
            primary key,
    name varchar(45) not null
        constraint role_name_uk
            unique
);


create table if not exists country
(
    country_id serial not null
        constraint country_pkey
            primary key,
    country    varchar
);

create table if not exists city
(
    city_id    serial  not null
        constraint city_city_id_key
            unique,
    city       varchar,
    country_id integer not null
        constraint one_county_can_have_many_cities_
            references country
            on update restrict on delete restrict,
    constraint city_pkey
        primary key (city_id, country_id)
);


create table project
(
    project_id    serial not null
        constraint project_pkey
            primary key,
    project_name  varchar,
    project_tasks varchar
);


create table if not exists team_user
(
    team_user_id serial  not null
        constraint team_user_team_user_id_key
            unique,
    user_id      integer not null
        constraint user_can_be_at_many_teams
            references "user"
            on update restrict on delete restrict,
    project_id   integer not null
        constraint project_has_structured_team
            references project
            on update restrict on delete restrict,
    constraint team_user_pkey
        primary key (team_user_id, user_id, project_id)
);


create table if not exists project_files
(
    project_files_id   serial not null
        constraint project_files_pkey
            primary key,
    file_path          varchar,
    project_project_id integer
        constraint fkfapm3pfyvoqkynh0v6a3qnywu
            references project
);


