CREATE TABLE Compra (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_cpf varchar(200),
  status VARCHAR(20),
  forma_pagamento VARCHAR(50),
  FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf)
);
CREATE TABLE Compra_Produto (
  compra_id INT,
  produto_id INT,
  FOREIGN KEY (compra_id) REFERENCES Compra(id),
  FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
SELECT * FROM Compra_Produto;
SELECT * FROM cliente;
SELECT MAX(id) AS ultima_compra FROM Compra;
UPDATE produtos SET QTD = QTD - 2 WHERE ID = 6;
ALTER TABLE Compra_Produto
ADD COLUMN quantidade INT DEFAULT 0;
SELECT produtos.*
FROM produtos
JOIN Compra_Produto ON produtos.ID = Compra_Produto.produto_id
JOIN Compra ON Compra.id = Compra_Produto.compra_id
WHERE Compra.id = 5;
SELECT SUM(Compra_Produto.quantidade) AS quantidade_total
FROM Compra_Produto
JOIN Compra ON Compra.id = Compra_Produto.compra_id
WHERE Compra.id = 9;
SELECT produtos.*, COALESCE(SUM(Compra_Produto.quantidade), 0) AS quantidade_total
FROM produtos
LEFT JOIN Compra_Produto ON produtos.ID = Compra_Produto.produto_id
LEFT JOIN Compra ON Compra.id = Compra_Produto.compra_id
WHERE Compra.id = 9
GROUP BY produtos.ID;
SELECT produtos.ID, produtos.NOME, produtos.FABRICANTE, produtos.CONCENTRACAO, produtos.PRECO, COALESCE(SUM(Compra_Produto.quantidade), 0) AS quantidade_total
FROM produtos
LEFT JOIN Compra_Produto ON produtos.ID = Compra_Produto.produto_id
LEFT JOIN Compra ON Compra.id = Compra_Produto.compra_id
WHERE Compra.id = 20
GROUP BY produtos.ID, produtos.NOME, produtos.FABRICANTE, produtos.CONCENTRACAO, produtos.PRECO;
UPDATE produtos SET QTD = QTD + 1 WHERE ID = 6;
DELETE FROM Compra_Produto WHERE compra_id = 20 AND produto_id = 6;
select * from Compra where status = 'Pendente';
SELECT Compra.id, cliente.nome
FROM Compra
INNER JOIN cliente ON Compra.cliente_cpf = cliente.cpf
WHERE Compra.status = 'pendente';
SELECT Compra.id, cliente.nome, COUNT(Compra_Produto.produto_id) AS quantidade_itens
FROM Compra
INNER JOIN cliente ON Compra.cliente_cpf = cliente.cpf
INNER JOIN Compra_Produto ON Compra.id = Compra_Produto.compra_id
WHERE Compra.status = 'pendente'
GROUP BY Compra.id, cliente.nome;
DELETE FROM cliente WHERE cpf = '11940518474';
SELECT * FROM cliente;
DELETE FROM cliente
WHERE cpf = '11940518474';
DELETE FROM Compra_Produto
WHERE compra_id IN (SELECT id FROM Compra WHERE cliente_cpf = '11940518474');
DELETE FROM Compra
WHERE cliente_cpf = '11940518474';