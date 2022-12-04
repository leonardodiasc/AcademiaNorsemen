package controller;

import java.sql.Connection;
import dao.ConnectionFactory;
import dao.VisitanteDAO;
import java.sql.SQLException;
import model.Visitante;
import view.FormPesquisaVisitanteView;
import view.MensagensAvisosView;


public class FormPesquisaVisitanteController {
   
    private FormPesquisaVisitanteView view;

    public FormPesquisaVisitanteController(FormPesquisaVisitanteView view) {
        this.view = view;
    }
    
    public MensagensAvisosView telaResultadoVisitantePesquisado() throws SQLException{
        //criar tela de aviso
        MensagensAvisosView telaComVisitantePesquisado = new MensagensAvisosView();
        telaComVisitantePesquisado.mostrarMensagem(pesquisarVisitantePorNome(),"Resultado da Busca", 1);
        return telaComVisitantePesquisado;
    }
    
    public String pesquisarVisitantePorNome() throws SQLException{
        
        //recebe o que foi digitado na tela e cria um visitante com isso
        String nomeVisitanteParaPesquisar = view.getCampoPesquisarVisitantePorNome().getText().toUpperCase();
        Visitante visitanteParaPesquisar = new Visitante(nomeVisitanteParaPesquisar);
        
        //criar conexão com o BD
        Connection conexao = new ConnectionFactory().connectionPostgreSQL();
        
        //cria o visitanteDAO e usa a conexão como parâmetro para ele utilizar as informações daquela conexão em específico
        VisitanteDAO visitanteSendoPesquisado = new VisitanteDAO(conexao);
        
        //verifica se existe no banco de dados para retornar uma tela de dados ou de erro de pesquisa
        if (visitanteSendoPesquisado.existeNoBancoPorNome(visitanteParaPesquisar)){
            Visitante resultadoVisitantePesquisado = visitanteSendoPesquisado.selectPorNome(visitanteParaPesquisar);
        
        //guarda os dados do usuário encontrado
        String dadosVisitantePesquisado = "-----------------------------\n"
                + "Nome do Visitante: "+resultadoVisitantePesquisado.getNome()+"\n"
                + "CPF: "+resultadoVisitantePesquisado.getCpf()+"\n"
                + "Valor a ser pago: "+resultadoVisitantePesquisado.getValorAserPago()+"\n"
                + "-----------------------------\n";
        
        return dadosVisitantePesquisado;
        
        } else {
            return "Erro ao Buscar o Visitante. Verifique o nome digitado!";
        }
    }
}
