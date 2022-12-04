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
    private long cpf;
    private int valorAserPago;
    
    public Visitante(String nome, long cpf, int valorAserPago){
        this.nome = nome;
        this.cpf = cpf;
        this.valorAserPago = valorAserPago;

    }

    public Visitante(String nome) {
        this.nome = nome;
    }
    
    public long getCpf(){
        return cpf;
    }
    public String getNome(){
        return nome;
    }
    public int getValorAserPago(){
        return valorAserPago;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(long cpf){
        this.cpf = cpf;
    }
    public void setValorAserPago(int valorAserPago){
        this.valorAserPago = valorAserPago;
    }
}
