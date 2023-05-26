/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dto.Cliente;
import Dto.Compra;
import connection.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
