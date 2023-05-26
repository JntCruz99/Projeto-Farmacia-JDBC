
package DAO;
import Dto.Produto;
import connection.ConexaoUtil;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




public class ProdutoD {
    public void inserir(Produto produto) throws SQLException{
        try{
        Connection connection = ConexaoUtil.getInstance().getConnection();
        String sql = "INSERT INTO produtos (NOME,FABRICANTE, CONCENTRACAO,PRECO,QTD ) VALUES (?, ?, ?, ?, ?)";
        
        
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getFabricante());
            stmt.setString(3, produto.getConcentracao());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getQtd());
             
            stmt.execute();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    public List<Produto> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setFabricante(rs.getString("fabricante"));
                produto.setConcentracao(rs.getString("fabricante"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
    public List<Produto> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setFabricante(rs.getString("fabricante"));
                produto.setConcentracao(rs.getString("concentracao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }

    public void update(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET nome = ? ,fabricante = ?,concentracao = ?,preco = ?,qtd = ? WHERE id = ?");
            
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getFabricante());
            stmt.setString(3, p.getConcentracao());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getQtd());
            stmt.setInt(6, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }public List<Produto> BuscarId(int id) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("""
                                        SELECT produtos.ID, produtos.NOME, produtos.FABRICANTE, produtos.CONCENTRACAO, produtos.PRECO, COALESCE(SUM(Compra_Produto.quantidade), 0) AS quantidade_total
                                        FROM produtos
                                        LEFT JOIN Compra_Produto ON produtos.ID = Compra_Produto.produto_id
                                        LEFT JOIN Compra ON Compra.id = Compra_Produto.compra_id
                                        WHERE Compra.id = ?
                                        GROUP BY produtos.ID, produtos.NOME, produtos.FABRICANTE, produtos.CONCENTRACAO, produtos.PRECO""");
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setFabricante(rs.getString("fabricante"));
                produto.setConcentracao(rs.getString("concentracao"));              
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd(rs.getInt("quantidade_total"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProdutoD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }
     
}
