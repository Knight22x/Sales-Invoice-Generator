/*
 * Created by JFormDesigner on Mon Nov 28 06:01:18 EET 2022
 */

package view;

import controller.ActionsHandler;
import controller.InvoiceSelectionListener;
import model.InvoiceLine;
import model.InvoicesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * @author asboifsu
 */
public class MainGUI extends JFrame {

    private final InvoiceSelectionListener invoiceSelectionListener = new InvoiceSelectionListener(this);
    private ArrayList<InvoiceLine> selectedInvoiceRecords;
    private InvoicesTable invoicesTable;
    private final ActionsHandler actionsHandler;

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

    public ArrayList<InvoiceLine> getSelectedInvoiceRecords() {
        return selectedInvoiceRecords;
    }

    public void setSelectedInvoiceRecords(ArrayList<InvoiceLine> invoiceRecords) {
        this.selectedInvoiceRecords = invoiceRecords;
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - asboifsu
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label8 = new JLabel();
        label9 = new JLabel();
        textField3 = new JTextField();
        label10 = new JLabel();
        textField4 = new JTextField();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        label11 = new JLabel();
        table1.setRowSelectionAllowed(true);
        table1.getSelectionModel().addListSelectionListener(invoiceSelectionListener);
        table2.setRowSelectionAllowed(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setText("Save File");
                menuItem1.addActionListener(actionsHandler);
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Load File");
                menuItem2.addActionListener(actionsHandler);
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("Invoices Table");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        label1.setForeground(Color.blue);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        //---- label3 ----
        label3.setText("Invoice Number");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD));

        //---- label8 ----
        label8.setText("Invoice Date");
        label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD));

        //---- label9 ----
        label9.setText("Customer Name");
        label9.setFont(label9.getFont().deriveFont(label9.getFont().getStyle() | Font.BOLD));

        //---- label10 ----
        label10.setText("Invoice Total");
        label10.setFont(label10.getFont().deriveFont(label10.getFont().getStyle() | Font.BOLD));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        textField1.setEditable(false);
        textField4.setEditable(false);

        //---- button1 ----
        button1.setText("Create Invoice");
        button1.addActionListener(actionsHandler);
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD));
        button1.setForeground(Color.yellow);
        button1.setBackground(Color.black);

        //---- button2 ----
        button2.setText("Delete Invoice");
        button2.addActionListener(actionsHandler);
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD));
        button2.setForeground(Color.yellow);
        button2.setBackground(Color.black);

        //---- button3 ----
        button3.setText("Create Record");
        button3.addActionListener(actionsHandler);
        button3.setForeground(Color.yellow);
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD));
        button3.setBackground(Color.black);

        //---- button4 ----
        button4.setText("Delete Record");
        button4.addActionListener(actionsHandler);
        button4.setForeground(Color.yellow);
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD));
        button4.setBackground(Color.black);

        //---- button5 ----
        button5.setText("Save");
        button5.addActionListener(actionsHandler);
        button5.setForeground(Color.yellow);
        button5.setFont(button5.getFont().deriveFont(button5.getFont().getStyle() | Font.BOLD));
        button5.setBackground(Color.black);

        //---- button6 ----
        button6.setText("Cancel");
        button6.addActionListener(actionsHandler);
        button6.setForeground(Color.yellow);
        button6.setFont(button6.getFont().deriveFont(button6.getFont().getStyle() | Font.BOLD));
        button6.setBackground(Color.black);

        //---- label11 ----
        label11.setText("Invoices Details");
        label11.setFont(label11.getFont().deriveFont(label11.getFont().getStyle() | Font.BOLD));
        label11.setForeground(Color.blue);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label9)
                                                .addComponent(label10)
                                                .addComponent(label11))
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textField3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label3)
                                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
                                            .addGap(20, 20, 20)
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textField2, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(button6, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(1, 1, 1))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 632, GroupLayout.PREFERRED_SIZE))))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(107, 107, 107)
                    .addComponent(button1)
                    .addGap(18, 18, 18)
                    .addComponent(button2)
                    .addGap(258, 258, 258)
                    .addComponent(button3)
                    .addGap(18, 18, 18)
                    .addComponent(button4))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(button5)
                                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button6))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(label11)
                                    .addGap(4, 4, 4)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(button1)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button4)
                                    .addComponent(button3))))
                        .addComponent(button2))
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - asboifsu
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label8;
    private JLabel label9;
    private JTextField textField3;
    private JLabel label10;
    private JTextField textField4;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JLabel label11;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
