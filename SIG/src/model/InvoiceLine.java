package model;



// Going to submit this as the initial commit as required ( Seen in the rubric ) However I guess this might be
// modified heavily later on as I progress in the project.

public class InvoiceLine {
    private int itemNum;            // hmmmm why isn't that in the rubric ?
    private String itemName;
    private double itemPrice;
    private int count;
    private int totalItemPrice;     // hmmmm why isn't that in the rubric ?
    private InvoiceHeader invoiceHeader;


    // auto generated the setters and getters as well as the constructor

    public InvoiceLine(int itemNum, String itemName, double itemPrice, int count, int totalItemPrice, InvoiceHeader invoiceHeader) {
        this.itemNum = itemNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.totalItemPrice = totalItemPrice;
        this.invoiceHeader = invoiceHeader;


    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
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

    public int getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(int totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    }