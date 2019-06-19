-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2019 at 03:26 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kd_brg` varchar(20) NOT NULL,
  `nm_brg` varchar(25) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kd_brg`, `nm_brg`, `deskripsi`, `harga`, `stok`) VALUES
('B001', 'Kursi Racoco Jati', 'Kayu Jati Finishing Warna Doop Natural NC (Natural Couting) Combinsi Loster Merek Jog Cleoptra, Tebal busa 8 cm, Sepatu anti gores, anti air dan anti toksit/tidak bau ', 1600000, 30),
('B002', 'Meja Rapat Berbentuk Oval', 'Ukuran 6 x 1,75 m = 10 m2\nOval, 12 Kaki Meja, Daun Meja Full Ukir, Finishing Warna Doop Natural (NC) Kombinasi Loster Merek Jog cleopatra, Tebal Meja 4 cm, Tebal Kaca 1 cm, Bevel dan Poly, Anti Air, Anti Gores dan Anti Toksit/Tidak Bau\n', 3000000, 1),
('B003', 'Meja Rias Set Bangku ', '	\nMeja Rias Multifungsi bisa digunakan sebagai meja belajar. Posisi cermin terletak dibalik top table sehingga praktis dalam pemakiannya. Dibagian laci meja terdapat sekat-sekat untuk mengatur kosmetik dan pernak pernik koleksi Anda.\n\nDimensi Produk\nMeja: 75 x 57.5 x 130 cm\nStool: 43 x 30 x 45 cm\n\nHarga Meja: 1.750.000\nHarga Bangku: 450.000\nHarga Set: 2.150.000', 2150000, 1),
('B004', 'Lemari Meja Rak TV Milano', 'LEMARI MEJA RAK TV\nMilano White Series\n\nDimensi 184 x 42 x 62 cm\nMaterial Kayu Mahoni, Premium Fiberboard, Veener Mahoni\nBahan Cat Tahan Lama dan Ramah Lingkungan\n*Dikirim dalam bentuk jadi tanpa rakit', 4000000, 2),
('B005', 'Rak Dinding Minimalis', 'Rak dinding minimalis dengan design simpel dan modern. Menjadikan ruang tamu, ruang keluarga atau kamar tidur Anda menjadi semakin elegan. Penempatan pemasangan bisa sesuai gambar atau di bentuk lain yang lebih menarik.\n\nSpesifikasi :\n*Ukuran :\n- Tinggi : 15 cm\n- Lebar : 9 cm\n- Panjang : 44 cm\n- Ketebalan Papan : +- 1,7 cm', 60000, 10),
('B006', 'Kursi Tamu Sofa Meja Set', '	\nBahan kayu jati pilihan berkualitas\nFinishing melamine. salak brown ,natural wood (by request)\n3211+meja+kaca\n\n\nProduk furniture yang kami buat menggunakan bahan dasar dari kayu jati dan kayu mahoni pilihan, di proses secara baik oleh tenaga ahli pengrajin furniture kami dalam memproduksi furniture kami di jepara. Dengan standar kualitas ekport di berbagai manca negara.\n- menerima berbagai macam furniture perabot rumah dengan desain terbaik antara lain : almari pakaian, almari hias, kursi tamu, kursi sofa, kursi makan, nakas, bufet tv , kitchen dll.', 18000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `barang_keluar`
--

CREATE TABLE `barang_keluar` (
  `id_kirim` varchar(20) NOT NULL,
  `notransaksi` varchar(25) NOT NULL,
  `tanggal` date NOT NULL,
  `nm_member` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `nm_brg` varchar(25) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `barang_masuk`
--

CREATE TABLE `barang_masuk` (
  `nomor` varchar(20) NOT NULL,
  `id_pemesanan` varchar(25) NOT NULL,
  `tanggal` date NOT NULL,
  `kd_brg` varchar(25) NOT NULL,
  `nm_brg` varchar(50) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `nm_sp` varchar(50) NOT NULL,
  `jumlah` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `barang_masuk`
--
DELIMITER $$
CREATE TRIGGER `nambah` AFTER INSERT ON `barang_masuk` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok+NEW.jumlah
WHERE kd_brg=NEW.kd_brg;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detailpemesanan`
--

CREATE TABLE `detailpemesanan` (
  `id_pemesanan` varchar(100) NOT NULL,
  `kd_brg` varchar(20) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `nm_sp` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `harga_beli` double NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaksi`
--

CREATE TABLE `detailtransaksi` (
  `notransaksi` varchar(25) NOT NULL,
  `kd_brg` varchar(10) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `hrg_jual` double NOT NULL,
  `jumlahbeli` varchar(11) NOT NULL,
  `subtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `detailtransaksi`
--
DELIMITER $$
CREATE TRIGGER `stokupdate` AFTER INSERT ON `detailtransaksi` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-NEW.jumlahbeli
WHERE kd_brg=NEW.kd_brg;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `kd_member` varchar(25) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `notlp` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`kd_member`, `nm_member`, `alamat`, `kota`, `notlp`) VALUES
('M001', 'Rohman', 'Kp.Jombang, RT/RW : 002/003 No 51\nLengkong Gudang Timur', 'Tangerang Selatan', '089602484584'),
('M002', 'Rohmad Saffi', 'Ciputat Raya', 'Tangerang Selatan', '081212345678');

-- --------------------------------------------------------

--
-- Table structure for table `pemesanan`
--

CREATE TABLE `pemesanan` (
  `id_pemesanan` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `total` double NOT NULL,
  `Id_supplier` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sementara`
--

CREATE TABLE `sementara` (
  `kd_brg` varchar(10) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `nm_member` varchar(100) NOT NULL,
  `hrg_jual` int(11) NOT NULL,
  `jumlahbeli` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sementara_kirim`
--

CREATE TABLE `sementara_kirim` (
  `nm_brg` varchar(50) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sementara_pesan`
--

CREATE TABLE `sementara_pesan` (
  `kd_brg` varchar(20) NOT NULL,
  `nm_brg` varchar(100) NOT NULL,
  `deskripsi` varchar(600) NOT NULL,
  `nm_sp` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `Id_supplier` varchar(20) NOT NULL,
  `Nama_supplier` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tlp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`Id_supplier`, `Nama_supplier`, `alamat`, `tlp`) VALUES
('SP001', 'Satu Hati Furniture', 'Jalan Dewi Sartika, Samping Ramayana Ciputat N0.25', '0212758890'),
('SP002', 'Ciputat Jaya Furniture', 'Jl. Aria Putra No.18', '087890192837');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `notransaksi` varchar(100) NOT NULL,
  `tgl` date NOT NULL,
  `totalbayar` double NOT NULL,
  `kd_user` varchar(25) NOT NULL,
  `kd_member` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `kd_user` varchar(20) NOT NULL,
  `nm_user` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`kd_user`, `nm_user`, `password`, `level`) VALUES
('K001', 'Rohman', '123', '1'),
('K002', 'Rohmad Safii', '123', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_brg`),
  ADD UNIQUE KEY `nm_brg` (`nm_brg`);

--
-- Indexes for table `barang_keluar`
--
ALTER TABLE `barang_keluar`
  ADD PRIMARY KEY (`id_kirim`),
  ADD KEY `notransaksi` (`notransaksi`);

--
-- Indexes for table `barang_masuk`
--
ALTER TABLE `barang_masuk`
  ADD PRIMARY KEY (`nomor`),
  ADD KEY `id_pemesanan` (`id_pemesanan`);

--
-- Indexes for table `detailpemesanan`
--
ALTER TABLE `detailpemesanan`
  ADD KEY `id_pemesanan` (`id_pemesanan`);

--
-- Indexes for table `detailtransaksi`
--
ALTER TABLE `detailtransaksi`
  ADD KEY `notransaksi` (`notransaksi`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`kd_member`);

--
-- Indexes for table `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`id_pemesanan`),
  ADD KEY `Id_supplier` (`Id_supplier`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`Id_supplier`),
  ADD UNIQUE KEY `Nama_supplier` (`Nama_supplier`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`notransaksi`),
  ADD KEY `kd_user` (`kd_user`),
  ADD KEY `kd_member` (`kd_member`);
ALTER TABLE `transaksi` ADD FULLTEXT KEY `kd_user_2` (`kd_user`);
ALTER TABLE `transaksi` ADD FULLTEXT KEY `kd_user_3` (`kd_user`);
ALTER TABLE `transaksi` ADD FULLTEXT KEY `kd_member_2` (`kd_member`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`kd_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang_keluar`
--
ALTER TABLE `barang_keluar`
  ADD CONSTRAINT `barang_keluar_ibfk_1` FOREIGN KEY (`notransaksi`) REFERENCES `transaksi` (`notransaksi`);

--
-- Constraints for table `detailpemesanan`
--
ALTER TABLE `detailpemesanan`
  ADD CONSTRAINT `detailpemesanan_ibfk_1` FOREIGN KEY (`id_pemesanan`) REFERENCES `pemesanan` (`id_pemesanan`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
