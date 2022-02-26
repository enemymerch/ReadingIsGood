create sequence hibernate_sequence start with 1 increment by 1;
create table book (id bigint not null, creation_date timestamp not null, update_date timestamp, author varchar(255) not null, name varchar(255) not null, price double not null, stock bigint not null, primary key (id));
create table customer (id bigint not null, creation_date timestamp not null, update_date timestamp, first_name varchar(255) not null, last_name varchar(255) not null, password varchar(255) not null, role integer, username varchar(255) not null, primary key (id));
create table orderx (id bigint not null, creation_date timestamp not null, update_date timestamp, quantity bigint not null, total_price double, book_id bigint not null, customer_id bigint not null, primary key (id));
alter table book add constraint UKgfm7ylfx4k177aes0pabaxl0l unique (name, author);
alter table customer add constraint UK_irnrrncatp2fvw52vp45j7rlw unique (username);
alter table orderx add constraint FK9jwqjak724h13t2bhcbneakl5 foreign key (book_id) references book;
alter table orderx add constraint FK5119gn7m1isxxkn8vs2i8wmuv foreign key (customer_id) references customer;


insert into CUSTOMER (ID, CREATION_DATE, UPDATE_DATE, FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME)
VALUES (1000, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 'Jhon', 'Carter', 'jhonny', 0, 'jhonny');

insert into CUSTOMER (ID, CREATION_DATE, UPDATE_DATE, FIRST_NAME, LAST_NAME, PASSWORD, ROLE, USERNAME)
VALUES (1001, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 'Mümin Can', 'Yılmaz', 'mcan', 0, 'mcan');

insert into book (ID, CREATION_DATE, UPDATE_DATE, AUTHOR, name, price, stock)
VALUES (2000, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 'JRR Tolkien', 'Fellowship of the Ring', 100.50, 120);

insert into book (ID, CREATION_DATE, UPDATE_DATE, AUTHOR, name, price, stock)
VALUES (2001, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 'JRR Tolkien', 'The Two Towers', 100.50, 120);

insert into book (ID, CREATION_DATE, UPDATE_DATE, AUTHOR, name, price, stock)
VALUES (2002, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 'JRR Tolkien', 'Return of the King', 100.50, 120);

insert into orderx (id, creation_date, update_date, quantity, book_id, customer_id)
VALUES (101, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 3, 2000, 1001);

insert into orderx (id, creation_date, update_date, quantity, book_id, customer_id)
VALUES (102, {ts '2022-02-25 18:47:52.69'}, {ts '2022-02-25 18:47:52.69'}, 3, 2000, 1000);