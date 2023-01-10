-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2023 at 04:28 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `szakemberkereso`
--
CREATE DATABASE IF NOT EXISTS `szakemberkereso` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `szakemberkereso`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `acceptByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByCustomer` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`customer_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByWorker` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`worker_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptRating` (IN `id_in` INT(11))  UPDATE `ratings`
SET `ratings`.`status` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `addFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFavorite` (IN `user_id_in` INT(11), IN `ads_id_in` INT(11))  INSERT INTO `favorites`
(
	`favorites`.`user_id`,
    `favorites`.`ads_id`
)
VALUES
(
	user_id_in,
    ads_id_in
)$$

DROP PROCEDURE IF EXISTS `addNewImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewImage` (IN `url_in` VARCHAR(255) CHARSET utf8, IN `title_in` VARCHAR(100) CHARSET utf8, IN `created_at_in` DATE, IN `user_id_in` INT(11))  INSERT INTO `images`
(
	`images`.`url`,
    `images`.`title`,
    `images`.`created_at`,
    `images`.`user_id`
)
VALUES
(
	url_in,
    title_in,
    created_at_in,
    user_id_in
)$$

DROP PROCEDURE IF EXISTS `changeJobStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeJobStatus` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`status` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `checkMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkMessage` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11))  UPDATE `messages`
SET `messages`.`checked` = 1
WHERE `messages`.`sender_id` = sender_id_in 
AND `messages`.`receiver_id` = receiver_id_in
AND `messages`.`checked` = 0$$

DROP PROCEDURE IF EXISTS `createAddress`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAddress` (IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))  INSERT INTO `addresses`
(
    `addresses`.`county_id`,
    `addresses`.`zip_code`,
    `addresses`.`city`,
    `addresses`.`street`,
    `addresses`.`number`,
    `addresses`.`staircase`,
    `addresses`.`floor`,
    `addresses`.`door`
)
VALUES
(
	county_id_in,
    zip_code_in,
    city_in,
    street_in,
    number_in,
    staircase_in,
    floor_in,
    door_in
)$$

DROP PROCEDURE IF EXISTS `createCompany`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCompany` (IN `name_in` VARCHAR(200) CHARSET utf8, IN `premise_address_in` VARCHAR(255) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)  INSERT INTO `companies`
(
    `companies`.`name`, 
    `companies`.`premise_address`, 
    `companies`.`tax_number`
)
VALUES
(
    name_in,
    premise_address_in,
    tax_number_in
)$$

DROP PROCEDURE IF EXISTS `createMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createMessage` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11), IN `message_in` TEXT CHARSET utf8)  INSERT INTO `messages`
(
	`messages`.`sender_id`,
    `messages`.`receiver_id`,
    `messages`.`message`
)
VALUES
(
	sender_id_in,
    receiver_id_in,
    message_in
)$$

DROP PROCEDURE IF EXISTS `createRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))  INSERT INTO `ratings`
(
	`ratings`.`ratinged_user_id`,
    `ratings`.`ratinger_user_id`,
    `ratings`.`desc`,
    `ratings`.`ratings_stars`
)
VALUES
(
	ratinged_user_id_in,
    ratinger_user_id_in,
    desc_in,
    ratings_stars_in
)$$

DROP PROCEDURE IF EXISTS `deleteAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAddressById` (IN `id_in` INT(11))  DELETE FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCompanyById` (IN `id_in` INT(11))  DELETE FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteFavorite` (IN `id_in` INT(11))  DELETE FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteImage` (IN `id_in` INT(11))  DELETE FROM `images`
WHERE `images`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteJob` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`deleted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRatingById` (IN `id_in` INT(11))  DELETE FROM `ratings`
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAddressById` (IN `id_in` INT(11))  SELECT * FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAllCompanies`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCompanies` ()  SELECT * FROM `companies`$$

DROP PROCEDURE IF EXISTS `getAllCounties`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCounties` ()  SELECT * FROM `counties`$$

DROP PROCEDURE IF EXISTS `getAllFavoritesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllFavoritesByUserId` (IN `user_id_in` INT)  SELECT * FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobs`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobs` ()  SELECT * FROM `jobs`$$

DROP PROCEDURE IF EXISTS `getAllJobTags`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobTags` ()  SELECT * FROM `job_tags`$$

