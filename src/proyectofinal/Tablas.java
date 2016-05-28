package proyectofinal;

import static BBDD.MySql.connect;
import BBDD.MySqlConsultas;
import BBDD.MySqlInserciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static proyectofinal.Menu.cbNivelEdu;
import static proyectofinal.Menu.idCandidato;

import static proyectofinal.Menu.tConsultaCandidatos;
import static proyectofinal.Menu.tExportCan;
import static proyectofinal.Menu.*;
import static proyectofinal.Menu.tIdiomas;
import static proyectofinal.Menu.tOtraFor;

/**
 *
 * @author Jano
 */
public class Tablas extends DefaultTableModel {

    public static DefaultTableModel modConsultaCandidatos = new DefaultTableModel();
    public static DefaultTableModel modeloForReglada = new DefaultTableModel();
    public static DefaultTableModel modeloIdiomas = new DefaultTableModel();
    public static DefaultTableModel modeloOtraFor = new DefaultTableModel();
    public static DefaultTableModel modeloExpCandi = new DefaultTableModel();
 
    public static void tablaConsultaCandidatos() {

        //AÑADIMOS LA CABECERA DE LA TABLA
        modConsultaCandidatos.addColumn("ID");
        modConsultaCandidatos.addColumn("NOMBRE");
        modConsultaCandidatos.addColumn("APELLIDOS");
        modConsultaCandidatos.addColumn("DNI");
        modConsultaCandidatos.addColumn("DIRECCIÓN");
        modConsultaCandidatos.addColumn("SEXO");
        modConsultaCandidatos.addColumn("F. NACIMIENTO");

        //APLICAMOS EL MODELO A LA JTABLE QUE TENEMOS EN EL JFRAME
        Menu.tConsultaCandidatos.setModel(modConsultaCandidatos);
        tConsultaCandidatos.getColumnModel().getColumn(0).setMaxWidth(25);
        tConsultaCandidatos.getColumnModel().getColumn(1).setMaxWidth(100);
        tConsultaCandidatos.getColumnModel().getColumn(3).setMaxWidth(80);
        tConsultaCandidatos.getColumnModel().getColumn(5).setMaxWidth(60);
        tConsultaCandidatos.getColumnModel().getColumn(6).setMaxWidth(80);
        Menu.tConsultaCandidatos.setModel(modConsultaCandidatos);

    }

    public static void tablaIdiomas() {

        modeloIdiomas.addColumn("IDIOMA");
        modeloIdiomas.addColumn("NIVEL");
        modeloIdiomas.addColumn("TÍTULO");
        modeloIdiomas.addColumn("INSTITUCIÓN");

        tIdiomas.setModel(modeloIdiomas);
        tIdiomas.getColumnModel().getColumn(0).setMaxWidth(100);
        tIdiomas.getColumnModel().getColumn(1).setMaxWidth(60);

        tIdiomas.setModel(modeloIdiomas);
    }

    public static void tablaFormacionReglada() {

        modeloForReglada.addColumn("AÑO");
        modeloForReglada.addColumn("TÍTULO");
        modeloForReglada.addColumn("INSTITUCIÓN");
        modeloForReglada.addColumn("NIVEL");
   
        tFormacionReglada.setModel(modeloForReglada);
        tFormacionReglada.getColumnModel().getColumn(0).setMaxWidth(60);
//        tFormacionReglada.getColumnModel().getColumn(3).setMaxWidth(220);
        tFormacionReglada.setModel(modeloForReglada);

    }
    public static  void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

