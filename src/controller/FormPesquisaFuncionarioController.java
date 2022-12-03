package controller;

import java.sql.Connection;
import dao.ConnectionFactory;
import dao.FuncionarioDAO;
import java.sql.SQLException;
import model.Funcionario;
import view.FormPesquisaFuncionarioView;
import view.MensagensAvisosView;


public class FormPesquisaFuncionarioController {
   
    private FormPesquisaFuncionarioView view;

    public FormPesquisaFuncionarioController(FormPesquisaFuncionarioView view) {
        this.view = view;
    }
    
    public MensagensAvisosView telaResultadoFuncionarioPesquisado() throws SQLException{
        //criar tela de aviso
        MensagensAvisosView telaComFuncionarioPesquisado = new MensagensAvisosView();
        telaComFuncionarioPesquisado.mostrarMensagem(pesquisarFuncionarioPorNome(),"Resultado da Busca", 1);
        return telaComFuncionarioPesquisado;
    }
    
    public String pesquisarFuncionarioPorNome() throws SQLException{
        
        //recebe o que foi digitado na tela e cria um funcionario com isso
        String nomeFuncionarioParaPesquisar = view.getCampoPesquisarFuncionarioPorNome().getText().toUpperCase();
        Funcionario funcionarioParaPesquisar = new Funcionario(nomeFuncionarioParaPesquisar);
        
        //criar conexão com o BD
        Connection conexao = new ConnectionFactory().connectionPostgreSQL();
        
        //cria o funcionarioDAO e usa a conexão como parâmetro para ele utilizar as informações daquela conexão em específico
        FuncionarioDAO funcionarioSendoPesquisado = new FuncionarioDAO(conexao);
        
        //verifica se existe no banco de dados para retornar uma tela de dados ou de erro de pesquisa
        if (funcionarioSendoPesquisado.existeNoBancoSomentePorNome(funcionarioParaPesquisar)){
            Funcionario resultadoFuncionarioPesquisado = funcionarioSendoPesquisado.selectPorNome(funcionarioParaPesquisar);
        
        //guarda os dados do usuário encontrado
        String dadosFuncionarioPesquisado = "-----------------------------\n"
                + "Id: "+resultadoFuncionarioPesquisado.getId()+"\n"
                + "Nome do Funcionário: "+resultadoFuncionarioPesquisado.getNome()+"\n"
                + "Função: "+resultadoFuncionarioPesquisado.getFuncao()+"\n"
                + "Endereço: "+resultadoFuncionarioPesquisado.getEndereco()+"\n"
                + "Telefone: "+resultadoFuncionarioPesquisado.getTelefone()+"\n"
                + "-----------------------------\n";
        
        return dadosFuncionarioPesquisado;
        
        } else {
            return "Erro ao Buscar o Funcionário. Verifique o nome digitado!";
        }
    }
}
