package connection;

import java.sql.Connection;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class ConexaoUtil {
    private static ConexaoUtil conexaoUtil;
    
    public static ConexaoUtil getInstance(){
        if(conexaoUtil == null){
            conexaoUtil = new ConexaoUtil();
            
        }
        return conexaoUtil;
    }
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        
        return DriverManager.getConnection("jdbc:mysql://ns840.hostgator.com.br/jntcru25_Mercadinho","jntcru25_jntcruz","@Fpb2021");
    }
    public static void main(String[]args){
        try{
        System.out.println(getInstance().getConnection());
        } catch(Exception e){
            e.printStackTrace();
        }
        }
}