       String cambio=String.valueOf(data);
      
    }
    public static void tablaOtraFormacion() {

        modeloOtraFor.addColumn("AÑO");
        modeloOtraFor.addColumn("DURACIÓN");
        modeloOtraFor.addColumn("DESCRIPCION");
        modeloOtraFor.addColumn("INSTITUCIÓN");
        tOtraFor.setModel(modeloOtraFor);
        tOtraFor.getColumnModel().getColumn(0).setMaxWidth(60);
        tOtraFor.getColumnModel().getColumn(1).setMaxWidth(85);
        tOtraFor.setModel(modeloOtraFor);
    }

    public static void tablaExportCan() {
        modeloExpCandi.addColumn("ID");
        modeloExpCandi.addColumn("NOMBRE");
        modeloExpCandi.addColumn("APELLIDOS");
        modeloExpCandi.addColumn("DNI");

        tExportCan.setModel(modeloExpCandi);
        tExportCan.getColumnModel().getColumn(0).setMaxWidth(25);
        tExportCan.getColumnModel().getColumn(3).setMaxWidth(80);
        tExportCan.setModel(modeloExpCandi);

    }

    public static void elimFilasExport() {

        while (tExportCan.getRowCount() > 0) {
            ((DefaultTableModel) tExportCan.getModel()).removeRow(0);
        }

    }

    public static void elimFilasFormacion() {

        while (tIdiomas.getRowCount() > 0) {
            ((DefaultTableModel) tIdiomas.getModel()).removeRow(0);
        }
        while (tOtraFor.getRowCount() > 0) {
            ((DefaultTableModel) tOtraFor.getModel()).removeRow(0);
        }
        while (tFormacionReglada.getRowCount() > 0) {
            ((DefaultTableModel) tFormacionReglada.getModel()).removeRow(0);
        }

    }

    public static void elimFilas() {

        while (tConsultaCandidatos.getRowCount() > 0) {
            ((DefaultTableModel) tConsultaCandidatos.getModel()).removeRow(0);
        }
        while (tIdiomas.getRowCount() > 0) {
            ((DefaultTableModel) tIdiomas.getModel()).removeRow(0);
        }
        while (tOtraFor.getRowCount() > 0) {
            ((DefaultTableModel) tOtraFor.getModel()).removeRow(0);
        }
        while (tFormacionReglada.getRowCount() > 0) {
            ((DefaultTableModel) tFormacionReglada.getModel()).removeRow(0);
        }
        while (tExportCan.getRowCount() > 0) {
            ((DefaultTableModel) tExportCan.getModel()).removeRow(0);
        }

    }

    public static void recogerFormacionReglada() throws SQLException {
        int filas = tFormacionReglada.getRowCount() - 1;
        ResultSet rsnv = null;
        String niv = null;
        String anho = String.valueOf(modeloForReglada.getValueAt(filas, 0));
        String titulo = String.valueOf(modeloForReglada.getValueAt(filas, 1));
        String institucion = String.valueOf(modeloForReglada.getValueAt(filas, 2));
        Statement st2 = connect.conexion.createStatement();
        String sqlnivel = "call niveducativo(" + idCandidato + ",'" + titulo + "','" + institucion + "');";
        rsnv = st2.executeQuery(sqlnivel);
        while (rsnv.next()) {
            niv = rsnv.getString(1);
        }

        if (anho != null && titulo != null && institucion != null) {
            MySqlInserciones.insertForReg(idCandidato, anho, titulo, institucion, niv);
            Tablas.elimFilas();
            MySqlConsultas.rellenaTablas(idCandidato);
        }
    }

    public static void recogerFormacionNOReglada() throws SQLException {
        int filas = tOtraFor.getRowCount() - 1;

        String anho = String.valueOf(modeloOtraFor.getValueAt(filas, 0));
        String duracion = String.valueOf(modeloOtraFor.getValueAt(filas, 1));
        String descripcion = String.valueOf(modeloOtraFor.getValueAt(filas, 2));
        String institucion = String.valueOf(modeloOtraFor.getValueAt(filas, 3));
        if (anho != null && duracion != null && descripcion != null) {
            MySqlInserciones.insertForNOReg(idCandidato, anho, duracion, descripcion, institucion);
        }
        Tablas.elimFilas();
        MySqlConsultas.rellenaTablas(idCandidato);
    }

    public static void recogerIdiomas() throws SQLException {

        int filas = tIdiomas.getRowCount() - 1;

        String idioma = String.valueOf(modeloIdiomas.getValueAt(filas, 0));
        String nivel = String.valueOf(modeloIdiomas.getValueAt(filas, 1));
        String titulo = String.valueOf(modeloIdiomas.getValueAt(filas, 2));
        String institucion = String.valueOf(modeloIdiomas.getValueAt(filas, 3));

        if (idioma != null && nivel != null && titulo != null) {
            MySqlInserciones.insertIdiomas(idCandidato, idioma, nivel, titulo, institucion);
        }
        Tablas.elimFilas();
        MySqlConsultas.rellenaTablas(idCandidato);
    }

    public static void scrollNvEdu() {

        ResultSet rs = BBDD.MySql.listarNvEdu();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        modelo.addElement("------");
        try {
            while (rs.next()) {
                String stringTemporal = (rs.getString(1));
                modelo.addElement(stringTemporal);
            }
            cbNivelEdu.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Tablas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
