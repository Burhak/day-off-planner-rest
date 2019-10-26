create table "setting" (
    "key" varchar(50) not null,
    "description" varchar(255) not null,
    "max" int4 not null,
    "min" int4 not null,
    "value" int4 not null,
    primary key ("key")
);
