package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceRecordsTable extends AbstractTableModel {

    private final String[] cols = {"Product", "Price", "Count", "Total"};
    ArrayList<InvoiceLine> invoiceRecordsList;

    public InvoiceRecordsTable(ArrayList<InvoiceLine> list) {
        this.invoiceRecordsList = list;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public int getRowCount() {
        return invoiceRecordsList.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return switch (col) {
            case 0 -> invoiceRecordsList.get(row).getItemName();
            case 1 -> invoiceRecordsList.get(row).getItemPrice();
            case 2 -> invoiceRecordsList.get(row).getCount();
            case 3 -> invoiceRecordsList.get(row).getTotalItemPrice();
            default -> "Empty";
        };
    }
}
