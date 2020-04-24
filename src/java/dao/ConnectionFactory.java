package dao;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author felipe
 */
public class ConnectionFactory {
    public Connection getConexao(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/produto","root","");
        } catch(Exception erro){
            throw new RuntimeException("Erro 1: " + erro);
        }
    }
}
