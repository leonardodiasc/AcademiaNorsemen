package model;

public class Professor {
    private int id;
    private String nome;
    private long CPF;
    private float salario;
    private String endereco;
    private long telefone;
    private String horasTrabalhadas;

    public Professor(int id, String nome, long CPF, float salario, String endereco, long telefone, String horasTrabalhadas) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.salario = salario;
        this.endereco = endereco;
        this.telefone = telefone;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Professor(String nome, long CPF, float salario, String endereco, long telefone, String horasTrabalhadas) {
        this.nome = nome;
        this.CPF = CPF; 
        this.salario = salario;
        this.endereco = endereco;
        this.telefone = telefone;
        this.horasTrabalhadas = horasTrabalhadas;
    }
        
    public Professor(int id, String nome, String horasTrabalhadas){
        this.id = id;
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Professor(String nome, String horasTrabalhadas) {
        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Professor(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    
    
}
