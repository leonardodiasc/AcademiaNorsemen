package controller;
import model.Usuario;
import java.sql.*;
import java.sql.SQLException;
import dao.UsuarioDAO;
import javax.swing.JOptionPane;
import view.MensagensAvisosView;
import javax.swing.*;

public class Controle {

   public static String[] JOptionPaneMultiInput() {
       String[] campos = new String[2];
      JTextField campoNome = new JTextField(5);
      JTextField campoSenha = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Nome:"));
      myPanel.add(campoNome);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Senha:"));
      myPanel.add(campoSenha);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Por favor digite o usuário e a senha do usuário:", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         campos[0] = campoNome.getText();
         campos[1] = campoSenha.getText();
      }
      return campos;
   }

    
    // método usado para iniciar o sistema
    public void iniciarSistema(Connection connection){
        String[] campos = new String[2];
        UsuarioDAO newUsuarioQuery = new UsuarioDAO(connection);
        
        //cria os objetos
        MensagensAvisosView tela = new MensagensAvisosView();
        
        
        //ativa o sistema
        boolean menuPrincipal = true;
        
        while (menuPrincipal == true){
            
            String opcaoEscolhida = tela.montarMenu();
            
            switch (opcaoEscolhida){
                case "1":
                    campos = JOptionPaneMultiInput();
                    Usuario newUsuario = new Usuario(campos[0], campos[1]);
                    newUsuarioQuery.insert(newUsuario);
                    break;
                case "2":
                    try{
                        campos[0] = JOptionPane.showInputDialog(
                         null,
                         "digite o nome do cliente que pesquisa:", //mensagem
                         ":: PESQUISA DE CLIENTES ::", //título
                         -1);
                        Usuario usuarioSelect = new Usuario(newUsuarioQuery.selectPorNome(campos[0]));
                        JOptionPane.showMessageDialog(null, usuarioSelect.getUsuario());
                        JOptionPane.showMessageDialog(null, usuarioSelect.getSenha());
                    }catch(Error e){
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                        throw new RuntimeException("Houve um erro na pesquisa de usuários!", e);
                    }
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Hello world!");
                    break;   
                case "0":
                    menuPrincipal = confirmarSair(tela); // passando o objeto para o outro método
                break;
  
            }
        }
    } // fecha o método iniciar o sistema
    
    // confirmar o encerramento do sistema
    private Boolean confirmarSair(MensagensAvisosView _tela){ // recebendo o objeto do outro método
        
        int sair = _tela.confirmarSaida("Tem certeza?", "Encerrando o Sistema", 3);
            
            if(sair == 0){ // 0 é o sim do menu 
                _tela.mostrarMensagem("Encerrando o sistema", "Encerrando", 1);
                return false;
            }
        return true;
    } // fecha o método confirmarSair
}
