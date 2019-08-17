create table tiket(
    id int auto_increment,
    amount int,
    category varchar(50),
    constraint pk_tiket_id primary key (id)
);

create table category(
    id varchar(50),
    name varchar(50),
    constraint pk_category_id primary key(id)
);

create table produk(
    id int auto_increment,
    name varchar(50),
    category_id varchar(50),
    stock int,
    price bigint,
    constraint pk_produk_id primary key(id),
	constraint fk_produk_category_id foreign key(category_id) references
	category(id)
);
