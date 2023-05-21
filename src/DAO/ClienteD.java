
package DAO;
import Dto.Cliente;
import connection.ConexaoUtil;
import java.awt.List;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
public class ClienteD {
        public void inserir(Cliente cliente) throws SQLException{
        try{
        Connection connection = ConexaoUtil.getInstance().getConnection();
        String sql = "INSERT INTO cliente (nome,endereco, telefone,email,cpf,rg,sexo ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getCpf());
            stmt.setString(6, cliente.getRg());
            stmt.setString(7, cliente.getSexo());
            
             
            stmt.execute();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
}public Cliente buscarPorCPF(String cpf) throws SQLException {
    Cliente cliente = null;
    try {
        Connection connection = ConexaoUtil.getInstance().getConnection();
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setNome(rs.getString("nome"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setRg(rs.getString("rg"));
            cliente.setSexo(rs.getString("sexo"));
        }
        
        rs.close();
        stmt.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return cliente;
}
}
