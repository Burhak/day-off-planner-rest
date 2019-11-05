create table "holiday" (
    "id" uuid not null,
    "date" timestamp not null,
    "name" varchar(255) not null,
    primary key ("id")
);