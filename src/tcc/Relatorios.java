/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.AsynchronousFillHandle;
import net.sf.jasperreports.engine.fill.AsynchronousFilllListener;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Henrique
 */
public class Relatorios {

    public static AsynchronousFillHandle handle;  

    private Relatorios() {
    }

    private static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/tcc";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Relatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public static void geraRelatorio(String relatorio, Map parametros) {
        Connection con = null;
        try {
            con = getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
//            JasperFillManager.fillReportToFile("C:/Users/Henrique/Documents/NetBeansProjects/Silo/src/forms/" + relatorio + ".jasper", parametros, con);
//            JasperViewer jv = new JasperViewer("C:/Users/Henrique/Documents/NetBeansProjects/Silo/src/forms/" + relatorio + ".jrprint", false, false);
//            jv.setTitle(relatorio);
//            jv.setVisible(true);

            JasperReport rol;
            rol = (JasperReport) JRLoader.loadObject("D:/Java/tcc/src/tcc/" + relatorio + ".jasper");
//            JasperPrint impressao = JasperFillManager.fillReport(rol, parametros, con);
//            JasperViewer.viewReport(impressao, false);

            final MensReport mens = new MensReport(new javax.swing.JFrame(), true);

            handle = AsynchronousFillHandle.createHandle(rol, parametros, con);
            handle.addListener(new AsynchronousFilllListener() {
                public void reportFinished(JasperPrint jp) {
                    mens.setMensagem("Relatório Gerado com Sucesso!");
                    mens.Mostrar(jp);
                }

                public void reportCancelled() {
                    mens.setMensagem("Relatório Cancelado pelo Usuário!");
                }

                public void reportFillError(Throwable thrwbl) {
                    mens.setMensagem("Erro ao Criar Relatório!\n"+thrwbl.getMessage());
                    System.out.println(thrwbl.getMessage());
//                            + thrwbl.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            });

            handle.startFill();

            mens.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
