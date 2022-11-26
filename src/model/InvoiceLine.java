package model;

public class InvoiceLine {

    private String itemName;
    private double itemPrice;
    private int count;
    private InvoiceHeader invoice;

    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoice;
    }

    public void setInvoiceHeader(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public double getTotalItemPrice() {
        return count * itemPrice;
    }

    @Override
    public String toString() {
        return invoice.getInvoiceNum() + "," + itemName + "," + itemPrice + "," + count;
    }
}