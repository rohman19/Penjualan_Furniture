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
public class Aplikasi_Barang_Keluar extends javax.swing.JFrame {
koneksi kon = new koneksi();
private Object [][] datasementara=null;
private String[]labelsementara={"Nama Barang","Deskripsi Barang","Jumlah Barang"};
public StringTokenizer token;
public String gantiformat="";
    /**
     * Creates new form Aplikasi_Pengiriman
     */
    public Aplikasi_Barang_Keluar(){
         initComponents();
         ImageIcon ico = new ImageIcon("src/gambar/glj.png");
        setIconImage(ico.getImage());
        kon.setKoneksi();
        setTanggal();
        awal();
    }
    
    public Date date=new Date ();
    public SimpleDateFormat noformat=new SimpleDateFormat("MM/yyyy");
     public String Jumlahbeli;
    public String Nomor;
    public String NamaBarang;
    public String Deskripsi;
    public String NamaMember;
    public String Alamat;
    public String Kota;
    public String Jumlah;
    
    public String getDeskripsi(){
        return Deskripsi;
    }
     public String getNomor(){
        return Nomor;
    }
    public String getAlamat(){
        return Alamat;
    }
    public String getKota(){
        return Kota;
    }
    public String getNamaBarang(){
        return NamaBarang;
    }
    public String getNamaMember(){
        return NamaMember;
    }
       public String getJumlahbeli(){
        return Jumlahbeli;
    }
    public String getJumlah(){
        return Jumlah;
    }
    private void bersih()
    {
        tnopem.setText("");
        tnmbrg.setText("");
        deskripsi.setText("");
      
        jml.setText("");
    }
    private void nonaktif()
    {
        tnopem.setEditable(false);
        ttanggal.setEditable(false);
        tnmbrg.setEditable(false);
        deskripsi.setEditable(false);
        tmember.setEditable(false);
        talamat.setEditable(false);
        tkota.setEditable(false);
        jml.setEditable(false);
    }
    private void aktif()
    {
        tnmbrg.setEditable(true);
         deskripsi.setEditable(true);
        tmember.setEditable(true);
        talamat.setEditable(true);
        tkota.setEditable(true);
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
    kon.rs=kon.st.executeQuery("select right(id_kirim,3)+1 "
            + "from barang_keluar as Nomor order by id_kirim desc");
    if(kon.rs.next())
    {
        urutan=kon.rs.getString(1);
        while(urutan.length()<3)
            urutan="0"+urutan;
        urutan="SJ-"+noformat.format(date)+urutan;
    }else
    {
        urutan="SJ-"+noformat.format(date)+"001";    
}
    }
catch (Exception e){
    JOptionPane.showMessageDialog(null, e);
}
return urutan;
}

