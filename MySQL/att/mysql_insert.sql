SET SQL_SAFE_UPDATES = 0;

-- -----------------------------------------------------
-- Table `CRUD`.`cargo`
-- -----------------------------------------------------
INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Administrador Geral", "Administrativo", 3170.00);
INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Gerente", "Administrativo", 2250.00);
INSERT INTO `cargo` (`Descricao`, `Departamento`, `Salario`) VALUES ("Vendedor", "Vendas", 1373.00);

-- -----------------------------------------------------
-- Table `CRUD`.`usuario`
-- -----------------------------------------------------
INSERT INTO `usuario` (`Login`, `Senha`, `CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`, `Cargo_idCargo`) 
VALUES ("adm", "123", "28351059327", "Luiz Silva", "1197254566", "LuizSilva@gmail.com", "Rua 1", "Bairro 1", "Sao paulo", "11111111", "SP", 1),
 ("marco", "123", "28351152147", "Marco Vieira", "1197412566", "MarcoVieira@gmail.com", "Rua 2", "Bairro 2", "Sao paulo", "22222222", "SP", 2),
 ("vitor", "123", "28351152147", "vitor Vieira", "1132356226", "Vitor@gmail.com", "Rua 2", "Bairro 2", "Sao paulo", "22222222", "SP", 3);
select * from usuario;
-- -----------------------------------------------------
-- Table `CRUD`.`fornecedor`
-- -----------------------------------------------------

INSERT INTO `fornecedor` (`CNPJ`, `Telefone`, `Email`, `nome`) VALUES ("42265852000150", "9391431746", "CarvalhoSA@gmail.com", "Hidraulica e cia");
INSERT INTO `fornecedor` (`CNPJ`, `Telefone`, `Email`, `nome`) VALUES ("84130890000146", "9781674954", "MeloComércio@gmail.com", "Telhado do zé");
INSERT INTO `fornecedor` (`CNPJ`, `Telefone`, `Email`, `nome`) VALUES ("15783769000109", "9091401474", "MoraesAssociados@gmail.com", "HRB materiais");
INSERT INTO `fornecedor` (`CNPJ`, `Telefone`, `Email`, `nome`) VALUES ("48709551000155", "9014814613", "FrancoAndFranco@gmail.com", "Sondax");
INSERT INTO `fornecedor` (`CNPJ`, `Telefone`, `Email`, `nome`) VALUES ("13158758155455", "9173618730", "SaraivaMoraes@gmail.com", "Vtec");
select * from fornecedor;
-- delete from fornecedor;
-- ALTER TABLE fornecedor AUTO_INCREMENT = 1;  
-- -----------------------------------------------------
-- Table `CRUD`.`categoria`
-- -----------------------------------------------------

INSERT INTO `categoria` (`Descricao`) VALUES ("Argamassas");
INSERT INTO `categoria` (`Descricao`) VALUES ("Cisternas");
INSERT INTO `categoria` (`Descricao`) VALUES ("Caixas d'Água");
INSERT INTO `categoria` (`Descricao`) VALUES ("Calhas");
INSERT INTO `categoria` (`Descricao`) VALUES ("Cimentos");
INSERT INTO `categoria` (`Descricao`) VALUES ("Madeiras");
INSERT INTO `categoria` (`Descricao`) VALUES ("Telhas");
INSERT INTO `categoria` (`Descricao`) VALUES ("Tijolos");
select * from categoria;
-- delete from categoria;
-- ALTER TABLE categoria AUTO_INCREMENT = 1;  

