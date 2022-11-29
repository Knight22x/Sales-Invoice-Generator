package controller;

import model.*;
import view.InvoiceRecordView;
import view.InvoiceView;
import view.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionsHandler implements ActionListener {

    private final MainGUI frameObject;
    InvoiceRecordView invoiceRecordView;
    InvoiceView invoiceView;

    public ActionsHandler(MainGUI frameObj) {
        frameObject = frameObj;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Initialize View" -> initializeView();
            case "Load File" -> loadFile();
            case "Save File" -> saveFile();
            case "Create Invoice" -> createInvoice();
            case "Delete Invoice" -> deleteInvoice();
            case "Create Record" -> createRecord();
            case "Delete Record" -> deleteRecord();
            case "OK New Invoice" -> okNewInvoice();
            case "Cancel New Invoice" -> cancelNewInvoice();
            case "OK New Record" -> okNewRecord();
            case "Cancel New Record" -> cancelNewRecord();
            case "Save" -> saveInvoiceEdit();
            case "Cancel" -> cancelInvoiceEdit();
            default -> {
            }
        }
    }

    private void initializeView() {
        ArrayList<InvoiceHeader> invoices = FileOperations.readFiles("assets/invoices.csv", "assets/invoice_records.csv");

        InvoicesTable invoicesTable = new InvoicesTable(invoices);
        frameObject.setInvoicesTable(invoicesTable);
        frameObject.getTable1().setModel(invoicesTable);
    }

    private void createInvoice() {
        JOptionPane.showMessageDialog(null,"Please enter the name and date; if the name is " + "entered only then the date will be auto entered with the date of today.");
        invoiceView = new InvoiceView(this);
        invoiceView.setVisible(true);
    }

    private void deleteInvoice() {
        int[] selectedRows = frameObject.getTable1().getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select an invoice first.");
            return;
        }

        int deletedRows = 0;
        for (int selectedRow : selectedRows) {
            frameObject.getInvoicesTable().getInvoices().remove(selectedRow - deletedRows);
            for (int i = 0; i < frameObject.getInvoicesTable().getInvoices().size(); i++) {
                frameObject.getInvoicesTable().getInvoices().get(i).setInvoiceNum(i + 1);
            }
            frameObject.getTable2().setModel(new InvoiceRecordsTable(new ArrayList<>()));
            deletedRows++;

            // Reset and Refresh
            frameObject.getTextField3().setText((null));
            frameObject.getTextField2().setText((null));
            frameObject.getTextField1().setText((null));
            frameObject.getTextField4().setText((null));
        }

        if (deletedRows > 0) {
            frameObject.getInvoicesTable().fireTableDataChanged();
        }
    }

    private void createRecord() {
        int selectedInvoice = frameObject.getTable1().getSelectedRow();

        if (selectedInvoice == -1) {
            JOptionPane.showMessageDialog(null, "Please select an invoice first.");
            return;
        }

        invoiceRecordView = new InvoiceRecordView(this);
        invoiceRecordView.setVisible(true);
    }

    private void deleteRecord() {
        int selectedInvoice = frameObject.getTable1().getSelectedRow();

        if (selectedInvoice == -1) {
            JOptionPane.showMessageDialog(null, "Please select an invoice first.");
            return;
        }

        int[] selectedRows = frameObject.getTable2().getSelectedRows();

        int deletedRows = 0;
        for (int selectedRow : selectedRows) {
            frameObject.getSelectedInvoiceRecords().remove(selectedRow - deletedRows);
            frameObject.getTable1().setRowSelectionInterval(selectedInvoice, selectedInvoice);
            deletedRows++;
        }

        if (deletedRows > 0) {
            frameObject.getInvoicesTable().fireTableDataChanged();
        }
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();

        JOptionPane.showMessageDialog(null, "Please select the invoices file.");
        int fileResult = fileChooser.showOpenDialog(frameObject);

        if (fileResult == JFileChooser.APPROVE_OPTION) {
            String invoicesPath = fileChooser.getSelectedFile().getAbsolutePath();

            JOptionPane.showMessageDialog(null, "Please select the invoice records file.");
            fileResult = fileChooser.showOpenDialog(frameObject);

            if (fileResult == JFileChooser.APPROVE_OPTION) {
                String recordsPath = fileChooser.getSelectedFile().getAbsolutePath();

                ArrayList<InvoiceHeader> invoices = FileOperations.readFiles(invoicesPath, recordsPath);

                InvoicesTable invoicesTable = new InvoicesTable(invoices);
                frameObject.setInvoicesTable(invoicesTable);
                frameObject.getTable1().setModel(invoicesTable);
            }
        }
    }

    private void saveFile(){
        FileOperations.writeFiles(frameObject.getInvoicesTable().getInvoices());
    }

    private void okNewInvoice() {
        invoiceView.setVisible(false);

        String customerName = invoiceView.getCustomerNameField().getText();
        String invoiceDateString = invoiceView.getInvoiceDateField().getText();

        Date invoiceDate;

        if (invoiceDateString == null || invoiceDateString.isBlank()) {
            JOptionPane.showMessageDialog(null, "No date was entered. Using today's date instead.");
            invoiceDate = new Date();
        } else {
            try {
                invoiceDate = Constants.DATE_FORMAT.parse(invoiceDateString);
            } catch (ParseException e) {
                Logger.getLogger(ActionsHandler.class.getName()).log(Level.WARNING, "Entered date format was incorrect. Using today's date instead.", e);
                JOptionPane.showMessageDialog(null, "Entered date format was incorrect. Using today's date instead.");
                invoiceDate = new Date();
            }
        }

        InvoiceHeader invoice = new InvoiceHeader(frameObject.getInvoicesTable().getInvoices().size() + 1, customerName, invoiceDate);
        frameObject.getInvoicesTable().getInvoices().add(invoice);
        frameObject.getInvoicesTable().fireTableDataChanged();

        invoiceView.dispose();
        invoiceView = null;
    }

    private void cancelNewInvoice() {
        invoiceView.setVisible(false);
        invoiceView.dispose();
        invoiceView = null;
    }

    private void okNewRecord() {
        invoiceRecordView.setVisible(false);

        try {
            String ItemName = invoiceRecordView.getItemNameField().getText();
            int itemCount = Integer.parseInt(invoiceRecordView.getItemCountField().getText());
            double itemPrice = Double.parseDouble(invoiceRecordView.getItemPriceField().getText());

            int selectedInvoice = Math.max(0, frameObject.getTable1().getSelectedRow());
            InvoiceHeader invoice = frameObject.getInvoicesTable().getInvoices().get(selectedInvoice);

            InvoiceLine record = new InvoiceLine(ItemName, itemPrice, itemCount, invoice);
            invoice.getInvoiceRecords().add(record);
            frameObject.getInvoicesTable().fireTableDataChanged();

            ArrayList<InvoiceLine> invoiceRecords = invoice.getInvoiceRecords();
            frameObject.setSelectedInvoiceRecords(invoiceRecords);
            frameObject.getTable2().setModel(new InvoiceRecordsTable(invoiceRecords));
            frameObject.getTextField3().setText(invoice.getCustomerName());
            frameObject.getTextField2().setText(Constants.DATE_FORMAT.format(invoice.getInvoiceDate()));
            frameObject.getTextField1().setText(String.valueOf(invoice.getInvoiceNum()));
            frameObject.getTextField4().setText(String.valueOf(invoice.getTotal()));

            invoiceRecordView.dispose();
            invoiceRecordView = null;
        } catch (NumberFormatException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.WARNING, "Please enter correct count and price.", e);
            JOptionPane.showMessageDialog(null, "Please enter correct count and price.");

            invoiceRecordView.setVisible(true);
        }
    }

    private void cancelNewRecord() {
        invoiceRecordView.setVisible(false);
        invoiceRecordView.dispose();
        invoiceRecordView = null;
    }

    private void saveInvoiceEdit() {
        int selectedInvoice = frameObject.getTable1().getSelectedRow();

        if (selectedInvoice == -1) {
            JOptionPane.showMessageDialog(null, "Please select an invoice first.");
            return;
        }

        InvoiceHeader invoice = frameObject.getInvoicesTable().getInvoices().get(selectedInvoice);

        try {
            Date invoiceDate = Constants.DATE_FORMAT.parse(frameObject.getTextField2().getText());

            String customerName = frameObject.getTextField3().getText();

            invoice.setCustomerName(customerName);
            invoice.setInvoiceDate(invoiceDate);

            frameObject.getInvoicesTable().fireTableRowsUpdated(selectedInvoice, selectedInvoice);
            FileOperations.writeFiles(frameObject.getInvoicesTable().getInvoices());

            JOptionPane.showMessageDialog(null, "Saved changes.");
        } catch (ParseException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.WARNING, "Entered date format was incorrect. Please fix the date format to dd-MM-yyyy first.", e);
            JOptionPane.showMessageDialog(null, "Entered date format was incorrect. Please fix the date format to dd-MM-yyyy first.");
        }
        catch (InvalidPathException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Invalid File Path.", e);
            JOptionPane.showMessageDialog(null, "Invalid File Path.");
        }
    }

    private void cancelInvoiceEdit() {
        int selectedInvoice = frameObject.getTable1().getSelectedRow();

        if (selectedInvoice == -1)
        {
            JOptionPane.showMessageDialog(null, "Please select an invoice first.");
            return;
        }

        InvoiceHeader invoice = frameObject.getInvoicesTable().getInvoices().get(selectedInvoice);

        frameObject.getTextField2().setText(Constants.DATE_FORMAT.format(invoice.getInvoiceDate()));
        frameObject.getTextField3().setText(invoice.getCustomerName());
    }

}
