
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Copper
 */
public class conexion {
    
    String userdb = System.getProperty("userdb");
    String passdb = System.getProperty("passdb");
    String url = System.getProperty("url");
    private Connection conn;
    
    public conexion(){
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            System.out.println("Registrando el Driver");
        }catch(ClassNotFoundException e){
            //System.out.println("No se registro el Driver " + e.getMessage());
        } 
        try{
            conn = (Connection) DriverManager.getConnection(url, userdb, passdb);
            System.out.println("Conexion exitosa");
        }catch(SQLException e){
            System.out.println("Error al conectar " + e.getMessage());
        }  
    }
    
    public Connection getConexion(){
        return conn;
    }
}
