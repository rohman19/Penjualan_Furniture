/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_furniture;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/**
 *
 * @author user
 */
public class Aplikasi_Pemesanan extends javax.swing.JFrame {
koneksi kon = new koneksi();
private Object [][] datasementara=null;
private String[]labelsementara={"Nama Barang","Deskripsi Barang","Nama Supplier","Jumlah Barang"};
public StringTokenizer token;
public String gantiformat="";
    /**
     * Creates new form Aplikasi_Pemesanan
     */
    public Aplikasi_Pemesanan() {
        initComponents();
        kon.setKoneksi();
        setTanggal();
        awal();
    }
    
    public Date date=new Date ();
    public SimpleDateFormat noformat=new SimpleDateFormat("MM/yyyy");
    public String NamaBarang;
    public String Deskripsi;
    public String NamaSupplier;
    public String Jumlah;
    
    public String getDeskripsi(){
        return Deskripsi;
    }
    public String getNamaBarang(){
        return NamaBarang;
    }
    public String getNamaSupplier(){
        return NamaSupplier;
    }
    public String getJumlah(){
        return Jumlah;
    }
    private void bersih()
    {
        tnopem.setText("");
        tnmbrg.setText("");
        deskripsi.setText("");
        tsupplier.setText("");
        jml.setText("");
    }
    private void nonaktif()
    {
        tnopem.setEditable(false);
        ttanggal.setEditable(false);
        tnmbrg.setEditable(false);
        deskripsi.setEditable(false);
        tsupplier.setEditable(false);
        jml.setEditable(false);
    }
    private void aktif()
    {
        tnmbrg.setEditable(true);
         deskripsi.setEditable(true);
        tsupplier.setEditable(true);
        jml.setEditable(true);
    }
    private void awal()
{
    nonaktif();
}

void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
    ttanggal.setText(kal.format(skrg));
}

public String nomor() 
{
    String urutan=null;
    try
    {
    kon.rs=kon.st.executeQuery("select right(id_pemesanan,3)+1 "
            + "from pemesanan as Nomor order by id_pemesanan desc");
    if(kon.rs.next())
    {
        urutan=kon.rs.getString(1);
        while(urutan.length()<3)
            urutan="0"+urutan;
        urutan="PO-"+noformat.format(date)+urutan;
    }else
    {
        urutan="PO-"+noformat.format(date)+"001";    
}
    }
catch (Exception e){
    JOptionPane.showMessageDialog(null, e);
}
return urutan;
}

