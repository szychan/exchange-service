create schema if not exists accounts;

create table if not exists accounts.account
(
    id UUID not null,
    balance numeric(19, 2) not null,
    currency varchar(3) not null default 'PLN',
    primary key (id)
);

insert into accounts.account (id, balance)
values (uuid_in('6724da0a-3109-41ae-968c-da434615f709'), 500);