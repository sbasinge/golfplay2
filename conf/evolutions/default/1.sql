# --- First database schema

# --- !Ups
create table Address (
    city varchar(128) not null,
    zip varchar(128) not null,
    state int not null,
    line1 varchar(128) not null,
    id bigint not null primary key auto_increment,
    line2 varchar(128) not null,
    facilityId bigint not null
  );
create table Facility (
    name varchar(128) not null,
    id bigint not null primary key auto_increment,
    phone varchar(128) not null
  );
create table Course (
    name varchar(128) not null,
    id bigint not null primary key auto_increment,
    facilityId bigint not null
  );
-- foreign key constraints :
alter table Address add constraint AddressFK1 foreign key (facilityId) references Facility(id);
alter table Course add constraint CourseFK2 foreign key (facilityId) references Facility(id);

# --- !Downs
drop table Address
drop table Facility
drop table Course