private void TampilTabelSementara(){
    try{
        String sql="Select *From sementara_kirim order by nm_brg";
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
                datasementara[x][1]=kon.rs.getString("deskripsi");
                datasementara[x][2]=kon.rs.getString("jumlah");
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
 private void SimpanBarangKeluar(){
        try{
            String sql="insert into barang_keluar values('"+tnopem.getText()+"','"+tnomor.getText()+"','"+ttanggal.getText()+"','"+tmember.getText()+"','"+talamat.getText()+"','"+tkota.getText()+"')";
            kon.st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }
 private void SimpanSementara(){
        try{
            String sql="insert into sementara_kirim values('"+tnmbrg.getText()+"',"
                    + "'"+deskripsi.getText()+"','"+jml.getText()+"')";
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
            String sql="delete from sementara_kirim where nm_brg='"+tnmbrg.getText()+"'";
                   
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

    private void simpanDetailPengiriman()
    {
        try{
            String detail = "insert barang_keluar select '" +tnopem.getText() +"','" +tnomor.getText() +"','" +ttanggal.getText() +"','" +tmember.getText() +"','" +talamat.getText() +"','" +tkota.getText() +"',nm_brg,deskripsi,jumlah from sementara_kirim";
            kon.st.executeUpdate(detail);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+ e.toString());
        }
    }
    private void HapusTabelSementara(){
        try{
            String sql="Delete from sementara_kirim";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void cetakstruk(){
    try{
        
       String file = "src/laporan/suratjalan.jasper";
        HashMap param = new HashMap();
        param.put("nokirim", tnopem.getText());
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

        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tmember = new javax.swing.JTextField();
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
        talamat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tkota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tnopem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ttanggal = new javax.swing.JTextField();
        tnomor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi_Pengiriman");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("FORM SURAT JALAN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel10.setText("Nama Barang");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel4.setText("Nama Member");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        tmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmemberActionPerformed(evt);
            }
        });
        getContentPane().add(tmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 310, -1));

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
        getContentPane().add(tnmbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 310, -1));

        btbrowse.setText("...");
        btbrowse.setToolTipText("Cari barang");
        btbrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btbrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 30, 20));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 490, 97));

        btbatal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        btbatal.setText("BATAL");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });
        getContentPane().add(btbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 110, 40));

        btsimpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/simpan.png"))); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 120, 40));

        bttambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/m.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });
        getContentPane().add(bttambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 40));

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
        getContentPane().add(btkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 100, 40));

        jLabel5.setText("Jumlah Barang");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlActionPerformed(evt);
            }
        });
        getContentPane().add(jml, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 92, -1));

        deskripsi.setColumns(20);
        deskripsi.setRows(5);
        jScrollPane2.setViewportView(deskripsi);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 310, 70));

        btbrowse2.setText("...");
        btbrowse2.setToolTipText("Cari barang");
        btbrowse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowse2ActionPerformed(evt);
            }
        });
        getContentPane().add(btbrowse2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 30, 20));

        jLabel6.setText("Deskripsi Barang");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        talamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talamatActionPerformed(evt);
            }
        });
        getContentPane().add(talamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 310, -1));

        jLabel7.setText("Alamat");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        tkota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkotaKeyPressed(evt);
            }
        });
        getContentPane().add(tkota, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 310, -1));

        jLabel8.setText("Kota");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("No. Surat Jalan");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        tnopem.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jPanel1.add(tnopem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, -1));

        jLabel2.setText("Tanggal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });
        jPanel1.add(ttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 120, -1));

        tnomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnomorActionPerformed(evt);
            }
        });
        jPanel1.add(tnomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 310, -1));

        jLabel9.setText("No Transaksi");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 30, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 550));

        setSize(new java.awt.Dimension(583, 590));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmemberActionPerformed
        // TODO add your handling code here:
        jml.requestFocus();
    }//GEN-LAST:event_tmemberActionPerformed

    private void tnmbrgComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tnmbrgComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tnmbrgComponentAdded

    private void tnmbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnmbrgActionPerformed
        // TODO add your handling code here:
        tampildatabarang();
    }//GEN-LAST:event_tnmbrgActionPerformed

    private void btbrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowseActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Pengiriman dataBarang=new Aplikasi_Data_Pengiriman(null, closable);
        dataBarang.pesan = this;
        dataBarang.setVisible(true);
        dataBarang.setResizable(true);
        tnmbrg.setText(NamaBarang);
        deskripsi.setText(Deskripsi);
        jml.setText(Jumlah);
    }//GEN-LAST:event_btbrowseActionPerformed

    private void tbpemesananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbpemesananKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE)
        {
            HapusIsiSementara();
        }
    }//GEN-LAST:event_tbpemesananKeyPressed

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
        tmember.setText("");
        jml.setText("");

        }else{

        }
    }//GEN-LAST:event_jmlActionPerformed

    private void btbrowse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowse2ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Member_Kirim dataMember=new Aplikasi_Data_Member_Kirim(null, closable);
        dataMember.pesan = this;
        dataMember.setVisible(true);
        dataMember.setResizable(true);
        tmember.setText(NamaMember);
        talamat.setText(Alamat);
        tkota.setText(Kota);
    }//GEN-LAST:event_btbrowse2ActionPerformed

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalActionPerformed

    private void talamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_talamatActionPerformed

    private void tnomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnomorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnomorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Transaksi dataMember=new Aplikasi_Data_Transaksi(null, closable);
        dataMember.keluar = this;
        dataMember.setVisible(true);
        dataMember.setResizable(true);
        tmember.setText(NamaMember);
        tnomor.setText(Nomor);
        tnmbrg.setText(NamaBarang);
        jml.setText(Jumlahbeli);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tkotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkotaKeyPressed
        // TODO add your handling code here:
           SimpanSementara();
        TampilTabelSementara();
    }//GEN-LAST:event_tkotaKeyPressed

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        aktif();
        tnopem.setText(nomor());
    }//GEN-LAST:event_bttambahActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:

        if (    tnopem.getText().equals("") ||
                 ttanggal.getText().equals("") ||
              tnomor.getText().equals("")||
            tnmbrg.getText().equals("") ||
           deskripsi.getText().equals("") ||
            tmember.getText().equals("") ||
            talamat.getText().equals("") ||
            tkota.getText().equals("") ||
            jml.getText().equals(""))
        {
            JOptionPane.showMessageDialog (rootPane,"Data belum lengkap.");
        } else {
            bttambah.setEnabled(true);
            btkeluar.setEnabled(true);
             simpanDetailPengiriman();
        JOptionPane.showMessageDialog(this,"Berhasil disimpan",
            "Informasi", JOptionPane.INFORMATION_MESSAGE);
        TampilTabelSementara();
        if(JOptionPane.showConfirmDialog(this, "Mau Cetak Surat Jalan?",
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
        }
       
    }//GEN-LAST:event_btsimpanActionPerformed

    private void btkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btkeluarActionPerformed
        // TODO add your handling code here:
        HapusTabelSementara();
        dispose();
    }//GEN-LAST:event_btkeluarActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        awal();
        HapusTabelSementara();
        tnopem.setText("");
    }//GEN-LAST:event_btbatalActionPerformed

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
            java.util.logging.Logger.getLogger(Aplikasi_Barang_Keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Barang_Keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Barang_Keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Barang_Keluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btbrowse2;
    private javax.swing.JButton btkeluar;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JTextArea deskripsi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jml;
    private javax.swing.JTextField talamat;
    private javax.swing.JTable tbpemesanan;
    private javax.swing.JTextField tkota;
    private javax.swing.JTextField tmember;
    private javax.swing.JTextField tnmbrg;
    private javax.swing.JTextField tnomor;
    private javax.swing.JTextField tnopem;
    private javax.swing.JTextField ttanggal;
    // End of variables declaration//GEN-END:variables
}
