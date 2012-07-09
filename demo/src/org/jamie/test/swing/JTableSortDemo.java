package org.jamie.test.swing;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTableSortDemo {

	 public static void main(String[] args) {
	        Object[][] data = { { "A", "79260.00","ʧ��" }, { "B", "450150.00" ,"����"}, { "C", "100000.00" ,"ces"}, { "D", "8.9" ,"���"} };
	        String columnNames[] = { "Item", "Value","����" };
	        TableModel model = new DefaultTableModel(data, columnNames) {
	            public Class<?> getColumnClass(int column) {
	            return getValueAt(0, column).getClass();
	            }
	        };
	        JTable table = new JTable(model);

	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	     //  sorter.setSortable(1, false);
	      table.setRowSorter(sorter);
	        
	        JScrollPane scrollPane = new JScrollPane(table);
	        JFrame frame = new JFrame("Sorting Table");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(scrollPane);
	        frame.setSize(300, 200);
	        frame.setVisible(true);
	    }

}
