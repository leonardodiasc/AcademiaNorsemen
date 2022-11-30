package model;

public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    
    public Usuario(int id, String usuario, String senha){
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }
    public Usuario(Usuario usuario){
    this.id = usuario.id;
    this.usuario = usuario.usuario;
    this.senha = usuario.senha;
    }

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(int id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
