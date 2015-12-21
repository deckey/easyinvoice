-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 21, 2015 at 04:58 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `easyinvoice.db`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `clientId` int(11) NOT NULL,
  `clientAmount` double DEFAULT NULL,
  `clientCompany` varchar(255) DEFAULT NULL,
  `clientContact` varchar(255) DEFAULT NULL,
  `clientEmail` varchar(255) DEFAULT NULL,
  `clientIndustry` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientWebsite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`, `clientAmount`, `clientCompany`, `clientContact`, `clientEmail`, `clientIndustry`, `clientPhone`, `clientWebsite`) VALUES
(10, 0, 'clientCompany1', 'clientContact1', 'clientEmail', 'clientIndustry1', 'clientPhone', 'clientWebsite'),
(11, 0, 'Client2', 'Client2Contact', 'client2@email.com', 'Industry2', 'Client2Phone', 'client2Website');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceId` int(11) NOT NULL,
  `invoiceAmount` double DEFAULT NULL,
  `invoiceCurrency` varchar(255) DEFAULT NULL,
  `invoiceDescription` varchar(255) DEFAULT NULL,
  `invoiceDueDate` date DEFAULT NULL,
  `invoiceIssueDate` date DEFAULT NULL,
  `invoiceNumber` varchar(255) DEFAULT NULL,
  `invoiceStatus` varchar(255) DEFAULT NULL,
  `invoiceClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `invoiceAmount`, `invoiceCurrency`, `invoiceDescription`, `invoiceDueDate`, `invoiceIssueDate`, `invoiceNumber`, `invoiceStatus`, `invoiceClientId`) VALUES
(13, 400, 'USD', 'description1', '2015-12-21', '2015-12-21', 'null', 'Open', 10),
(14, 0, 'USD', NULL, '2015-12-24', '2015-12-21', '2015-1221', NULL, 10),
(15, 0, 'USD', NULL, '2015-12-19', '2015-12-21', '2015-1221', NULL, 10),
(16, 0, 'GBP', NULL, '2015-12-07', '2015-12-01', '2015-1221', NULL, 11);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberName`, `memberPassword`, `memberRole`, `memberUsername`) VALUES
(1, 'Administrator', 'admin', 'Administrator', 'admin');

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
(10, 'client1Address', 'client1City', 'client1 country', 'some notes', 'client1shipping', 'client1ShippingCity', 'client1shipCountry');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `serviceId` int(11) NOT NULL,
  `serviceAmount` double DEFAULT NULL,
  `serviceDescription` varchar(255) DEFAULT NULL,
  `invoiceServiceId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceAmount`, `serviceDescription`, `invoiceServiceId`) VALUES
(5, 400, 'Service 1 description', 13),
(6, 1000, 'Service 1-2 description', 13),
(7, 30, 'dawdawd', 14),
(8, 30, 'dwadwadawd', 15),
(9, 30, 'fbdfgbedbf', 16);

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
  ADD PRIMARY KEY (`invoiceId`),
  ADD KEY `FK_roj299l1vk25hjmfnbtktcad5` (`invoiceClientId`);

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
  ADD PRIMARY KEY (`serviceId`),
  ADD KEY `FK_2eyaovgqiq0pre63prev6yeje` (`invoiceServiceId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
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
