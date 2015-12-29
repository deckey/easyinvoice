-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 29, 2015 at 12:46 PM
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
  `clientTaxId` int(11) DEFAULT NULL,
  `clientContact` varchar(255) DEFAULT NULL,
  `clientEmail` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientWebsite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`, `clientAmount`, `clientCompany`, `clientTaxId`, `clientContact`, `clientEmail`, `clientPhone`, `clientWebsite`) VALUES
(14, 0, 'Paragon Inc.', NULL, 'Mark', 'apofjdfposjdg', 'psodgkspdgj', 'spdogjspdg'),
(15, 0, 'dwdaw', NULL, 'dawdad', 'wadawdaw', 'dawdaw', 'adawd'),
(16, 0, 'eweqw', NULL, 'qwdqwd', 'qwdqwdq', 'wdqwdqw', 'dqwdqwd');

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
  `invoiceClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `invoiceAmount`, `invoiceCurrency`, `invoiceDescription`, `invoiceDueDate`, `invoiceIssueDate`, `invoiceNumber`, `invoiceStatus`, `invoiceClientId`) VALUES
(55, 1200, 0, 'Invoice 2', '2015-12-31', '2015-12-24', '2015-1224', NULL, 14);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memberId`, `memberName`, `memberPassword`, `memberRole`, `memberUsername`) VALUES
(1, 'Administrator', 'admin', 'Administrator', 'admin'),
(2, 'Account manager', 'manager', 'User', 'manager');

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
(14, 'asdapsfokas', 'pdoksdpgo', 'USA', NULL, NULL, NULL, NULL),
(15, 'dawdaw', 'dawdawd', 'dawdawd', NULL, NULL, NULL, NULL),
(16, 'eqweq', 'dqwdqw', 'dqwdqwd', NULL, 'dqwdqwd', 'dqwdqw', 'dqwdqwd');

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceNumber`, `serviceAmount`, `serviceDescription`, `invoiceServiceId`) VALUES
(24, 1, 1200, 'Supervision', 55);

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
  MODIFY `clientId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
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
