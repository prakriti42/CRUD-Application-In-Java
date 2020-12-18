/**
 * PhoneBook Class
 * This class contains the code for an Interactive User Interface for a Phone Book.
 * @author:Parkrit_Regmi
 */

//java packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PhoneBook extends JFrame {

    //Initialization of Instance Variables

    private JTextField firstName;
    private JTextField lastName;
    private JTextField phoneNumber;
    private JCheckBox pvt;              
    private String contactInfo;
    private String contactNumber;
    private JPanel dataPanel;
    private DefaultTableModel table;
    private JTable records;
    private JButton button1,button2,button3,button4;

    //Constructor Method
    public PhoneBook(){
        setVisible(true);                                           //makes the JFrame visible

        setTitle("Phone Book");                                     //sets the title of the JFrame
        setSize(new Dimension(400, 400));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //enables the function of exit button
        setResizable(false);                                    //disables resizing of the frame

        setLayout(new GridLayout(1,2));         //Layout for the complete Frame

        setJMenuBar(guiMenu());

        add(DataTableUI());
        add(gridMainUI());

        pack();                                             //sets the JComponents as per the size

        setLocationRelativeTo(null);                    //centers the JFrame
    }

    private JMenuBar guiMenu(){

        JMenuBar menuBar= new JMenuBar();

        //Menu Bars
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        fileMenu.setMnemonic(KeyEvent.VK_F);                //Shortcut Key for File Menu (Alt+F)
        menuBar.add(fileMenu);                                  //adds file menu to the JMenuBar
        fileMenu.setToolTipText("Exit");                        //displays tip on hover

        editMenu.setMnemonic(KeyEvent.VK_E);            //Shortcut Key for File Menu (Alt+E)
        menuBar.add(editMenu);                                      //adds edit menu to the JMenuBar
        editMenu.setToolTipText("Clear,Search,Add,Remove");         //displays tip on hover

        helpMenu.setMnemonic(KeyEvent.VK_H);        //Shortcut Key for File Menu (Alt+H)
        menuBar.add(helpMenu);                              //adds help menu to the JMenuBar
        helpMenu.setToolTipText("About");                   //displays tip on hover

        //Menu Items of the menu file
        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setMnemonic(KeyEvent.VK_X);
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));    //hotkey for exitMenu
        fileMenu.add(exitMenu);             //adds exit menu as the sub item of the file menu

        exitMenu.addActionListener(new ActionListener() {    //event listener for the EXIT menu
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  //sets the visibility of the JFrame false
                dispose();          //alters and cleans the running JFrame
            }
        });


        //Menu Items of edit menu
        JMenuItem clearMenu = new JMenuItem("Clear");
        clearMenu.setMnemonic(KeyEvent.VK_C);
        clearMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK)); //hotkey for clearMenu
        editMenu.add(clearMenu);

        clearMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.doClick();     //performs operation of button1 that is labeled as "Clear"
            }
        });

        JMenuItem searchMenu = new JMenuItem("Search");
        editMenu.add(searchMenu);
        searchMenu.setMnemonic(KeyEvent.VK_S);
        searchMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));       //hotkey for searchMenu

        searchMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.doClick();          //performs operation of button2 that is labeled as "Search"
            }
        });

        editMenu.addSeparator();                        //draws a line in between the Menu Items

        JMenuItem addMenu = new JMenuItem("Add");
        editMenu.add(addMenu);
        addMenu.setMnemonic(KeyEvent.VK_A);
        addMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));     //hotkey for addMenu
        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.doClick();                      //performs operation of button3 that is labeled as "Add"
            }
        });


        JMenuItem removeMenu = new JMenuItem("Remove");
        removeMenu.setMnemonic(KeyEvent.VK_R);
        removeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));    //hotkey for removeMenu
        editMenu.add(removeMenu);

        removeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.doClick();   //performs operation of button4 that is labeled as "Remove"
            }
        });

        //Menu Items of help menu
        JMenuItem aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);

        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dataPanel,"It is still in trial version!!","About",JOptionPane.PLAIN_MESSAGE);
            }
        });

        return menuBar;
    }

    //UI for the JPanel

    private JPanel gridMainUI(){

        //JPanel Container for JPanelA(Info:),JPanelB(File As:),JPanelC(buttons)
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3,0));           //Layout for the Container

        //First Panel inside the Container JPanel
        JPanel panelA = new JPanel();
        panelA.setLayout(new GridLayout(4,2));      //Layout for PanelA inside the Container JPanel
        panelA.setBorder(BorderFactory.createTitledBorder("Info:")); //sets the title with border for PanelA



        panelA.add(new JLabel("First Name"));           //start of PanelA contents
        firstName = new JTextField(10);
        panelA.add(firstName);


        panelA.add(new JLabel("Second Name"));
        lastName= new JTextField(10);
        panelA.add(lastName);

        panelA.add(new JLabel("Phone"));
        phoneNumber= new JTextField(10);
        panelA.add(phoneNumber);

        pvt= new JCheckBox("Private");
        panelA.add(pvt);                        //end of PanelA contents
        container.add(panelA);
        pack();

        //PanelB inside the container
        JPanel panelB = new JPanel();
        panelB.setLayout(new GridLayout(2,1));
        panelB.setBorder(BorderFactory.createTitledBorder("File As:"));

        //radio buttons
        JRadioButton rdFS = new JRadioButton("Forename , Surname");
        JRadioButton rdSF = new JRadioButton("Surname , Forename");

        ButtonGroup rd= new ButtonGroup();          //grouping of the radio buttons
        rd.add(rdFS);
        rd.add(rdSF);
        rdFS.setSelected(true);                     //selects the JRadioButton rdFS by default.
        rdFS.setForeground(Color.gray);             //selects the JRadioButton's Font color gray by default

        //removes the visible border outline seen upon selection of the radio button
        rdFS.setFocusPainted(false);
        rdSF.setFocusPainted(false);

        panelB.add(rdFS);
        panelB.add(rdSF);                   //end of panelB

        container.add(panelB);

        //PanelC inside the container
        JPanel panelC = new JPanel();
        panelC.setLayout(new GridLayout(2,2));  //Layout for the buttons section
        button1 =(new JButton("Clear"));
        button2 =(new JButton("Search"));
        button3 =(new JButton("Add"));
        button4 =(new JButton("Remove"));

        panelC.add(button1);
        panelC.add(button2);
        panelC.add(button3);
        panelC.add(button4);
        //end of PanelC

        container.add(panelC);

        //Events for the Clear Button
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                pvt.setSelected(false);

                records.clearSelection();
                button1.setToolTipText("Erase entered data from the phone book.");
            }
        });

        //Event for the Search Button
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(container,"Search functionality will be supported in the professional version","Information",JOptionPane.PLAIN_MESSAGE);
            }
        });

        //Events for the Add Button
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String foreName = firstName.getText();
                String surname= lastName.getText();
                contactNumber = phoneNumber.getText();
                String contactInfo= "";
                if (pvt.isSelected()){
                    contactInfo="Private";
                }
                else {
                    contactInfo="Not Private";
                }
                table.addRow(new Object[]{foreName,surname,contactNumber,contactInfo});
                button3.setToolTipText("Add entered data to the table");
                button1.doClick();
        }
        });

        //Events for the Remove Button
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(dataPanel,"Enter the row number of the records you want to delete.","Detail",JOptionPane.PLAIN_MESSAGE);

                int confirm = JOptionPane.showConfirmDialog(dataPanel,"Are you sure you want to delete the selected row?","Warning!!!",JOptionPane.INFORMATION_MESSAGE);

                //Exception Handling in case of row number input other than integer data type and missing row.
                try {
                    if (confirm == JOptionPane.YES_OPTION) {
                        int selectedRow = Integer.parseInt(row);
                        table.removeRow(selectedRow - 1);
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(dataPanel, "The row must be entered in number.", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                catch(ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(dataPanel, "The row you entered doesn't exit.", "Error", JOptionPane.PLAIN_MESSAGE);
                    }

                button1.doClick(); //clears the Input fields upon successful deletion of the records.
            }
        });

        rdFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               records.moveColumn(0,1);    //command for swapping the first two columns
                //commands for showing the active radio button
                rdFS.setForeground(Color.gray);
                rdSF.setForeground(Color.black);
            }
        });

        rdSF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                records.moveColumn(1,0);  //command for swapping the first two columns
                //commands for showing the active radio button
                rdSF.setForeground(Color.gray);
                rdFS.setForeground(Color.black);
            }
        });
        return container;
    }

    //UI for the JTable
    private JPanel DataTableUI(){
        dataPanel = new JPanel();
        dataPanel.setBorder(BorderFactory.createTitledBorder("Name:"));

        table = new DefaultTableModel();
        table.setColumnIdentifiers(new String[] {"Fore Name","Sur Name","Phone Number","Phone Detail"});  //column names for the JTable records
        records= new JTable(table);

        dataPanel.add(records);

        JScrollPane scrollPane = new JScrollPane(records);
        dataPanel.add(scrollPane);

        //Event handling when the user selects the row from the table.
        records.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = records.getSelectedRow();
                firstName.setText(table.getValueAt(selectedRow,0).toString());
                lastName.setText(table.getValueAt(selectedRow,1).toString());
                phoneNumber.setText(table.getValueAt(selectedRow,2).toString());
                contactInfo= table.getValueAt(selectedRow,3).toString();
                if(contactInfo.equals("Private")){
                    pvt.setSelected(true);
                }
                else{
                    pvt.setSelected(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //no events to monitor
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //no events to handle
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //no events to handle
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //no events to handle
            }
        });

        return dataPanel;
    }

    //main method
    public static void main(String[] args) {
        new PhoneBook();
    }
}
