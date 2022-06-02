-- MySQL Script generated by MySQL Workbench
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema CRUD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CRUD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CRUD` DEFAULT CHARACTER SET utf8 ;
USE `CRUD` ;


-- -----------------------------------------------------
-- Table `CRUD`.`Pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Pessoa` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Pessoa` (
  `CPF` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Bairro` VARCHAR(45) NOT NULL,
  `Cidade` VARCHAR(45) NOT NULL,
  `CEP` VARCHAR(45) NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CPF`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Cargo` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Cargo` (
  `idCargo` INT NOT NULL AUTO_INCREMENT,
  `Descricao` VARCHAR(45) NOT NULL,
  `Departamento` VARCHAR(45) NOT NULL,
  `Salario` DOUBLE NOT NULL,
  PRIMARY KEY (`idCargo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(45) NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  `Pessoa_CPF` VARCHAR(45) NOT NULL,
  `Cargo_idCargo` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  FOREIGN KEY (`Pessoa_CPF`) REFERENCES `CRUD`.`Pessoa` (`CPF`),
  FOREIGN KEY (`Cargo_idCargo`) REFERENCES `CRUD`.`Cargo` (`idCargo`))
ENGINE = InnoDB;
  
-- -----------------------------------------------------
-- Table `CRUD`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Pessoa_CPF` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`),
  FOREIGN KEY (`Pessoa_CPF`) REFERENCES `CRUD`.`Pessoa` (`CPF`)
  );


-- -----------------------------------------------------
-- Table `CRUD`.`Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Pedido` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `DataPedido` DATE NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `CRUD`.`Usuario` (`idUsuario`),
  FOREIGN KEY (`Cliente_idCliente`) REFERENCES `CRUD`.`Cliente` (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Categoria` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `Descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Produto` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `PrecoCusto` DOUBLE NOT NULL,
  `PrecoVenda` DOUBLE NOT NULL,
  `QntEstoque` INT NOT NULL,
  `QntEstoqueMin` INT NOT NULL,
  `QntEstoqueMax` INT NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  FOREIGN KEY (`Categoria_idCategoria`) REFERENCES `CRUD`.`Categoria` (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Fornecedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Fornecedor` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `CNPJ` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Pedido_has_Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Pedido_has_Produto` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Pedido_has_Produto` (
  `Quantidade` INT NOT NULL,
  `Pedido_idPedido` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  PRIMARY KEY (`Pedido_idPedido`, `Produto_idProduto`),
  FOREIGN KEY (`Pedido_idPedido`) REFERENCES `CRUD`.`Pedido` (`idPedido`),
  FOREIGN KEY (`Produto_idProduto`) REFERENCES `CRUD`.`Produto` (`idProduto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Produto_has_Fornecedor` 10
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Produto_has_Fornecedor` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Produto_has_Fornecedor` (
  `Produto_idProduto` INT NOT NULL,
  `Fornecedor_idFornecedor` INT NOT NULL,
  PRIMARY KEY (`Produto_idProduto`, `Fornecedor_idFornecedor`),
  FOREIGN KEY (`Produto_idProduto`) REFERENCES `CRUD`.`Produto` (`idProduto`),
  FOREIGN KEY (`Fornecedor_idFornecedor`) REFERENCES `CRUD`.`Fornecedor` (`idFornecedor`))
ENGINE = InnoDB;


