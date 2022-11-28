package model;

import controller.ActionsHandler;
import utils.InvoiceUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class FileOperations {

    public static void main(String[] args) {
        ArrayList<InvoiceHeader> invoices = FileOperations.readFiles("assets/invoices.csv", "assets/invoice_records.csv");

        for (InvoiceHeader invoice : invoices) {
            System.out.println(invoice.getInvoiceNum());
            System.out.println("{");
            for (InvoiceLine record : invoice.getInvoiceRecords()) {
                System.out.println(record.getItemName() + ", " + record.getItemPrice() + ", " + record.getCount());
            }
            System.out.println("}");
            System.out.println();
        }
    }

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
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "File(s) not found", e);
            JOptionPane.showMessageDialog(null, "File(s) not found");
        } catch (ParseException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Invalid Date Format", e);
            JOptionPane.showMessageDialog(null, "Invalid Date Format");
        } catch (NumberFormatException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Invalid File Format", e);
            JOptionPane.showMessageDialog(null, "Invalid File Format");
        }


        return invoices;
    }

    public static void writeFiles(ArrayList<InvoiceHeader> invoices) {
        //JFileChooser chooser = new JFileChooser();
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

        JOptionPane.showMessageDialog(null, "Saving the invoices file");
        File invoiceFile = new File(Paths.get(System.getProperty("user.dir") + Constants.INVOICE_FILE).toUri());
        try {
            FileWriter writer = new FileWriter(invoiceFile);
            writer.write(invoicesSb.toString());
            writer.close();
        } catch (IOException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Invalid File Format.", e);
            JOptionPane.showMessageDialog(null, "Invalid File Format.");
        } catch (InvalidPathException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Invalid File Path.", e);
            JOptionPane.showMessageDialog(null, "Invalid File Path.");
        }

//        int saveResult = chooser.showSaveDialog(null);
//        if (saveResult == JFileChooser.APPROVE_OPTION) {
        // File invoiceFile = chooser.getSelectedFile();
//
        // ******************* Wrong file format exception *************** //
        /*

        File invoiceFile = new File(Paths.get(Constants.INVALID_INVOICE_FILE).toUri());
        String extension = null;
        String fileName = invoiceFile.toString();
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }
        assert extension != null;

        try {
            if (extension.endsWith(".csv")) {
                FileWriter writer = new FileWriter(invoiceFile, true);
                writer.write(invoicesSb.toString());
                writer.close();
            }
            else {
                throw new IOException();
            }
        } catch (
                FileNotFoundException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "File/Folder path not found", e);
            JOptionPane.showMessageDialog(null, "File/Folder path not found");
        } catch (IOException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Can only write and save to a file " +
                    "of .csv type", e);
            JOptionPane.showMessageDialog(null,"Can only write and save to a file " +
                    "of .csv type" );
        }
        */
        // File / folder path case
        //File invoiceFile = new File(Paths.get(Constants.ABSENT_INVOICE_FILE).toUri());  //for file/folder path not found exception
        JOptionPane.showMessageDialog(null, "Saving the invoice records file");

//        saveResult = chooser.showSaveDialog(null);
//        if (saveResult == JFileChooser.APPROVE_OPTION) {

        File recordsFile = new File(Paths.get(System.getProperty("user.dir") + Constants.INVOICE_RECORDS_FILE).toUri());
        try {
            FileWriter writer = new FileWriter(recordsFile);
            writer.write(recordsSb.toString());
            writer.close();
        } catch (
                IOException e) {
            Logger.getLogger(ActionsHandler.class.getName()).log(Level.SEVERE, "Couldn't save invoice records file", e);
        }
    }


}