package dao;

import java.sql.Connection;
import model.Professor;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import view.MensagensAvisosView;

public class ProfessorDAO {
    
    private final Connection connection;
    
    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Professor professor){
        
        if (professor.getSalario() < 0){
            
           MensagensAvisosView telaAvisoSalarioNegativo = new MensagensAvisosView();
           telaAvisoSalarioNegativo.mostrarMensagem("O salário não pode ser negativo", "Salário Inválido", 0);
            
        } else {
            try {
                String sql = "INSERT INTO professor(nome,cpf,salario,endereco,telefone,horasTrabalhadas) VALUES(?,?,?,?,?,?); ";
                PreparedStatement stm = connection.prepareStatement(sql);
            
                // adicionando validação de dados para evitar SQL injection no banco de dados
                stm.setString(1, professor.getNome().toUpperCase());
                stm.setLong(2, professor.getCPF());
                stm.setFloat(3, professor.getSalario());
                stm.setString(4, professor.getEndereco());
                stm.setLong(5, professor.getTelefone());
                stm.setString(6, professor.getHorasTrabalhadas());
                stm.execute();
                
                MensagensAvisosView telaAvisoUsuarioCadastradoSucesso = new MensagensAvisosView();
                telaAvisoUsuarioCadastradoSucesso.mostrarMensagem("Professor cadastrado com sucesso", "Novo Professor", 1);
            
            } catch (SQLException e){
                throw new RuntimeException("Houve um erro ao cadastrar o novo professor!", e);
            }   
        }
    }
    
    public void update (Professor professor) {
        try {
            String sql = "UPDATE professor SET nome = ?, salario = ?, endereco = ?, telefone = ?, horasTrabalhadas = ?, WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, professor.getNome().toUpperCase());
            stm.setFloat(2, professor.getSalario());
            stm.setString(3, professor.getEndereco());
            stm.setLong(4, professor.getTelefone());
            stm.setString(5, professor.getHorasTrabalhadas());
            stm.setInt(6, professor.getId());
            stm.execute();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao atualizar o professor!", e);
        }
    }
    
    public void delete(Professor professor) {
        try {
            String sql = "DELETE FROM professor WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setInt(1, professor.getId());
            stm.execute();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao deletar o professor!", e);
        }
    }
    
    public ArrayList<Professor> selectAll(){
        try {
            String sql = "SELECT * FROM professor";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            return pesquisaListaProfessores(stm);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao trazer a tabela de professores!", e);
        }
    }

    private ArrayList<Professor> pesquisaListaProfessores(PreparedStatement stm) throws SQLException {
        // criando o arraylist de usuários
        ArrayList<Professor> professores = new ArrayList<>();
        
        //executa o statement e traz o resultado da execução do comando sql, guardando na variável resultset
        stm.execute();
        ResultSet resultSet = stm.getResultSet();
        
        // laço enquanto tiver novas linhas do resultado da tebala usuarios, ele trará cada elemento tupla
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            long CPF = resultSet.getLong("CPF");
            float salario = resultSet.getFloat("salario");
            String endereco = resultSet.getString("endereco");
            long telefone = resultSet.getLong("telefone");
            String horasTrabalhadas = resultSet.getString("horasTrabalhadas");
            
            Professor professorComDadosDoBanco = new Professor(id, nome, CPF, salario, endereco, telefone, horasTrabalhadas);
            professores.add(professorComDadosDoBanco);
        }
        
        return professores;
    }
    
    public Professor selectPorId (Professor professor){
        try {
            String sql = "SELECT * FROM professor WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, professor.getId());
            
            return pesquisaListaProfessores(stm).get(0);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro na pesquisa de professores por id!", e);
        }
    }
    
    public Professor selectPorNome (Professor professor){
        try {
            String sql = "SELECT * FROM professor WHERE nome = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, professor.getNome());
            
            return pesquisaListaProfessores(stm).get(0);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro na pesquisa de professores por nome!", e); 
        } 
    }
    
    public boolean existeNoBancoPorNomeEHorasTrabalhadas(Professor professor){
        try {
            String sql = "SELECT * FROM professor WHERE nome = ? AND horasTrabalhadas = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, professor.getNome().toUpperCase());
            stm.setString(2, professor.getHorasTrabalhadas());
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            
            return resultSet.next();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo professor!", e);
        }
    }
    
    public boolean existeNoBancoSomentePorNome(Professor professor){
        try {
            String sql = "SELECT * FROM professor WHERE nome = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, professor.getNome().toUpperCase());
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            
            return resultSet.next();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo professor!", e);
        }
    }
    
}