DROP PROCEDURE IF EXISTS `getAllMessages`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMessages` ()  SELECT * FROM `messages`$$

DROP PROCEDURE IF EXISTS `getAllMessagesBetweenUsers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMessagesBetweenUsers` (IN `user1_id_in` INT(11), IN `user2_id_in` INT(11))  SELECT * FROM `messages`
WHERE (`messages`.`sender_id` = user1_id_in
AND `messages`.`receiver_id` = user2_id_in)
OR (`messages`.`sender_id` = user2_id_in
AND `messages`.`receiver_id` = user1_id_in)
ORDER BY `messages`.`sended_at` ASC$$

DROP PROCEDURE IF EXISTS `getAllRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatings` ()  SELECT * FROM `ratings`$$

DROP PROCEDURE IF EXISTS `getCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCompanyById` (IN `id_in` INT(11))  SELECT * FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getFavoriteById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFavoriteById` (IN `id_in` INT(11))  SELECT * FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getImagesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getImagesByUserId` (IN `user_id_in` INT(11))  SELECT * FROM `images`
WHERE `images`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getJobTagById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobTagById` (IN `id_in` INT(11))  SELECT * FROM `job_tags`
WHERE `job_tags`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingById` (IN `id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingByRatinged`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingByRatinged` (IN `user_id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`ratinged_user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getRatingByRatinger`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingByRatinger` (IN `user_id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`ratinger_user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `updateAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAddressById` (IN `id_in` INT(11), IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))  UPDATE `addresses`
SET `addresses`.`county_id` = county_id_in,
	`addresses`.`zip_code` = zip_code_in,
    `addresses`.`city` = city_in,
    `addresses`.`street` = street_in,
    `addresses`.`number` = number_in,
    `addresses`.`staircase` = staircase_in,
    `addresses`.`floor` = floor_in,
    `addresses`.`door` = door_in
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCompanyById` (IN `id_in` INT(11), IN `name_in` VARCHAR(200) CHARSET utf8, IN `premise_address_in` VARCHAR(255) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)  UPDATE `companies`
SET `companies`.`name` = name_in,
	`companies`.`premise_address` = premise_address_in,
    `companies`.`tax_number` = tax_number_in
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRatingById` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))  UPDATE `ratings`
SET `ratings`.`desc` = desc_in,
	`ratings`.`ratings_stars` = ratings_stars_in,
    `ratings`.`status` = 0
WHERE `ratings`.`id` = id_in$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL,
  `zip_code` int(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `number` varchar(30) NOT NULL,
  `staircase` varchar(30) DEFAULT NULL,
  `floor` int(4) DEFAULT NULL,
  `door` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `county_id`, `zip_code`, `city`, `street`, `number`, `staircase`, `floor`, `door`) VALUES
