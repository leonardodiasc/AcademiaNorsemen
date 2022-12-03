package controller;

import java.sql.Connection;
import dao.ConnectionFactory;
import dao.ProfessorDAO;
import java.sql.SQLException;
import model.Professor;
import view.FormPesquisaProfessorView;
import view.MensagensAvisosView;


public class FormPesquisaProfessorController {
   
    private FormPesquisaProfessorView view;

    public FormPesquisaProfessorController(FormPesquisaProfessorView view) {
        this.view = view;
    }
    
    public MensagensAvisosView telaResultadoProfessorPesquisado() throws SQLException{
        //criar tela de aviso
        MensagensAvisosView telaComProfessorPesquisado = new MensagensAvisosView();
        telaComProfessorPesquisado.mostrarMensagem(pesquisarProfessorPorNome(),"Resultado da Busca", 1);
        return telaComProfessorPesquisado;
    }
    
    public String pesquisarProfessorPorNome() throws SQLException{
        
        //recebe o que foi digitado na tela e cria um professor com isso
        String nomeProfessorParaPesquisar = view.getCampoPesquisarProfessorPorNome().getText().toUpperCase();
        Professor professorParaPesquisar = new Professor(nomeProfessorParaPesquisar);
        
        //criar conexão com o BD
        Connection conexao = new ConnectionFactory().connectionPostgreSQL();
        
        //cria o professorDAO e usa a conexão como parâmetro para ele utilizar as informações daquela conexão em específico
        ProfessorDAO professorSendoPesquisado = new ProfessorDAO(conexao);
        
        //verifica se existe no banco de dados para retornar uma tela de dados ou de erro de pesquisa
        if (professorSendoPesquisado.existeNoBancoSomentePorNome(professorParaPesquisar)){
            Professor resultadoProfessorPesquisado = professorSendoPesquisado.selectPorNome(professorParaPesquisar);
        
        //guarda os dados do usuário encontrado
        String dadosProfessorPesquisado = "-----------------------------\n"
                + "Id: "+resultadoProfessorPesquisado.getId()+"\n"
                + "Nome do Professor: "+resultadoProfessorPesquisado.getNome()+"\n"
                + "CPF: "+resultadoProfessorPesquisado.getCPF()+"\n"
                + "Endereço: "+resultadoProfessorPesquisado.getEndereco()+"\n"
                + "Telefone: "+resultadoProfessorPesquisado.getTelefone()+"\n"
                + "HorasTrabalhadas: "+resultadoProfessorPesquisado.getHorasTrabalhadas()+"\n"
                + "-----------------------------\n";
        
        return dadosProfessorPesquisado;
        
        } else {
            return "Erro ao Buscar o Professor. Verifique o nome digitado!";
        }
    }
}
