package controller;

import dao.ConnectionFactory;
import dao.ProfessorDAO;
import static java.lang.Float.parseFloat;
import static java.lang.Long.parseLong;
import java.sql.Connection;
import java.sql.SQLException;
import model.Professor;
import view.FormCadastroProfessorView;
import view.MensagensAvisosView;

public class FormCadastroProfessorController {
    
    private FormCadastroProfessorView view;

    public FormCadastroProfessorController(FormCadastroProfessorView view) {
        this.view = view;
    }

    public void salvaNovoProfessor() throws SQLException{
        
        // recebe o que foi digitado na Tela de Cadastramento
        String nomeNovoProfessor = view.getCampoNomeNovoProfessor().getText();
        long cpfNovoProfessor = parseLong(view.getCampoCPFNovoProfessor().getText());
        float salarioNovoProfessor = parseFloat(view.getCampoSalarioNovoProfessor().getText());
        String enderecoNovoProfessor = view.getCampoEnderecoNovoProfessor().getText();
        long telefoneNovoProfessor = parseLong(view.getCampoTelefoneNovoProfessor().getText());
        String horasTrabalhadasNovoProfessor = view.getCampoHorasTrabalhadasNovoProfessor().getText();
        
        // guardar o que foi digitado num objeto do tipo usuario
        Professor novoProfessorCadastrado = new Professor(nomeNovoProfessor, cpfNovoProfessor, salarioNovoProfessor, enderecoNovoProfessor, telefoneNovoProfessor, horasTrabalhadasNovoProfessor);
        
        // cria uma conexão com o banco de dados
        Connection conexao = new ConnectionFactory().connectionPostgreSQL();
        
        //cria um usuárioDAO pra chamar a função de verificar se existe no BD através do "If Else" e dps inserir(insert)
        //passa a conexao como parametro para que o usuarioDAO crie um objeto com aquela conexão única
        ProfessorDAO professores = new ProfessorDAO(conexao);
        
        if (professores.existeNoBancoSomentePorNome(novoProfessorCadastrado)){
            MensagensAvisosView telaAvisoUsuarioCadastradoJaExiste = new MensagensAvisosView();
            telaAvisoUsuarioCadastradoJaExiste.mostrarMensagem("Professor já existe no Banco de dados", "Professor Existente", 1);
            conexao.close();
        } else {
            //insere o usuario no banco
            professores.insert(novoProfessorCadastrado);
            
            //fecha a conexão e avisa que o cadastro deu certo
            conexao.close();
        }
    }
}
