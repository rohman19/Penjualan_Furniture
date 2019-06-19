/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_furniture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Aplikasi_Barang_Masuk extends javax.swing.JInternalFrame {
 koneksi kon = new koneksi();
private String tgl;
private Object [][] databarangmasuk=null;
private String[]labelbarangmasuk={"Nomor Barang Masuk","No Pemesanan","Tanggal","Kode Barang","Nama Barang","Deskripsi","Nama Supplier","Jumlah"};
    /**
     * Creates new form Aplikasi_Barang_Masuk
     */
public Connection conn;

    public Aplikasi_Barang_Masuk() throws SQLException {
        initComponents();
         conn = DriverManager.getConnection("jdbc:mysql://localhost/penjualan_db","root","");
        
        kon.setKoneksi();
       
        BacaTabel();
    }
     public String KodeBarang;
    public String NamaBarang;
    public String NamaSupplier;
    public String Jumlah;
     public String Deskripsi;
    public String IdPemesanan;
     public String getIdPemesanan(){
        return IdPemesanan;
    }
    public String getKodeBarang(){
        return KodeBarang;
    }
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
        tno.setText("");
      tkd_brg.setText("");
        tnm_brg.setText("");
        tnm_supplier.setText("");
        tdes.setText("");
        tjumlah.setText("");
        pesan.setText("");
    }
private void nonaktif()
    {
     tkd_brg.setEditable(false);
        tdes.setEditable(false);
        tnm_brg.setEditable(false);
        tnm_supplier.setEditable(false);
        tjumlah.setEditable(false);
         pesan.setEditable(false);
       
    }
private void aktif()
    {
       tkd_brg.setEditable(true);
        tdes.setEditable(true);
        tnm_brg.setEditable(true);
        tnm_supplier.setEditable(true);
        tjumlah.setEditable(true);
        pesan.setEditable(true);
    }
private String id()
    {
        String no=null;
    try{
        String sql = "Select right(nomor,3)+1 from barang_masuk ";
        ResultSet rs = kon.st.executeQuery(sql);
        if (rs.next()){
            rs.last();
            no = rs.getString(1);
            while (no.length()<3){
                no="00"+no;
                no="BM"+no;
            tno.setText(no);    
            }
        }else{
            no="BM001";
            tno.setText(no);    
        }
    }catch (Exception e){     
    }return no;
    }
private void BacaTabel(){
        try{
            String sql="Select *From barang_masuk order by nomor";
            kon.rs=kon.st.executeQuery(sql);
             ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            databarangmasuk= new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                databarangmasuk[x][0]=kon.rs.getString("nomor");
                databarangmasuk[x][1]=kon.rs.getString("id_pemesanan");
                databarangmasuk[x][2]=kon.rs.getString("tanggal");
                databarangmasuk[x][3]=kon.rs.getString("kd_brg");
                databarangmasuk[x][4]=kon.rs.getString("nm_brg");
                databarangmasuk[x][5]=kon.rs.getString("deskripsi");
               databarangmasuk[x][6]=kon.rs.getString("nm_sp");
                databarangmasuk[x][7]=kon.rs.getString("jumlah");
                x++;
}
           tbl_brg.setModel(new DefaultTableModel(databarangmasuk,labelbarangmasuk));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void BacaTabelCari()
    {
        try{
            String sql="Select *From barang_masuk where id_pemesanan like '%" +tcari.getText()+"%'";
            kon.rs=kon.st.executeQuery(sql);
             ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            databarangmasuk= new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                databarangmasuk[x][0]=kon.rs.getString("nomor");
                databarangmasuk[x][1]=kon.rs.getString("id_pemesanan");
                databarangmasuk[x][2]=kon.rs.getString("tanggal");
                databarangmasuk[x][3]=kon.rs.getString("kd_brg");
                databarangmasuk[x][4]=kon.rs.getString("nm_brg");
                databarangmasuk[x][5]=kon.rs.getString("deskripsi");
               databarangmasuk[x][6]=kon.rs.getString("nm_sp");
                databarangmasuk[x][7]=kon.rs.getString("jumlah");
                x++;
}
           tbl_brg.setModel(new DefaultTableModel(databarangmasuk,labelbarangmasuk));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void SetTabel()
    {
        int row=tbl_brg.getSelectedRow();
        tno.setText((String)tbl_brg.getValueAt(row, 0));
        pesan.setText((String)tbl_brg.getValueAt(row, 1));
        tanggal.setDateFormatString((String)tbl_brg.getValueAt(row, 2));
        tkd_brg.setText((String)tbl_brg.getValueAt(row, 3));
        tnm_brg.setText((String)tbl_brg.getValueAt(row, 4));
         tdes.setText((String)tbl_brg.getValueAt(row, 5));
        tnm_supplier.setText((String)tbl_brg.getValueAt(row, 6));
       tjumlah.setText((String)tbl_brg.getValueAt(row, 7));
}
    
    private void UpdateData()
    {
       try{
            String sql="update barang_masuk set nomor='"+tno.getText()+"',"
                    +"id_pemesanan='"+pesan.getText()+"',"
                    +"tanggal='"+tanggal.getDate()+"',"
                    +"nm_brg='"+tnm_brg.getText()+"',"
                    +"deskripsi='"+tdes.getText()+"',"
                    +"nm_sp='"+tnm_supplier.getText()+"',"
                    +"jumlah='"+tjumlah.getText()+"'where nomor='"+tno.getText()+"'";
                   
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data Berhasil Diedit");
            bersih();
            BacaTabel();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e); 
    
    }
    }
    private void HapusData()
    {
       try{
            String sql="delete from barang_masuk where nomor='"+tno.getText()+"'";
                   
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            bersih();
            BacaTabel();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e); 
    }
    }
