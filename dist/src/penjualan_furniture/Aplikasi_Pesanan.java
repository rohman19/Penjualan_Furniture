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
public class Aplikasi_Pesanan extends javax.swing.JInternalFrame {
   koneksi kon = new koneksi();
private Object [][] datasementara=null;
private String[]labelsementara={"Nama Barang","Deskripsi Barang","Nama Supplier","Alamat","Harga Beli","Jumlah Barang","Subtotal"};
public StringTokenizer token;
public String gantiformat="";
    /**
     * Creates new form Aplikasi_Pesanan
     */
public Connection conn;
    public Aplikasi_Pesanan() throws SQLException {
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
     public String KodeBarang;
        public String Alamat;
        public String IdSupplier;
  public String getKodeBarang(){
        return KodeBarang;
    }
    public String getDeskripsi(){
        return Deskripsi;
    }
     public String getAlamat(){
        return Alamat;
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
      public String getIdSupplier(){
        return IdSupplier;
    }
    private void bersih()
    {
        tnopem.setText("");
        tkd_brg.setText("");
        tnmbrg.setText("");
        deskripsi.setText("");
        id_supplier.setText("");
        tsupplier.setText("");
        talamat.setText("");
        thrg.setText("");
        jml.setText("");
        tsb.setText("");
    }
    private void nonaktif()
    {
        
        tnopem.setEditable(false);
        tkd_brg.setEditable(false);
        ttanggal.setEditable(false);
        tnmbrg.setEditable(false);
        deskripsi.setEditable(false);
        id_supplier.setEditable(false);
        tsupplier.setEditable(false);
        talamat.setEditable(false);
        thrg.setEditable(false);
        jml.setEditable(false);
        tsb.setEditable(false);
    }
    private void aktif()
    {
        tkd_brg.setEditable(true);
        tnmbrg.setEditable(true);
         deskripsi.setEditable(true);
          id_supplier.setEditable(true);
        tsupplier.setEditable(true);
        jml.setEditable(true);
        thrg.setEditable(true);
        talamat.setEditable(true);
        tsb.setEditable(true);
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
        String sql="Select *From sementara_pesan order by kd_brg";
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
                datasementara[x][0]=kon.rs.getString("kd_brg");
                datasementara[x][0]=kon.rs.getString("nm_brg");
                datasementara[x][2]=kon.rs.getString("nm_sp");
                datasementara[x][1]=kon.rs.getString("deskripsi");
                datasementara[x][5]=kon.rs.getString("jumlah");
                datasementara[x][3]=kon.rs.getString("alamat");
                datasementara[x][4]=kon.rs.getString("harga_beli");
                datasementara[x][6]=kon.rs.getString("subtotal");
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
            String sql="select * from barang where kd_brg='"+tkd_brg.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next())
            {
                tkd_brg.setText(kon.rs.getString("kd_brg"));
               jml.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Kode Barang"+tkd_brg.getText()+"tidak ditemukan");
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
            }
 private void SimpanSementara(){
        try{
            String sql="insert into sementara_pesan values('"+tkd_brg.getText()+"',"
                    + "'"+tnmbrg.getText()+"','"+deskripsi.getText()+"',"
                    + "'"+tsupplier.getText()+"','"+talamat.getText()+"',"
                    + "'"+thrg.getText()+"','"+jml.getText()+"','"+tsb.getText()+"')";
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
            String sql="delete from sementara_pesan where kd_brg='"+tkd_brg.getText()+"'";
                   
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
            String sql="insert into pemesanan values('"+tnopem.getText()+"','"+ttanggal.getText()+"','"+total.getText()+"','"+id_supplier.getText()+"')";
            kon.st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }
    private void simpanDetailPemesanan()
    {
        try{
            String detail = "insert detailpemesanan select '" +tnopem.getText() +"',kd_brg,nm_brg,deskripsi,nm_sp,alamat,harga_beli,jumlah,subtotal from sementara_pesan";
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
        jLabel7 = new javax.swing.JLabel();
        thrg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tsb = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        talamat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tkd_brg = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        id_supplier = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aplikasi Pesanan");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Aplikasi_Pemesanan"));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("PURCHASE ORDER");

        jLabel10.setText("Nama Barang");

        jLabel4.setText("Nama Supplier");

        tsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsupplierActionPerformed(evt);
            }
        });

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

