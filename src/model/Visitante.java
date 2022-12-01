package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author leo
 */
public class Visitante {
    private String nome;
    private int cpf;
    private int valorAserPago;
    private boolean estaAtivo;
    
    public Visitante(String nome, int cpf, int valorAserPago){
        this.nome = nome;
        this.cpf = cpf;
        this.valorAserPago = valorAserPago;
        this.estaAtivo = true;
    }
    public Visitante(String nome, int cpf, int valorAserPago, boolean estaAtivo){
        this.nome = nome;
        this.cpf = cpf;
        this.valorAserPago = valorAserPago;
        this.estaAtivo = estaAtivo;
    }
    public int getCpf(){
        return cpf;
    }
    public String getNome(){
        return nome;
    }
    public int getValorAserPago(){
        return valorAserPago;
    }
    public boolean getEstaAtivo(){
        return estaAtivo;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    public void setValorAserPago(int valorAserPago){
        this.valorAserPago = valorAserPago;
    }
    public void setEstaAtivo(boolean estaAtivo){
        this.estaAtivo = estaAtivo;
    }
}
