package controller;

import model.Constants;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceRecordsTable;
import view.MainGUI;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class InvoiceSelectionListener implements ListSelectionListener {

    private final MainGUI frameObject;

    public InvoiceSelectionListener(MainGUI frameObject) {
        this.frameObject = frameObject;
    }

    public void valueChanged(ListSelectionEvent e) {
        InvoiceRecordsTable invoiceRecordsTable;
        if (frameObject.getTable1().getSelectedRow() >= 0) {
            InvoiceHeader selectedHeader = frameObject.getInvoicesTable().getInvoices().get(frameObject.getTable1().getSelectedRow());
            ArrayList<InvoiceLine> selectedRecords = selectedHeader.getInvoiceRecords();
            invoiceRecordsTable = new InvoiceRecordsTable(selectedRecords);
            frameObject.setSelectedInvoiceRecords(selectedRecords);
            frameObject.getTable2().setModel(invoiceRecordsTable);
            frameObject.getTextField3().setText(selectedHeader.getCustomerName());
            frameObject.getTextField2().setText(Constants.DATE_FORMAT.format(selectedHeader.getInvoiceDate()));
            frameObject.getTextField1().setText(String.valueOf(selectedHeader.getInvoiceNum()));
            frameObject.getTextField4().setText(String.valueOf(selectedHeader.getTotal()));
        } else {
            frameObject.getTable2().setModel(new InvoiceRecordsTable(new ArrayList<>()));
            frameObject.getTextField3().setText(null);
            frameObject.getTextField2().setText(null);
            frameObject.getTextField1().setText(null);
            frameObject.getTextField4().setText(null);
        }
    }
}