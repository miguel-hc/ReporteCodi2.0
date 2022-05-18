
package cortecaja;

import com.conexion.conexion;
import com.controller.configuracionController;
import com.controller.reporteController;
import com.view.Home;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class CorteCaja {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        Component dialog = null;
        configuracionController cc = new configuracionController();
        try{
            FileInputStream propFile = new FileInputStream(System.getProperty("user.dir")+"//lib//resource//paramSystem//config.txt");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            System.setProperties(p);
            if (System.getProperty("mostrarproperties").compareTo("si") == 0){
                System.getProperties().list(System.out);
            }
            
        }catch(java.io.FileNotFoundException e){
            System.out.println("No se encontro el Archivo");
            JOptionPane.showMessageDialog(dialog,"No se encontro el Archivo "+e);
            System.exit(-1);
        }
        catch(java.io.IOException e){
            System.out.println("Ocurio algun error de I/O");
            JOptionPane.showMessageDialog(dialog,"Ocurio algun error de I/O "+e);
            System.exit(-1);
        }
       
        
        cc.leerArchivo();
        
        Home home = new Home();
        home.setVisible(true);
        
        
        
        reporteController rc = new reporteController();
        System.out.println(System.getProperty("user.dir"));
    }
    
}
