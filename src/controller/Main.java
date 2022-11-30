package controller;

import dao.ConnectionFactory;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        
        Controle controle = new Controle();
        Connection connection = ConnectionFactory.connectionMySQL();
        controle.iniciarSistema(connection);
        ConnectionFactory.closeConnectionMySQL();
    }
    
}
