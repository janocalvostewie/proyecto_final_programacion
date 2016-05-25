package proyectofinal;

import BBDD.MySql;
import static BBDD.MySql.connect;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import static proyectofinal.Menu.capturaparaexcel;
import static proyectofinal.Menu.exapel1;
import static proyectofinal.Menu.excp;
import static proyectofinal.Menu.exdni;
import static proyectofinal.Menu.exidio1;
import static proyectofinal.Menu.exidio2;
import static proyectofinal.Menu.exloca;
import static proyectofinal.Menu.exnombre;
import static proyectofinal.Menu.exsexo;
import static proyectofinal.Menu.tExportCan;

/**
 *
 * @author Jano
 */
public class Writable {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;

    public void setOutputFile(String inputFile) {

        this.inputFile = inputFile;

    }

    public void write() throws IOException, WriteException, RowsExceededException, SQLException {

        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("es", "ES"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Informe", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);
        workbook.write();
        workbook.close();

    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {

        // CREAR UN FORMATO DE FUENTE
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);

        // DEFINIR EL FORMATO DE LA CELDA CON LO QUE CREAMOS ANTES
        times = new WritableCellFormat(times10pt);

        // UNIR LAS CELDAS
        times.setWrap(true);
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);

        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        timesBoldUnderline.setWrap(true);
        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);

        addCaption(sheet, 0, 0, "ID");
        addCaption(sheet, 1, 0, "Nombre");
        addCaption(sheet, 2, 0, "Apellidos            ");
        addCaption(sheet, 3, 0, "DNI");
        addCaption(sheet, 4, 0, "Dirección               ");
        addCaption(sheet, 5, 0, "Email            ");
        addCaption(sheet, 6, 0, "Fecha Nacimiento");
        addCaption(sheet, 7, 0, "Tel. Fijo");
        addCaption(sheet, 8, 0, "Tel.Móvil");
        addCaption(sheet, 9, 0, "Sexo      ");
        addCaption(sheet, 10, 0, "Localidad");

    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException,
            SQLException {
        String temporal = null;
        capturaparaexcel();
        ResultSet rsid = null;
        ResultSet rsdatos = null;
        Statement st2 = connect.conexion.createStatement();
        int filas = 0;
        int y = 0;
        int idCan = 0;
        int[] columnaDatosPersonales = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        //BUCLE FILAS
        String sqlid = "call consultaExportacionCandidato('" + exnombre + "','" + exapel1 + "','" + exdni + "','" + excp + "','" + exloca + "','" + exsexo + "','" + exidio1 + "','" + exidio2 + "')";
        rsid = MySql.connect.st.executeQuery(sqlid);
        while (rsid.next()) {
            filas = filas + 1;
            idCan = rsid.getInt(1);

            //BUCLE COLUMNAS
            for (int c = 0; c < columnaDatosPersonales.length; c++) {
                y = c + 1;
                temporal = rsid.getString(y);
                addLabel(sheet, c, filas, temporal);

                //FORMATO A LAS CELDAS
                CellView celdas = sheet.getColumnView(c);
                celdas.setAutosize(true);
                sheet.setColumnView(c, celdas);
            }

        }

    }
//AÑADIMOS LAS POSIBILIDAD DE AGREGAR TEXTO A UNA CELDA
    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {

        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);

    }


    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {

        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);

    }

}
