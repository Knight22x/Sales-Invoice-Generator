package model;

import view.MainGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoicesTable extends AbstractTableModel {

    private final ArrayList<InvoiceHeader> invoices;
    private final String[] cols = {"Number", "Date", "Customer", "Total"};

    public InvoicesTable(ArrayList<InvoiceHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    public ArrayList<InvoiceHeader> getInvoices() {
        return invoices;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return switch (col) {
            case 0 -> invoices.get(row).getInvoiceNum();
            case 1 -> Constants.DATE_FORMAT.format(invoices.get(row).getInvoiceDate());
            case 2 -> invoices.get(row).getCustomerName();
            case 3 -> invoices.get(row).getTotal();
            default -> "Empty";
        };
    }
}
