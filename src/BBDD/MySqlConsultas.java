/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import static JanilloMySql.JanilloMySqlntb.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectofinal.Menu;
import proyectofinal.Tablas;
import static proyectofinal.Tablas.modConsultaCandidatos;
import static proyectofinal.Tablas.modeloExpCandi;

import static proyectofinal.Tablas.modeloForReglada;
import static proyectofinal.Tablas.modeloIdiomas;
import static proyectofinal.Tablas.modeloOtraFor;

/**
 *
 * @author Jano
 */
public class MySqlConsultas extends MySql {

    public static ResultSet rs = null;

    public static void consultaCandidatos(String nombre, String apellidos, String direccion, String email, String fnacimiento,
            String telfijo, String telmovil, String dni, String sexo, String codpos, String localidad) {
//     String Query = "call poyectofinal.consultacandidatos('"+nombre+"','"+apellidos+"','"+ direccion+"','"+
//            email+"','"+fnacimiento+"','"+ telfijo+"','"+telmovil +"','"+ dni +"','"+  sexo+ "');";
        String Query = "select id, nombre, apellidos, dni,direccion, sexo, fnacimiento from candidatos where "
                + "nombre like UPPER('%" + nombre + "%')"
                + "and apellidos like UPPER('%" + apellidos + "%') "
                + "and direccion like UPPER('%" + direccion + "%') "
                + "and email like UPPER('%" + email + "%')"
                + "and fnacimiento like UPPER('%" + fnacimiento + "%') "
                + "and telfijo like UPPER('%" + telfijo + "%') "
                + "and telmovil like UPPER('%" + telmovil + "%') "
                + "and dni like UPPER('%" + dni + "%') "
                + "and sexo like UPPER('%" + sexo + "%') "
                + "and sexo like UPPER('%" + codpos + "%')"
                + "and sexo like UPPER('%" + localidad + "%');";
        try {

            rs = MySql.connect.st.executeQuery(Query);
            String[] datos = new String[7];
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);

                Tablas.modConsultaCandidatos.addRow(datos);

            }
            Menu.tConsultaCandidatos.setModel(modConsultaCandidatos);

        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Consulta Fallida");
        }

    }

    public static String[] consultaMinuciosa(int idCandidato) {
        String[] datos = new String[13];
        String Query = "select * from candidatos where id=" + idCandidato + ";";

        try {

            rs = MySql.connect.st.executeQuery(Query);

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt(1));
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Consulta Fallida");
        }

        return datos;
    }

    public static void rellenaIdiomas(int idCan) {
        ResultSet rsIdiomas = null;
        //CONSULTA DE LOS IDIOMAS Y CREACIÓN DE SU TABLA
        String sqlIdiomas = "call consultaIdiomas(" + idCan + ")";

        try {
            rsIdiomas = MySql.connect.st.executeQuery(sqlIdiomas);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] idiomas = new String[4];
        try {
            while (rsIdiomas.next()) {
                idiomas[0] = rsIdiomas.getString(1);
                idiomas[1] = rsIdiomas.getString(2);
                idiomas[2] = rsIdiomas.getString(3);
                idiomas[3] = rsIdiomas.getString(4);
                modeloIdiomas.addRow(idiomas);
            }
            modeloIdiomas.addRow(new String[4]);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void rellenaForR(int idCan) throws SQLException {
        ResultSet rsForR = null;
        ResultSet rsnv = null;

        //CONSULTA DE LA FORMACIÓN REGLADA Y CREACION DE SU TABLA
        String sqlForR = "call consultaFormacionReglada(" + idCan + ")";
        try {
            rsForR = MySql.connect.st.executeQuery(sqlForR);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] formacionR = new String[4];
        try {
            while (rsForR.next()) {
                Statement st2 = connect.conexion.createStatement();
                formacionR[0] = rsForR.getString(1);
                formacionR[1] = rsForR.getString(2);
                formacionR[2] = rsForR.getString(3);
                String sqlnivel = "call niveducativo(" + idCan + ",'" + formacionR[1] + "','" + formacionR[2] + "');";
                rsnv = st2.executeQuery(sqlnivel);
                while (rsnv.next()) {
                    formacionR[3] = rsnv.getString(1);
                }
//                formacionR[3]=
                modeloForReglada.addRow(formacionR);
            }
            modeloForReglada.addRow(new String[3]);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void rellenaForNR(int idCan) {

        ResultSet rsForNR = null;
        //CONSULTA DE LA FORMACION NO REGLADA Y CREACION DE SU TABLA
        String sqlForNR = "call consultaForNOReglada(" + idCan + ")";
        try {
            rsForNR = MySql.connect.st.executeQuery(sqlForNR);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] formacionNR = new String[4];
        try {
            while (rsForNR.next()) {
                formacionNR[0] = rsForNR.getString(1);
                formacionNR[1] = rsForNR.getString(2);
                formacionNR[2] = rsForNR.getString(3);
                formacionNR[3] = rsForNR.getString(4);

                modeloOtraFor.addRow(formacionNR);
            }
            modeloOtraFor.addRow(new String[4]);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public static ResultSet rellenaExCandiR(String nombre, String ape1, String dni, String codpo, String localidad, String sex) {
//
//        ResultSet rsExCan = null;
//
//        String sqlExCan = "call consultaExportacionCandidato('" + nombre + "','" + ape1 + "','" + dni + "','" + codpo + "','" + localidad + "','" + sex + "')";
//        try {
//            rsExCan = MySql.connect.st.executeQuery(sqlExCan);
//        } catch (SQLException ex) {
//            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rsExCan;
//    }

    public static void rellenaExCandiRTabla(String nombre, String ape1, String dni, String codpo, String localidad, String sex,String exidio1, String exidio2) {

   ResultSet rsExCan = null;
 
       
        String sqlExCan = "call consultaExportacionCandidato('"+ nombre +"','"+ape1+"','"+dni+"','"+codpo+"','"+localidad+"','"+sex+"','"+exidio1+"','"+exidio2+"')";
        try {
            rsExCan = MySql.connect.st.executeQuery(sqlExCan);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }     
        String[] exCan = new String[7];         
          
        try {
            while (rsExCan.next()) {
                exCan[0] = String.valueOf(rsExCan.getInt(1));
              
                exCan[1] = rsExCan.getString(2);
                exCan[2] = rsExCan.getString(3);
                exCan[3] = rsExCan.getString(4);
                modeloExpCandi.addRow(exCan);
               
            }
            modeloOtraFor.addRow(new String[4]);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void rellenaTablas(int idCan) throws SQLException {

        rellenaIdiomas(idCan);
        rellenaForR(idCan);
        rellenaForNR(idCan);

    }

    public static String consultaCV(int idCan) {
        ResultSet rs1 = null;
        String rutacv = null;
        String sql = "select rutacv from candidatos where id=" + idCan + ";";

        try {
            rs1 = MySql.connect.st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs1.next()) {
                rutacv = rs1.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rutacv;
    }

    public static ResultSet exceldatos(int idCan) throws SQLException {
Statement stdatos=connect.conexion.createStatement();
        String sql = "select nombre, apellidos, dni, direccion,email,telfijo, telmovil,fnacimiento,localidad from candidatos where id=" + idCan + ";";
        rs = stdatos.executeQuery(sql);
        return rs;
    }

    public static ResultSet excelidiomas(int idCan) throws SQLException {
Statement stidi=connect.conexion.createStatement();
        String sql = "select idioma, nivel from idiomas where id=" + idCan + ";";
        rs = stidi.executeQuery(sql);
        return rs;
    }

    public static ResultSet excelformacion(int idCan) throws SQLException {
       
        String sql = "select descripcion from formacion where idCandidato=" + idCan + ";";
        String niv=null;
        rs = MySql.connect.st.executeQuery(sql);
   
return rs;
    }

}
