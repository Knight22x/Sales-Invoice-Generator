package view;

import controller.ActionsHandler;

import javax.swing.*;
import java.awt.*;

public class InvoiceView extends JDialog {

    private final JTextField customerNameField;
    private final JTextField invoiceDateField;

    public InvoiceView(ActionsHandler actionsHandler) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(360, 180);
        setLocation(200, 200);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2, 8, 8));
        add(fieldsPanel);

        JLabel customerNameLabel = new JLabel("Customer Name");
        JLabel invoiceDateLabel = new JLabel("Invoice Date (dd-MM-yyyy)");
        customerNameField = new JTextField();
        invoiceDateField = new JTextField();

        fieldsPanel.add(customerNameLabel);
        fieldsPanel.add(customerNameField);
        fieldsPanel.add(invoiceDateLabel);
        fieldsPanel.add(invoiceDateField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        add(buttonsPanel);

        JButton ok = new JButton("OK");
        ok.setActionCommand("OK New Invoice");
        ok.addActionListener(actionsHandler);
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel New Invoice");
        cancel.addActionListener(actionsHandler);

        buttonsPanel.add(ok);
        buttonsPanel.add(cancel);
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
}