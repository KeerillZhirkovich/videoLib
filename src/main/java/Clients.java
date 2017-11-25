
import dao.io.IODAOClient;
import dao.io.IODAODisc;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Objects.isNull;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.trim;
import model.Client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SL3W
 */
public class Clients extends javax.swing.JFrame {

    /**
     * Creates new form Clients
     */
    private static IODAOClient daoClients;
    public Clients() {
        initComponents();
        try 
        {
            daoClients = new IODAOClient();
            ShowClientsList();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка при чтении файла.");
        }   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Список клиентов");
        setResizable(false);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID клиента", "Имя", "Фамилия", "Телефон"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setText("Добавить клиента");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Выдать диск");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Удалить клиента");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean pressAdd=false;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!pressAdd)
        {
            pressAdd=true;
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.insertRow(jTable1.getRowCount(),new Vector(0));
//            if (daoClients.getClients().size()>0)
//            {
//                int x=(daoClients.getClient(daoClients.getClients().size()-1).getClientID());
//                  jTable1.setValueAt(x+1, jTable1.getRowCount()-1, 0);
//            }
//            else
//                jTable1.setValueAt("1", jTable1.getRowCount()-1, 0);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
        }
        else
        {
        if (!isNull(jTable1.getValueAt(jTable1.getRowCount()-1, 1)) && !isNull(jTable1.getValueAt(jTable1.getRowCount()-1,2)))
            {
                try {
                Client client=new Client();
                //int x=Integer.parseInt(trim((jTable1.getValueAt(jTable1.getRowCount()-1, 0))));
                //client.setClientID(x);
                client.setName(trim(jTable1.getValueAt(jTable1.getRowCount()-1, 1)));
                client.setSurname(trim(jTable1.getValueAt(jTable1.getRowCount()-1, 2)));
                try {
                    client.setPhone(trim((jTable1.getValueAt(jTable1.getRowCount()-1, 3).toString())));
                }
                catch (Exception e) {}
                daoClients.setClient(client);
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                pressAdd=false;
                ShowClientsList();
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "При добавлении клиента произошла ошибка");
                } 
            }
            else
                JOptionPane.showMessageDialog(this, "Поля \"Имя\" и \"Фамилия\" не могут быть пустыми!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int x=jTable1.getSelectedRow();
        if (x!=-1)
        {
            daoClients.deleteClient((int) jTable1.getValueAt(x, 0));
            ClearList();
            ShowClientsList();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x=jTable1.getSelectedRow();
        if (x!=-1)
        {
            try {
                MainForm.setClient(discID, daoClients.getClient((int) jTable1.getValueAt(x, 0)).getClientID());
            } catch (IOException | ClassNotFoundException ex) {  }
        }
        else
            JOptionPane.showMessageDialog(this, "Вы не выбрали клиента");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clients().setVisible(true);
            }
        });
    }

    private void ClearList()
    {
        DefaultTableModel dm = (DefaultTableModel)jTable1.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
    
    private void ShowClientsList() {
        ClearList();
        ArrayList<Client> clients= daoClients.getClients();
        
        DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
        for (int j=0;j<clients.size();j++)
        {
             dtm.insertRow(j,new Vector(0));
             jTable1.setValueAt(clients.get(j).getClientID(), j, 0);
             jTable1.setValueAt(clients.get(j).getName(), j, 1);
             jTable1.setValueAt(clients.get(j).getSurname(), j, 2);
             jTable1.setValueAt(clients.get(j).getPhone(), j, 3);
        }
    }
    
    private static int discID;
    public static void setDiscID(int _discID)
    {
       discID=_discID;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}