-- -----------------------------------------------------
-- Table TRIGGER - tg_atualiza_encomenda
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS tg_atualiza_encomenda;
DELIMITER $
create trigger tg_atualiza_encomenda before update
on encomenda
for each row
begin
	set new.Situacao = 'Entregue';
    update produto set QntEstoque = QntEstoque + new.Quantidade
    where produto.idProduto = new.Produto_idProduto;
end$
delimiter ;

-- -----------------------------------------------------
-- Table TRIGGER - pedido_has_produto_AFTER_INSERT
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS `crud`.`pedido_has_produto_AFTER_INSERT`;
DELIMITER $$
USE `crud`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `pedido_has_produto_AFTER_INSERT` AFTER INSERT ON `pedido_has_produto` FOR EACH ROW BEGIN
	call AtualizaQuantidade(new.quantidade, new.Produto_idProduto); 
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Table PROCEDURE - AtualizaQuantidade
-- -----------------------------------------------------
DELIMITER $$
USE `crud`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AtualizaQuantidade`(qntd int, idProduto int)
BEGIN
	update produto as prod set prod.qntestoque = prod.qntestoque - qntd where prod.idProduto = idProduto; 
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Table VIEW - lastPedido
-- -----------------------------------------------------
DROP VIEW IF EXISTS `crud`.`lastPedido`;
CREATE VIEW lastPedido AS
SELECT  max(idPedido)  as maxId
FROM PEDIDO;

-- -----------------------------------------------------
-- Table VIEW - Relatorio
-- -----------------------------------------------------
DROP VIEW IF EXISTS `crud`.`relatorio`;
CREATE VIEW relatorio AS
select ped.idPedido as ID, ped.DataPedido as 'Data do Pedido',  cliente.nome as Cliente, usuario.nome as Vendedor
from pedido as ped
LEFT JOIN cliente on ped.Cliente_idCliente = cliente.idCliente
LEFT JOIN usuario on ped.Usuario_idUsuario = usuario.idUsuario;