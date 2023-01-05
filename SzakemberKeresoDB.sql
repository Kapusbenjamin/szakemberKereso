-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Jan 05. 16:16
-- Kiszolgáló verziója: 10.4.24-MariaDB
-- PHP verzió: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `szakemberkereso`
--
CREATE DATABASE IF NOT EXISTS `szakemberkereso` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `szakemberkereso`;

DELIMITER $$
--
-- Eljárások
--
DROP PROCEDURE IF EXISTS `acceptRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptRating` (IN `id_in` INT(11))   UPDATE `ratings`
SET `ratings`.`status` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `addFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFavorite` (IN `user_id_in` INT(11), IN `ads_id_in` INT(11))   INSERT INTO `favorites`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewImage` (IN `url_in` VARCHAR(255) CHARSET utf8, IN `title_in` VARCHAR(100) CHARSET utf8, IN `created_at_in` DATE, IN `user_id_in` INT(11))   INSERT INTO `images`
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

DROP PROCEDURE IF EXISTS `checkMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkMessage` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11))   UPDATE `messages`
SET `messages`.`checked` = 1
WHERE `messages`.`sender_id` = sender_id_in 
AND `messages`.`receiver_id` = receiver_id_in
AND `messages`.`checked` = 0$$

DROP PROCEDURE IF EXISTS `createCompany`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCompany` (IN `name_in` VARCHAR(200) CHARSET utf8, IN `premise_address_in` VARCHAR(255) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)   INSERT INTO `companies`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createMessage` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11), IN `message_in` TEXT CHARSET utf8)   INSERT INTO `messages`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))   INSERT INTO `ratings`
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

DROP PROCEDURE IF EXISTS `deleteCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCompanyById` (IN `id_in` INT(11))   UPDATE `companies`
SET `companies`.`deleted` = 1
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteFavorite` (IN `id_in` INT(11))   DELETE FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteImage` (IN `id_in` INT(11))   DELETE FROM `images`
WHERE `images`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAllCompanies`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCompanies` ()   SELECT * FROM `companies`$$

DROP PROCEDURE IF EXISTS `getAllFavoritesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllFavoritesByUserId` (IN `user_id_in` INT)   SELECT * FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobTags`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobTags` ()   SELECT * FROM `job_tags`$$

DROP PROCEDURE IF EXISTS `getAllMessages`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMessages` ()   SELECT * FROM `messages`$$

DROP PROCEDURE IF EXISTS `getAllMessagesBetweenUsers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMessagesBetweenUsers` (IN `user1_id_in` INT(11), IN `user2_id_in` INT(11))   SELECT * FROM `messages`
WHERE (`messages`.`sender_id` = user1_id_in
AND `messages`.`receiver_id` = user2_id_in)
OR (`messages`.`sender_id` = user2_id_in
AND `messages`.`receiver_id` = user1_id_in)
ORDER BY `messages`.`sended_at` ASC$$

DROP PROCEDURE IF EXISTS `getAllRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatings` ()   SELECT * FROM `ratings`$$

DROP PROCEDURE IF EXISTS `getCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCompanyById` (IN `id_in` INT(11))   SELECT * FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getFavoriteById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFavoriteById` (IN `id_in` INT(11))   SELECT * FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getImagesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getImagesByUserId` (IN `user_id_in` INT(11))   SELECT * FROM `images`
WHERE `images`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getJobTagById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobTagById` (IN `id_in` INT(11))   SELECT * FROM `job_tags`
WHERE `job_tags`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingById` (IN `id_in` INT(11))   SELECT * FROM `ratings`
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingByUserId` (IN `user_id_in` INT(11))   SELECT * FROM `ratings`
WHERE `ratings`.`ratinged_user_id` = user_id_in
OR `ratings`.`ratinger_user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `updateCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCompanyById` (IN `id_in` INT(11), IN `name_in` VARCHAR(200) CHARSET utf8, IN `premise_address_in` VARCHAR(255) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)   UPDATE `companies`
SET `companies`.`name` = name_in,
	`companies`.`premise_address` = premise_address_in,
    `companies`.`tax_number` = tax_number_in
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRatingById` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))   UPDATE `ratings`
SET `ratings`.`desc` = desc_in,
	`ratings`.`ratings_stars` = ratings_stars_in,
    `ratings`.`status` = 0
WHERE `ratings`.`id` = id_in$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `number` varchar(30) NOT NULL,
  `staircase` varchar(30) DEFAULT NULL,
  `floor` int(4) DEFAULT NULL,
  `door` int(8) DEFAULT NULL,
  `street_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ads`
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
-- Tábla szerkezet ehhez a táblához `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `postal_code` int(5) NOT NULL,
  `city` varchar(100) NOT NULL,
  `county_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `premise_address` varchar(255) NOT NULL,
  `tax_number` varchar(255) NOT NULL,
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `counties`
--

DROP TABLE IF EXISTS `counties`;
CREATE TABLE `counties` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `favorites`
--

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ads_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `images`
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
-- Tábla szerkezet ehhez a táblához `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `desc` text NOT NULL,
  `total` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT -1,
  `worker_accepted` int(1) NOT NULL DEFAULT 0,
  `customer_accepted` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `job_tags`
--

DROP TABLE IF EXISTS `job_tags`;
CREATE TABLE `job_tags` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `messages`
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
-- Tábla szerkezet ehhez a táblához `ratings`
--

DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `ratinged_user_id` int(11) NOT NULL,
  `ratinger_user_id` int(11) NOT NULL,
  `desc` text NOT NULL,
  `ratings_stars` int(2) NOT NULL DEFAULT 0,
  `status` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `streets`
--

DROP TABLE IF EXISTS `streets`;
CREATE TABLE `streets` (
  `id` int(11) NOT NULL,
  `street_name_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `street_names`
--

DROP TABLE IF EXISTS `street_names`;
CREATE TABLE `street_names` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
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
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `ads`
--
ALTER TABLE `ads`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `counties`
--
ALTER TABLE `counties`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `job_tags`
--
ALTER TABLE `job_tags`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `streets`
--
ALTER TABLE `streets`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `street_names`
--
ALTER TABLE `street_names`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `job_tags`
--
ALTER TABLE `job_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `streets`
--
ALTER TABLE `streets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `street_names`
--
ALTER TABLE `street_names`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `ads`
--
ALTER TABLE `ads`
  ADD CONSTRAINT `ads_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
