drop table "carryover";
drop table "individual_limit";

create table "carryover" (
    "id" uuid not null,
    "year" int4 not null,
    "hours" int4 not null,
    "user_id" uuid not null,
    "leave_type_id" uuid not null,
    primary key ("id")
);

create table "individual_limit" (
    "id" uuid not null,
    "limit" int4 not null,
    "user_id" uuid not null,
    "leave_type_id" uuid not null,
    primary key ("id")
);

alter table "carryover"
    add constraint "UK2ws246q27o2j7tqhdg2bqtujr" unique ("leave_type_id", "user_id", "year");

alter table "individual_limit"
    add constraint "UKq2f1l7nyry7g77jjk23b6u7wq" unique ("leave_type_id", "user_id");

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