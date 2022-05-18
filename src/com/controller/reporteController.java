
package com.controller;

import com.conexion.conexion;
import com.model.provedorModel;
import com.model.reporteModel;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Copper
 */
public class reporteController {
    
    java.sql.Statement s = null;
    java.sql.ResultSet rs = null;
    conexion con = new conexion();
    configuracionController cc = new configuracionController();
    
    public double getZ(String fecha, String usuario) throws SQLException{
        double z = 0;
        double abono = 0;
        reporteModel model = null;
       
        s = con.getConexion().createStatement();       
        rs = s.executeQuery("select sum(CUEN_DET01.importe) as abono from CUEN_DET01 where CUEN_DET01.fecha_apli = '"+fecha+"' and CUEN_DET01.usuario = '"+usuario+"';");
        while(rs.next()){
            z = rs.getDouble("abono");
            System.out.print(rs.getDouble("abono"));
        }
        
        return z;
    }
    
    
    public ArrayList<provedorModel> cargarProvedores() throws SQLException{
        reporteModel model = null;
        ArrayList provedores = new ArrayList();
        provedorModel rmodel;
       
        s = con.getConexion().createStatement();
        rs = s.executeQuery("select PROV01.clave, PROV01.nombre from PROV01");
        
        while(rs.next()){
            rmodel = new provedorModel();
            rmodel.setClave(rs.getString(1));
            rmodel.setNombre(rs.getString(2));
            System.out.print(rs.getString(2));
            provedores.add(rmodel);
        }
        return provedores;
    }
    
    
    public String getIdProvedor(String nombre) throws SQLException{
        String DNI = "";
        reporteModel model = null;
        s = con.getConexion().createStatement();
        rs = s.executeQuery("select PROV01.clave from PROV01 where PROV01.nombre = '"+nombre+"';");
        while(rs.next()){
            DNI = rs.getString(1);
            System.out.print(rs.getString(1));
        }
        return DNI;
    }
    
