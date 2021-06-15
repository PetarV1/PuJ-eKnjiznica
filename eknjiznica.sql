-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2021 at 01:03 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eknjiznica`
--

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE `autor` (
  `ID` int(10) UNSIGNED NOT NULL,
  `ime_autora` varchar(40) NOT NULL,
  `prezime_autora` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `autor`
--

INSERT INTO `autor` (`ID`, `ime_autora`, `prezime_autora`) VALUES
(0, 'Autor', 'Autor'),
(50, 'James Matthew', 'Barrie'),
(51, 'Filip', 'Marić'),
(52, 'Vedran', 'Krčadinac'),
(53, 'Ivo', 'Alfirević'),
(54, 'Robert', 'Beuc'),
(141, 'Cesar', 'Millan');

-- --------------------------------------------------------

--
-- Table structure for table `knjiga`
--

CREATE TABLE `knjiga` (
  `ID` int(10) UNSIGNED NOT NULL,
  `naziv_knjige` varchar(50) NOT NULL,
  `godina_izdanja` int(10) UNSIGNED DEFAULT NULL,
  `Kategorija` varchar(50) NOT NULL,
  `broj_stranica` varchar(10) DEFAULT NULL,
  `kolegijID` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `autorID` int(10) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `knjiga`
--

INSERT INTO `knjiga` (`ID`, `naziv_knjige`, `godina_izdanja`, `Kategorija`, `broj_stranica`, `kolegijID`, `autorID`) VALUES
(100, 'Osnove algoritama', NULL, 'Stručna literatura', NULL, 103, 52),
(101, 'Nauka o čvrstoći', 1989, 'Stručna literatura', '162', 104, 53),
(102, 'Petar Pan', 1911, 'Dječji roman', '24', 0, 50),
(103, 'Programiranje1', 2015, 'Stručna literatura', '378', 102, 51),
(104, 'Uvod u elektroniku', NULL, 'Stručna literatura', '96', 105, 54),
(3177, 'Šaptač psima', 0, 'Ostalo', '112', 0, 141);

-- --------------------------------------------------------

--
-- Table structure for table `kolegij`
--

CREATE TABLE `kolegij` (
  `ID` int(10) UNSIGNED NOT NULL,
  `naziv_kolegija` varchar(50) NOT NULL,
  `studijID` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kolegij`
--

INSERT INTO `kolegij` (`ID`, `naziv_kolegija`, `studijID`) VALUES
(0, 'NullKolegij', 1),
(102, 'Programiranje 1', 1),
(103, 'Algoritmi', 1),
(104, 'Nauka o čvrstoći', 3),
(105, 'Elektronika', 2);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID` int(10) UNSIGNED NOT NULL,
  `korisnicko_ime` varchar(30) NOT NULL,
  `lozinka` varchar(256) NOT NULL,
  `vrsta_korisnika` enum('Admin','Student') NOT NULL,
  `studentID` int(10) UNSIGNED DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID`, `korisnicko_ime`, `lozinka`, `vrsta_korisnika`, `studentID`) VALUES
(50, 'p', '1', 'Admin', 0),
(51, 'pero', '123', 'Student', 70),
(76, 'filip', '123', 'Admin', 0),
(77, 'mmaric', 'abcd', 'Student', 72);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ID` int(10) UNSIGNED NOT NULL,
  `ime` varchar(40) NOT NULL,
  `prezime` varchar(40) NOT NULL,
  `broj_indeksa` varchar(20) NOT NULL,
  `prosjek_ocjena` float UNSIGNED NOT NULL,
  `studijID` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `ime`, `prezime`, `broj_indeksa`, `prosjek_ocjena`, `studijID`) VALUES
(0, 'Admin', 'Admin', '0000', 0, 1),
(70, 'Petar', 'Vasilj', '1810rr', 3.6, 1),
(72, 'Marko', 'Maric', '3000rr', 4.7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `studij`
--

CREATE TABLE `studij` (
  `ID` int(10) UNSIGNED NOT NULL,
  `naziv_studija` enum('racunarstvo','strojarstvo','elektrotehnika','neodredjeno') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studij`
--

INSERT INTO `studij` (`ID`, `naziv_studija`) VALUES
(1, 'racunarstvo'),
(2, 'elektrotehnika'),
(3, 'strojarstvo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `KnjigaAutorFK` (`autorID`) USING BTREE,
  ADD KEY `KnjigaKolegijFK` (`kolegijID`);

--
-- Indexes for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `KolegijStudijFK` (`studijID`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `KorisnikStudentFK` (`studentID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `StudentStudijFK` (`studijID`);

--
-- Indexes for table `studij`
--
ALTER TABLE `studij`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3178;

--
-- AUTO_INCREMENT for table `kolegij`
--
ALTER TABLE `kolegij`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `studij`
--
ALTER TABLE `studij`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD CONSTRAINT `KnjigaAutorFK` FOREIGN KEY (`autorID`) REFERENCES `autor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `KnjigaKolegijFK` FOREIGN KEY (`kolegijID`) REFERENCES `kolegij` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kolegij`
--
ALTER TABLE `kolegij`
  ADD CONSTRAINT `KolegijStudijFK` FOREIGN KEY (`studijID`) REFERENCES `studij` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `KorisnikStudentFK` FOREIGN KEY (`studentID`) REFERENCES `student` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `StudentStudijFK` FOREIGN KEY (`studijID`) REFERENCES `studij` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
