package model;

import java.text.SimpleDateFormat;

public final class Constants {

    public static final String INVOICE_FILE = "/assets/invoices.csv";
    public static final String ABSENT_INVOICE_FILE = "/assets/invoicesx.csv";
    public static final String INVALID_INVOICE_FILE = "assets/invoiceswrongfileformat.docx";
    public static final String INVOICE_RECORDS_FILE = "/assets/invoice_records.csv";
    public static String DATE_PATTERN = "dd-MM-yyyy";
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

}