    public double getTotalCompraProvedor(String DNI, String Fecha) throws SQLException{
        double total = 0;
        reporteModel model = null;
        s = con.getConexion().createStatement();
        rs = s.executeQuery("select compc01.can_tot from compc01 where compc01.cve_clpv = '"+DNI+"' and compc01.fecha_doc = '"+Fecha+"' and compc01.status = 'E' or (compc01.cve_clpv = '"+DNI+"' and compc01.fecha_doc = '"+Fecha+"' and compc01.status = 'O');");
        while(rs.next()){
            total = rs.getInt("can_tot");
            System.out.println(total);
        }
        System.out.println(total);
        return total;
    }
    
    
    public void getReporte(String fecha, String ncaja, double l1, double l2, double l3, double monedas, double ncheque, double voucher, 
            String p1nombre, String p1monto, String p2nombre, String p2monto, String p3nombre, String p3monto, String p4nombre, String p4monto, 
            String p5nombre, String p5monto, String p6nombre, String p6monto, String subcompra,
            String gasto1, double tgasto1, String gasto2, double tgasto2,String gasto3, double tgasto3,String gasto4, double tgasto4,String gasto5, double tgasto5,String gasto6, double tgasto6,String gasto7, double tgasto7
            , String subtotalg, String z, String fs, double por1, double por2, double por3,double por4,double por5, double por6, double totalcompra, double totalgastos, String caja,
            String l1comentario, String l2comentario, String l3comentario, String zcomentario
            ) throws IOException{
        
        
        //cc.leerArchivo();
        try{
            
            String path = System.getProperty("user.dir")+"/Reportes/ReporteCajaHojaCarta.jasper";
            HashMap parametros = new HashMap();
            
            double lmonedas = l1+l2+l3+monedas;
            
            switch (caja){
                case "MENUDEO":
                    cc.modificarArchivoReporteMenudeo(Integer.valueOf(System.getProperty("ncortemenudeo")));
                    parametros.put("nreporte",System.getProperty("ncortemenudeo"));
                    break;
                case "MAYOREO":
                    cc.modificarArchivoReportemayoreo(Integer.valueOf(System.getProperty("ncortemayoreo")));
                    parametros.put("nreporte",System.getProperty("ncortemayoreo"));
                    break;
                case "ADMINISTRADOR":
                    cc.modificarArchivoReportemayoreo(Integer.valueOf(System.getProperty("ncorteauxiliar")));
                    parametros.put("nreporte",System.getProperty("ncorteauxiliar"));
            }
            
            
            parametros.put("tienda",System.getProperty("tienda"));
            parametros.put("fecha",fecha );
            parametros.put("nombrecaja",ncaja);
            parametros.put("l1",formatoMoneda(l1));
            parametros.put("l2",formatoMoneda(l2));
            parametros.put("l3",formatoMoneda(l3));
            parametros.put("monedas",formatoMoneda(monedas));
            parametros.put("supl",formatoMoneda(lmonedas));
            parametros.put("ncheque",formatoMoneda(ncheque));
            parametros.put("suplcheque",formatoMoneda(lmonedas+ncheque));
            parametros.put("voucher",formatoMoneda(voucher));
            parametros.put("subvoucher",formatoMoneda(lmonedas+ncheque+voucher));
            parametros.put("pro1nombre",p1nombre);
            parametros.put("pro1monto",p1monto);
            parametros.put("pro2nombre",p2nombre);
            parametros.put("pro2monto",p2monto);
            parametros.put("pro3nombre",p3nombre);
            parametros.put("pro3monto",p3monto);
            parametros.put("pro4nombre",p4nombre);
            parametros.put("pro4monto",p4monto);
            parametros.put("pro5nombre",p5nombre);
            parametros.put("pro5monto",p5monto);
            parametros.put("pro6nombre",p6nombre);
            parametros.put("pro6monto",p6monto);
            parametros.put("subcompraprovedor",formatoMoneda(lmonedas+totalcompra+ncheque+voucher));
            parametros.put("gasto1",gasto1);
            parametros.put("mgasto1",formatoMoneda(tgasto1));
            parametros.put("gasto2",gasto2);
            parametros.put("mgasto2",formatoMoneda(tgasto2));
            parametros.put("gasto3",gasto3);
            parametros.put("mgasto3",formatoMoneda(tgasto3));
            parametros.put("gasto4",gasto4);
            parametros.put("mgasto4",formatoMoneda(tgasto4));
            parametros.put("gasto5",gasto5);
            parametros.put("mgasto5",formatoMoneda(tgasto5));
            parametros.put("gasto6",gasto6);
            parametros.put("mgasto6",formatoMoneda(tgasto6));
            parametros.put("gasto7",gasto7);
            parametros.put("mgasto7",formatoMoneda(tgasto7));
            parametros.put("subgastos",formatoMoneda(totalgastos+lmonedas+totalcompra+ncheque+voucher));
            parametros.put("subtotalgeneral",formatoMoneda(totalgastos+lmonedas+totalcompra+ncheque+voucher));
            parametros.put("z", z );
            parametros.put("fs", fs );
            parametros.put("%1",por1 + "%" );
            parametros.put("%2",por2 + "%");
            parametros.put("%3",por3 + "%");
            parametros.put("%4",por4 + "%");
            parametros.put("%5",por5 + "%");
            parametros.put("%6",por6 + "%");
            parametros.put("l1comentario",l1comentario);
            parametros.put("l2comentario",l2comentario);
            parametros.put("l3comentario",l3comentario);
            parametros.put("zcomentario",zcomentario);
            
            JasperPrint jprint = JasperFillManager.fillReport(path, parametros, new JREmptyDataSource());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
            
        }catch(JRException e){
            System.out.print(e.toString());
        }
    
    }
    
    
    public void getReporteVoucher(String fecha, double cmr, double cmc, double cmac, double cmar, double car, double cac, double total) throws IOException{
        try{
            cc.modificarArchivoBoucher(Integer.valueOf(System.getProperty("numboucher")));
            
            String path = System.getProperty("user.dir")+"/Reportes/boucher.jasper";
            HashMap parametros = new HashMap();
            
            parametros.put("tienda",System.getProperty("tienda"));
            parametros.put("fecha", fecha);
            parametros.put("contador", System.getProperty("numboucher"));
            parametros.put("cmr", formatoMoneda(cmr));
            parametros.put("cmc", formatoMoneda(cmc));
            parametros.put("cmac", formatoMoneda(cmac));
            parametros.put("cmar", formatoMoneda(cmar));
            parametros.put("car", formatoMoneda(car));
            parametros.put("cac", formatoMoneda(cac));
            parametros.put("total", formatoMoneda(total));
            parametros.put("encargado", System.getProperty("encargado"));
            
            JasperPrint jprint = JasperFillManager.fillReport(path, parametros, new JREmptyDataSource());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
            
        }catch(JRException e){
            System.out.print(e.toString());
        }
    
    }
    
    
    public String formatoMoneda(double Format){   
        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
        return dollarFormat.format(Format);
    }
    
    
}