private void TampilTabelSementara(){
    try{
        String sql="Select *From sementara_pesan order by nm_brg";
        kon.rs=kon.st.executeQuery(sql);
        ResultSetMetaData m=kon.rs.getMetaData();
        int kolom= m.getColumnCount();
        int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            datasementara= new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                datasementara[x][0]=kon.rs.getString("nm_brg");
                datasementara[x][2]=kon.rs.getString("nm_sp");
                datasementara[x][1]=kon.rs.getString("deskripsi");
                datasementara[x][3]=kon.rs.getString("jumlah");
                x++;
            }
            tbpemesanan.setModel(new DefaultTableModel(datasementara,labelsementara));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
 private void tampildatabarang()
    {
        try{
            String sql="select * from barang where nm_brg='"+tnmbrg.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next())
            {
                tnmbrg.setText(kon.rs.getString("nm_brg"));
               jml.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Nama Barang"+tnmbrg.getText()+"tidak ditemukan");
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
            }
 private void SimpanSementara(){
        try{
            String sql="insert into sementara_pesan values('"+tnmbrg.getText()+"',"
                    + "'"+deskripsi.getText()+"','"+tsupplier.getText()+"','"+jml.getText()+"')";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void HapusIsiSementara()
     {
       try{
            String sql="delete from sementara_pesan where nm_brg='"+tnmbrg.getText()+"'";
                   
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            bersih();
            TampilTabelSementara();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e); 
    }
    
    }
private void SimpanPemesanan(){
        try{
            String sql="insert into pemesanan values('"+tnopem.getText()+"','"+ttanggal.getText()+"')";
            kon.st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }
    private void simpanDetailPemesanan()
    {
        try{
            String detail = "insert detailpemesanan select '" +tnopem.getText() +"',nm_brg,deskripsi,nm_sp,jumlah from sementara_pesan";
            kon.st.executeUpdate(detail);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+ e.toString());
        }
    }
    private void HapusTabelSementara(){
        try{
            String sql="Delete from sementara_pesan";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void cetakstruk(){
    try{
        
       String file = "src/laporan/pesan.jasper";
        HashMap param = new HashMap();
        param.put("nopesan", tnopem.getText());
        JasperPrint print = JasperFillManager.fillReport(file, param, kon.setKoneksi());
        JasperViewer.viewReport(print, false);
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, e.getMessage());
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
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tsupplier = new javax.swing.JTextField();
        tnmbrg = new javax.swing.JTextField();
        btbrowse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpemesanan = new javax.swing.JTable();
        btbatal = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        bttambah = new javax.swing.JButton();
        btkeluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jml = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        deskripsi = new javax.swing.JTextArea();
        btbrowse2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ttanggal = new javax.swing.JTextField();
        tnopem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi_Pemesanan");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Aplikasi_Pemesanan"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("PURCHASE ORDER");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jLabel10.setText("Nama Barang");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel4.setText("Nama Supplier");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        tsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsupplierActionPerformed(evt);
            }
        });
        jPanel1.add(tsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 310, -1));

        tnmbrg.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tnmbrgComponentAdded(evt);
            }
        });
        tnmbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnmbrgActionPerformed(evt);
            }
        });
        jPanel1.add(tnmbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 310, -1));

        btbrowse.setText("...");
        btbrowse.setToolTipText("Cari barang");
        btbrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowseActionPerformed(evt);
            }
        });
        jPanel1.add(btbrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 30, 20));

        tbpemesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpemesanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbpemesananKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbpemesanan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 490, 97));

        btbatal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        btbatal.setText("BATAL");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });
        jPanel1.add(btbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 110, 40));

        btsimpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/simpan.png"))); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 120, 40));

        bttambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/m.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });
        jPanel1.add(bttambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 130, 40));

        btkeluar.setBackground(new java.awt.Color(255, 255, 255));
        btkeluar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/logout.png"))); // NOI18N
        btkeluar.setText("KELUAR");
        btkeluar.setBorder(null);
        btkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btkeluarActionPerformed(evt);
            }
        });
        jPanel1.add(btkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 100, 40));

        jLabel5.setText("Jumlah Barang");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlActionPerformed(evt);
            }
        });
        jPanel1.add(jml, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 92, -1));

        deskripsi.setColumns(20);
        deskripsi.setRows(5);
        jScrollPane2.setViewportView(deskripsi);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 310, 70));

        btbrowse2.setText("...");
        btbrowse2.setToolTipText("Cari barang");
        btbrowse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowse2ActionPerformed(evt);
            }
        });
        jPanel1.add(btbrowse2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 30, 20));

        jLabel6.setText("Deskripsi Barang");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });
        jPanel1.add(ttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 120, -1));

        tnopem.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jPanel1.add(tnopem, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 150, -1));

        jLabel1.setText("No. Pemesanan");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel2.setText("Tanggal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 470));

        setSize(new java.awt.Dimension(571, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalActionPerformed

    private void btbrowse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowse2ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Supplier dataSupplier=new Aplikasi_Data_Supplier(null, closable);
        dataSupplier.pesanan = this;
        dataSupplier.setVisible(true);
        dataSupplier.setResizable(true);
        tsupplier.setText(NamaSupplier);

    }//GEN-LAST:event_btbrowse2ActionPerformed

    private void jmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlActionPerformed
        // TODO add your handling code here:

        SimpanSementara();
        TampilTabelSementara();

        if(JOptionPane.showConfirmDialog(this, "Mau Tambah Barang?",
            "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        aktif();
        tnmbrg.requestFocus();
        tnmbrg.setText("");
        deskripsi.setText("");
        tsupplier.setText("");
        jml.setText("");

        }else{

        }
    }//GEN-LAST:event_jmlActionPerformed

    private void btkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btkeluarActionPerformed
        // TODO add your handling code here:
        HapusTabelSementara();
        dispose();
    }//GEN-LAST:event_btkeluarActionPerformed

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        aktif();
        tnopem.setText(nomor());
    }//GEN-LAST:event_bttambahActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        SimpanPemesanan();
        simpanDetailPemesanan();
        JOptionPane.showMessageDialog(this,"Berhasil disimpan",
            "Informasi", JOptionPane.INFORMATION_MESSAGE);
        TampilTabelSementara();
        if(JOptionPane.showConfirmDialog(this, "Mau Cetak PO?",
            "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
        cetakstruk();

        HapusTabelSementara();
        awal();
        bersih();
        tnopem.setText("");
        TampilTabelSementara();
        }
        else
        {
            HapusTabelSementara();
            awal();
            bersih();
            tnopem.setText("");
        }
    }//GEN-LAST:event_btsimpanActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        awal();
        HapusTabelSementara();
        tnopem.setText("");
    }//GEN-LAST:event_btbatalActionPerformed

    private void tbpemesananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpemesananKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE)
        {
            HapusIsiSementara();
        }
    }//GEN-LAST:event_tbpemesananKeyPressed

    private void btbrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowseActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Barang_Pesan dataBarang=new Aplikasi_Data_Barang_Pesan(null, closable);
        dataBarang.pesanan = this;
        dataBarang.setVisible(true);
        dataBarang.setResizable(true);
        tnmbrg.setText(NamaBarang);
        deskripsi.setText(Deskripsi);
        jml.setText(Jumlah);
    }//GEN-LAST:event_btbrowseActionPerformed

    private void tnmbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnmbrgActionPerformed
        // TODO add your handling code here:
        tampildatabarang();
    }//GEN-LAST:event_tnmbrgActionPerformed

    private void tnmbrgComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tnmbrgComponentAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_tnmbrgComponentAdded

    private void tsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsupplierActionPerformed
        // TODO add your handling code here:
        jml.requestFocus();
    }//GEN-LAST:event_tsupplierActionPerformed

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
            java.util.logging.Logger.getLogger(Aplikasi_Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplikasi_Pemesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btbrowse2;
    private javax.swing.JButton btkeluar;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JTextArea deskripsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jml;
    private javax.swing.JTable tbpemesanan;
    private javax.swing.JTextField tnmbrg;
    private javax.swing.JTextField tnopem;
    private javax.swing.JTextField tsupplier;
    private javax.swing.JTextField ttanggal;
    // End of variables declaration//GEN-END:variables
}
