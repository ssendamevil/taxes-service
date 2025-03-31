create table users(
    id bigserial primary key,
    username varchar(255) unique not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    middlename varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    created_at date not null default current_date,
    updated_at date not null default current_date
);

create table incomes (
    id bigserial primary key,
    user_id bigint not null,
    type varchar(255),
    amount decimal not null,
    source varchar(255),
    date timestamp,
    constraint fk_incomes_user foreign key (user_id) references users(id) on delete cascade
);

create table properties (
     id bigserial primary key,
     user_id bigint not null,
     type varchar(255) not null,
     price decimal not null,
     description varchar(255),
     date timestamp,
     constraint fk_properties_user foreign key (user_id) references users(id) on delete cascade
);

create table taxes (
    id bigserial primary key,
    user_id bigint not null,
    income_id bigint,
    property_id bigint,
    amount decimal not null,
    description varchar(255) not null,
    type varchar(255) not null,
    date timestamp not null,
    constraint fk_taxes_user foreign key (user_id) references users(id) on delete cascade
);

