drop table if exists cliente;
create table cliente (
    id BIGSERIAL primary key,
    cpf varchar(14),
    nome varchar(255),
    email varchar(255),
    telefone varchar(20),
    logradouro varchar(100),
    bairro varchar(100),
    cidade varchar(50),
    uf char(2),
    cep char(9),
    ativo boolean default true
);


drop table if exists categoria_produto;
drop table if exists categoria;

create table categoria (
id BIGSERIAL primary key,
nome varchar(100),
ativo boolean default true
);


drop table if exists produto;

create table produto (
    id BIGSERIAL primary key,
    nome varchar(100),
    preco decimal(10,2),
    descricao varchar(1000),
    quantidade_estoque integer not null,
    categoria_id bigint not null,
    ativo boolean default true,
    
    foreign key (categoria_id) references categoria (id)
);