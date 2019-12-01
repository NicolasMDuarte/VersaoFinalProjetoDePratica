
select * from PpProduto

select * from PpCliente

delete from PpCliente where codigo = 7

select * from PpCliente where email = 'nouani@gmail.com'

select * from Categoria

select * from PpVenda

create table PpVenda
(
    codigo int identity primary key,
	codProd int not null,
	codCli int not null,
	quantidade int not null,
	constraint fkCodProd foreign key(codProd) references PpProduto(codigo),
	constraint fkCodCli foreign key(codCli) references PpCliente(codigo)
)

select * from PpVenda
drop table PpVenda

update PpProduto set nome = 'Final Fantasy XV: A king''s tale' where codigo = 7
update Categoria set nome = 'Games' where idCat = 1

insert into PpProduto values('Sea of Thieves', 6, 60.00, 34, 'sea of thieves xbox one.png')
insert into Categoria values('Batman')
insert into PpCliente values('Arruda', '123.234.456-45', '23456-3456', 'rua sla', 'arruda@gmail.com', 'arruda')

-- create proc updateEstoque_sp
alter proc updateEstoque_sp
@qtdComprada int = null,
@codProd int = null
as
declare @qtdAtual int = null
set @qtdAtual = (select estoque from PpProduto where codigo = @codProd)
update PpProduto set estoque = @qtdAtual - @qtdComprada where codigo = @codProd

execute updateEstoque_sp 1, 1


