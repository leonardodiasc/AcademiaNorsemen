package controller;

import dao.ConnectionFactory;
import dao.VisitanteDAO;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.sql.Connection;
import java.sql.SQLException;
import model.Visitante;
import view.FormCadastroVisitanteView;
import view.MensagensAvisosView;

public class FormCadastroVisitanteController {
    
    private FormCadastroVisitanteView view;
    
    public FormCadastroVisitanteController(FormCadastroVisitanteView view){
        this.view = view;
    }

    public void salvaNovoVisitante() throws SQLException{
        
        // recebe o que foi digitado na Tela de Cadastramento
        String nomeNovoVisitante = view.getCampoNomeNovoVisitante().getText();
        long cpfNovoVisitante = parseLong(view.getCampoCPFNovoVisitante().getText());
        int valorAserPagoNovoVisitante = parseInt(view.getCampoValorPagoNovoVisitante().getText());
        
        // guardar o que foi digitado num objeto do tipo usuario
        Visitante novoVisitanteCadastrado = new Visitante(nomeNovoVisitante, cpfNovoVisitante, valorAserPagoNovoVisitante);
        
        // cria uma conexão com o banco de dados
        Connection conexao = new ConnectionFactory().connectionPostgreSQL();
        
        //cria um usuárioDAO pra chamar a função de verificar se existe no BD através do "If Else" e dps inserir(insert)
        //passa a conexao como parametro para que o usuarioDAO crie um objeto com aquela conexão única
        VisitanteDAO visitantes = new VisitanteDAO(conexao);
        
        if (visitantes.existeNoBancoPorNome(novoVisitanteCadastrado)){
            MensagensAvisosView telaAvisoVisitanteCadastradoJaExiste = new MensagensAvisosView();
            telaAvisoVisitanteCadastradoJaExiste.mostrarMensagem("Visitante já existe no Banco de dados", "Visitante Existente", 1);
            conexao.close();
        } else {
            //insere o usuario no banco
            visitantes.insert(novoVisitanteCadastrado);
            MensagensAvisosView telaAvisoVisitanteCadastradoSucesso = new MensagensAvisosView();
            telaAvisoVisitanteCadastradoSucesso.mostrarMensagem("Visitante cadastrado com sucesso", "Visitante cadastrado", 1);
            
            //fecha a conexão e avisa que o cadastro deu certo
            conexao.close();
        }
    }
}
