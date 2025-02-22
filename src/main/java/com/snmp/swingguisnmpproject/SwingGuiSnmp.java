/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.snmp.swingguisnmpproject;

/**
 *
 * @author Parse
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class SwingGuiSnmp {

    private JFrame jFrame;
    private TextField textField1, textField2, textField3,textField4,textField5,textField6,textField7,textField8,textField9,textField10;
    private JLabel jLabel1, jLabel2, jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10;
    private JButton refreshButton,closeButton;
    private SwingGuiSnmpProject snmpMainApp;

    public SwingGuiSnmp() {
        snmpMainApp = new SwingGuiSnmpProject();
        jFrame = new JFrame("SNMP OID Viewer");
        jFrame.setSize(400, 600);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);


        jLabel1 = new JLabel("Amp type");
        jLabel1.setBounds(20, 20, 100, 25);
        jFrame.add(jLabel1);


        textField1 = new TextField();
        textField1.setBounds(180, 20, 180, 25);
        textField1.setEditable(false);
        jFrame.add(textField1);




        jLabel2 = new JLabel("Operating mode");
        jLabel2.setBounds(20, 60, 120, 25);
        jFrame.add(jLabel2);


        textField2 = new TextField();
        textField2.setBounds(180, 60, 180, 25);
        textField2.setEditable(false);
        jFrame.add(textField2);


        jLabel3 = new JLabel("Input power");
        jLabel3.setBounds(20, 100, 120, 25);
        jFrame.add(jLabel3);


        textField3 = new TextField();
        textField3.setBounds(180, 100, 180, 25);
        textField3.setEditable(false);
        jFrame.add(textField3);


        jLabel4 = new JLabel("Output power");
        jLabel4.setBounds(20, 140, 120, 25);
        jFrame.add(jLabel4);


        textField4 = new TextField();
        textField4.setBounds(180, 140, 180, 25);
        textField4.setEditable(false);
        jFrame.add(textField4);


        jLabel5 = new JLabel("MSA input");
        jLabel5.setBounds(20, 180, 120, 25);
        jFrame.add(jLabel5);


        textField5 = new TextField();
        textField5.setBounds(180, 180, 180, 25);
        textField5.setEditable(false);
        jFrame.add(textField5);



        jLabel6 = new JLabel("MSA output");
        jLabel6.setBounds(20, 220, 120, 25);
        jFrame.add(jLabel6);


        textField6 = new TextField();
        textField6.setBounds(180, 220, 180, 25);
        textField6.setEditable(false);
        jFrame.add(textField6);



        jLabel7 = new JLabel("Case temp");
        jLabel7.setBounds(20, 260, 120, 25);
        jFrame.add(jLabel7);


        textField7 = new TextField();
        textField7.setBounds(180, 260, 180, 25);
        textField7.setEditable(false);
        jFrame.add(textField7);



        jLabel8 = new JLabel("Raman input power");
        jLabel8.setBounds(20, 300, 120, 25);
        jFrame.add(jLabel8);


        textField8 = new TextField();
        textField8.setBounds(180, 300, 180, 25);
        textField8.setEditable(false);
        jFrame.add(textField8);


        jLabel9 = new JLabel(" Amp type2");
        jLabel9.setBounds(20, 340, 120, 25);
        jFrame.add(jLabel9);


        textField9 = new TextField();
        textField9.setBounds(180, 340, 180, 25);
        textField9.setEditable(false);
        jFrame.add(textField9);


        jLabel10 = new JLabel("Total signal gain");
        jLabel10.setBounds(20, 380, 120, 25);
        jFrame.add(jLabel10);


        textField10 = new TextField();
        textField10.setBounds(180, 380, 180, 25);
        textField10.setEditable(false);
        jFrame.add(textField10);










refreshButton = new JButton("Refresh");
refreshButton.setBounds(50,480,100,45);
refreshButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            updateOidViewer();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
});
jFrame.add(refreshButton);



closeButton = new JButton("Close");
closeButton.setBounds(200,480,100,45);
closeButton.addActionListener(e -> System.exit(0));
jFrame.add(closeButton);




        jFrame.setVisible(true);
    }

    private void updateOidViewer() throws IOException {

        Map<String , String> oidValues = snmpMainApp.getSnmpValue();
 try {
     textField1.setText(oidValues.getOrDefault("pdu1","No Response"));
     textField2.setText(oidValues.getOrDefault("pdu2","No Response"));
     textField3.setText(oidValues.getOrDefault("pdu3","No Response"));
     textField4.setText(oidValues.getOrDefault("pdu4","No Response"));
     textField5.setText(oidValues.getOrDefault("pdu5","No Response"));
     textField6.setText(oidValues.getOrDefault("pdu6","No Response"));
     textField7.setText(oidValues.getOrDefault("pdu7","No Response"));
     textField8.setText(oidValues.getOrDefault("pdu8","No Response"));
     textField9.setText(oidValues.getOrDefault("pdu9","No Response"));
     textField10.setText(oidValues.getOrDefault("pdu10","No Response"));
 }catch (Exception e){
     JOptionPane.showMessageDialog(jFrame,"Error Fetching from snmp","Error",JOptionPane.ERROR_MESSAGE);
 }





    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingGuiSnmp::new);
    }
}
