-- //Projeto "GameStore" - Prática Profissional

create table PpCliente
(
	codigo int primary key,
	nome varchar(50) not null,
	CPF char(14) not null,
	telefone char(14) not null,
	endereco varchar(200) not null,
	email varchar(100) not null,
	senha varchar(200) not null
)

create table PpCategoria
(
	codigo int primary key,
	nome varchar(25) not null
)

create table PpProduto
(
	codigo int primary key,
	nome varchar(50) not null,
	codCategoria int not null,
	preco money not null,
	estoque int not null,
	imagem varchar(1000) not null,
	constraint fkCodCat foreign key (codCategoria) references PpCategoria(codigo) 
)

create table PpFuncionario
(
	codigo int primary key,
	nome varchar(50) not null,
	CPF char(14) not null,
	telefone char(14) not null,
	endereco varchar(200) not null,
	email varchar(100) not null,
	salario money not null
)

create table PpVenda
(
    codigo int primary key,
	codProd int not null,
	codCli int not null,
	quantidade int not null,
	constraint fkCodProd foreign key(codProd) references PpProduto(codigo),
	constraint fkCodCli foreign key(codCli) references PpCliente(codigo)
)