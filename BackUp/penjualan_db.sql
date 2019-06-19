-- MySQL dump 10.16  Distrib 10.1.19-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `penjualan_db`
--

/*!40000 DROP DATABASE IF EXISTS `penjualan_db`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `penjualan_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `penjualan_db`;

--
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `kd_brg` varchar(20) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  PRIMARY KEY (`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` VALUES ('B001','Meja Rapat Berbentuk Oval','Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n',2200000,8),('B002','Kursi Roroco Jati','Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ',1500000,3),('B003','Jam Meja Duduk Kayu Jati','	\nJam Meja Duduk Kayu Jati Model Gong Rumah Joglo Antik Unik\n\nUkuran: 26x26x8 cm\n\nCocok buat hiasan meja kerja Anda atau pajangan pada ruangan Anda. Bisa juga sebagai kado atau souvenir.\n\nPembelian tidak termasuk baterai.',65000,10),('B004','Tempat Aqua Gelas Dari Kayu Jati Unik','	\nTempat air mineral gelas dari kayu jati untuk Tatakan air mineral gelas. Buatan pengrajin ukir Jepara. Meja tamu Anda menjadi lebih cantik dengan sajian air mineral ditempatkan pada Tatakan/Tempat air mineral ini.',198000,1),('B005','Lampu Meja Kayu Jati','PROMO: Free Bohlam Pijar 5 Watt\n\nLampu Meja ini sangat cocok untuk ditempatkan di kamar tidur atau ruang tamu, memberikan cahaya yang redup sehingga membuat ruangan anda lebih hangat dan menjaga kualitas tidur menjadi lebih baik. \n\nBahan : Kayu jati pilihan\nDimensi (p x l x t) : 10 x 10 x 25 cm\nFitting lampu : E27 Standar\nFinishing : Melamine natural doff\nKabel SNI panjang 1.5 meter dengan saklar On-Off. ',125000,9),('B006','Meja Rias Unik Kayu Jati','\nspesifikasi kursi tamu :\nbahan baku kayu jati\nFormasi 1 meja rias \nfinishing melamine\nModel Minimalis\nDi produksi oleh mebel jepara\n\nCocok untuk mengisi ruang makan anda\nProduk terbuat dari kayu jati yg sudah teruji kualitas ketahanannya\n\nfinishing melamine. salak brown ,natural wood/sesuai selera',3500000,1),('B007','Meja Belajar Laptop PS Kerja Kayu Jati Belanda ','Meja Belajar Kerja Kayu Kotak dengan spesifikasi sebagai berikut :\n1. Bahan: Jati Belanda / Kayu us bekas palet\n2. Ukuran Meja 80 cm x 40 cm\n3. Tinggi Meja 35 cm',154000,1),('B008','Meja Tamu Coffe Jati Besi','Lahat merupakan meja tamu dengan desain industrial minimalis yang menarik. Kehangatan kayu yang dipadupadankan dengan material bergaya industrial seperti besi, menawarkan definisi yang tepat antara keindahan alami dengan ketrampilan pengrajin. \n\nPanjang: 70 CM\nLebar: 70 CM\nTinggi: 52 CM\n\nMaterial: Solid Suar & Kombinasi Besi\n\nDengan membeli produk karya pengrajin lokal melalui Mendekor, kamu telah turut ambil bagian dalam menata kehidupan mereka, menjadi lebih baik dan memajukan industri kreatif di Indonesia',1949000,10);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barang_keluar`
--

DROP TABLE IF EXISTS `barang_keluar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang_keluar` (
  `tanggal` date NOT NULL,
  `kd_brg` varchar(50) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `nm_member` varchar(700) NOT NULL,
  `jumlah` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang_keluar`
--

LOCK TABLES `barang_keluar` WRITE;
/*!40000 ALTER TABLE `barang_keluar` DISABLE KEYS */;
INSERT INTO `barang_keluar` VALUES ('2019-05-06','B001','Meja Rapat Berbentuk Oval','Rohman','1'),('2019-05-06','B002','Kursi Roroco Jati','Rohman','1');
/*!40000 ALTER TABLE `barang_keluar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barang_masuk`
--

DROP TABLE IF EXISTS `barang_masuk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang_masuk` (
  `tanggal` date NOT NULL,
  `kd_brg` varchar(20) NOT NULL,
  `nm_brg` varchar(50) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `nm_sp` varchar(50) NOT NULL,
  `jumlah` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang_masuk`
--

LOCK TABLES `barang_masuk` WRITE;
/*!40000 ALTER TABLE `barang_masuk` DISABLE KEYS */;
INSERT INTO `barang_masuk` VALUES ('2019-05-05','P001','Meja Rapat Berbentuk Oval','Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n','Toko Jaya Furniture',1),('2019-05-06','P002','Kursi Roroco Jati','Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ','Toko Jaya Furniture',1);
/*!40000 ALTER TABLE `barang_masuk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailpemesanan`
--

DROP TABLE IF EXISTS `detailpemesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detailpemesanan` (
  `id_pemesanan` varchar(100) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `nm_sp` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailpemesanan`
--

LOCK TABLES `detailpemesanan` WRITE;
/*!40000 ALTER TABLE `detailpemesanan` DISABLE KEYS */;
INSERT INTO `detailpemesanan` VALUES ('PO-05/2019001','Meja Rapat Berbentuk Oval','Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n','Toko Samsul Furniture',1),('002/GLJ-PO/05/2019','Meja Rapat Berbentuk Oval','Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n','Toko Samsul Furniture',1),('PO-05/2019002','Meja Rapat Berbentuk Oval','Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n','Toko Samsul Furniture',1),('PO-05/2019003','Kursi Roroco Jati','Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ','Toko Jaya Furniture',1);
/*!40000 ALTER TABLE `detailpemesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailtransaksi`
--

DROP TABLE IF EXISTS `detailtransaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detailtransaksi` (
  `notransaksi` varchar(100) NOT NULL,
  `kd_brg` varchar(10) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `hrg_jual` double NOT NULL,
  `jumlahbeli` varchar(11) NOT NULL,
  `subtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailtransaksi`
--

LOCK TABLES `detailtransaksi` WRITE;
/*!40000 ALTER TABLE `detailtransaksi` DISABLE KEYS */;
INSERT INTO `detailtransaksi` VALUES ('FK-05/2019001','B001','Meja Rapat Berbentuk Oval','Rohman',2200000,'1',2200000),('FK-05/2019001','B002','Kursi Roroco Jati','Rohman',1500000,'1',1500000);
/*!40000 ALTER TABLE `detailtransaksi` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `stokupdate` AFTER INSERT ON `detailtransaksi` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-NEW.jumlahbeli
WHERE kd_brg=NEW.kd_brg;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `kd_member` varchar(50) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `notlp` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('M001','Rohman','Kp.Jombang, RT/RW : 002/003 No 51\nLengkong Gudang Timur','Tangerang Selatan','089602484584');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pemesanan`
--

DROP TABLE IF EXISTS `pemesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pemesanan` (
  `id_pemesanan` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  PRIMARY KEY (`id_pemesanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pemesanan`
--

LOCK TABLES `pemesanan` WRITE;
/*!40000 ALTER TABLE `pemesanan` DISABLE KEYS */;
INSERT INTO `pemesanan` VALUES ('002/GLJ-PO/05/2019','2019-05-05'),('PO-05/2019001','2019-05-05'),('PO-05/2019002','2019-05-06'),('PO-05/2019003','2019-05-06');
/*!40000 ALTER TABLE `pemesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengiriman`
--

DROP TABLE IF EXISTS `pengiriman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pengiriman` (
  `id_kirim` varchar(20) NOT NULL,
  `tanggal` date NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `nm_member` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengiriman`
--

LOCK TABLES `pengiriman` WRITE;
/*!40000 ALTER TABLE `pengiriman` DISABLE KEYS */;
INSERT INTO `pengiriman` VALUES ('SJ-05/2019001','2019-05-06','Kursi Roroco Jati','Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ','Rohman','Kp.Jombang, RT/RW : 002/003 No 51 Lengkong Gudang Timur','Tangerang Selatan',8),('SJ-05/2019002','2019-05-06','Meja Rias Unik Kayu Jati','\nspesifikasi kursi tamu :\nbahan baku kayu jati\nFormasi 1 meja rias \nfinishing melamine\nModel Minimalis\nDi produksi oleh mebel jepara\n\nCocok untuk mengisi ruang makan anda\nProduk terbuat dari kayu jati yg sudah teruji kualitas ketahanannya\n\nfinishing melamine. salak brown ,natural wood/sesuai selera','Rohman','Kp.Jombang, RT/RW : 002/003 No 51 Lengkong Gudang Timur','Tangerang Selatan',8),('SJ-05/2019003','2019-05-06','Kursi Roroco Jati','Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ','Rohman','Kp.Jombang, RT/RW : 002/003 No 51 Lengkong Gudang Timur','Tangerang Selatan',2),('SJ-05/2019003','2019-05-06','Meja Rias Unik Kayu Jati','\nspesifikasi kursi tamu :\nbahan baku kayu jati\nFormasi 1 meja rias \nfinishing melamine\nModel Minimalis\nDi produksi oleh mebel jepara\n\nCocok untuk mengisi ruang makan anda\nProduk terbuat dari kayu jati yg sudah teruji kualitas ketahanannya\n\nfinishing melamine. salak brown ,natural wood/sesuai selera','Rohman','Kp.Jombang, RT/RW : 002/003 No 51 Lengkong Gudang Timur','Tangerang Selatan',3);
/*!40000 ALTER TABLE `pengiriman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sementara`
--

DROP TABLE IF EXISTS `sementara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sementara` (
  `kd_brg` varchar(10) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `hrg_jual` int(11) NOT NULL,
  `jumlahbeli` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sementara`
--

LOCK TABLES `sementara` WRITE;
/*!40000 ALTER TABLE `sementara` DISABLE KEYS */;
/*!40000 ALTER TABLE `sementara` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sementara_kirim`
--

DROP TABLE IF EXISTS `sementara_kirim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sementara_kirim` (
  `nm_brg` varchar(50) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sementara_kirim`
--

LOCK TABLES `sementara_kirim` WRITE;
/*!40000 ALTER TABLE `sementara_kirim` DISABLE KEYS */;
/*!40000 ALTER TABLE `sementara_kirim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sementara_pesan`
--

DROP TABLE IF EXISTS `sementara_pesan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sementara_pesan` (
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `nm_sp` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sementara_pesan`
--

LOCK TABLES `sementara_pesan` WRITE;
/*!40000 ALTER TABLE `sementara_pesan` DISABLE KEYS */;
/*!40000 ALTER TABLE `sementara_pesan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `Id_supplier` varchar(20) NOT NULL,
  `Nama_supplier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('SP001','Toko Jaya Furniture'),('SP002','Toko Pamulang Furniture'),('SP003','Toko Samsul Furniture');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksi` (
  `notransaksi` varchar(100) NOT NULL,
  `tgl` date NOT NULL,
  `totalbayar` double NOT NULL,
  `kd_user` varchar(10) NOT NULL,
  PRIMARY KEY (`notransaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES ('FK-05/2019001','2019-05-06',3700000,'12162829');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `kd_user` varchar(20) NOT NULL,
  `nm_user` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('12162829','Rohman','12345','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-06 21:02:07
