create table "leave_request" (
    "id" uuid not null,
    "from_date" timestamp not null,
    "status" varchar(255) not null,
    "timestamp" timestamp not null,
    "to_date" timestamp not null,
    "type_id" uuid not null,
    "user_id" uuid not null,
    primary key ("id")
);

create table "leave_request_approval" (
    "id" uuid not null,
    "approved" boolean,
    "approver_id" uuid not null,
    "leave_request_id" uuid not null,
    primary key ("id")
);

create table "approver" (
    "user_id" uuid not null,
    "approver_id" uuid not null
);

alter table "leave_request"
    add constraint "FKg5ed9hl50ti9oeahsy9rb0sl4"
    foreign key ("type_id")
    references "leave_type";

alter table "leave_request"
    add constraint "FKaun20o5mf01biwv8p499kv728"
    foreign key ("user_id")
    references "employee";

alter table "leave_request_approval"
    add constraint "FKahu7m8w0bylsndvlr0n4widbi"
    foreign key ("approver_id")
    references "employee";

alter table "leave_request_approval"
    add constraint "FKlyjpnqp31gbbbd6jcmywmj1di"
    foreign key ("leave_request_id")
    references "leave_request";

alter table "approver"
    add constraint "FKafomsu91nat7rxownf50p38u"
    foreign key ("approver_id")
    references "employee";

alter table "approver"
    add constraint "FK8w237k9b8a6bo5ubsxufvowcl"
    foreign key ("user_id")
    references "employee";
