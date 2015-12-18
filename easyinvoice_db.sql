-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 18, 2015 at 02:07 AM
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
  `clientCompany` varchar(255) DEFAULT NULL,
  `clientContact` varchar(32) NOT NULL,
  `clientAmount` double DEFAULT NULL,
  `clientEmail` varchar(255) DEFAULT NULL,
  `clientIndustry` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientWebsite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`, `clientCompany`, `clientContact`, `clientAmount`, `clientEmail`, `clientIndustry`, `clientPhone`, `clientWebsite`) VALUES
(6, 'clientCompany1', 'clientContact1', 0, 'clientEmail', 'clientIndustry1', 'clientPhone', 'clientWebsite'),
(7, 'clientCompany2', 'clientContact2', 0, 'clientEmail2', 'clientIndustry2', 'clientPhone2', 'clientWebsite2');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceId` int(11) NOT NULL,
  `invoiceNumber` varchar(16) NOT NULL,
  `invoiceAmount` double DEFAULT NULL,
  `invoiceCurrency` varchar(255) DEFAULT NULL,
  `invoiceDescription` varchar(255) DEFAULT NULL,
  `invoiceDueDate` date DEFAULT NULL,
  `invoiceIssueDate` date DEFAULT NULL,
  `invoiceStatus` varchar(255) DEFAULT NULL,
  `invoiceClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `invoiceNumber`, `invoiceAmount`, `invoiceCurrency`, `invoiceDescription`, `invoiceDueDate`, `invoiceIssueDate`, `invoiceStatus`, `invoiceClientId`) VALUES
(3, 'null', 200, 'EUR', 'description2', '2015-12-18', '2015-12-18', 'Overdue', 7),
(4, 'null', 400, 'USD', 'description1', '2015-12-18', '2015-12-18', 'Open', 7);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `memberId` int(11) NOT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberUsername` varchar(255) DEFAULT NULL,
  `memberPassword` varchar(255) DEFAULT NULL,
  `memberRole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberName`, `memberUsername`, `memberPassword`, `memberRole`) VALUES
(1, 'Administrator', 'admin', 'admin', 'Administrator'),
(8, 'user', 'user', 'user', 'User'),
(14, 'Supervisor', 'sup', 'sup', 'User');

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
(6, 'client1Address', 'client1City', 'client1 country', 'some notes', 'client1shipping', 'client1ShippingCity', 'client1shipCountry');

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
  ADD KEY `invoiceClient_FK` (`invoiceClientId`);

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoiceClient_FK` FOREIGN KEY (`invoiceClientId`) REFERENCES `client` (`clientId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
