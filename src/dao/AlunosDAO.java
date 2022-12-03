package dao;

import model.Aluno;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AlunosDAO {
     Connection conn;
     PreparedStatement pstm;
     ResultSet rs;
     ArrayList<Aluno> lista = new ArrayList<> ();
     
     //cadastar
     public void cadastarAlunos(Aluno objalunosdto){
       String sql ="insert into alunos(nome_aluno,cpf_aluno,idade_aluno,mensalidade_aluno,multaporatraso_aluno,"
                  + "endereco_aluno,telefone_aluno) values (?,?,?,?,?,?,?)"; 
       
       conn = new ConnectionFactory().connectionPostgreSQL();
       
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objalunosdto.getNome_aluno());
            pstm.setString(2, objalunosdto.getCpf_aluno());
            pstm.setInt(3, objalunosdto.getIdade_aluno());
            pstm.setString(4, objalunosdto.getMensalidade_aluno());
            pstm.setString(5, objalunosdto.getMultaporatraso_aluno());
            pstm.setString(6, objalunosdto.getEndereco_aluno());
            pstm.setString(7, objalunosdto.getTelefone_aluno());
            
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Alunos salvo com sucesso");
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "AlunosDAO"+erro);
            
        }
        
    }
    
    //pesquisar todos /listar
    
     public ArrayList<Aluno> PesquisarAlunos(){
         
        String sql= "select * from alunos";
        conn = new ConnectionFactory().connectionPostgreSQL();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()){
                Aluno objalunosDTO = new Aluno();
                objalunosDTO.setId_aluno(rs.getInt("id_aluno"));
                objalunosDTO.setNome_aluno(rs.getString("nome_aluno"));
                objalunosDTO.setCpf_aluno(rs.getString("cpf_aluno"));
                objalunosDTO.setIdade_aluno(rs.getInt("idade_aluno"));
                objalunosDTO.setMensalidade_aluno(rs.getString("mensalidade_aluno"));
                objalunosDTO.setMultaporatraso_aluno(rs.getString("multaporatraso_aluno"));
                objalunosDTO.setEndereco_aluno(rs.getString("endereco_aluno"));
                objalunosDTO.setTelefone_aluno(rs.getString("telefone_aluno"));
                
                lista.add(objalunosDTO);
            }
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "AlunosDAO pesquisar:"+erro);
        }
        return lista;
    }
    
}