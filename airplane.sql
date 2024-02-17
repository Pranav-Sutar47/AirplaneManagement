-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 17, 2024 at 10:29 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airplane`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Name` varchar(25) NOT NULL,
  `Designation` varchar(25) NOT NULL,
  `Department` varchar(25) NOT NULL,
  `Salary` float NOT NULL,
  `Address` varchar(30) NOT NULL,
  `Age` int(11) NOT NULL,
  `MobileNo` varchar(10) NOT NULL,
  `Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Name`, `Designation`, `Department`, `Salary`, `Address`, `Age`, `MobileNo`, `Id`) VALUES
('Viraj', 'Piolet', 'Airplane', 100000, 'Solapur', 29, '9730628692', 3),
('Ratnadeep', 'Controller', 'Airport', 100000, 'Kolhapur', 20, '9730628695', 4),
('Roshan', 'Controller', 'Airplane', 50000, 'MP', 25, '9730628696', 5);

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `flightId` int(11) NOT NULL,
  `flightName` varchar(30) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `StartingTime` datetime NOT NULL,
  `ReachingTime` datetime NOT NULL,
  `Source` varchar(30) NOT NULL,
  `Destination` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  `Booking` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`flightId`, `flightName`, `Capacity`, `StartingTime`, `ReachingTime`, `Source`, `Destination`, `Price`, `Booking`) VALUES
(1, 'Express', 40, '2024-01-16 16:05:55', '2024-01-17 16:05:55', 'Kolhapur', 'Pune', 500, '40,39,38,5,6,7,8,9,10,11,12,13,14,'),
(2, 'mah', 40, '2024-02-06 12:10:10', '2024-02-07 12:10:12', 'Kolhapur', 'Delhi', 4500, '');

-- --------------------------------------------------------

--
-- Table structure for table `luggage`
--

CREATE TABLE `luggage` (
  `LuggageId` int(11) NOT NULL,
  `flightId` int(11) NOT NULL,
  `Id` int(11) NOT NULL,
  `Luggage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `luggage`
--

INSERT INTO `luggage` (`LuggageId`, `flightId`, `Id`, `Luggage`) VALUES
(1, 1, 12, 2),
(2, 1, 13, 40),
(3, 1, 14, 40),
(4, 1, 16, 20);

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE `passenger` (
  `Id` int(11) NOT NULL,
  `flightId` int(11) NOT NULL,
  `PassengerName` varchar(30) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Source` varchar(30) NOT NULL,
  `Destination` varchar(30) NOT NULL,
  `StartTime` datetime NOT NULL,
  `ReachTime` datetime NOT NULL,
  `TotalP` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `MobileNo` varchar(10) NOT NULL,
  `AdharNo` varchar(12) NOT NULL,
  `SeatNos` varchar(100) NOT NULL,
  `LuggageId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `passenger`
--

INSERT INTO `passenger` (`Id`, `flightId`, `PassengerName`, `Age`, `Gender`, `Source`, `Destination`, `StartTime`, `ReachTime`, `TotalP`, `Price`, `MobileNo`, `AdharNo`, `SeatNos`, `LuggageId`) VALUES
(16, 1, 'Pranav', 24, 'Male', 'Kolhapur', 'Pune', '2024-01-16 16:05:55', '2024-01-17 16:05:55', 10, 5000, '9730628692', '789456123654', '5,6,7,8,9,10,11,12,13,14,', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `MobileNo` (`MobileNo`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flightId`);

--
-- Indexes for table `luggage`
--
ALTER TABLE `luggage`
  ADD PRIMARY KEY (`LuggageId`);

--
-- Indexes for table `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `AdharNo` (`AdharNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `luggage`
--
ALTER TABLE `luggage`
  MODIFY `LuggageId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `passenger`
--
ALTER TABLE `passenger`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
