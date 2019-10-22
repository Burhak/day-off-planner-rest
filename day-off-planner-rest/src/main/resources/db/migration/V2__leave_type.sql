create table "leave_type" (
    "id" uuid not null,
    "approval_needed" boolean not null,
    "deleted" boolean not null,
    "limited" boolean not null,
    "name" varchar(255) not null,
    primary key ("id")
);

alter table "leave_type"
    add constraint UK_dxx8ej97lrqn1votgtttfw9b9 unique ("name");