        btbrowse.setText("...");
        btbrowse.setToolTipText("Cari barang");
        btbrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowseActionPerformed(evt);
            }
        });

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

        btbatal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        btbatal.setText("BATAL");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });

        btsimpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/simpan.png"))); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });

        bttambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/m.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
            }
        });

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

        jLabel5.setText("Jumlah Barang");

        jml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlActionPerformed(evt);
            }
        });

        deskripsi.setColumns(20);
        deskripsi.setRows(5);
        jScrollPane2.setViewportView(deskripsi);

        btbrowse2.setText("...");
        btbrowse2.setToolTipText("Cari barang");
        btbrowse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowse2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Deskripsi Barang");

        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });

        tnopem.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N

        jLabel1.setText("No. Pemesanan");

        jLabel2.setText("Tanggal");

        jLabel7.setText("Harga Beli");

        thrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thrgActionPerformed(evt);
            }
        });

        jLabel8.setText("Subtotal");

        jLabel9.setText("Total");

        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        jLabel11.setText("Alamat Supplier");

        jLabel12.setText("Kode Barang");

        tkd_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkd_brgActionPerformed(evt);
            }
        });

        jLabel13.setText("Id Supplier");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(bttambah, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(55, 55, 55)
                                    .addComponent(tnopem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel12))
                                    .addGap(51, 51, 51)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(tkd_brg, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tnmbrg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btbrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(talamat, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thrg, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jml, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tsb, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(id_supplier, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tsupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btbrowse2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tnopem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btbrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(tnmbrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(id_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbrowse2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(talamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(thrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(tsb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bttambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_formMouseClicked

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalActionPerformed

    private void btbrowse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowse2ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Supplier dataSupplier=new Aplikasi_Data_Supplier(null, closable);
        dataSupplier.pesan = this;
        dataSupplier.setVisible(true);
        dataSupplier.setResizable(true);
        id_supplier.setText(IdSupplier);
        tsupplier.setText(NamaSupplier);
        talamat.setText(Alamat);
    }//GEN-LAST:event_btbrowse2ActionPerformed

    private void jmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlActionPerformed
        // TODO add your handling code here:
  int harga,jumbel,totl;

        harga=Integer.parseInt(thrg.getText());
        jumbel=Integer.parseInt(jml.getText());
        totl=harga*jumbel;

        tsb.setText(Integer.toString(totl));
        SimpanSementara();
        TampilTabelSementara();
      int ttl=0;
        for(int a=0;a<tbpemesanan.getRowCount();a++)
        {
            int sub=Integer.parseInt((String)tbpemesanan.getValueAt(a, 6));
            ttl+=sub;
        }
        total.setText(Integer.toString(ttl));  
       
        if(JOptionPane.showConfirmDialog(this, "Mau Tambah Barang?",
            "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        aktif();
        tnmbrg.requestFocus();
        deskripsi.setText("");
        tsupplier.setText("");
        talamat.setText("");
        thrg.setText("");
         jml.setText("");
        tsb.setText("");
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
        if (    tnopem.getText().equals("") ||
                 ttanggal.getText().equals("") ||
              tkd_brg.getText().equals("")||
            tnmbrg.getText().equals("") ||
           deskripsi.getText().equals("") ||
            id_supplier.getText().equals("") ||
            tsupplier.getText().equals("") ||
            talamat.getText().equals("") ||
            thrg.getText().equals("") ||
            jml.getText().equals("") ||
            tsb.getText().equals("") ||
            total.getText().equals(""))
        {
            JOptionPane.showMessageDialog (rootPane,"Data belum lengkap.");
        } else {
            bttambah.setEnabled(true);
            btkeluar.setEnabled(true);
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
        }
    }//GEN-LAST:event_btsimpanActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
       bersih();
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
        dataBarang.pesan = this;
        dataBarang.setVisible(true);
        dataBarang.setResizable(true);
        tkd_brg.setText(KodeBarang);
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

    private void thrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thrgActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_totalActionPerformed

    private void tkd_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkd_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkd_brgActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btbrowse2;
    private javax.swing.JButton btkeluar;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.JTextArea deskripsi;
    private javax.swing.JTextField id_supplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField jml;
    private javax.swing.JTextField talamat;
    private javax.swing.JTable tbpemesanan;
    private javax.swing.JTextField thrg;
    private javax.swing.JTextField tkd_brg;
    private javax.swing.JTextField tnmbrg;
    private javax.swing.JTextField tnopem;
    private javax.swing.JTextField total;
    private javax.swing.JTextField tsb;
    private javax.swing.JTextField tsupplier;
    private javax.swing.JTextField ttanggal;
    // End of variables declaration//GEN-END:variables
}