private void SimpanData(){
try{
String sql="insert into barang_masuk values('"+tno.getText()+"','"+tanggal.getDate()+"','"+tnm_brg.getText()+"','"+tdes.getText()+"','"+tnm_supplier.getText()+"','"+tjumlah.getText()+"')";
kon.st.executeUpdate(sql);
JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
bersih();
BacaTabel();
}
catch(SQLException e){
JOptionPane.showMessageDialog(null,e);
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
        bt_hapus = new javax.swing.JButton();
        bt_keluar = new javax.swing.JButton();
        bt_batal = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pesan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tnm_brg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tdes = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        tnm_supplier = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tjumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        tkd_brg = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_brg = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aplikasi_Barang_Masuk");

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
        jPanel3.add(bt_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 160, 43));

        bt_batal.setBackground(new java.awt.Color(255, 255, 255));
        bt_batal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bt_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        bt_batal.setText("Batal");
        bt_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batalActionPerformed(evt);
            }
        });
        jPanel3.add(bt_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 160, 40));

        jPanel4.setBackground(new java.awt.Color(102, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setText("Tanggal");

        pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesanActionPerformed(evt);
            }
        });

        jLabel2.setText("No Pemesanan");

        jLabel4.setText("Nama Barang");

        jLabel5.setText("Deskripsi");

        tanggal.setDateFormatString("yyyy-MM-dd");

        tdes.setColumns(20);
        tdes.setRows(5);
        jScrollPane3.setViewportView(tdes);

        jLabel7.setText("Nama Supplier");

        jLabel9.setText("Jumlah");

        jLabel10.setText("No Barang Masuk");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tkd_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkd_brgActionPerformed(evt);
            }
        });

        jLabel11.setText("Kode Barang");

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tnm_brg, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(tnm_supplier, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tno, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tkd_brg)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tnm_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        tbl_brg.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_brgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_brg);

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

        jLabel3.setText("Cari Barang Masuk");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void bt_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_simpanActionPerformed
        // TODO add your handling code here:
        conn = null;
        Statement stmt;

        if (    tno.getText().equals("") ||
                 pesan.getText().equals("") ||
              tkd_brg.getText().equals("")||
            tnm_brg.getText().equals("") ||
           tdes.getText().equals("") ||
            tnm_supplier.getText().equals("") ||
            tjumlah.getText().equals(""))
        {
            JOptionPane.showMessageDialog (rootPane,"Data belum lengkap.");
        }
        else {
            String Tanggal = ((JTextField)tanggal.getDateEditor().getUiComponent()).getText();
            String nomor= tno.getText();
              String Idpemesanan= pesan.getText();
           String Kodebarang = tkd_brg.getText();
            String namabarang = tnm_brg.getText();
          String deskripsi = tdes.getText();
            String namasupplier = tnm_supplier.getText();
            String jumlah = tjumlah.getText();
            try {

                Date skrg=new Date();
                SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
                String tgl=frm.format(skrg);
                conn = DriverManager.getConnection("jdbc:mysql://localhost/penjualan_db","root","");
                stmt = conn.createStatement();
                stmt.executeUpdate("insert into barang_masuk values('"+nomor+"','"+Idpemesanan+"','"+Tanggal+"','"+Kodebarang+"','"+namabarang+"','"+deskripsi+"','"+namasupplier+"','"+jumlah+"')");
                JOptionPane.showMessageDialog(null,"Data Berhasil DiSimpan!");

            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        BacaTabel();
        bersih();
    }//GEN-LAST:event_bt_simpanActionPerformed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        // TODO add your handling code here:
        aktif();
        bersih();
        id();
        tno.setEnabled(false);
        pesan.setEnabled(false);
        bt_batal.setEnabled(true);
        bt_tambah.setEnabled(false);
        bt_simpan.setEnabled(true);
    }//GEN-LAST:event_bt_tambahActionPerformed

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

    private void bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_keluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bt_keluarActionPerformed

    private void bt_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batalActionPerformed
        // TODO add your handling code here:
        nonaktif();
        bersih();
        bt_tambah.setEnabled(true);
    }//GEN-LAST:event_bt_batalActionPerformed

    private void tbl_brgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_brgMouseClicked
        // TODO add your handling code here:
        SetTabel();
        bt_hapus.setEnabled(true);

        bt_tambah.setEnabled(false);
    }//GEN-LAST:event_tbl_brgMouseClicked

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
        BacaTabel();
    }//GEN-LAST:event_tcariKeyTyped

    private void pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         boolean closable = true;
        Aplikasi_Data_Pesanan dataBarang=new Aplikasi_Data_Pesanan(null, closable);
        dataBarang.masuk = this;
        dataBarang.setVisible(true);
        dataBarang.setResizable(true);
        pesan.setText(IdPemesanan);
        tnm_brg.setText(NamaBarang);
        tnm_supplier.setText(NamaSupplier);
        tdes.setText(Deskripsi);
      tkd_brg.setText(KodeBarang);
        tjumlah.setText(Jumlah);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tkd_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkd_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkd_brgActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_batal;
    private javax.swing.JButton bt_hapus;
    private javax.swing.JButton bt_keluar;
    private javax.swing.JButton bt_simpan;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField pesan;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTable tbl_brg;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextArea tdes;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tkd_brg;
    private javax.swing.JTextField tnm_brg;
    private javax.swing.JTextField tnm_supplier;
    private javax.swing.JTextField tno;
    // End of variables declaration//GEN-END:variables

  
}
