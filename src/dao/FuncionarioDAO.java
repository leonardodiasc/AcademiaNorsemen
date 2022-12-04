package dao;

import java.sql.Connection;
import model.Funcionario;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import view.MensagensAvisosView;

public class FuncionarioDAO {
    
    private final Connection connection;
    
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Funcionario funcionario){
        
        if (funcionario.getSalario() < 0){
            
           MensagensAvisosView telaAvisoSalarioNegativo = new MensagensAvisosView();
           telaAvisoSalarioNegativo.mostrarMensagem("O salário não pode ser negativo", "Salário Inválido", 0);
            
        } else {
            try {
                String sql = "INSERT INTO funcionario (nome,funcao,salario,endereco,telefone) VALUES(?,?,?,?,?); ";
                PreparedStatement stm = connection.prepareStatement(sql);
            
                // adicionando validação de dados para evitar SQL injection no banco de dados
                stm.setString(1, funcionario.getNome().toUpperCase());
                stm.setString(2, funcionario.getFuncao());
                stm.setFloat(3, funcionario.getSalario());
                stm.setString(4, funcionario.getEndereco());
                stm.setLong(5, funcionario.getTelefone());
                stm.execute();
                
                MensagensAvisosView telaAvisoUsuarioCadastradoSucesso = new MensagensAvisosView();
                telaAvisoUsuarioCadastradoSucesso.mostrarMensagem("Funcionário cadastrado com sucesso", "Novo Funcionário", 1);
            
            } catch (SQLException e){
                throw new RuntimeException("Houve um erro ao cadastrar o novo funcionário!", e);
            }   
        }
    }
    
    public ArrayList<Funcionario> selectAll(){
        try {
            String sql = "SELECT * FROM funcionario";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            return pesquisaListaFuncionarios(stm);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao trazer a tabela de funcionarios!", e);
        }
    }

    private ArrayList<Funcionario> pesquisaListaFuncionarios(PreparedStatement stm) throws SQLException {
        // criando o arraylist de usuários
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        //executa o statement e traz o resultado da execução do comando sql, guardando na variável resultset
        stm.execute();
        ResultSet resultSet = stm.getResultSet();
        
        // laço enquanto tiver novas linhas do resultado da tebala usuarios, ele trará cada elemento tupla
        while (resultSet.next()) {
            int id = resultSet.getInt("id_funcionario");
            String nome = resultSet.getString("nome");
            String funcao = resultSet.getString("funcao");
            float salario = resultSet.getFloat("salario");
            String endereco = resultSet.getString("endereco");
            long telefone = resultSet.getLong("telefone");
            
            Funcionario funcionarioComDadosDoBanco = new Funcionario(id, nome, funcao, salario, endereco, telefone);
            funcionarios.add(funcionarioComDadosDoBanco);
        }
        
        return funcionarios;
    }
    
    public Funcionario selectPorNome (Funcionario funcionario){
        try {
            String sql = "SELECT * FROM funcionario WHERE nome = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            
            return pesquisaListaFuncionarios(stm).get(0);
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro na pesquisa de funcionarios por nome!", e); 
        } 
    }
   
    public boolean existeNoBancoSomentePorNome(Funcionario funcionario){
        try {
            String sql = "SELECT * FROM funcionario WHERE nome = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            
            // adicionando validação de dados para evitar SQL injection no banco de dados
            stm.setString(1, funcionario.getNome().toUpperCase());
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            
            return resultSet.next();
            
        } catch (SQLException e){
            throw new RuntimeException("Houve um erro ao cadastrar o novo funcionario!", e);
        }
    }
    
}
