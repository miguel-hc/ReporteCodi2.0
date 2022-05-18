
package com.controller;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Copper
 */
public class configuracionController {
 
    public void modificarArchivo(int ncortemenudeo,int ncortemayoreo,int ncorteauxiliar ,String tienda, String encargado, int nboucher) throws IOException{
        try{
            OutputStream salida = new FileOutputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties propiedades = new Properties();
            propiedades.setProperty("ncortemenudeo", ncortemenudeo+"");
            propiedades.setProperty("ncortemayoreo", ncortemayoreo+"");
            propiedades.setProperty("ncorteauxiliar", ncorteauxiliar+"");
            propiedades.setProperty("tienda", tienda);
            propiedades.setProperty("encargado", encargado);
            propiedades.setProperty("numboucher", nboucher+ "");
            propiedades.store(salida, null);
            leerArchivo();
        }catch(java.io.FileNotFoundException e){ 
        }
    }
    
    public void leerArchivo(){
        Component dialog = null;
        try{
            FileInputStream propFile = new FileInputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            System.setProperties(p);
            System.getProperties().list(System.out);
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
    }
    
    public void modificarArchivoBoucher(int nboucher) throws IOException{
        try{
            OutputStream salida = new FileOutputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties propiedades = new Properties();
            propiedades.setProperty("ncortemenudeo", Integer.valueOf(System.getProperty("ncortemenudeo"))+"");
            propiedades.setProperty("ncortemayoreo", Integer.valueOf(System.getProperty("ncortemayoreo"))+"");
            propiedades.setProperty("ncorteauxiliar", Integer.valueOf(System.getProperty("ncorteauxiliar"))+"");
            propiedades.setProperty("tienda", System.getProperty("tienda"));
            propiedades.setProperty("encargado", System.getProperty("encargado"));
            propiedades.setProperty("numboucher", nboucher + 1 + "");
            propiedades.store(salida, null);
            //leerArchivo();
        }catch(java.io.FileNotFoundException e){ 
        }
    }
    
    public void modificarArchivoReporteMenudeo(int nreporte) throws IOException{
        try{
            OutputStream salida = new FileOutputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties propiedades = new Properties();
            
            propiedades.setProperty("ncortemenudeo", nreporte + 1 + "");
            propiedades.setProperty("ncortemayoreo", Integer.valueOf(System.getProperty("ncortemayoreo"))+"");
            propiedades.setProperty("ncorteauxiliar", Integer.valueOf(System.getProperty("ncorteauxiliar"))+"");
            
            propiedades.setProperty("tienda", System.getProperty("tienda"));
            propiedades.setProperty("encargado", System.getProperty("encargado"));
            propiedades.setProperty("numboucher", Integer.valueOf(System.getProperty("numboucher"))+"");
            propiedades.store(salida, null);
            //leerArchivo();
        }catch(java.io.FileNotFoundException e){ 
        }
    }
    
    
    public void modificarArchivoReportemayoreo(int nreporte) throws IOException{
        try{
            OutputStream salida = new FileOutputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties propiedades = new Properties();
            
            propiedades.setProperty("ncortemenudeo", Integer.valueOf(System.getProperty("ncortemenudeo"))+"");
            propiedades.setProperty("ncortemayoreo", nreporte + 1 + "");
            propiedades.setProperty("ncorteauxiliar", Integer.valueOf(System.getProperty("ncorteauxiliar"))+"");
            
            propiedades.setProperty("tienda", System.getProperty("tienda"));
            propiedades.setProperty("encargado", System.getProperty("encargado"));
            propiedades.setProperty("numboucher", Integer.valueOf(System.getProperty("numboucher"))+"");
            propiedades.store(salida, null);
            //leerArchivo();
        }catch(java.io.FileNotFoundException e){ 
        }
    }
    
    
    public void modificarArchivoReporteAuxiliar(int nreporte) throws IOException{
        try{
            OutputStream salida = new FileOutputStream(System.getProperty("user.dir")+"/lib/resource/paramSystem/corep.txt");
            Properties propiedades = new Properties();
            
            propiedades.setProperty("ncortemenudeo", Integer.valueOf(System.getProperty("ncortemenudeo"))+"");
            propiedades.setProperty("ncortemayoreo", Integer.valueOf(System.getProperty("ncortemayoreo"))+"");
            propiedades.setProperty("ncorteauxiliar", nreporte + 1 + "");
            
            propiedades.setProperty("tienda", System.getProperty("tienda"));
            propiedades.setProperty("encargado", System.getProperty("encargado"));
            propiedades.setProperty("numboucher", Integer.valueOf(System.getProperty("numboucher"))+"");
            propiedades.store(salida, null);
            //leerArchivo();
        }catch(java.io.FileNotFoundException e){ 
        }
    }
    
}
