create sequence "hibernate_sequence";

create table "employee" (
    "id" uuid not null,
    "admin" boolean not null,
    "deleted" boolean not null,
    "email" varchar(255) not null,
    "first_name" varchar(255) not null,
    "last_name" varchar(255) not null,
    "password" varchar(255) not null,
    "supervisor_id" uuid,
    primary key ("id")
);

create table "access_token" (
    "email" varchar(255) not null,
    "expires_at" timestamp not null,
    "token" varchar(510) not null,
    "token_type" varchar(255),
    primary key ("email")
);

alter table "employee"
    add constraint UK_fopic1oh5oln2khj8eat6ino0 unique ("email");

alter table "employee"
    add constraint "FKlqoo0k4s0vsr5webdu51ylo4w"
    foreign key ("supervisor_id")
    references "employee";
