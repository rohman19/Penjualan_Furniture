/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_furniture;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Aplikasi_Master_Supplier extends javax.swing.JInternalFrame {
koneksi kon=new koneksi();
private Object [][] datasupplier=null;
private String[]label={"Id Supplier","Nama Supplier","Alamat","No Telepon"};
    /**
     * Creates new form Aplikasi_Master_Supplier
     */
    public Aplikasi_Master_Supplier() {
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
private void setTable(){
int row=tbl_sup.getSelectedRow();
tkd_sup.setText((String)tbl_sup.getValueAt(row,0));
tnmsup.setText((String)tbl_sup.getValueAt(row,1));
talamat.setText((String)tbl_sup.getValueAt(row,2));
tnotlp.setText((String)tbl_sup.getValueAt(row,3));
}
private String kdsupp()
{
String no=null;
try{
String sql = "Select right(Id_supplier,3)+1 from supplier ";
ResultSet rs = kon.st.executeQuery(sql);
if (rs.next()){
rs.last();
no = rs.getString(1);
while (no.length()<3){
no="00"+no;
no="SP"+no;
tkd_sup.setText(no);
}
}else{
no="SP001";
tkd_sup.setText(no);
}
}catch (Exception e){
}return no;
}
private void BersihField(){
tkd_sup.setText("");
tnmsup.setText("");
}
private void aktif(){
tkd_sup.setEnabled(true);
tnmsup.setEnabled(true);
}
private void nonaktif(){
tkd_sup.setEnabled(false);
tnmsup.setEnabled(false);

bt_update.setEnabled(false);
bt_hapus.setEnabled(false);
bt_simpan.setEnabled(false);
}
private void SimpanData(){
try{
String sql="insert into supplier values('"+tkd_sup.getText()+"','"+tnmsup.getText()+"','"+talamat.getText()+"','"+tnotlp.getText()+"')";
kon.st.executeUpdate(sql);
JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
BersihField();
BacaTabelSupplier();
}
catch(SQLException e){
JOptionPane.showMessageDialog(null,e);
}
}
private void EditData(){
try{
String sql="Update supplier set Id_supplier='"+tkd_sup.getText()+"',Nama_supplier='"+tnmsup.getText()+"',alamat='"+talamat.getText()+"',tlp='"+tnotlp.getText()+"' where Id_supplier='"+tkd_sup.getText()+"'";
kon.st.executeUpdate(sql);
JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
BersihField();
BacaTabelSupplier();
}
catch(SQLException e){
JOptionPane.showMessageDialog(null,e);
}
}
private void HapusData(){
try{
String sql="Delete from supplier where Id_supplier='"+tkd_sup.getText()+"'";
kon.st.executeUpdate(sql);
JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
BersihField();
BacaTabelSupplier();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        bt_simpan = new javax.swing.JButton();
        bt_tambah = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_hapus = new javax.swing.JButton();
        bt_keluar = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tkd_sup = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tnmsup = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        talamat = new javax.swing.JTextArea();
        tnotlp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sup = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aplikasi Master Supplier");

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_simpan.setBackground(new java.awt.Color(255, 255, 255));
        bt_simpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/simpan.png"))); // NOI18N
        bt_simpan.setText("Simpan");
        bt_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_simpanActionPerformed(evt);
            }
        });
        jPanel3.add(bt_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 190, 40));

        bt_tambah.setBackground(new java.awt.Color(255, 255, 255));
        bt_tambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/m.png"))); // NOI18N
        bt_tambah.setText("Tambah");
        bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tambahActionPerformed(evt);
            }
        });
        jPanel3.add(bt_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        bt_update.setBackground(new java.awt.Color(255, 255, 255));
        bt_update.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/update.png"))); // NOI18N
        bt_update.setText("Ubah");
        bt_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateActionPerformed(evt);
            }
        });
        jPanel3.add(bt_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 190, 40));

        bt_hapus.setBackground(new java.awt.Color(255, 255, 255));
        bt_hapus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/delete.png"))); // NOI18N
        bt_hapus.setText("Hapus");
        bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_hapusActionPerformed(evt);
            }
        });
        jPanel3.add(bt_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 20, 160, 40));

        bt_keluar.setBackground(new java.awt.Color(255, 255, 255));
        bt_keluar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/logout.png"))); // NOI18N
        bt_keluar.setText("Tutup");
        bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_keluarActionPerformed(evt);
            }
        });
        jPanel3.add(bt_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 140, 160, 43));

        bt_batal.setBackground(new java.awt.Color(255, 255, 255));
        bt_batal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });
        jPanel3.add(bt_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 160, 40));

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tkd_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkd_supActionPerformed(evt);
            }
        });

        jLabel1.setText("Kode Supplier");

        tnmsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnmsupActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Supplier");

        talamat.setColumns(20);
        talamat.setRows(5);
        jScrollPane2.setViewportView(talamat);

        jLabel4.setText("Alamat Supplier");

        jLabel5.setText("No Telepon");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(tnmsup, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tkd_sup, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tnotlp))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tkd_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tnmsup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnotlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/glj (2).png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("GEMILANG LESTARI JAYA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

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

        jLabel3.setText("Cari Supplier");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(90, 90, 90)
                        .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_supMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_supMouseClicked
        // TODO add your handling code here:
        setTable();
        bt_hapus.setEnabled(true);
     
        bt_tambah.setEnabled(false);
    }//GEN-LAST:event_tbl_supMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
        BacaTabelSupplier2();
    }//GEN-LAST:event_tcariKeyTyped

    private void tnmsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnmsupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnmsupActionPerformed

    private void tkd_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkd_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkd_supActionPerformed

    private void bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_keluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bt_keluarActionPerformed

    private void bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_hapusActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HapusData();
            bt_tambah.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            bt_tambah.setEnabled(true);
            return;
        }
    }//GEN-LAST:event_bt_hapusActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        // TODO add your handling code here:
        nonaktif();
        BersihField();
        bt_tambah.setEnabled(true);
    }//GEN-LAST:event_bt_batalActionPerformed

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed
        // TODO add your handling code here:
       BersihField();
        bt_tambah.setEnabled(true);
        EditData();
    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpanActionPerformed
        // TODO add your handling code here:
        if (tkd_sup.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            bt_tambah.setEnabled(true);
            }else if (tnmsup.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama belum di isi", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            bt_tambah.setEnabled(true);
            }else if (talamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alamat belum di isi", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            bt_tambah.setEnabled(true);
            }else if (tnotlp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No telepon belum di isi", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            bt_tambah.setEnabled(true);
        } else {
            bt_tambah.setEnabled(true);
            bt_keluar.setEnabled(true);
            SimpanData();
        }
    }//GEN-LAST:event_bt_simpanActionPerformed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        // TODO add your handling code here:
        aktif();
        BersihField();
        kdsupp();
        tkd_sup.setEnabled(false);
        tnmsup.requestFocus();
        bt_batal.setEnabled(true);
        bt_tambah.setEnabled(false);
        bt_simpan.setEnabled(true);
    }//GEN-LAST:event_bt_tambahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_batal;
    private javax.swing.JButton bt_hapus;
    private javax.swing.JButton bt_keluar;
    private javax.swing.JButton bt_simpan;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JButton bt_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea talamat;
    private javax.swing.JTable tbl_sup;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tkd_sup;
    private javax.swing.JTextField tnmsup;
    private javax.swing.JTextField tnotlp;
    // End of variables declaration//GEN-END:variables
}
