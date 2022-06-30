-- MySQL Script generated by MySQL Workbench
-- Wed Jun 15 12:15:21 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CRUD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CRUD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CRUD` DEFAULT CHARACTER SET utf8 ;
USE `CRUD` ;

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
  `CPF` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(60) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Bairro` VARCHAR(45) NOT NULL,
  `Cidade` VARCHAR(45) NOT NULL,
  `CEP` VARCHAR(10) NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  `Cargo_idCargo` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_Usuario_Cargo1_idx` (`Cargo_idCargo` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Cargo1`
    FOREIGN KEY (`Cargo_idCargo`)
    REFERENCES `CRUD`.`Cargo` (`idCargo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(60) NOT NULL,
  `CPF` VARCHAR(15) NOT NULL,
  `Telefone` VARCHAR(15) NULL,
  `Email` VARCHAR(45) NULL,
  `Rua` VARCHAR(45) NULL,
  `Bairro` VARCHAR(45) NULL,
  `Cidade` VARCHAR(45) NULL,
  `CEP` VARCHAR(45) NULL,
  `Estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`));


-- -----------------------------------------------------
-- Table `CRUD`.`Pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Pedido` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `DataPedido` DATE NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Cliente_idCliente` INT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Pedido_Cliente2_idx` (`Cliente_idCliente` ASC) VISIBLE,
  INDEX `fk_Pedido_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Cliente2`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `CRUD`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CRUD`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produto_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `CRUD`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `Nome` VARCHAR(45) NOT NULL,
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
  INDEX `fk_Pedido_has_Produto_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  INDEX `fk_Pedido_has_Produto_Pedido1_idx` (`Pedido_idPedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_Produto_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `CRUD`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Produto_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `CRUD`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CRUD`.`Encomenda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CRUD`.`Encomenda` ;

CREATE TABLE IF NOT EXISTS `CRUD`.`Encomenda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Produto_idProduto` INT NOT NULL,
  `Fornecedor_idFornecedor` INT NOT NULL,
  `Quantidade` INT NOT NULL,
  `Situacao` varchar(45) not null,
  PRIMARY KEY (`id`),
  INDEX `fk_Produto_has_Fornecedor_Fornecedor1_idx` (`Fornecedor_idFornecedor` ASC) VISIBLE,
  INDEX `fk_Produto_has_Fornecedor_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_Fornecedor_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `CRUD`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Fornecedor_Fornecedor1`
    FOREIGN KEY (`Fornecedor_idFornecedor`)
    REFERENCES `CRUD`.`Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

alter table encomenda
add column Situacao varchar(20);

create table Carrinho(
quantidade int not null,
Produto_idProduto int not null,
foreign key (Produto_idProduto) references Produto(idProduto)
);

CREATE VIEW lastPedido AS
SELECT  max(idPedido)  as maxId
FROM PEDIDO;

DROP TRIGGER IF EXISTS `crud`.`pedido_has_produto_AFTER_INSERT`;
DELIMITER $$
USE `crud`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `pedido_has_produto_AFTER_INSERT` AFTER INSERT ON `pedido_has_produto` FOR EACH ROW BEGIN
	call AtualizaQuantidade(new.quantidade, new.Produto_idProduto); 
END$$
DELIMITER ;

DELIMITER $$
USE `crud`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizaQuantidade`(qntd int, idProduto int)
BEGIN
	update produto as prod set prod.qntestoque = prod.qntestoque - qntd where prod.idProduto = idProduto; 
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
