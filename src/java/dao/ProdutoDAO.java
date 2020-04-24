package dao;

import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author felipe
 */
public class ProdutoDAO {
    
    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Produto> lista = new ArrayList<>();
    
    public ProdutoDAO(){
        conn = new ConnectionFactory().getConexao();
    }
    
    public void inserir(Produto produto){
        String sql = "INSERT INTO PRODUTOS (DESCRICAO, PRECO) VALUES (?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.execute();
            stmt.close();
        } catch(Exception erro){
            throw new RuntimeException("Erro 2: " + erro);
        }
    }
    
    public void alterar(Produto produto){
        String sql = "UPDATE PRODUTOS SET DESCRICAO  = ?, PRECO = ? WHERE CODIGO = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setDouble(3, produto.getCodigo());
            stmt.execute();
            stmt.close();
        } catch(Exception erro){
            throw new RuntimeException("Erro 3: " + erro);
        }
    }
    
    public void excluir(int valor){
        String sql = "DELETE FROM PRODUTOS WHERE CODIGO = VALOR";
        try {
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch(Exception erro){
            throw new RuntimeException("Erro 4: " + erro);
        }
    }
    
    public ArrayList<Produto> listarTodos(){
        String sql = "SELECT * FROM PRODUTOS";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                lista.add(produto);
            }
        } catch (Exception erro) {
            throw new RuntimeException("Erro 5: " + erro);
        }
        return lista;
    }
    
    public ArrayList<Produto> listarTodosDescricao(String valor){
        String sql = "SELECT * FROM PRODUTOS where descricao like '%"+valor+"%'";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                lista.add(produto);
            }
        } catch (Exception erro) {
            throw new RuntimeException("Erro 6: " + erro);
        }
        return lista;
    }
}
