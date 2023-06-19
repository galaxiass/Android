-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: localhost
-- Χρόνος δημιουργίας: 19 Ιουν 2023 στις 18:13:34
-- Έκδοση διακομιστή: 10.4.24-MariaDB
-- Έκδοση PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `physiodate`
--
CREATE DATABASE IF NOT EXISTS `physiodate` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `physiodate`;
-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `appointment`
--

CREATE TABLE `appointment` (
  `patient_amka` varchar(11) NOT NULL,
  `clinic_afm` varchar(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `date` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `appointment`
--

INSERT INTO `appointment` (`patient_amka`, `clinic_afm`, `service_id`, `date`) VALUES
('17089300452', '245389907', 2, '14/07/2023, 12:00 π.μ.');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `clinic`
--

CREATE TABLE `clinic` (
  `name` varchar(100) NOT NULL,
  `afm` varchar(11) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `clinic`
--

INSERT INTO `clinic` (`name`, `afm`, `address`) VALUES
('Φυσικοθεραπεία Αργύρης Παπαστεργίου', '245389907', 'Λεωνίδα Ιασονίδου 17, Θεσσαλονίκη');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `patient`
--

CREATE TABLE `patient` (
  `name` varchar(100) NOT NULL,
  `amka` varchar(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `patient`
--

INSERT INTO `patient` (`name`, `amka`, `address`, `password`) VALUES
('Όνομα', '0', 'Διεύθυνση', 'Κωδικός'),
('Δημοσθένης Καραμήτρου', '17089300452', 'Ελ. Βενιζέλου 75, Αμπελόκηποι', 'Pa$$w0rd!');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `services`
--

CREATE TABLE `services` (
  `name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `id` int(11) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `services`
--

INSERT INTO `services` (`name`, `price`, `id`, `description`) VALUES
('Μάλαξη', 35, 1, 'Μάλαξη μυϊηκής ομάδας για την ελλάτωση πόνου/επιτάχυνση ανάρρωσης'),
('Ηλεκτροθεραπεία', 50, 2, 'Xαλάρωση των μυϊκών σπασμών, πρόληψη και καθυστέρηση της ατροφίας λόγω αχρησίας, αύξηση της τοπικής κυκλοφορίας του αίματος, μυϊκή αποκατάσταση και επανεκπαίδευση με ηλεκτρική μυϊκή διέγερση, διατήρηση και αύξηση του εύρους κίνησης, διαχείριση του χρόνιου και δυσίατου πόνου.'),
('Κινησιοθεραπεία', 45.5, 3, 'Aποκατάσταση της σωστής κινητικότητας των αρθρώσεων και βελτίωση της μυϊκής δύναμης και αντοχής, με αποτέλεσμα τη βελτίωση της αποτελεσματικότητας του καρδιαγγειακού και του πνευμονικού συστήματος. Η θεραπεία αυτή μπορεί επίσης να εφαρμοστεί για τη διόρθωση των ατελειών στάσης.'),
('Αναπνευστική Φυσικοθεραπεία', 40, 4, 'Θεραπεία προβλημάτων του αναπνευστικού συστήματος'),
('Γηριατρική Φυσικοθεραπεία', 30, 5, 'Βελτίωση της ποιότητας ζωής του ασθενούς και παράταση της ανεξαρτησίας και σωματικής ευεξίας');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `appointment`
--
ALTER TABLE `appointment`
  ADD KEY `clinic_afm` (`clinic_afm`),
  ADD KEY `patient_amka` (`patient_amka`),
  ADD KEY `service_id` (`service_id`);

--
-- Ευρετήρια για πίνακα `clinic`
--
ALTER TABLE `clinic`
  ADD PRIMARY KEY (`afm`);

--
-- Ευρετήρια για πίνακα `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`amka`);

--
-- Ευρετήρια για πίνακα `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`clinic_afm`) REFERENCES `clinic` (`afm`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`patient_amka`) REFERENCES `patient` (`amka`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
