package utils;

import model.InvoiceHeader;

import java.util.List;

public final class InvoiceUtils {

    public static InvoiceHeader getInvoice(List<InvoiceHeader> invoices, int number) {
        for (InvoiceHeader invoice : invoices) {
            if (invoice.getInvoiceNum() == number) {
                return invoice;
            }
        }
        return null;
    }
}
