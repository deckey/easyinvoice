-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 12, 2016 at 11:52 PM
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
  `clientTaxId` int(11) DEFAULT NULL,
  `clientContact` varchar(255) DEFAULT NULL,
  `clientEmail` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientWebsite` varchar(255) DEFAULT NULL,
  `clientCreationDate` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`, `clientCompany`, `clientTaxId`, `clientContact`, `clientEmail`, `clientPhone`, `clientWebsite`, `clientCreationDate`) VALUES
(19, 'Posh & Media', 3233, 'Ivana Ilic', 'ivanail@posh.rs', '3044332', 'http://www.posh.rs', '2016-01-01'),
(24, 'Bozeman Foods Inc.', 313, 'Mark Williams', 'mark@bozeman.us', '40443210', NULL, '2016-01-05'),
(25, 'Fido', 322, 'Claes Dietman', 'claesd@fido.se', '3233222', 'http://www.fido.se', '2016-01-12'),
(26, 'Digimedia', 3321, 'Rastko Petrovic', 'rastkop@digi.rs', '2121214', 'http://digimedia.rs', '2016-01-12'),
(27, 'Total Adv doo', 3232, 'Petar Staletic', 'stalpe@total.rs', '322234', 'http://www.total-adv.com', '2016-01-12'),
(28, 'Adabisc', 656, 'Hamzeh Zahr', 'hamz@adabisc.tv', '4343442', NULL, '2016-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `companyId` int(11) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `companyAddress` varchar(255) DEFAULT NULL,
  `companyCity` varchar(255) DEFAULT NULL,
  `companyZip` varchar(255) DEFAULT NULL,
  `companyCountry` varchar(255) DEFAULT NULL,
  `companyPhone` varchar(255) DEFAULT NULL,
  `companyBankAddress` varchar(255) DEFAULT NULL,
  `companyBankName` varchar(255) DEFAULT NULL,
  `companyBusinessCode` varchar(255) DEFAULT NULL,
  `companyCorrespondent` varchar(255) DEFAULT NULL,
  `companyCRNumber` varchar(255) DEFAULT NULL,
  `companyIban` varchar(255) DEFAULT NULL,
  `companySwiftCode` varchar(255) DEFAULT NULL,
  `companyTaxId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`companyId`, `companyName`, `companyAddress`, `companyCity`, `companyZip`, `companyCountry`, `companyPhone`, `companyBankAddress`, `companyBankName`, `companyBusinessCode`, `companyCorrespondent`, `companyCRNumber`, `companyIban`, `companySwiftCode`, `companyTaxId`) VALUES
(1, 'Deform', 'Sarajevska 2', 'Belgrade', '11000', 'Serbia', '381 11 2458 408', 'Djordja Stanojevica 16, Belgrade, RS', 'Raiffeisen Banka AD', '6209', 'Standard Chartered Bank New York, USA\r\nSWIFT: SCBLUS33\r\nABA: 0260-0256-1\r\n', '61362983', 'RS35265100000010602248', 'RZBSRSBG', '106443233');

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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `invoiceAmount`, `invoiceCurrency`, `invoiceDescription`, `invoiceDueDate`, `invoiceIssueDate`, `invoiceNumber`, `invoiceStatus`, `invoiceCreationDate`, `invoiceClientId`) VALUES
(73, 1200, 0, 'Paragon 3D animation project', '2016-01-17', '2016-01-10', '2016-110', 'Closed', '2016-01-08 11:01:15', 24),
(78, 1200, 1, 'TV Commercial', '2015-12-09', '2015-12-02', '2015-1202', 'Overdue', '2016-01-12 23:41:26', 25),
(79, 1600, 1, 'Game intro', '2016-01-06', '2016-01-02', '2016-102', 'Overdue', '2016-01-12 23:43:31', 28),
(80, 2000, 1, 'Lucky parrot TVC', '2016-01-21', '2016-01-07', '2016-107', 'Open', '2016-01-12 23:45:33', 28),
(81, 1400, 2, 'Maya training', '2016-01-25', '2016-01-03', '2016-103', 'Open', '2016-01-12 23:47:11', 27),
(82, 1600, 1, 'Vodafone commercial', '2016-01-02', '2015-12-07', '2015-1207', 'Overdue', '2016-01-12 23:48:36', 28);

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
(23, 'asfapfoka', 'Austin, TX', 'USA', NULL, 'USA', 'Austin, TX', 'USA'),
(24, '815-A Brazo''s St. # 281', 'Austin TX. 78701 ', 'USA', NULL, 'Brazo''s St. # 244', 'Houston TX', 'USA'),
(25, ' Rosenlundsgatan 36', 'Stockholm', 'Sweden', NULL, 'Sweden', 'Stockholm', 'Sweden'),
(26, 'Bulevar Vojvode Miši?a 12 ', 'Belgrade', 'Serbia', NULL, 'Serbia', 'Belgrade', 'Serbia'),
(27, 'Takovska 2', 'Kragujevac', 'Serbia', NULL, 'Serbia', 'Kragujevac', 'Serbia'),
(28, 'The Gate MallBay 2 Tower 4th Floor', 'Doha', 'Qatar', NULL, 'Qatar', 'Doha', 'Qatar');

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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceNumber`, `serviceAmount`, `serviceDescription`, `invoiceServiceId`) VALUES
(104, 1, 1200, '3D animation supervision', 73),
(110, 2, 800, 'Animation', 78),
(111, 1, 400, 'Modeling', 78),
(112, 2, 400, 'Rigging', 79),
(113, 1, 600, 'Character concept', 79),
(114, 3, 600, 'Scripting', 79),
(115, 1, 2000, 'On-site supervision', 80),
(116, 1, 1400, 'Tutorial videos', 81),
(117, 1, 1600, '3D modeling and animation', 82);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientId`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`companyId`);

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
  MODIFY `clientId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `companyId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=83;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memberId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=118;
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