-- -----------------------------------------------------
-- Table `CRUD`.`produto`
-- -----------------------------------------------------

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Argamassa Interna ACI 20kg", 10.30, 14.90, 50,  1);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`,  `Categoria_idCategoria`) 
VALUES ("Argamassa Quartzolit ACI Interno Cinza 20kg", 15.70, 20, 30,  1);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Argamassa Colante Para Porcelanato Cinza 20kg", 24, 29.50, 40,  1);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Eco Tanque 240L Bombona Casológica", 293.90, 321.30, 10,  2);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Cisterna de Polietileno Subterrânea 5.000L", 4485.90, 4830.90, 8,  2);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Cisterna de Polietileno 3.000L", 3440.30, 3710.30, 6,  2);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Caixa d'água Polietileno 1.000L", 344.30, 370.30, 20,  3);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Caixa d'água Polietileno 500L", 229.90, 270, 15,  3);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Tanque de Polietileno 5.000L", 2599.99, 2910.90, 10,  3);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Calha Moldura Galvanizada C28 3m", 57, 62, 40,  4);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Calha de PVC Branco 5m", 132.60, 150.90, 35,  4);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Cimento CPB-40 Branco Estrutural 15kg", 49, 52.90, 50,  5);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Cimento CP II E 32 Saco de 50kg", 30.80, 35.20, 60,  5);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Cimento Comum Cinza Saco 1kg", 2, 3.20, 130,  5);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Viga Saligna Bruta 5cmx11cmx3m", 35.90, 39.90, 80,  6);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Caibro Bruto Saligna 5cmx5,7cmx3m", 15.90, 18.40, 70,  6);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Caibro Eucalipto Natural Bruto 6cmx6cmx3m", 20, 23.10, 100,  6);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Telha Fibrocimento 2,44mx1,10mx5mm", 53.20, 55.10, 90,  7);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Telha Polipropileno 2,44mx1,10m", 100, 110.20, 70,  7);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Telha de PVC Plan Cerâmica 2,42x0,88m", 120.30, 133.40, 100,  7);

INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Bloco de Concreto Vedação Vazado 9x19x39cm", 2.50, 3, 200,  8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Canaleta de Concreto 19x19x39cm", 5, 6.20, 170,  8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Bloco Cerâmico Vedação 11,5x14x24cm", 1, 1.40, 210,  8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Canaleta Cerâmica 11,5x14x24cm", 2, 2.40, 210,  8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Tijolo Comum Vermelho 8,7x4,3x18,6cm", 0.50, 1, 250,  8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Meio Tijolo Modular 12,5x6,0x12,5cm", 2, 2.40, 210, 8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Tijolo Colonial Vermelho 12,8cmx7,6cmx27,8cm", 3, 3.70, 100, 8);
INSERT INTO `produto` (`nome`, `PrecoCusto`, `PrecoVenda`, `QntEstoque`, `Categoria_idCategoria`) 
VALUES ("Tijolo Refratário 11,5x23x5,1cm", 6.80, 7.40, 180, 8);

select * from produto;

-- delete from produto;
-- ALTER TABLE produto AUTO_INCREMENT = 1; 
-- -----------------------------------------------------
-- Table `CRUD`.`cliente`
-- -----------------------------------------------------
INSERT INTO `cliente` (`CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`) VALUES ("28351059327", "Luan Rodrigues", "980000138", "luan14rodrigues17@gmail.com", "Rua Pedro Vasques", "Vila Madalena", "São Paulo", "37261940", "São Paulo");
INSERT INTO `cliente` (`CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`) VALUES ("03917274631", "Diana Thalita", "958133943", "Diaga4Thalita@gmail.com", "Rua Pietro Costa", "Vila Felix", "São Paulo", "39178335", "São Paulo");
INSERT INTO `cliente` (`CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`) VALUES ("82716352950", "Joaquim Laercio", "999524642", "Joaquin94Laercio@gmail.com", "Rua Marli Melo", "Vila Calebe Nova", "São Paulo", "91731285", "São Paulo");
INSERT INTO `cliente` (`CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`) VALUES ("27310168097", "Kairo Hauck", "904079742", "Kairo31Hauck@gmail.com", "Rua Aero", "Vila Tayden Landrum", "São Paulo", "91732609", "São Paulo");
INSERT INTO `cliente` (`CPF`, `Nome`, `Telefone`, `Email`, `Rua`, `Bairro`, `Cidade`, `CEP`, `Estado`) VALUES ("28414036023", "Mariana Dalton", "938484599", "Mariana93Dalton@gmail.com", "Rua Michaelangelo", "Vila Harley Rosales", "São Paulo", "39178226", "São Paulo");
select *  from cliente;

-- -----------------------------------------------------
-- Table `CRUD`.`encomenda`
-- -----------------------------------------------------
INSERT INTO encomenda (Produto_idProduto, Fornecedor_idFornecedor, quantidade, situacao) values (1, 3, 45, "Em aberto");
INSERT INTO encomenda (Produto_idProduto, Fornecedor_idFornecedor, quantidade, situacao) values (4, 1, 12, "Em aberto");
INSERT INTO encomenda (Produto_idProduto, Fornecedor_idFornecedor, quantidade, situacao) values (5, 1, 11, "Em aberto");

select * from encomenda;