(1, 2, 7600, 'Pécs', '48-as tér', '12', NULL, NULL, NULL),
(2, 2, 7600, 'Pécs', 'Apafi utca', '23', '1', 2, 3),
(3, 2, 7600, 'Pécs', 'Barbakán tér', '34', NULL, NULL, NULL),
(4, 2, 7600, 'Pécs', 'Ág utca', '56', NULL, NULL, NULL),
(5, 2, 7600, 'Pécs', 'Gólya utca', '11', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ads`
--

DROP TABLE IF EXISTS `ads`;
CREATE TABLE `ads` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `job_tag_id` int(11) NOT NULL,
  `desc` text NOT NULL,
  `county_id` int(11) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` int(1) NOT NULL DEFAULT 0,
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `premise_address` varchar(255) NOT NULL,
  `tax_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `counties`
--

DROP TABLE IF EXISTS `counties`;
CREATE TABLE `counties` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `counties`
--

INSERT INTO `counties` (`id`, `name`) VALUES
(1, 'Bács-Kiskun'),
(2, 'Baranya'),
(3, 'Békés'),
(4, 'Borsod-Abaúj-Zemplén'),
(5, 'Budapest'),
(6, 'Csongrád-Csanád'),
(7, 'Fejér'),
(8, 'Győr-Moson-Sopron'),
(9, 'Hajdú-Bihar'),
(10, 'Heves'),
(11, 'Jász-Nagykun-Szolnok'),
(12, 'Komárom-Esztergom'),
(13, 'Nógrád'),
(14, 'Pest'),
(15, 'Somogy'),
(16, 'Szabolcs-Szatmár-Bereg'),
(17, 'Tolna'),
(18, 'Vas'),
(19, 'Veszprém'),
(20, 'Zala');

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ads_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `created_at` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `desc` text NOT NULL,
  `total` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `worker_accepted` int(1) NOT NULL DEFAULT 0,
  `customer_accepted` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `job_tags`
--

DROP TABLE IF EXISTS `job_tags`;
CREATE TABLE `job_tags` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job_tags`
--

INSERT INTO `job_tags` (`id`, `name`) VALUES
(1, 'Ács'),
(2, 'Asztalos'),
(3, 'Autószerelő'),
(4, 'Bádogos'),
(5, 'Biztonság-technika'),
(6, 'Burkoló'),
(7, 'Csőhálózat-szerelő'),
(8, 'Festő-mázoló-tapétázó'),
(9, 'Gáz-szerelő'),
(10, 'Hő-és-hangszigetelő'),
(11, 'Hűtő-klíma-hőszivattyú'),
(12, 'Kazánszerelő'),
(13, 'Kertész'),
(14, 'Kőműves'),
(15, 'Klíma-szerelő'),
(16, 'Nyílászáró-szerelő'),
(17, 'Szobafestő'),
(18, 'Tetőfedő'),
(19, 'Villany szerelő'),
(20, 'Víz-szerelő'),
(21, 'Vízkútfúró'),
(22, 'Vízszigetelő');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `checked` int(1) NOT NULL DEFAULT 0,
  `sended_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `ratinged_user_id` int(11) NOT NULL,
  `ratinger_user_id` int(11) NOT NULL,
  `desc` text NOT NULL,
  `ratings_stars` int(2) NOT NULL DEFAULT 0,
  `status` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `acces_type` int(1) NOT NULL DEFAULT 0,
  `email` varchar(200) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `password` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `job_tag_id` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT -1,
  `last_login_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `activated_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `address_id` int(11) NOT NULL,
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `acces_type`, `email`, `phone`, `password`, `company_id`, `job_tag_id`, `status`, `last_login_at`, `created_at`, `activated_at`, `updated_at`, `address_id`, `deleted`) VALUES
(1, 'Teszt', 'Ferenc', 0, 'tesztf@teszt-user.com', '+36202567896', '1234', NULL, NULL, -1, NULL, '2023-01-05 15:57:39', NULL, '2023-01-05 15:57:39', 1, 0),
(2, 'Teszt', 'László', 1, 'tesztl@teszt-user.com', '+36202567894', '1234', 1, 2, 0, '2023-01-05 15:48:18', '2023-01-05 15:57:39', '2023-01-05 15:48:18', '2023-01-05 15:57:39', 2, 0),
(3, 'Teszt', 'Izabella', 0, 'tesztiza@teszt-user.com', '+36302987764', '1234', NULL, NULL, 0, '2023-01-05 15:55:18', '2023-01-05 15:57:39', '2023-01-04 15:48:18', '2023-01-05 15:57:39', 3, 0),
(4, 'Teszt', 'Admin', 2, 'teszta@teszt-user.com', '+36702753456', '1234', NULL, NULL, 0, '2023-01-06 15:48:18', '2023-01-05 15:57:39', '2023-01-01 15:48:18', '2023-01-05 15:57:39', 4, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ads`
--
ALTER TABLE `ads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `counties`
--
ALTER TABLE `counties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_tags`
--
ALTER TABLE `job_tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_tags`
--
ALTER TABLE `job_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ads`
--
ALTER TABLE `ads`
  ADD CONSTRAINT `ads_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
