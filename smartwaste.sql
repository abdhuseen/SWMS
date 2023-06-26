-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 26, 2023 at 06:46 AM
-- Server version: 8.0.17
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartwaste`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `aname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assn` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adateofbirth` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `asalary` double NOT NULL,
  `aaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `aphone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apassword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`aid`, `aname`, `assn`, `adateofbirth`, `asalary`, `aaddress`, `aphone`, `apassword`) VALUES
(1, 'علي الماجد', '123456789', '1995-02-02', 500, 'عمان', '147852369', '1234'),
(2, 'عمر الرسلان', '9874562354', '1986-10-02', 1200, 'الزرقاء', '50602354', '1010'),
(3, 'علي المجيدي', '1234567890123456', '1995-01-01', 550, 'البلقاء', '0123456789', '1234'),
(4, 'موسى الصالحي ', '9632587410123456', '1992-04-13', 650, 'مأدبا', '9876543210', '5678'),
(5, 'نديم المالكي', '1234569877896540', '1990-10-10', 850, 'اربد', '0147852369', '9123'),
(6, 'ماجد كريم', '1020304050604085', '1988-12-11', 350, 'عجلون', '0123123123', '9632'),
(7, 'سامي سعيد', '9988776622110354', '1986-05-06', 850, 'جرش', '1010203050', '1010'),
(8, 'مالك العلي', '0003336669992221', '1991-12-12', 520, 'المفرق', '9080556622', '2030'),
(9, 'نذير مصطفى', '1616123210456789', '1992-10-18', 550, 'معان', '9998887771', '4045'),
(10, 'أكرم العامري', '2020503014789517', '1991-09-10', 950, 'العقبة', '1010103030', '6070'),
(11, 'سيف الصافي', '9633697411239518', '1991-12-12', 600, 'الكرك', '1238523691', '9090'),
(12, 'لؤي حمودة', '9631238521598521', '1988-11-15', 750, 'الطفيلة', '1597539512', '9000'),
(15, 'سليم خالد', '1234567899633221', '1975-01-01', 2500, 'عمان', '0782018202152', '0000');

-- --------------------------------------------------------

--
-- Table structure for table `bin`
--

