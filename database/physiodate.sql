-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: localhost
-- Χρόνος δημιουργίας: 17 Ιουν 2023 στις 13:06:54
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

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `clinic`
--

CREATE TABLE `clinic` (
  `name` varchar(100) NOT NULL,
  `afm` varchar(11) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
('Όνομα', '0', 'Διεύθυνση', 'Κωδικός');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
