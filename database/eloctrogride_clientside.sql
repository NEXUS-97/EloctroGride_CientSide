-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 14, 2022 at 05:48 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `eloctrogride_clientside`
--
CREATE DATABASE IF NOT EXISTS `eloctrogride_clientside` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `eloctrogride_clientside`;

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE IF NOT EXISTS `billing` (
  `eID` int(11) NOT NULL AUTO_INCREMENT,
  `eName` varchar(255) NOT NULL,
  `eAddress` varchar(255) DEFAULT NULL,
  `eEmail` varchar(255) DEFAULT NULL,
  `eDate` varchar(255) DEFAULT NULL,
  `pno` varchar(11) NOT NULL,
  PRIMARY KEY (`eID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`eID`, `eName`, `eAddress`, `eEmail`, `eDate`, `pno`) VALUES
(30, '111', '2022-05-21', '2022-06-05', '123', 'Pending'),
(34, '1234', '2022-05-01', '2022-05-31', '12345', 'Done'),
(36, '12', '2022-05-01', '2022-05-29', '12344', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

CREATE TABLE IF NOT EXISTS `logins` (
  `log_uname` varchar(50) NOT NULL,
  `log_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`log_uname`, `log_password`) VALUES
('thisara', '123');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
  `PaymentID` int(11) NOT NULL AUTO_INCREMENT,
  `PaymentDate` varchar(255) NOT NULL,
  `PaymentType` varchar(255) DEFAULT NULL,
  `Amount` varchar(255) DEFAULT NULL,
  `CardNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`PaymentID`, `PaymentDate`, `PaymentType`, `Amount`, `CardNumber`) VALUES
(29, '12.08.2018', 'Debitcard', 'LKR2000', 12334313),
(32, '12.08.2014', 'Creditcard', 'LKR3400', 12232321),
(34, '12.09.2022', 'asdfg', '1000', 12345678);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
