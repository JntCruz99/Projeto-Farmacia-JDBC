/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dto.Cliente;
import Dto.Compra;
import Dto.Produto;
import java.sql.ResultSet;
import connection.ConexaoUtil;
import connection.ConnectionFactory;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jonata
 */
public class CompraD {

    public void InserirCompra(Compra compra) throws SQLException {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "INSERT INTO Compra (cliente_cpf, status, forma_pagamento) VALUES (?, ?, ?);";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, compra.getClienteCPF());
            stmt.setString(2, compra.getStatus());
            stmt.setString(3, compra.getForma_pagamento());

            stmt.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int UltimaCompra() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idd = 0; // Declare a variável idd antes do bloco while

        try {
            stmt = con.prepareStatement("SELECT MAX(id) AS ultima_compra FROM Compra");
            rs = stmt.executeQuery();

            while (rs.next()) {
                idd = rs.getInt("ultima_compra"); // Atribua o valor à variável idd
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return idd;
    }

    public void AdicionarProduto(int idcompra, int idproduto, int qtd) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Compra_Produto (compra_id, produto_id,quantidade) VALUES (?, ?,?)");
            stmt.setInt(1, idcompra);
            stmt.setInt(2, idproduto);
            stmt.setInt(3, qtd);
            stmt.executeUpdate();

            stmt = con.prepareStatement("UPDATE produtos SET QTD = QTD - ? WHERE ID = ?");
            stmt.setInt(1, qtd);
            stmt.setInt(2, idproduto);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void RemoverProduto(int idcompra, int idproduto, int qtd) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            
            stmt = con.prepareStatement("UPDATE produtos SET QTD = QTD + ? WHERE ID = ?");
            stmt.setInt(1, qtd);
            stmt.setInt(2, idproduto);
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("DELETE FROM Compra_Produto WHERE compra_id = ? AND produto_id = ?");
            stmt.setInt(1, idcompra);
            stmt.setInt(2, idproduto);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        public List<Compra> ComprasPendentes() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("""
                                        SELECT Compra.id, cliente.nome, COUNT(Compra_Produto.produto_id) AS quantidade_itens
                                        FROM Compra
                                        INNER JOIN cliente ON Compra.cliente_cpf = cliente.cpf
                                        INNER JOIN Compra_Produto ON Compra.id = Compra_Produto.compra_id
                                        WHERE Compra.status = 'pendente'
                                        GROUP BY Compra.id, cliente.nome""");
            rs = stmt.executeQuery();

            while (rs.next()) {

                
                Cliente cliente = new Cliente();
                Compra compra = new Compra(cliente);
                cliente.setNome(rs.getString("nome"));
                compra.setId(rs.getInt("id"));
                compra.setQtd_itens(rs.getInt("quantidade_itens"));

                compras.add(compra);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProdutoD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return compras;

    }
        public void FinalizarCompra(int idcompra, String f_pagamento) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            
            stmt = con.prepareStatement("UPDATE Compra SET status = 'Finalizado' , forma_pagamento = ? WHERE id = ?");
            stmt.setString(1,f_pagamento);
            stmt.setInt(2, idcompra);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        public void CancelarCompra(int idcompra) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            
            stmt = con.prepareStatement("UPDATE Compra SET status = 'CANCELADA' , forma_pagamento = ? WHERE id = ?");
            stmt.setString(1,"CANCELADA");
            stmt.setInt(2, idcompra);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        public List<Compra> HistoricoCompras() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("""
                                        SELECT Compra.id, cliente.nome, Compra.status, Compra.forma_pagamento, COUNT(Compra_Produto.produto_id) AS quantidade_itens
                                        FROM Compra
                                        INNER JOIN cliente ON Compra.cliente_cpf = cliente.cpf
                                        INNER JOIN Compra_Produto ON Compra.id = Compra_Produto.compra_id
                                        GROUP BY Compra.id, cliente.nome, Compra.status, Compra.forma_pagamento""");
            rs = stmt.executeQuery();

            while (rs.next()) {

                
                Cliente cliente = new Cliente();
                Compra compra = new Compra(cliente);
                cliente.setNome(rs.getString("nome"));
                compra.setId(rs.getInt("id"));
                compra.setQtd_itens(rs.getInt("quantidade_itens"));
                compra.setStatus(rs.getString("status"));
                compra.setForma_pagamento(rs.getString("forma_pagamento"));

                compras.add(compra);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProdutoD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return compras;

    }
        public List<Compra> RelatorioCompras(String status) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("""
                                        SELECT Compra.id, cliente.nome, Compra.status, Compra.forma_pagamento, COUNT(Compra_Produto.produto_id) AS quantidade_itens
                                        FROM Compra
                                        INNER JOIN cliente ON Compra.cliente_cpf = cliente.cpf
                                        INNER JOIN Compra_Produto ON Compra.id = Compra_Produto.compra_id
                                        WHERE Compra.status = ?
                                        GROUP BY Compra.id, cliente.nome, Compra.status, Compra.forma_pagamento""");
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {

                
                Cliente cliente = new Cliente();
                Compra compra = new Compra(cliente);
                cliente.setNome(rs.getString("nome"));
                compra.setId(rs.getInt("id"));
                compra.setQtd_itens(rs.getInt("quantidade_itens"));
                compra.setStatus(rs.getString("status"));
                compra.setForma_pagamento(rs.getString("forma_pagamento"));

                compras.add(compra);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProdutoD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return compras;

    }
   
}
