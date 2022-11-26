package model;

import view.MainGUI;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    private final String customerName;
    private final Date invoiceDate;
    private int invoiceNum;

    private final ArrayList<InvoiceLine> invoiceRecords = new ArrayList<>();

    public InvoiceHeader(int invoiceNum, String customerName, Date invoiceDate) {
        this.invoiceNum = invoiceNum;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public double getTotal() {
        double total = 0.0;
        for (InvoiceLine record : invoiceRecords) {
            total += record.getTotalItemPrice();
        }
        return total;
    }

    public ArrayList<InvoiceLine> getInvoiceRecords() {
        return invoiceRecords;
    }

    @Override
    public String toString() {
        return invoiceNum + "," + MainGUI.simpleDateFormat.format(invoiceDate) + "," + customerName;
    }
}