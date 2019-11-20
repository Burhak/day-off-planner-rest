create table "leave_request_message" (
    "id" uuid not null,
    "message" varchar(255) not null,
    "timestamp" timestamp not null,
    "approver_id" uuid not null,
    "leave_request_id" uuid not null,
    primary key ("id")
);

alter table "leave_request_message"
    add constraint "FKhnmamrsrt2yjdf9guw33qvnxg"
    foreign key ("approver_id")
    references "employee";

alter table "leave_request_message"
    add constraint "FK2suci4anqjpbt128kluif1q9o"
    foreign key ("leave_request_id")
    references "leave_request";