CREATE TABLE `bin` (
  `bid` int(11) NOT NULL,
  `bstate` int(11) NOT NULL,
  `f_nid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bin`
--

INSERT INTO `bin` (`bid`, `bstate`, `f_nid`) VALUES
(1, 0, 1),
(3, 1, 3),
(4, 1, 4),
(5, 2, 5),
(6, 2, 6),
(7, 2, 7),
(8, 2, 8),
(9, 0, 9),
(10, 2, 10),
(11, 0, 1),
(12, 0, 1),
(13, 2, 8),
(14, 0, 8),
(15, 2, 8),
(16, 0, 1),
(17, 0, 1),
(18, 0, 1),
(22, 2, 8),
(23, 2, 8),
(26, 0, 1),
(27, 0, 10),
(28, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `ctid` int(11) NOT NULL,
  `ctname` varchar(100) NOT NULL,
  `f_aid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`ctid`, `ctname`, `f_aid`) VALUES
(1, 'عمان', 1),
(2, 'الزرقاء', 2),
(3, 'البلقاء', 3),
(4, 'مأدبا', 4),
(5, 'اربد', 5),
(6, 'عجلون', 6),
(7, 'جرش', 7),
(8, 'المفرق', 8),
(9, 'العقبة', 10),
(10, 'الكرك', 11),
(11, 'الطفيلة', 12),
(12, 'معان', 9);

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `cId` int(11) NOT NULL,
  `cdate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cdatecomplete` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isdone` int(100) NOT NULL,
  `ccontent` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_ctid` int(11) NOT NULL,
  `f_uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`cId`, `cdate`, `cdatecomplete`, `isdone`, `ccontent`, `f_ctid`, `f_uid`) VALUES
(5, '2023-06-12 12:12:26', '0', 1, 'jhfghjsaglhg', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `did` int(11) NOT NULL,
  `dname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dssn` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ddateofbirth` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dsalary` double NOT NULL,
  `daddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dphone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dpassword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`did`, `dname`, `dssn`, `ddateofbirth`, `dsalary`, `daddress`, `dphone`, `dpassword`) VALUES
(1, 'موسى العقيدي', '159753123', '1991-08-13', 400, 'عمان', '123321456', '123456'),
(2, 'كريم عفيفي', '654852369', '1993-12-14', 400, 'السلط', '012358963', '202020'),
(3, 'سالم سعيد', '1234567890147852', '1992-11-15', 400, 'عمان', '0795410867', '3030'),
(4, 'عون الصخيري', '789654987', '1997-12-12', 400, 'عمان', '00326554656', '4040'),
(5, 'عمر السامي', '50203696331', '1991-04-10', 500, 'عمان', '0034565654', '1212'),
(7, 'عون', '1234567890', '2023-02-23', 500, 'عمان', '1234567890', '0123456789');

-- --------------------------------------------------------

--
-- Table structure for table `neighborhood`
--

CREATE TABLE `neighborhood` (
  `nbid` int(11) NOT NULL,
  `nbname` varchar(100) NOT NULL,
  `f_ctid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `neighborhood`
--

INSERT INTO `neighborhood` (`nbid`, `nbname`, `f_ctid`) VALUES
(1, 'صويلح', 1),
(2, 'تلاع العلي', 1),
(3, 'أم السماق', 1),
(4, 'الجاردنز', 1),
(5, 'الكمالية', 1),
(6, 'أبو نصير', 1);

-- --------------------------------------------------------

--
-- Table structure for table `node`
--

CREATE TABLE `node` (
  `nid` int(11) NOT NULL,
  `nlatitude` double NOT NULL,
  `nlongitude` double NOT NULL,
  `nstreet` varchar(150) NOT NULL,
  `f_nbid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `node`
--

INSERT INTO `node` (`nid`, `nlatitude`, `nlongitude`, `nstreet`, `f_nbid`) VALUES
(1, 32.020255, 35.838976, 'الثورة العربية الكبرى', 2),
(3, 32.021323, 35.835162, 'ابن ظفر الصقلي', 1),
(4, 32.023875, 35.834264, 'احمد ابن رشد', 1),
(5, 32.020831, 35.834616, 'الميسلون', 1),
(6, 31.999139, 35.874113, 'علي الأزيدي', 2),
(7, 31.995736, 35.873361, 'موسى الساكت', 2),
(8, 32.05209, 35.874016, 'الأمانة', 6),
(9, 32.050184, 35.873317, 'الأمانة', 6),
(10, 31.998927, 35.850984, '\r\nعيسى الصبيحي', 4),
(15, 32.02026, 35.850984, 'dhdhjfd', 1);

-- --------------------------------------------------------

--
-- Table structure for table `node_trip`
--

CREATE TABLE `node_trip` (
  `f_nid` int(11) NOT NULL,
  `f_trid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `node_trip`
--

INSERT INTO `node_trip` (`f_nid`, `f_trid`) VALUES
(1, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(3, 2),
(5, 2),
(1, 3),
(3, 3),
(5, 3),
(1, 4),
(3, 4),
(5, 4),
(1, 5),
(3, 5),
(5, 5),
(1, 6),
(3, 6),
(4, 6),
(5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `trip`
--

CREATE TABLE `trip` (
  `trid` int(11) NOT NULL,
  `trstartTime` varchar(100) NOT NULL,
  `trendTime` varchar(100) NOT NULL,
  `trdistance` double NOT NULL,
  `trdate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_tid` int(11) NOT NULL,
  `f_did` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trip`
--

INSERT INTO `trip` (`trid`, `trstartTime`, `trendTime`, `trdistance`, `trdate`, `f_tid`, `f_did`) VALUES
(1, '22:6:48', '22:12:48', 1.874, '2023-6-3', 3, 3),
(2, '22:8:47', '22:10:47', 0.789, '2023-6-3', 3, 3),
(3, '20:27:13', '20:29:13', 0.789, '2023-6-5', 3, 3),
(4, '22:10:22', '22:12:22', 0.789, '2023-6-6', 3, 3),
(5, '13:51:22', '13:53:22', 0.789, '2023-6-7', 3, 3),
(6, '14:45:25', '14:51:25', 1.874, '2023-6-12', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `truck`
--

CREATE TABLE `truck` (
  `tid` int(11) NOT NULL,
  `tnumber` int(11) NOT NULL,
  `tplate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tcapacity` double NOT NULL,
  `tstate` int(11) NOT NULL,
  `tisused` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `truck`
--

INSERT INTO `truck` (`tid`, `tnumber`, `tplate`, `tcapacity`, `tstate`, `tisused`) VALUES
(1, 10, '10-202020', 15, 1, 1),
(2, 20, '12-965425', 15, 1, 1),
(3, 30, '30-96545', 10, 1, 1),
(4, 40, '30-9632584', 10, 1, 0),
(5, 50, '50-963258', 5, 1, 0),
(6, 60, '20-96325158', 5, 5, 0),
(7, 70, '30-856694', 5, 0, 0),
(9, 50, '13658989', 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `ussn` char(16) NOT NULL,
  `uphone` varchar(100) NOT NULL,
  `ucity` varchar(100) NOT NULL,
  `uneiberhood` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `udateofbirth` varchar(150) NOT NULL,
  `upassword` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `uname`, `ussn`, `uphone`, `ucity`, `uneiberhood`, `udateofbirth`, `upassword`) VALUES
(1, 'abd', '1234567890', '0795410867', 'عمان', 'صويلح', '2000-09-13', '12345678'),
(2, 'fdd', '1234567812', '1234567890', 'عمان', 'صويلح', '2000-02-21', '123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `bin`
--
ALTER TABLE `bin`
  ADD PRIMARY KEY (`bid`),
  ADD KEY `f_nid` (`f_nid`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`ctid`),
  ADD UNIQUE KEY `f_aid` (`f_aid`);

--
-- Indexes for table `complaint`
--
ALTER TABLE `complaint`
  ADD PRIMARY KEY (`cId`),
  ADD KEY `f_ctid` (`f_ctid`),
  ADD KEY `complaint_ibfk_1` (`f_uid`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`did`);

--
-- Indexes for table `neighborhood`
--
ALTER TABLE `neighborhood`
  ADD PRIMARY KEY (`nbid`),
  ADD KEY `f_ctid` (`f_ctid`);

--
-- Indexes for table `node`
--
ALTER TABLE `node`
  ADD PRIMARY KEY (`nid`),
  ADD KEY `f_nbid` (`f_nbid`);

--
-- Indexes for table `node_trip`
--
ALTER TABLE `node_trip`
  ADD PRIMARY KEY (`f_nid`,`f_trid`),
  ADD KEY `f_trid` (`f_trid`);

--
-- Indexes for table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`trid`),
  ADD KEY `trip_ibfk_1` (`f_did`),
  ADD KEY `f_tid` (`f_tid`);

--
-- Indexes for table `truck`
--
ALTER TABLE `truck`
  ADD PRIMARY KEY (`tid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `bin`
--
ALTER TABLE `bin`
  MODIFY `bid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `ctid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `cId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `did` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `neighborhood`
--
ALTER TABLE `neighborhood`
  MODIFY `nbid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `node`
--
ALTER TABLE `node`
  MODIFY `nid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `trip`
--
ALTER TABLE `trip`
  MODIFY `trid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `truck`
--
ALTER TABLE `truck`
  MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bin`
--
ALTER TABLE `bin`
  ADD CONSTRAINT `bin_ibfk_1` FOREIGN KEY (`f_nid`) REFERENCES `node` (`nid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `city_ibfk_1` FOREIGN KEY (`f_aid`) REFERENCES `admin` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `complaint`
--
ALTER TABLE `complaint`
  ADD CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`f_uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `neighborhood`
--
ALTER TABLE `neighborhood`
  ADD CONSTRAINT `neighborhood_ibfk_1` FOREIGN KEY (`f_ctid`) REFERENCES `city` (`ctid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `node`
--
ALTER TABLE `node`
  ADD CONSTRAINT `node_ibfk_1` FOREIGN KEY (`f_nbid`) REFERENCES `neighborhood` (`nbid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `node_trip`
--
ALTER TABLE `node_trip`
  ADD CONSTRAINT `node_trip_ibfk_1` FOREIGN KEY (`f_nid`) REFERENCES `node` (`nid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `node_trip_ibfk_2` FOREIGN KEY (`f_trid`) REFERENCES `trip` (`trid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`f_did`) REFERENCES `driver` (`did`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `trip_ibfk_2` FOREIGN KEY (`f_tid`) REFERENCES `truck` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
