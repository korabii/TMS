-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 10, 2019 at 06:12 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tms`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(10) NOT NULL,
  `title` varchar(4) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `date_of_birth` date NOT NULL,
  `start_of_employment` date NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `mobile_number` varchar(11) NOT NULL,
  `training_level` varchar(2) NOT NULL,
  `last_qa` date NOT NULL,
  `store_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `title`, `firstname`, `lastname`, `date_of_birth`, `start_of_employment`, `email_address`, `mobile_number`, `training_level`, `last_qa`, `store_id`) VALUES
('pn00000002', 'mr', 'Mike', 'Johnson', '1978-12-22', '2018-10-10', 'mj@mailing.com', '07467876543', 'sa', '2019-08-04', 'st00000001'),
('pn00000003', 'mr', 'Dave', 'Tanner', '1967-06-15', '2019-07-09', 'davetanner@mymail.com', '07675463631', 'na', '2019-08-04', 'st00000001'),
('pn00000004', 'mr', 'Simon', 'Lister', '1929-02-22', '2019-07-10', 'simone@aol.com', '07467986754', 'na', '2018-12-02', 'st00000001'),
('pn00000005', 'mrs', 'Mandy', 'Muse', '1988-05-27', '2019-10-11', 'mmuse@yafoo.co.uk', '07473058765', 'sa', '2019-08-07', 'st00000001'),
('pn00000008', 'mx', 'Frank', 'Heinz', '1988-02-13', '2018-04-09', 'frank@mail.com', '07574967865', 'ss', '2019-07-05', 'st00000001'),
('pn00000011', 'mr', 'Norman', 'Averyman', '2001-03-11', '2018-04-10', 'Averyman@averywhere.com', '07567634562', 'ss', '2019-07-31', 'st00000001'),
('pn00000013', 'mr', 'George', 'Willis', '1965-06-22', '2019-07-11', 'gwillis@mail.com', '07474967555', 'na', '2019-02-07', 'st00000001'),
('pn00000014', 'miss', 'Dana', 'Donnelly', '1986-06-22', '2019-04-09', 'dana@mailing.com', '07678967555', 'js', '2019-08-06', 'st00000001'),
('pn00000015', 'mr', 'Eric', 'ericsson', '1986-06-22', '2019-04-10', 'eericsson@gmail.com', '07678967555', 'js', '2019-08-15', 'st00000001'),
('pn00000017', 'mr', 'Stan', 'Still', '1986-06-22', '2019-04-11', 'stanstill@gmail.com', '07678967555', 'js', '2019-04-02', 'st00000001'),
('pn00000018', 'mr', 'Justin', 'Case', '1978-11-13', '2018-04-11', 'justincase@mail.com', '07473046953', 'ss', '2019-02-11', 'st00000001'),
('pn00000019', 'ms', 'Erika', 'Stein', '1990-08-01', '2018-10-09', 'ericasdsww@eee.com', '07676545454', 'sa', '2019-08-07', 'st00000001'),
('pn00000029', 'mr', 'Ken', 'Smith', '1977-06-15', '2018-04-09', 'ksmith@mail.com', '07656463951', 'qa', '2019-08-04', 'st00000001'),
('pn00000031', 'ms', 'Sue', 'O\'Conner', '1986-12-08', '2018-04-10', 'sue@mailing.com', '07875674685', 'qa', '2019-08-02', 'st00000001'),
('pn00000033', 'mr', 'Edd', 'Baker', '1988-12-15', '2018-04-11', 'eddb@mailing.com', '07867569856', 'qa', '2019-08-04', 'st00000001');

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `store_id` varchar(10) NOT NULL,
  `number` int(11) NOT NULL,
  `streetname` varchar(80) NOT NULL,
  `city` varchar(60) NOT NULL,
  `postcode` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`store_id`, `number`, `streetname`, `city`, `postcode`) VALUES
('st00000001', 25, 'Fulham road', 'London', 'SW15 4RT'),
('st00000002', 27, 'hammersmith road', 'london', 'sw18 7ty');

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

CREATE TABLE `training` (
  `employee_id` varchar(10) NOT NULL,
  `training_event_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`employee_id`, `training_event_id`) VALUES
('pn00000003', 2);

-- --------------------------------------------------------

--
-- Table structure for table `trainingdepartment`
--

CREATE TABLE `trainingdepartment` (
  `td_id` varchar(10) NOT NULL,
  `number` int(11) NOT NULL,
  `streetname` varchar(80) NOT NULL,
  `city` varchar(60) NOT NULL,
  `postcode` varchar(9) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trainingdepartment`
--

INSERT INTO `trainingdepartment` (`td_id`, `number`, `streetname`, `city`, `postcode`, `capacity`) VALUES
('td00000001', 12, 'baker street', 'london', 'sw15 6jq', 20),
('td00000002', 36, 'oxford street', 'london', 'sw20 5rt', 10);

-- --------------------------------------------------------

--
-- Table structure for table `trainingevent`
--

CREATE TABLE `trainingevent` (
  `te_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  `type` varchar(2) NOT NULL,
  `td_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trainingevent`
--

INSERT INTO `trainingevent` (`te_id`, `date_time`, `type`, `td_id`) VALUES
(1, '2019-07-12 09:00:00', 'sa', 'td00000001'),
(2, '2019-07-20 09:00:00', 'js', 'td00000001'),
(3, '2019-08-15 09:00:00', 'sa', 'td00000002');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `employee_id` (`employee_id`),
  ADD KEY `store link` (`store_id`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`store_id`),
  ADD UNIQUE KEY `store_id` (`store_id`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`employee_id`,`training_event_id`),
  ADD KEY `employee link` (`employee_id`),
  ADD KEY `training event link` (`training_event_id`);

--
-- Indexes for table `trainingdepartment`
--
ALTER TABLE `trainingdepartment`
  ADD PRIMARY KEY (`td_id`),
  ADD UNIQUE KEY `td_id` (`td_id`);

--
-- Indexes for table `trainingevent`
--
ALTER TABLE `trainingevent`
  ADD PRIMARY KEY (`te_id`),
  ADD KEY `td_id` (`td_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `trainingevent`
--
ALTER TABLE `trainingevent`
  MODIFY `te_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `training`
--
ALTER TABLE `training`
  ADD CONSTRAINT `employee link` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `training event link` FOREIGN KEY (`training_event_id`) REFERENCES `trainingevent` (`te_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trainingdepartment`
--
ALTER TABLE `trainingdepartment`
  ADD CONSTRAINT `training department event link` FOREIGN KEY (`td_id`) REFERENCES `trainingevent` (`td_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trainingevent`
--
ALTER TABLE `trainingevent`
  ADD CONSTRAINT `trainingevent_ibfk_1` FOREIGN KEY (`td_id`) REFERENCES `trainingdepartment` (`td_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
