-- //Projeto "GameStore" - Prática Profissional

create table PpCliente
(
	codigo int identity primary key,
	nome varchar(50) not null,
	CPF char(14) not null,
	telefone char(14) not null,
	endereco varchar(200) not null,
	email varchar(100) not null
)

create table PpCategoria
(
	codigo int identity primary key,
	nome varchar(25) not null
)

create table PpProduto
(
	codigo int identity primary key,
	nome varchar(50) not null,
	codCategoria int not null,
	preco money not null,
	estoque int not null,
	constraint fkCodCat foreign key (codCategoria) references PpCategoria(codigo) 
)

create table PpFuncionario
(
	codigo int identity primary key,
	nome varchar(50) not null,
	CPF char(14) not null,
	telefone char(14) not null,
	endereco varchar(200) not null,
	email varchar(100) not null,
	salario money not null
)