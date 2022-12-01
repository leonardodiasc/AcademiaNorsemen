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
            String sql = "INSERT INTO Visitante (nome,cpf,valorAserPago,estaAtivo) VALUES(?,?,?,?); ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, visitante.getNome());
            stm.setInt(2, visitante.getCpf());
            stm.setInt(3, visitante.getValorAserPago());
            stm.setBoolean(4, visitante.getEstaAtivo());
            stm.execute();
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo visitante!", e);
        }
    }
    
    public void update (Visitante visitante) {
        try {
            String sql = "UPDATE funcionario SET nome = ?, funcao = ?, salario = ?, endereco = ?, telefone = ? WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, visitante.getNome());
            stm.setInt(2, visitante.getCpf());
            stm.setInt(3, visitante.getValorAserPago());
            stm.setBoolean(4, visitante.getEstaAtivo());
            stm.execute();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao atualizar o visitante!", e);
        }
    }
    
    public void delete(Visitante visitante) {
        try {
            String sql = "DELETE FROM funcionario WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setInt(1, visitante.getCpf());
            stm.execute();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao deletar o visitante!", e);
        }
    }
    
    public ArrayList<Visitante> selectAll(){
        try {
            String sql = "SELECT * FROM Visitante";
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
            int cpf = resultSet.getInt("Cpf");
            int valorAserPago = resultSet.getInt("salario");
            boolean estaAtivo = resultSet.getBoolean("endereco");
            
            Visitante visitanteComDadosDoBanco = new Visitante(nome,cpf,valorAserPago,estaAtivo);
            visitantes.add(visitanteComDadosDoBanco);
        }
        
        return visitantes;
    }
    
    public Visitante selectPorCpf (Visitante visitante){
        try {
            String sql = "SELECT * FROM funcionario WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, visitante.getCpf());
            
            return pesquisaListaVisitantes(stm).get(0);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro na pesquisa de funcionarios!", e);
        }
    }
    
        public boolean existeNoBancoPorNome(Visitante visitante){
        try {
            String sql = "SELECT * FROM funcionario WHERE nome = ? AND funcao = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, visitante.getNome().toUpperCase());
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            
            return resultSet.next();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo funcionario!", e);
        }
    }
    
}
