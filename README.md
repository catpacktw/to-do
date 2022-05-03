## TO-DO List App Server Site Project
### Project build by JDK11 and integrated with postgresql

#### To run service on local machine:
#### 1. Init database by SQL below:  
    create table task
    (
        id      bigserial
        constraint task_pk
        primary key,
        title   varchar(50)  default '' not null,
        content varchar(500) default '' not null,
        status  smallint default 0 not null,
        weight  int default 0 not null
    );

#### 2. Change datasource properties in application.properties file