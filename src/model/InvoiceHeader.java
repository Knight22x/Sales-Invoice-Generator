package model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    private String customerName;
    private Date invoiceDate;
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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
        return invoiceNum + "," + Constants.DATE_FORMAT.format(invoiceDate) + "," + customerName;
    }
}
