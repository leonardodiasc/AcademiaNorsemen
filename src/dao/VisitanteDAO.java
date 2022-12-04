package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Visitante;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leo
 */
public class VisitanteDAO {
   
    private final Connection connection;
    
    public VisitanteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Visitante visitante){
        try {
            String sql = "INSERT INTO visitante (nome,cpf,valoraserpago) VALUES(?,?,?); ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, visitante.getNome().toUpperCase());
            stm.setLong(2, visitante.getCpf());
            stm.setInt(3, visitante.getValorAserPago());
            stm.execute();
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo visitante!", e);
        }
    }
    
  
    public ArrayList<Visitante> selectAll(){
        try {
            String sql = "SELECT * FROM visitante";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            return pesquisaListaVisitantes(stm);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao capturar a tabela de visitantes!", e);
        }
    }

    private ArrayList<Visitante> pesquisaListaVisitantes(PreparedStatement stm) throws SQLException {
        // criando o arraylist de usuários
        ArrayList<Visitante> visitantes = new ArrayList<>();
        
        //executa o statement e traz o resultado da execução do comando sql, guardando na variável resultset
        stm.execute();
        ResultSet resultSet = stm.getResultSet();
        
        // laço enquanto tiver novas linhas do resultado da tebala usuarios, ele trará cada elemento tupla
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            long cpf = resultSet.getLong("cpf");
            int valorAserPago = resultSet.getInt("valoraserpago");
            
            Visitante visitanteComDadosDoBanco = new Visitante(nome,cpf,valorAserPago);
            visitantes.add(visitanteComDadosDoBanco);
        }
        
        return visitantes;
    }
  
        public Visitante selectPorNome (Visitante visitante){
        try {
            String sql = "SELECT * FROM visitante WHERE nome = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, visitante.getNome());
            
            return pesquisaListaVisitantes(stm).get(0);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro na pesquisa de visitante por nome!", e); 
        } 
    }
    
        public boolean existeNoBancoPorNome(Visitante visitante){
        try {
            String sql = "SELECT * FROM visitante WHERE nome = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, visitante.getNome().toUpperCase());
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            
            return resultSet.next();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao verificar o nome do visitante no BD!", e);
        }
    }
    
}
