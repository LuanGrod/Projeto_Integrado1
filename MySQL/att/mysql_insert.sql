alter table encomenda
add column Situacao varchar(20);

create table itensCarrinho(
quantidade int not null,
Produto_idProduto int not null,
foreign key (Produto_idProduto) references Produto(idProduto)
);

CREATE VIEW lastPedido AS
SELECT  max(idPedido)  as maxId
FROM PEDIDO;

INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Administrador Geral", "Administrativo", 3170.00);
INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Gerente", "Administrativo", 2250.00);
INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Vendedor", "Vendas", 1373.00);

INSERT INTO `usuario` (`Login`, `Senha`, `CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`, `Cargo_idCargo`) 
VALUES ("adm", "123", "28351059327", "Luiz Silva", "1197254566", "LuizSilva@gmail.com", "Rua 1", "Bairro 1", "Sao paulo", "11111111", "SP", 1),
 ("marco", "123", "28351152147", "Marco Vieira", "1197412566", "MarcoVieira@gmail.com", "Rua 2", "Bairro 2", "Sao paulo", "22222222", "SP", 2),
 ("vitor", "123", "28351152147", "vitor Vieira", "1132356226", "Vitor@gmail.com", "Rua 2", "Bairro 2", "Sao paulo", "22222222", "SP", 3);

INSERT INTO fornecedor(cnpj, telefone, email, nome) values ( "12312321", "11982736352", "vigas@gmail.com", "Vigas+");

INSERT INTO categoria (descricao) values("Madeira");
INSERT INTO produto(nome, precoCusto, precoVenda, QntEstoque, QntEstoqueMin, Categoria_idCategoria) values ("chapa de madeira", 50, 80, 10, 5, 1);
INSERT INTO produto_has_fornecedor values(3,1);


SET SQL_SAFE_UPDATES = 0;

select * from carrinho;
select * from pedido_has_produto;
select * from pedido;





