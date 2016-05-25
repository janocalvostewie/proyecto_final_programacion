
package BBDD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Jano
 */
public class MySqlInserciones extends MySql{
    
     public static void insertCandidatos(String nombre, String apellidos, String direccion, String email, String fnacimiento,
            String telfijo, String telmovil, String dni, String sexo, String rutacv,String codpos,String localidad ) {

        String Query = "call insertcandidato('" + nombre + "','" + apellidos + "','" + direccion + "','"
                + email + "','" + fnacimiento + "','" + telfijo + "','" + telmovil + "','" + dni + "','" + sexo + "','"
                + rutacv + "','"+codpos+"','"+localidad+"');";
        try {

            MySql.connect.st.executeQuery(Query);
            JOptionPane.showMessageDialog(null, "Candidato añadido");
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Candidato NO añadido");
        }

    }
    
     
     
       public static void modiCandidato(int idCandidato,String nombre, String apellidos, String direccion, String email, String fnacimiento,
            String telfijo, String telmovil, String dni, String sexo, String rutacv,String codpos,String localidad ){
    
    
        String Query = "call updateInfoBasica("+idCandidato+",'" + nombre + "','" + apellidos + "','" + direccion + "','"
                + email + "','" + fnacimiento + "','" + telfijo + "','" + telmovil + "','" + dni + "','" + sexo + "','"
                + rutacv + "','"+codpos+"','"+localidad+"');";
        try {

            MySql.connect.st.executeQuery(Query);
            JOptionPane.showMessageDialog(null, "Candidato modificado");
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Candidato NO modificado");
        }
    
       }
       
       public static void insertForReg(int idCan, String anho, String titulo, String institu){
       String sql="insert into formacion(idCandidato,anho,descripcion, institucion, reglada) values "
               +"("+idCan+",'"+anho+"',UPPER('"+titulo+"'),UPPER('"+institu+"'),'S')";
         try {
             MySql.connect.st.executeUpdate(sql);
         } catch (SQLException ex) {
             Logger.getLogger(MySqlInserciones.class.getName()).log(Level.SEVERE, null, ex);
         }
       
       }
       
        public static void insertForNOReg(int idCan, String anho,String duracion, String titulo, String institu){
       String sql="insert into formacion(idCandidato,anho,duracion,descripcion, institucion, reglada) values "
               +"("+idCan+",'"+anho+"',UPPER('"+duracion+"'),UPPER('"+titulo+"'),UPPER('"+institu+"'),'N')";
         try {
             MySql.connect.st.executeUpdate(sql);
         } catch (SQLException ex) {
             Logger.getLogger(MySqlInserciones.class.getName()).log(Level.SEVERE, null, ex);
         }
       
       }
         public static void insertIdiomas(int idCan, String idioma,String nivel, String titulo, String institu){
       String sql="insert into idiomas(idCandidato,idioma,nivel,titulo, institucion) values "
               +"("+idCan+",UPPER('"+idioma+"'),UPPER('"+nivel+"'),UPPER('"+titulo+"'),UPPER('"+institu+"'))";
         try {
             MySql.connect.st.executeUpdate(sql);
         } catch (SQLException ex) {
             Logger.getLogger(MySqlInserciones.class.getName()).log(Level.SEVERE, null, ex);
         }
       
       }
}