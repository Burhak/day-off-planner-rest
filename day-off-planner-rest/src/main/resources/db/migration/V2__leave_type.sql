create table "leave_type" (
    "id" uuid not null,
    "approval_needed" boolean not null,
    "deleted" boolean not null,
    "name" varchar(255) not null,
    "limit" int4,
    "carryover" int4,
    primary key ("id")
);

create table "individual_limit" (
    "user_id" uuid not null,
    "leave_type_id" uuid not null,
    "limit" int4 not null,
    primary key ("leave_type_id", "user_id")
);

create table "carryover" (
    "user_id" uuid not null,
    "leave_type_id" uuid not null,
    "year" int4 not null,
    "hours" int4 not null,
    primary key ("leave_type_id", "user_id", "year")
);

alter table "leave_type"
    add constraint UK_dxx8ej97lrqn1votgtttfw9b9 unique ("name");

alter table "carryover"
    add constraint "FKsltrs4ijulnngomugbpkx9n9c"
    foreign key ("user_id")
    references "employee";

alter table "carryover"
    add constraint "FKs8w3olu7epyymxkdge8oo2prh"
    foreign key ("leave_type_id")
    references "leave_type";

alter table "individual_limit"
    add constraint "FKgxo3bf5a5sq93koqnrlyi5ta0"
    foreign key ("user_id")
    references "employee";

alter table "individual_limit"
    add constraint "FKcu9f3bvcpmrib3chhtncq9vtd"
    foreign key ("leave_type_id")
    references "leave_type";
