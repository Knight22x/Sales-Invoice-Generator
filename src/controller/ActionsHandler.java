package controller;

import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceRecordsTable;
import model.InvoicesTable;
import utils.InvoiceUtils;
import view.InvoiceRecordView;
import view.InvoiceView;
import view.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ActionsHandler implements ActionListener {

    private final MainGUI frameObject;
    InvoiceRecordView invoiceRecordView;
    InvoiceView invoiceView;
    private int selectedInvoice;

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
            default -> {
            }
        }
    }

    private void initializeView() {
        loadInvoices("assets/invoices.csv", "assets/invoice_records.csv");
    }

    private void loadInvoices(String invoicesPath, String recordsPath) {
        try {
            List<String> invoiceLines = Files.lines(Paths.get(invoicesPath)).collect(Collectors.toList());

            ArrayList<InvoiceHeader> invoices = new ArrayList<>();
            for (String invoiceLine : invoiceLines) {
                String[] parts = invoiceLine.split(",");
                InvoiceHeader invoice = new InvoiceHeader(Integer.parseInt(parts[0]), parts[2], MainGUI.simpleDateFormat.parse(parts[1]));
                invoices.add(invoice);
            }

            List<String> recordLines = Files.lines(Paths.get(recordsPath)).collect(Collectors.toList());

            for (String recordLine : recordLines) {
                String[] parts = recordLine.split(",");
                int invoiceNum = Integer.parseInt(parts[0]);
                InvoiceHeader invoice = InvoiceUtils.getInvoice(invoices, invoiceNum);
                InvoiceLine invoiceRecord = new InvoiceLine(parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), invoice);
                assert invoice != null;
                invoice.getInvoiceRecords().add(invoiceRecord);
            }

            InvoicesTable invoicesTable = new InvoicesTable(invoices);
            frameObject.setInvoicesTable(invoicesTable);
            frameObject.getTable1().setModel(invoicesTable);
        } catch (IOException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Wrong File Format", e);
            JOptionPane.showMessageDialog(null, "Wrong File Format");
        } catch (ParseException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Failed to parse file(s)", e);
            JOptionPane.showMessageDialog(null, "Failed to parse file(s)");
        }
    }

    private void createInvoice() {
        JOptionPane.showMessageDialog(null, "Please enter the name and date; if the name is " + "entered only then the date will be auto entered with the date of today.");
        invoiceView = new InvoiceView(this);
        invoiceView.setVisible(true);
    }

    private void deleteInvoice() {
        int[] selectedRows = frameObject.getTable1().getSelectedRows();

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
        invoiceRecordView = new InvoiceRecordView(this);
        invoiceRecordView.setVisible(true);
    }

    private void deleteRecord() {
        int selectedInvoice = frameObject.getTable1().getSelectedRow();

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
                loadInvoices(invoicesPath, recordsPath);
            }
        }
    }

    private void saveFile() {
        ArrayList<InvoiceHeader> invoices = frameObject.getInvoicesTable().getInvoices();
        JFileChooser chooser = new JFileChooser();

        StringBuilder invoicesSb = new StringBuilder();
        StringBuilder recordsSb = new StringBuilder();
        for (InvoiceHeader invoice : invoices) {
            invoicesSb.append(invoice.toString()).append("\n");
            for (InvoiceLine record : invoice.getInvoiceRecords()) {
                recordsSb.append(record.toString()).append("\n");
            }
        }

        invoicesSb = new StringBuilder(invoicesSb.substring(0, invoicesSb.length() - 1));
        recordsSb = new StringBuilder(recordsSb.substring(0, recordsSb.length() - 1));

        JOptionPane.showMessageDialog(null, "Saving the invoices file; please save the file" +
                " with .csv extension.");

        int saveResult = chooser.showSaveDialog(frameObject);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File invoiceFile = chooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(invoiceFile);
                writer.write(invoicesSb.toString());
                writer.close();
            } catch (IOException e) {
                Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Cannot save file due to having" +
                        "empty data", e);
                JOptionPane.showMessageDialog(null, "Cannot save file due to having" +
                        "empty data");
            }
        }

        JOptionPane.showMessageDialog(null, "Saving the invoice records file; please save the file" +
                " with .csv extension.");

        saveResult = chooser.showSaveDialog(frameObject);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File recordsFile = chooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(recordsFile);
                writer.write(recordsSb.toString());
                writer.close();
            } catch (IOException e) {
                Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Error", e);
            }
        }
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
                invoiceDate = MainGUI.simpleDateFormat.parse(invoiceDateString);
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

        String ItemName = invoiceRecordView.getItemNameField().getText();
        int itemCount = Integer.parseInt(invoiceRecordView.getItemCountField().getText());
        double itemPrice = Double.parseDouble(invoiceRecordView.getItemPriceField().getText());

        InvoiceHeader invoice;
        if (frameObject.getTable1().getSelectedRow() >= 0) {
            selectedInvoice = frameObject.getTable1().getSelectedRow();
            invoice = frameObject.getInvoicesTable().getInvoices().get(frameObject.getTable1().getSelectedRow());
        } else {
            invoice = frameObject.getInvoicesTable().getInvoices().get(selectedInvoice);
        }

        InvoiceLine record = new InvoiceLine(ItemName, itemPrice, itemCount, invoice);
        invoice.getInvoiceRecords().add(record);
        frameObject.getInvoicesTable().fireTableDataChanged();

        ArrayList<InvoiceLine> invoiceRecords = invoice.getInvoiceRecords();
        frameObject.setSelectedInvoiceRecords(invoiceRecords);
        frameObject.getTable2().setModel(new InvoiceRecordsTable(invoiceRecords));
        frameObject.getTextField3().setText(invoice.getCustomerName());
        frameObject.getTextField2().setText(String.valueOf(invoice.getInvoiceDate()));
        frameObject.getTextField1().setText(String.valueOf(invoice.getInvoiceNum()));
        frameObject.getTextField4().setText(String.valueOf(invoice.getTotal()));

        invoiceRecordView.dispose();
        invoiceRecordView = null;
    }

    private void cancelNewRecord() {
        invoiceRecordView.setVisible(false);
        invoiceRecordView.dispose();
        invoiceRecordView = null;
    }
}
