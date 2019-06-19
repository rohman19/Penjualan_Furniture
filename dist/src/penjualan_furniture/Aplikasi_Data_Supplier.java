/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_furniture;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Aplikasi_Data_Supplier extends javax.swing.JDialog {
koneksi kon=new koneksi();
public Aplikasi_Pesanan pesan = null;
public Aplikasi_Pemesanan pesanan = null;
private Object [][] datasupplier=null;
private String[]label={"Id Supplier","Nama Supplier","Alamat","No Telepon"};
    /**
     * Creates new form Aplikasi_Data_Supplier
     */
    public Aplikasi_Data_Supplier(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        kon.setKoneksi();
        BacaTabelSupplier();
    }
    private void BacaTabelSupplier(){
try{
String sql="Select *From supplier order by Id_supplier";
kon.rs=kon.st.executeQuery(sql);
ResultSetMetaData m=kon.rs.getMetaData();
int kolom=m.getColumnCount();
int baris=0;
while(kon.rs.next()){
baris=kon.rs.getRow();
}
datasupplier=new Object[baris][kolom];
int x=0;
kon.rs.beforeFirst();
while(kon.rs.next()){
datasupplier[x][0]=kon.rs.getString("Id_supplier");
datasupplier[x][1]=kon.rs.getString("Nama_supplier");
datasupplier[x][2]=kon.rs.getString("alamat");
datasupplier[x][3]=kon.rs.getString("tlp");
x++;
}
tbl_sup.setModel(new DefaultTableModel(datasupplier,label));
}
catch(SQLException e){
JOptionPane.showMessageDialog(null, e);
}
}
private void BacaTabelSupplier2(){
try{
String sql="select *from supplier where Nama_supplier like '%" +tcari.getText()+ "%' ";
kon.rs=kon.st.executeQuery(sql);
ResultSetMetaData m=kon.rs.getMetaData();
int kolom=m.getColumnCount();
int baris=0;
while(kon.rs.next()){
baris=kon.rs.getRow();
}
datasupplier=new Object[baris][kolom];
int x=0;
kon.rs.beforeFirst();
while(kon.rs.next()){
datasupplier[x][0]=kon.rs.getString("Id_supplier");
datasupplier[x][1]=kon.rs.getString("Nama_supplier");
datasupplier[x][2]=kon.rs.getString("alamat");
datasupplier[x][3]=kon.rs.getString("tlp");
x++;
}
tbl_sup.setModel(new DefaultTableModel(datasupplier,label));
}
catch(SQLException e){
JOptionPane.showMessageDialog(null, e);

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
        tbl_sup = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi Data Supplier");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_sup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_sup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_supMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sup);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 140));

        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });
        getContentPane().add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 120, -1));

        jLabel3.setText("Cari Supplier");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        setSize(new java.awt.Dimension(489, 245));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_supMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supMouseClicked
        // TODO add your handling code here:
       int tabelSupplier = tbl_sup.getSelectedRow();
       pesan.IdSupplier = tbl_sup.getValueAt(tabelSupplier, 0).toString();
        pesan.NamaSupplier = tbl_sup.getValueAt(tabelSupplier, 1).toString();
        pesan.Alamat = tbl_sup.getValueAt(tabelSupplier, 2).toString();
        this.dispose();
       
    }//GEN-LAST:event_tbl_supMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
        BacaTabelSupplier2();
    }//GEN-LAST:event_tcariKeyTyped

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
            java.util.logging.Logger.getLogger(Aplikasi_Data_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Data_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Data_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Data_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Aplikasi_Data_Supplier dialog = new Aplikasi_Data_Supplier(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_sup;
    private javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}