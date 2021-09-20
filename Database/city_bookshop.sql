-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 20, 2021 at 12:14 PM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `city_bookshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `BID` int NOT NULL,
  `BName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BUnitPrice` double NOT NULL,
  `BStock` int NOT NULL,
  `CID` int NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `BName` (`BName`),
  KEY `CID` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`BID`, `BName`, `BUnitPrice`, `BStock`, `CID`) VALUES
(1, 'The Silent Patient', 3.83, 15, 1),
(2, 'The Complete Novels of Sherlock Holmes', 3.1, 25, 1),
(3, 'Harry Potter and the Cursed Child', 4.54, 25, 1),
(4, 'The Oath of the Vayuputras', 5.23, 25, 1),
(5, 'Shantaram', 5.51, 10, 1),
(6, 'The Tintin Collection: The Adventure of Tintin - Compact edition - (8 books)', 64.68, 20, 2),
(7, 'The Artist Way: A Spiritual Path to Higher Creat', 7.41, 25, 2),
(8, 'Greatest Works of Jane Austen: Set of 5 Books', 6.32, 25, 2),
(9, 'THE STRANGER IN THE MIRROR', 4.99, 25, 2),
(10, 'The Design of Everyday Things: Revised and Expande', 9.46, 25, 2),
(11, 'Cracking the Coding Interview', 7.76, 18, 3),
(12, 'Core Java: An Integrated Approach, New: Includes All Versions upto Java 8', 7.26, 25, 3),
(13, 'The Key Man: How the Global Elite Was Duped by a Capitalist Fairy Tale', 8.32, 7, 3),
(14, 'An Ugly Truth: Inside Facebook Battle for Domination', 8.9, 25, 3),
(15, 'Hacking: The Art of Exploitation, 2nd Edition', 24.52, 25, 3),
(16, 'Three Rays: Stories from Satyajit Ray', 7.33, 25, 4),
(17, 'The Complete Hitchhiker Guide to the Galaxy Boxset', 13.49, 13, 4),
(18, 'The Shadow of the Wind The Cemetery of Forgotten Books 1\r\n', 4.72, 25, 4),
(19, 'Rick Riordan - Percy Jackson', 7.88, 9, 4),
(20, 'Rising Like a Storm', 4.41, 11, 4),
(21, 'Mythos: The Greek Myths Retold (Stephen Fry Greek Myths)', 5.38, 25, 5),
(22, 'Knowledge Encyclopedia History!: The Past as You have Never Seen it', 10.28, 25, 5),
(23, 'Cleopatra', 7.41, 17, 5),
(24, 'Pyramids temples and tombs of ancient Egypt. An illustrated atlas of the land', 5.73, 15, 5),
(25, 'Man Search For Meaning', 7.78, 13, 5);

-- --------------------------------------------------------

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
CREATE TABLE IF NOT EXISTS `book_category` (
  `CID` int NOT NULL AUTO_INCREMENT,
  `CName` varchar(50) NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `book_category`
--

INSERT INTO `book_category` (`CID`, `CName`) VALUES
(1, 'Action & Adventure'),
(2, 'Arts, Film & Photography'),
(3, 'Computer & Internet'),
(4, 'Science  fiction & Fantasy'),
(5, 'History'),
(6, 'sharu');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `EID` int NOT NULL,
  `EName` varchar(50) NOT NULL,
  `EEmail` varchar(100) NOT NULL,
  `EPhoto` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `LID` int NOT NULL,
  PRIMARY KEY (`EID`),
  KEY `LID` (`LID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EID`, `EName`, `EEmail`, `EPhoto`, `LID`) VALUES
(111, 'Rizni', 'mnriznimohamed@gmail.com', 'rizni.jpg', 1),
(222, 'Raza', 'mnrazamohamed@gmail.com', NULL, 2),
(333, 'Sharujan', 'sarujanss09@gmail.com', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE IF NOT EXISTS `invoice` (
  `IID` int NOT NULL AUTO_INCREMENT,
  `IQuantity` double NOT NULL,
  `IDate` date NOT NULL,
  `Total` double NOT NULL,
  `BID` int NOT NULL,
  `EID` int NOT NULL,
  PRIMARY KEY (`IID`),
  KEY `BID` (`BID`),
  KEY `EID` (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=1235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`IID`, `IQuantity`, `IDate`, `Total`, `BID`, `EID`) VALUES
(1, 10, '2020-07-14', 38.3, 1, 111),
(2, 12, '2020-07-15', 161.88, 17, 222),
(3, 15, '2020-08-16', 82.64999999999999, 5, 333),
(4, 5, '2020-08-16', 323.40000000000003, 6, 222),
(5, 7, '2020-08-16', 54.32, 11, 111),
(6, 8, '2021-06-14', 59.28, 23, 333),
(7, 10, '2021-07-15', 57.300000000000004, 24, 222),
(8, 12, '2021-07-15', 93.36, 25, 333),
(9, 14, '2021-08-15', 61.74, 20, 222),
(10, 16, '2021-08-16', 126.08, 19, 222),
(11, 18, '2021-08-16', 149.76, 13, 111);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `LID` int NOT NULL AUTO_INCREMENT,
  `LUsername` varchar(50) NOT NULL,
  `LPassword` varchar(50) NOT NULL,
  `LRemember` tinyint(1) NOT NULL,
  `RID` int NOT NULL,
  PRIMARY KEY (`LID`),
  KEY `RID` (`RID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`LID`, `LUsername`, `LPassword`, `LRemember`, `RID`) VALUES
(1, 'rizni', '123', 0, 1),
(2, 'raza', 'zxc', 1, 1),
(3, 'sharu', 'sharu', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `RID` int NOT NULL AUTO_INCREMENT,
  `RName` varchar(50) NOT NULL,
  PRIMARY KEY (`RID`),
  UNIQUE KEY `RName` (`RName`),
  KEY `RName_2` (`RName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`RID`, `RName`) VALUES
(1, 'Admin'),
(2, 'Cashier');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`CID`) REFERENCES `book_category` (`CID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`LID`) REFERENCES `login` (`LID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`BID`) REFERENCES `book` (`BID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`EID`) REFERENCES `employee` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`RID`) REFERENCES `role` (`RID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
