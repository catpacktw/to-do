DROP TABLE IF EXISTS task;
create table task
(
    id      bigserial
        constraint task_pk
            primary key,
    title   varchar(50)  default '' not null,
    content varchar(500) default '' not null,
    status  smallint default 0 not null
);