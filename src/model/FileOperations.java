package model;

import controller.ActionsHandler;
import utils.InvoiceUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class FileOperations {

    public static ArrayList<InvoiceHeader> readFiles(String invoicesPath, String recordsPath) {
        ArrayList<InvoiceHeader> invoices = new ArrayList<>();

        try {
            List<String> invoiceLines = Files.lines(Paths.get(invoicesPath)).collect(Collectors.toList());

            for (String invoiceLine : invoiceLines) {
                String[] parts = invoiceLine.split(",");
                InvoiceHeader invoice = new InvoiceHeader(Integer.parseInt(parts[0]), parts[2], Constants.DATE_FORMAT.parse(parts[1]));
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
        } catch (IOException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Couldn't read file", e);
            JOptionPane.showMessageDialog(null, "Couldn't read file");
        } catch (ParseException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Failed to parse file(s)", e);
            JOptionPane.showMessageDialog(null, "Failed to parse file(s)");
        }

        return invoices;
    }

    public static void writeFiles(ArrayList<InvoiceHeader> invoices) {
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

        int saveResult = chooser.showSaveDialog(null);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File invoiceFile = chooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(invoiceFile);
                writer.write(invoicesSb.toString());
                writer.close();
            } catch (IOException e) {
                Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Couldn't save invoices file", e);
                JOptionPane.showMessageDialog(null, "Couldn't save invoices file");
            }
        }

        JOptionPane.showMessageDialog(null, "Saving the invoice records file; please save the file" +
                " with .csv extension.");

        saveResult = chooser.showSaveDialog(null);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File recordsFile = chooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(recordsFile);
                writer.write(recordsSb.toString());
                writer.close();
            } catch (IOException e) {
                Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Couldn't save invoice records file", e);
            }
        }
    }
}
