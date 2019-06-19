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
public class Aplikasi_Transaksi extends javax.swing.JInternalFrame {
koneksi kon = new koneksi();
private Object [][] datasementara=null;
private String[]labelsementara={"Kode Barang","Nama Barang","Nama Member","Harga","Jumlah Beli","Subtotal"};
public StringTokenizer token;
public String gantiformat="";
    /**
     * Creates new form Aplikasi_Transaksi1
     */
    public Aplikasi_Transaksi() {
        initComponents();
        kon.setKoneksi();
        setTanggal();
        awal();
    }
    public Date date=new Date ();
    public SimpleDateFormat noformat=new SimpleDateFormat("MM/yyyy");
    public String KodeBarang;
    public String NamaBarang;
    public String HargaBarang;
    public String KodeUser;
    public String NamaMember;
     public String KodeMember;
    public String getNamaMember(){
        return NamaMember;
    }
     public String getKodeMember(){
        return KodeMember;
    }
    public String getKodeBarang(){
        return KodeBarang;
    }
    public String getNamaBarang(){
        return NamaBarang;
    }
    public String getHargaBarang(){
        return HargaBarang;
    }
    public String getKodeUser(){
        return KodeUser;
    }
    private void bersih()
    {
        tnotrans.setText("");
        tkodebarang.setText("");
        tharga.setText("");
        tjumbel.setText("");
        tsubtotal.setText("");
        tbayar.setText("");
        tkembali.setText("");
        ltotal.setText("0");
    }
    private void nonaktif()
    {
        tnotrans.setEditable(false);
        ttanggal.setEditable(false);
        tkodebarang.setEditable(false);
        tkodeuser.setEditable(false);
        tnamabarang.setEditable(false);
        tharga.setEditable(false);
        tjumbel.setEditable(false);
        tsubtotal.setEditable(false);
        tbayar.setEditable(false);
        tkembali.setEditable(false);
    }
    private void aktif()
    {
        tkodebarang.setEditable(true);
        tjumbel.setEditable(true);
        tbayar.setEditable(true);
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
    kon.rs=kon.st.executeQuery("select right(notransaksi,3)+1 "
            + "from transaksi as Nomor order by notransaksi desc");
    if(kon.rs.next())
    {
        urutan=kon.rs.getString(1);
        while(urutan.length()<3)
            urutan="0"+urutan;
        urutan="FK-"+noformat.format(date)+urutan;
    }else
    {
        urutan="FK-"+noformat.format(date)+"001";    
}
    }
catch (Exception e){
    JOptionPane.showMessageDialog(null, e);
}
return urutan;
}

