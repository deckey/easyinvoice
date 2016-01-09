-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2016 at 09:35 PM
-- Server version: 5.6.24
-- PHP Version: 5.5.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `easyinvoice.db`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `clientId` int(11) NOT NULL,
  `clientCompany` varchar(255) DEFAULT NULL,
  `clientTaxId` int(11) DEFAULT NULL,
  `clientContact` varchar(255) DEFAULT NULL,
  `clientEmail` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientWebsite` varchar(255) DEFAULT NULL,
  `clientCreationDate` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`, `clientCompany`, `clientTaxId`, `clientContact`, `clientEmail`, `clientPhone`, `clientWebsite`, `clientCreationDate`) VALUES
(19, 'Posh & Media', 323, 'Ivanafw', 'fspdfojpo', 'sdpojfsdpfjq', 'spodgjsdpogj', '2016-01-01'),
(21, 'Adawdwa', 454, 'dawdaw', 'dawdaw', 'dawdaw', 'dawdaw', '2015-12-16'),
(22, 'dawdaw', NULL, 'awdawd', 'awdawd', 'awdaw', 'awdawdaw', '2015-12-20'),
(24, 'Bozeman Foods Inc.', 313, 'Mark Williams', 'asddas', 'wefwefwe', 'fwsedfwef', '2016-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceId` int(11) NOT NULL,
  `invoiceAmount` double DEFAULT NULL,
  `invoiceCurrency` int(11) DEFAULT NULL,
  `invoiceDescription` varchar(255) DEFAULT NULL,
  `invoiceDueDate` date DEFAULT NULL,
  `invoiceIssueDate` date DEFAULT NULL,
  `invoiceNumber` varchar(255) DEFAULT NULL,
  `invoiceStatus` varchar(255) DEFAULT NULL,
  `invoiceCreationDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `invoiceClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `invoiceAmount`, `invoiceCurrency`, `invoiceDescription`, `invoiceDueDate`, `invoiceIssueDate`, `invoiceNumber`, `invoiceStatus`, `invoiceCreationDate`, `invoiceClientId`) VALUES
(60, 367, 1, 'sstt', '2016-01-12', '2016-01-01', '2016-10347', 'Closed', '2016-01-05 12:19:25', 19),
(62, 4766, 2, 'dwdawdaw', '2016-01-15', '2016-01-04', '2016-104', 'Open', '2016-01-04 21:34:13', 21),
(63, 356, 1, 'dwawda', '2016-01-30', '2016-01-01', '2016-1091', 'Open', '2016-01-04 21:19:02', 21),
(64, 323, 0, 'dawdaw', '2016-01-28', '2016-01-02', '2016-109', 'Closed', '2016-01-05 12:39:29', 19),
(66, 323, 1, 'fgwefwef', '2016-01-08', '2016-01-04', '2016-1042', 'Open', '2016-01-04 20:39:35', 22),
(67, 323, 1, 'dawdw', '2016-01-21', '2016-01-04', '2016-104332', 'Open', '2016-01-04 20:40:50', 22),
(68, 646, 1, 'dwadwad', '2016-01-01', '2016-01-01', '2016-101', 'Overdue', '2016-01-05 12:17:12', 19),
(69, 323, 2, 'dasdaww33dd', '2016-01-22', '2016-01-05', '2016-105', 'Closed', '2016-01-05 12:30:51', 21),
(71, 3233, 1, 'dadawd', '2016-01-02', '2016-01-01', '2016-101', 'Overdue', '2016-01-05 12:55:33', 22),
(73, 1200, 0, 'Paragon 3D animation project', '2016-01-17', '2016-01-10', '2016-110', 'Closed', '2016-01-08 11:01:15', 24),
(74, 434, 2, 'fwefwe', '2016-01-02', '2016-01-01', '2016-1014', 'Overdue', '2016-01-09 21:25:18', 22);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `memberId` int(11) NOT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL,
  `memberUsername` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberName`, `memberPassword`, `memberRole`, `memberUsername`) VALUES
(3, 'Administrator', 'admin', 'Administrator', 'admin'),
(4, 'Account Manager', 'acc', 'User', 'acc');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `registrationId` int(11) NOT NULL,
  `registrationAddress` varchar(255) DEFAULT NULL,
  `registrationCity` varchar(255) DEFAULT NULL,
  `registrationCountry` varchar(255) DEFAULT NULL,
  `registrationNotes` varchar(255) DEFAULT NULL,
  `registrationShipAddress` varchar(255) DEFAULT NULL,
  `registrationShipCity` varchar(255) DEFAULT NULL,
  `registrationShipCountry` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`registrationId`, `registrationAddress`, `registrationCity`, `registrationCountry`, `registrationNotes`, `registrationShipAddress`, `registrationShipCity`, `registrationShipCountry`) VALUES
(19, 'Visnjicka 32', 'Belgrade', 'Serbia', NULL, 'Visnjicka 32', 'Belgrade', 'Serbia'),
(21, 'dawdwa', 'dawdaw', 'dawda', NULL, 'dawdwadw', 'dawdaw', 'dawda'),
(22, 'dawdawd', 'awdaw', 'dawda', NULL, 'dawdawd', 'we', 'dawda'),
(23, 'asfapfoka', 'Austin, TX', 'USA', NULL, 'USA', 'Austin, TX', 'USA'),
(24, 'dpajdpaj', 'Austin, TX', 'USA', NULL, 'USA', 'Austin, TX', 'USA');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `serviceId` int(11) NOT NULL,
  `serviceNumber` int(11) DEFAULT NULL,
  `serviceAmount` double DEFAULT NULL,
  `serviceDescription` varchar(255) DEFAULT NULL,
  `invoiceServiceId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceNumber`, `serviceAmount`, `serviceDescription`, `invoiceServiceId`) VALUES
(86, 2, 44, 'dadw', 60),
(87, 1, 323, 'dawdw', 60),
(89, 2, 4443, 'dwawadwa', 62),
(90, 1, 323, 'dawwa', 62),
(91, 1, 323, 'daw', 63),
(92, 2, 33, 'ffwewf', 63),
(93, 1, 323, 'dwaw', 64),
(96, 1, 323, 'dadw', 66),
(97, 1, 323, 'dawdaw', 67),
(98, 2, 323, 'dwadw', 68),
(99, 1, 323, 'dawd', 68),
(100, 1, 323, 'daw', 69),
(102, 1, 3233, 'dwawd', 71),
(104, 1, 1200, '3D animation supervision', 73),
(105, 1, 434, 'fwefwe', 74);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientId`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceId`), ADD KEY `FK_roj299l1vk25hjmfnbtktcad5` (`invoiceClientId`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`memberId`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`registrationId`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serviceId`), ADD KEY `FK_2eyaovgqiq0pre63prev6yeje` (`invoiceServiceId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=106;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
ADD CONSTRAINT `FK_roj299l1vk25hjmfnbtktcad5` FOREIGN KEY (`invoiceClientId`) REFERENCES `client` (`clientId`);

--
-- Constraints for table `service`
--
ALTER TABLE `service`
ADD CONSTRAINT `FK_2eyaovgqiq0pre63prev6yeje` FOREIGN KEY (`invoiceServiceId`) REFERENCES `invoice` (`invoiceId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
