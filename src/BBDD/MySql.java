package BBDD;

import JanilloMySql.JanilloMySqlntb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectofinal.Menu;
import static proyectofinal.Tablas.modConsultaCandidatos;

import static proyectofinal.Tablas.modeloForReglada;
import static proyectofinal.Tablas.modeloIdiomas;
import static proyectofinal.Tablas.modeloOtraFor;

/**
 *
 * @author Jano
 */
public class MySql {

    public static JanilloMySqlntb connect = new JanilloMySqlntb();
   

    public MySql() {
        try {
            //LANZAMOS EL MÉTODO QUE CREARÁ LA CONEXIÓN CON LA BASE DE DATOS GUARDÁNDOLA EN LA VARIABLE 'CONNECT'
            //ADEMÁS DE INSTANCIAR UN OBJETO STATEMENT
            JanilloMySqlntb.conectarse("poyectofinal", "janillo", "Cthulhu+22");
        } catch (Exception ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean loguearse(String usu, String pass) throws SQLException {
        boolean proceso = false;

        ResultSet rs = null;
        String query = "select usuario, pass from usuarios where usuario='" + usu + "' and pass='" + pass + "';";
        rs = connect.st.executeQuery(query);
        if (!rs.isBeforeFirst()) {
            proceso = false;
        } else {
            proceso = true;
        }

        return proceso;
    }

   public static ResultSet listarNvEdu() {
        ResultSet rs = null;
        try {
           
            String Query = "select nivel from niveleducativo";

            rs = connect.st.executeQuery(Query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error alsacar datos" + ex);
        }
        return rs;
    }
  

}