private void TampilTabelSementara(){
    try{
        String sql="Select *From sementara order by kd_brg";
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
                datasementara[x][1]=kon.rs.getString("nm_brg");
                datasementara[x][2]=kon.rs.getString("nm_member");
                datasementara[x][3]=kon.rs.getString("hrg_jual");
                datasementara[x][4]=kon.rs.getString("jumlahbeli");
                datasementara[x][5]=kon.rs.getString("subtotal");
                x++;
            }
            tbtransaksi.setModel(new DefaultTableModel(datasementara,labelsementara));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampildatabarang()
    {
        try{
            String sql="select * from barang where kd_brg='"+tkodebarang.getText()+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next())
            {
                tnamabarang.setText(kon.rs.getString("nm_brg"));
                tharga.setText(kon.rs.getString("harga"));
                tjumbel.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Kode Barang"+tkodebarang.getText()+"tidak ditemukan");
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
            }

    private void SimpanSementara(){
        try{
            String sql="insert into sementara values('"+tkodebarang.getText()+"',"
                    + "'"+tnamabarang.getText()+"','"+tmember.getText()+"',"
                    + "'"+tharga.getText()+"','"+tjumbel.getText()+"',"
                    + "'"+tsubtotal.getText()+"')";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void HapusIsiSementara(){
        int row=tbtransaksi.getSelectedRow();
        int x;
        int total=Integer.parseInt(ltotal.getText());
        x=Integer.parseInt((String)tbtransaksi.getValueAt(row, 5));
        total=total-x;
        ltotal.setText(Integer.toString(total));
        try{
            String sql="Delete from sementara where kd_brg'"+(String)tbtransaksi.getValueAt(row,0)+"'";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
    private void SimpanTransaksi(){
        try{
            String sql="insert into transaksi values('"+tnotrans.getText()+"',"
                    + "'"+ttanggal.getText()+"','"+ltotal.getText()+"',"
                    + "'"+tkodeuser.getText()+"','"+tkodemember.getText()+"')";
            kon.st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }
    private void simpanDetailTransaksi()
    {
        try{
            String detail = "insert detailtransaksi select '" +tnotrans.getText() +"',kd_brg,nm_brg,nm_member,hrg_jual,jumlahbeli,subtotal from sementara";
            kon.st.executeUpdate(detail);
        }
        catch(SQLException e){
            System.out.println("koneksi gagal"+ e.toString());
        }
    }
    private void HapusTabelSementara(){
        try{
            String sql="Delete from sementara ";
            kon.st.executeUpdate(sql);
            TampilTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

private void cetakstruk(){
    try{
        
       String file = "src/laporan/Struk.jasper";
        HashMap param = new HashMap();
        param.put("notrans", tnotrans.getText());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tnotrans = new javax.swing.JTextField();
        ttanggal = new javax.swing.JTextField();
        tkodeuser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tkodebarang = new javax.swing.JTextField();
        tnamabarang = new javax.swing.JTextField();
        tmember = new javax.swing.JTextField();
        tharga = new javax.swing.JTextField();
        tjumbel = new javax.swing.JTextField();
        tsubtotal = new javax.swing.JTextField();
        btbrowse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtransaksi = new javax.swing.JTable();
        bttambah = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        btkeluar = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ltotal = new javax.swing.JLabel();
        tkembali = new javax.swing.JTextField();
        tbayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btbrowse1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        tkodemember = new javax.swing.JTextField();

        setBackground(new java.awt.Color(102, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Aplikasi Transaksi");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setText("No. Transaksi");

        jLabel2.setText("Tanggal");

        jLabel3.setText("Kode User");

        tnotrans.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });

        tkodeuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkodeuserActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama Member");

        jLabel5.setText("Nama Barang");

        jLabel6.setText("Harga Barang");

        jLabel7.setText("Jumlah Beli");

        jLabel8.setText("Subtotal");

        tkodebarang.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tkodebarangComponentAdded(evt);
            }
        });
        tkodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkodebarangActionPerformed(evt);
            }
        });

        tmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmemberActionPerformed(evt);
            }
        });

        tharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thargaActionPerformed(evt);
            }
        });

        tjumbel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumbelActionPerformed(evt);
            }
        });

        btbrowse.setText("...");
        btbrowse.setToolTipText("Cari barang");
        btbrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowseActionPerformed(evt);
            }
        });

        tbtransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbtransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbtransaksiKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbtransaksi);

        bttambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bttambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/m.png"))); // NOI18N
        bttambah.setText("TAMBAH");
        bttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttambahActionPerformed(evt);
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

        btbatal.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_furniture/bersih.png"))); // NOI18N
        btbatal.setText("BATAL");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Rp");

        ltotal.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        ltotal.setText("total");

        tkembali.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tkembali.setForeground(new java.awt.Color(51, 51, 51));
        tkembali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tkembaliKeyTyped(evt);
            }
        });

        tbayar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tbayar.setForeground(new java.awt.Color(51, 51, 51));
        tbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbayarActionPerformed(evt);
            }
        });
        tbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbayarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbayarKeyTyped(evt);
            }
        });

        jLabel11.setText("Uang Bayar");

        jLabel12.setText("Uang Kembali");

        jLabel10.setText("Kode Barang");

        btbrowse1.setText("...");
        btbrowse1.setToolTipText("Cari barang");
        btbrowse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbrowse1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jRadioButton1.setText("DEBIT");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jRadioButton2.setText("TUNAI");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Kode Member");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(jLabel2)
                .addGap(92, 92, 92)
                .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tkodeuser, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bttambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btsimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btbatal))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9)
                                .addGap(54, 54, 54)
                                .addComponent(ltotal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tkodemember, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btbrowse1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tmember, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btbrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tharga, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tjumbel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tnotrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(ttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(tkodeuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btbrowse1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tkodemember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btbrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tjumbel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bttambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltotal)
                            .addComponent(jLabel9))
                        .addContainerGap())))
        );

        setBounds(0, 0, 1199, 401);
    }// </editor-fold>//GEN-END:initComponents

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggalActionPerformed

    private void tkodeuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkodeuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkodeuserActionPerformed

    private void tkodebarangComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tkodebarangComponentAdded
        // TODO add your handling code here:
        tjumbel.requestFocus();
    }//GEN-LAST:event_tkodebarangComponentAdded

    private void tkodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkodebarangActionPerformed
        // TODO add your handling code here:
        tampildatabarang();
    }//GEN-LAST:event_tkodebarangActionPerformed

    private void tmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tmemberActionPerformed

    private void thargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thargaActionPerformed

    private void tjumbelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumbelActionPerformed
        // TODO add your handling code here:

        int harga,jumbel,total;

        harga=Integer.parseInt(tharga.getText());
        jumbel=Integer.parseInt(tjumbel.getText());
        total=harga*jumbel;

        tsubtotal.setText(Integer.toString(total));
        SimpanSementara();
        TampilTabelSementara();
        int ttl=0;
        for(int a=0;a<tbtransaksi.getRowCount();a++)
        {
            int sub=Integer.parseInt((String)tbtransaksi.getValueAt(a, 5));
            ttl+=sub;
        }
        ltotal.setText(Integer.toString(ttl));

        if(JOptionPane.showConfirmDialog(this, "Mau Tambah Barang?",
            "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        aktif();
        tkodebarang.requestFocus();
        tkodebarang.setText("");
        tnamabarang.setText("");
        tmember.setText("");
        tharga.setText("");
        tjumbel.setText("");
        tsubtotal.setText("");
        }else{

        }
    }//GEN-LAST:event_tjumbelActionPerformed

    private void btbrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowseActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Barang dataBarang=new Aplikasi_Data_Barang(null, closable);
        dataBarang.transaksi = this;
        dataBarang.setVisible(true);
        dataBarang.setResizable(true);
        tkodebarang.setText(KodeBarang);
        tnamabarang.setText(NamaBarang);
        tharga.setText(HargaBarang);
    }//GEN-LAST:event_btbrowseActionPerformed

    private void tbtransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbtransaksiKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE)
        {
            HapusIsiSementara();
        }
    }//GEN-LAST:event_tbtransaksiKeyPressed

    private void bttambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttambahActionPerformed
        // TODO add your handling code here:
        aktif();
        tnotrans.setText(nomor());
        tkodeuser.setText(KodeUser);
        TampilTabelSementara();
    }//GEN-LAST:event_bttambahActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        if (    tnotrans.getText().equals("") ||
                 ttanggal.getText().equals("") ||
              tkodeuser.getText().equals("")||
            tkodemember.getText().equals("") ||
           tmember.getText().equals("") ||
            tkodebarang.getText().equals("") ||
            tnamabarang.getText().equals("") ||
            tharga.getText().equals("") ||
            tjumbel.getText().equals("") ||
            tsubtotal.getText().equals(""))
        {
            JOptionPane.showMessageDialog (rootPane,"Data belum lengkap.");
        } else {
            bttambah.setEnabled(true);
            btkeluar.setEnabled(true);
        SimpanTransaksi();
        simpanDetailTransaksi();
        JOptionPane.showMessageDialog(this,"Berhasil disimpan",
            "Informasi", JOptionPane.INFORMATION_MESSAGE);
        TampilTabelSementara();
        if(JOptionPane.showConfirmDialog(this, "Mau Cetak Struk?",
            "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
        cetakstruk();

        HapusTabelSementara();
        awal();
        bersih();
        tnotrans.setText("");
        TampilTabelSementara();
        }
        else
        {
            HapusTabelSementara();
            awal();
            bersih();
            tnotrans.setText("");
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
        tnotrans.setText("");
    }//GEN-LAST:event_btbatalActionPerformed

    private void tkembaliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkembaliKeyTyped
        // TODO add your handling code here:
        //validasi data harus angka atau tombol backspace

    }//GEN-LAST:event_tkembaliKeyTyped

    private void tbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbayarActionPerformed
        // TODO add your handling code here:
        int kembali,total,bayar;
        total=Integer.parseInt(ltotal.getText());
        bayar=Integer.parseInt(tbayar.getText());
        kembali=bayar-total;
        tkembali.setText(Integer.toString(kembali));

    }//GEN-LAST:event_tbayarActionPerformed

    private void tbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbayarKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbayarKeyPressed

    private void tbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbayarKeyTyped
        // TODO add your handling code here:
        //validasi data harus angka atau tombol backspace

    }//GEN-LAST:event_tbayarKeyTyped

    private void btbrowse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbrowse1ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        Aplikasi_Data_Member dataMember=new Aplikasi_Data_Member(null, closable);
        dataMember.transaksi = this;
        dataMember.setVisible(true);
        dataMember.setResizable(true);
        tkodemember.setText(KodeMember);
        tmember.setText(NamaMember);

    }//GEN-LAST:event_btbrowse1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
         int totl=0;
        for(int a=0;a<tbtransaksi.getRowCount();a++)
        {
            int sub=Integer.parseInt((String)tbtransaksi.getValueAt(a, 5));
            totl+=sub;
        }
        tbayar.setText(Integer.toString(totl));

        tbayar.requestFocus();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
         tbayar.setText("");
        tbayar.requestFocus();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        tkodeuser.setText(KodeUser);
        TampilTabelSementara();
    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbatal;
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btbrowse1;
    private javax.swing.JButton btkeluar;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton bttambah;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ltotal;
    private javax.swing.JTextField tbayar;
    private javax.swing.JTable tbtransaksi;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tjumbel;
    private javax.swing.JTextField tkembali;
    private javax.swing.JTextField tkodebarang;
    private javax.swing.JTextField tkodemember;
    private javax.swing.JTextField tkodeuser;
    private javax.swing.JTextField tmember;
    private javax.swing.JTextField tnamabarang;
    private javax.swing.JTextField tnotrans;
    private javax.swing.JTextField tsubtotal;
    private javax.swing.JTextField ttanggal;
    // End of variables declaration//GEN-END:variables

  
}
