package view;


import controller.ActionsHandler;

import javax.swing.*;
import java.awt.*;


public class InvoiceRecordView extends JDialog {

    private final JTextField itemNameField;
    private final JTextField itemCountField;
    private final JTextField itemPriceField;

    public InvoiceRecordView(ActionsHandler actionsHandler) {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(360, 180);
        setLocation(200, 200);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3,2,8,8));
        add(fieldsPanel);

        JLabel labelItemName = new JLabel("Item Name");
        JLabel labelItemCount = new JLabel("Item Count");
        JLabel labelItemPrice = new JLabel("Item Price");

        itemNameField = new JTextField();
        itemCountField = new JTextField();
        itemPriceField = new JTextField();

        fieldsPanel.add(labelItemName);
        fieldsPanel.add(itemNameField);
        fieldsPanel.add(labelItemCount);
        fieldsPanel.add(itemCountField);
        fieldsPanel.add(labelItemPrice);
        fieldsPanel.add(itemPriceField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        add(buttonsPanel);

        JButton ok = new JButton("OK");
        ok.setActionCommand("OK New Record");
        ok.addActionListener(actionsHandler);
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("Cancel New Record");
        cancel.addActionListener(actionsHandler);

        buttonsPanel.add(ok);
        buttonsPanel.add(cancel);
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
