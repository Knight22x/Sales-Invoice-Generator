/*
 * Created by JFormDesigner on Sat Nov 26 00:44:34 EET 2022
 */

package view;

import controller.ActionsHandler;
import controller.Listener;
import model.InvoiceLine;
import model.InvoicesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author asboifsu  // it's me Abdelrahman Hesham, but I typed any random name to get the license for the Jformdesigner
 */
public class MainGUI extends JFrame {

    public static String pattern = "dd-MM-yyyy";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private final Listener selectionListener = new Listener(this);
    private ArrayList<InvoiceLine> selectedInvoiceRecords;
    private InvoicesTable invoicesTable;
    private final ActionsHandler actionsHandler;
    private JTable table1;
    private JTable table2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public MainGUI() {
        actionsHandler = new ActionsHandler(this);
        initComponents();
        actionsHandler.actionPerformed(new ActionEvent(this, -1, "Initialize View"));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public InvoicesTable getInvoicesTable() {
        return invoicesTable;
    }

    public void setInvoicesTable(InvoicesTable invoicesTable) {
        this.invoicesTable = invoicesTable;
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - asboifsu
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
        // Generated using JFormDesigner Evaluation license - asboifsu
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menu1 = new JMenu();
        JMenuItem menuItem1 = new JMenuItem();
        JMenuItem menuItem2 = new JMenuItem();
        JScrollPane scrollPane1 = new JScrollPane();
        JScrollPane scrollPane2 = new JScrollPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table1 = new JTable();
        table1.setRowSelectionAllowed(true);
        table1.getSelectionModel().addListSelectionListener(selectionListener);

        table2 = new JTable();
        table2.setRowSelectionAllowed(true);

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setText("Load File");
                menuItem1.addActionListener(actionsHandler);
                menu1.add(menuItem1);
                menu1.addSeparator();

                //---- menuItem2 ----
                menuItem2.setText("Save File");
                menuItem2.addActionListener(actionsHandler);
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }

        //---- button1 ----
        JButton button1 = new JButton("Create Record");
        button1.addActionListener(actionsHandler);
        button1.setForeground(Color.yellow);
        button1.setBackground(Color.black);
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD));

        //---- button2 ----
        JButton button2 = new JButton("Delete Record");
        button2.addActionListener(actionsHandler);
        button2.setForeground(Color.yellow);
        button2.setBackground(Color.black);
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));

        //---- button3 ----
        JButton button3 = new JButton("Create Invoice");
        button3.addActionListener(actionsHandler);
        button3.setBackground(Color.black);
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD));
        button3.setForeground(Color.yellow);

        //---- button4 ----
        JButton button4 = new JButton("Delete Invoice");
        button4.addActionListener(actionsHandler);
        button4.setBackground(Color.black);
        button4.setForeground(Color.yellow);
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD));

        //---- label1 ----
        JLabel label1 = new JLabel();
        label1.setText("Invoices Table");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        label1.setForeground(Color.blue);

        //---- label2 ----
        JLabel label2 = new JLabel();
        label2.setText("Invoice Details");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label2.setForeground(Color.blue);

        //---- label3 ----
        JLabel label3 = new JLabel();
        label3.setText("Invoice Number");

        //---- label4 ----
        JLabel label4 = new JLabel();
        label4.setText("Invoice Date");

        //---- label5 ----
        JLabel label5 = new JLabel();
        label5.setText("Customer Name");

        //---- label6 ----
        JLabel label6 = new JLabel();
        label6.setText("Invoice Total");

        //---- label7 ----
        JLabel label7 = new JLabel();
        label7.setText("Sales Invoice Generator developed by Abdelrahman Hesham");
        label7.setForeground(new Color(0xff6600));
        label7.setFont(label7.getFont().deriveFont(label7.getFont().getStyle() | Font.BOLD));

        textField1 = new JTextField();
        textField1.setEditable(false);

        textField2 = new JTextField();
        textField2.setEditable(false);

        textField3 = new JTextField();
        textField3.setEditable(false);

        textField4 = new JTextField();
        textField4.setEditable(false);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(button3)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                                .addComponent(button4))
                                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(scrollPane2)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                                .addComponent(button1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(button2))
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                                        .addComponent(label4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                                        .addComponent(label5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                                        .addComponent(label6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                                        .addComponent(textField1)
                                                                        .addComponent(textField2)
                                                                        .addComponent(textField3)
                                                                        .addComponent(textField4)
                                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                                .addGap(42, 42, 42))))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(label7)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label1)
                                .addGap(9, 9, 9)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label3)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label4)
                                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label6)
                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(7, 7, 7)
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(button1)
                                                .addComponent(button4)
                                                .addComponent(button3))
                                        .addComponent(button2))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public ArrayList<InvoiceLine> getSelectedInvoiceRecords() {
        return selectedInvoiceRecords;
    }

    public void setSelectedInvoiceRecords(ArrayList<InvoiceLine> invoiceRecords) {
        this.selectedInvoiceRecords = invoiceRecords;
    }
}
