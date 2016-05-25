
package BBDD;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jano
 */
public class MySqlBorrados extends MySql{
    
    
    public static void borradoCascada(int idCan){
    
      String Query = "call borradocascada("+idCan+");";
        try {

            MySql.connect.st.executeQuery(Query);
            JOptionPane.showMessageDialog(null, "Candidato Borrado");
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Candidato NO borrado");
        }
    
    
    }
    public static void borraForR(int idCandidato, String anho, String titulo, String institucion){
    String sql ="delete from formacion where idCandidato="+idCandidato
            +" and anho='"+anho
            +"' and descripcion='"+titulo
            +"' and institucion='"+institucion
            +"' and reglada='S';";
        try {
            MySql.connect.st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlBorrados.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     public static void borraForNR(int idCandidato,String anho, String duracion, String descrip, String institu){
    
     String sql ="delete from formacion where idCandidato="+idCandidato
            +" and anho='"+anho
             +"' and duracion='"+duracion
            +"' and descripcion='"+descrip
            +"' and institucion='"+institu
            +"' and reglada='N';";
        try {
            MySql.connect.st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlBorrados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static void borraIdiomas(int idCandidato,String idioma, String nivel, String titulo, String institu){
      String sql ="delete from idiomas where idCandidato="+idCandidato
            +" and idioma='"+idioma
             +"' and nivel='"+nivel
            +"' and titulo='"+titulo
            +"' and institucion='"+institu+"';";
           
        try {
            MySql.connect.st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlBorrados.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
