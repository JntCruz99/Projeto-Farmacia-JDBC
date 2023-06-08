SHOW CREATE TABLE cliente;
SHOW CREATE TABLE Compra;
SHOW CREATE TABLE Compra_Produto;
SHOW CREATE TABLE produtos;
cliente	CREATE TABLE `cliente` (
   `nome` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
   `endereco` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
   `telefone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
   `email` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
   `cpf` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
   `rg` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
   `sexo` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`cpf`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
 Compra	CREATE TABLE `Compra` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `cliente_cpf` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
   `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
   `forma_pagamento` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `cliente_cpf` (`cliente_cpf`),
   CONSTRAINT `Compra_ibfk_1` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`)
 ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
 Compra_Produto	CREATE TABLE `Compra_Produto` (
   `compra_id` int(11) DEFAULT NULL,
   `produto_id` int(11) DEFAULT NULL,
   KEY `compra_id` (`compra_id`),
   KEY `produto_id` (`produto_id`),
   CONSTRAINT `Compra_Produto_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `Compra` (`id`),
   CONSTRAINT `Compra_Produto_ibfk_2` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
 produtos	CREATE TABLE `produtos` (
   `ID` int(11) NOT NULL AUTO_INCREMENT,
   `NOME` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
   `FABRICANTE` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
   `CONCENTRACAO` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
   `PRECO` double DEFAULT NULL,
   `QTD` int(11) DEFAULT NULL,
   PRIMARY KEY (`ID`)
 ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci