# --- !Ups
-- table declarations :
create table Address (
    city varchar(128) not null,
    zip varchar(128) not null,
    state int not null,
    line1 varchar(128) not null,
    id bigint not null primary key auto_increment,
    line2 varchar(128) not null
  );
create table Course (
    name varchar(128) not null,
    id bigint not null primary key auto_increment,
    phone varchar(128) not null,
    addressId bigint not null
  );
  
-- foreign key constraints :
alter table Course add constraint CourseFK1 foreign key (addressId) references Address(id);

# --- !Downs
drop table Address
drop table Course