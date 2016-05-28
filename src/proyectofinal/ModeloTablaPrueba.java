/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jano
 */
public class ModeloTablaPrueba extends DefaultTableModel{
    
    @Override
    public boolean isCellEditable(int row, int col)
        { return true; }
    @Override
    public void setValueAt(Object value, int row, int col) {
        Object[][] rowData = null;
        rowData[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        Object data = model.getValueAt(row, column);
  
//now you have the data in the cell and the place in the grid where the 
  
//cell is so you can use the data as you want
    }
}
