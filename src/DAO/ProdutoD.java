
package DAO;
import Dto.Produto;
import connection.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class ProdutoD {
    public void inserir(Produto produto) throws SQLException{
        try{
        Connection connection = ConexaoUtil.getInstance().getConnection();
        String sql = "INSERT INTO produtos (NOME,FABRICANTE, CONCENTRACAO,PRECO,QTD ) VALUES (?, ?, ?, ?, ?)";
        
        
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getFabricante());
            stmt.setString(3, produto.getConcentração());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getQtd());
             
            stmt.execute();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    
     
}
