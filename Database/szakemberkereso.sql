-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2023 at 03:41 PM
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
DROP PROCEDURE IF EXISTS `acceptAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptAd` (IN `id_in` INT(11))  UPDATE `ads`
SET `ads`.`status` = 1
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByCustomer` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`customer_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByWorker` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`worker_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptImage` (IN `id_in` INT(11))  UPDATE `images`
SET `images`.`status` = 1
WHERE `images`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptRating` (IN `id_in` INT(11))  UPDATE `ratings`
SET `ratings`.`status` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `addCountyToAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCountyToAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))  INSERT INTO `ads_counties`
(
    `ads_counties`.`ad_id`,
    `ads_counties`.`county_id`
)
VALUE
(
    ad_id_in,
    county_id_in
)$$

DROP PROCEDURE IF EXISTS `addFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFavorite` (IN `user_id_in` INT(11), IN `ad_id_in` INT(11))  INSERT INTO `favorites`
(
	`favorites`.`user_id`,
    `favorites`.`ad_id`
)
VALUES
(
	user_id_in,
    ad_id_in
)$$

DROP PROCEDURE IF EXISTS `addImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addImage` (IN `url_in` VARCHAR(255) CHARSET utf8, IN `title_in` VARCHAR(100) CHARSET utf8, IN `created_at_in` TIMESTAMP, IN `user_id_in` INT(11))  INSERT INTO `images`
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

DROP PROCEDURE IF EXISTS `addNewCountyToAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewCountyToAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))  INSERT INTO `ads_counties`
(
    `ads_counties`.`ad_id`,
    `ads_counties`.`county_id`
)
VALUE
(
    ad_id_in,
    county_id_in
)$$

DROP PROCEDURE IF EXISTS `addNewJobToUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewJobToUser` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11))  BEGIN
	DECLARE db int;

	SELECT COUNT(*) INTO db FROM `users_jobs`
    WHERE `users_jobs`.`user_id` = user_id_in
    AND `users_jobs`.`job_tag_id` =job_tag_id_in;
    
    IF(db = 0)
    	THEN
            INSERT INTO `users_jobs`
            (
                `users_jobs`.`user_id`,
                `users_jobs`.`job_tag_id`
            )
            VALUE
            (
                user_id_in,
                job_tag_id_in
            );
 	END IF;
END$$

DROP PROCEDURE IF EXISTS `changeAccess`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeAccess` (IN `user_id_in` INT(11))  UPDATE `users`
SET `users`.`access_type` = 1
WHERE `users`.`id` = user_id_in$$

DROP PROCEDURE IF EXISTS `changeJobStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeJobStatus` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`status` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `changePassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changePassword` (IN `id_in` INT(11), IN `password_in` VARCHAR(255) CHARSET utf8)  UPDATE `users`
SET `users`.`password` = password_in
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `checkMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkMessage` (IN `chat_id_in` INT(11), IN `user_id_in` INT(11))  UPDATE `messages`
SET `messages`.`checked` = 1
WHERE `messages`.`chat_id` = chat_id_in
AND `messages`.`receiver_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `createAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAd` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, OUT `last_id_out` INT(11))  BEGIN
	INSERT INTO `ads`
	(
		`ads`.`user_id`,
    	`ads`.`job_tag_id`,
    	`ads`.`description`
	)
	VALUES (
		user_id_in,
    	job_tag_id_in,
    	desc_in
	);
	SELECT LAST_INSERT_ID() INTO last_id_out;
END$$

DROP PROCEDURE IF EXISTS `createAddress`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAddress` (IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))  BEGIN
	CALL `stringToNull`(staircase_in);
    CALL `integerToNull`(floor_in);
    CALL `integerToNull`(door_in);
    INSERT INTO `addresses`
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
    );
END$$

DROP PROCEDURE IF EXISTS `createChat`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createChat` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11))  INSERT INTO `chats`
(
	`chats`.`sender_id`,
    `chats`.`receiver_id`
)
SELECT * FROM 
(SELECT sender_id_in, receiver_id_in)
AS `values`
WHERE NOT EXISTS (
    SELECT `chats`.`sender_id`, `chats`.`receiver_id`
    FROM `chats`
    WHERE (`chats`.`sender_id` = sender_id_in
   	OR `chats`.`receiver_id` = sender_id_in)
    AND (`chats`.`sender_id` = receiver_id_in
    OR `chats`.`receiver_id` = receiver_id_in)
) LIMIT 1$$

DROP PROCEDURE IF EXISTS `createCompany`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCompany` (IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8)  BEGIN
   DECLARE company_address_id INT(11);
    
   CALL `createAddress`(premise_county_id_in, premise_zip_code_in, premise_city_in, premise_street_in, premise_number_in, premise_staircase_in, premise_floor_in, premise_door_in);
   SELECT LAST_INSERT_ID() INTO company_address_id;
   
   INSERT INTO `companies`
    (
        `companies`.`name`, 
        `companies`.`address_id`, 
        `companies`.`tax_number`
    )
    VALUES
    (
        company_name_in,
        company_address_id,
        tax_number_in
    );
END$$

DROP PROCEDURE IF EXISTS `createJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createJob` (IN `customer_id_in` INT(11), IN `worker_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)  INSERT INTO `jobs`
(
   `jobs`.`customer_id`,
   `jobs`.`worker_id`,
   `jobs`.`description`,
   `jobs`.`customer_accepted` 
)
VALUES (
	customer_id_in,
    worker_id_in,
    desc_in,
    1
)$$

DROP PROCEDURE IF EXISTS `createMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createMessage` (IN `chat_id_in` INT(11), IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11), IN `message_in` TEXT CHARSET utf8)  INSERT INTO `messages`
(
    `messages`.`chat_id`,
	`messages`.`sender_id`,
    `messages`.`receiver_id`,
    `messages`.`message`
)
VALUES
(
    chat_id_in,
	sender_id_in,
    receiver_id_in,
    message_in
)$$

DROP PROCEDURE IF EXISTS `createNewAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewAds` (IN `user_id_in` INT(11), IN `job_tag_id` INT(11), IN `desc_in` TEXT CHARSET utf8, OUT `last_id_out` INT(11))  BEGIN
	INSERT INTO `ads`
	(
		`ads`.`user_id`,
    	`ads`.`job_tag_id`,
    	`ads`.`desc`
	)
	VALUES (
		user_id_in,
    	job_tag_id_in,
    	desc_in
	);
	SELECT LAST_INSERT_ID() INTO last_id_out;
END$$

DROP PROCEDURE IF EXISTS `createRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))  INSERT INTO `ratings`
(
	`ratings`.`ratinged_user_id`,
    `ratings`.`ratinger_user_id`,
    `ratings`.`description`,
    `ratings`.`ratings_stars`
)
VALUES
(
	ratinged_user_id_in,
    ratinger_user_id_in,
    desc_in,
    ratings_stars_in
)$$

DROP PROCEDURE IF EXISTS `createUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))  BEGIN
	DECLARE address_id INT(11);
    
    CALL `createAddress`(county_id_in, zip_code_in, city_in, street_in, number_in, staircase_in, floor_in, door_in);
    SELECT LAST_INSERT_ID() INTO address_id;
    
	INSERT INTO `users`
    (
    	`users`.`first_name`,
        `users`.`last_name`,
        `users`.`email`,
        `users`.`phone`,
        `users`.`password`,
        `users`.`address_id`
    )
    VALUES (
    	first_name_in,
        last_name_in,
        email_in,
        phone_in,
        password_in,
        address_id
    );
END$$

DROP PROCEDURE IF EXISTS `createUserWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUserWorker` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8), IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8)  BEGIN
	DECLARE address_id INT(11);
    DECLARE company_id INT(11);
    
    CALL `createAddress`(county_id_in, zip_code_in, city_in, street_in, number_in, staircase_in, floor_in, door_in);
    SELECT LAST_INSERT_ID() INTO address_id;
    
    CALL `createCompany`(company_name_in, premise_county_id_in, premise_zip_code_in, premise_city_in, premise_street_in, premise_number_in, premise_staircase_in, premise_floor_in, premise_door_in, tax_number_in);
    SELECT LAST_INSERT_ID() INTO company_id;
    
	INSERT INTO `users`
    (
    	`users`.`first_name`,
        `users`.`last_name`,
        `users`.`access_type`,
        `users`.`email`,
        `users`.`phone`,
        `users`.`password`,
        `users`.`company_id`,
        `users`.`address_id`
    )
    VALUES (
    	first_name_in,
        last_name_in,
        1,
        email_in,
        phone_in,
        password_in,
        company_id,
        address_id
    );
END$$

DROP PROCEDURE IF EXISTS `deleteAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAd` (IN `id_in` INT(11))  UPDATE `ads`
SET `ads`.`deleted` = 1
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAddressById` (IN `id_in` INT(11))  DELETE FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCompanyById` (IN `id_in` INT(11))  DELETE FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteCountyFromAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCountyFromAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))  DELETE FROM `ads_counties`
WHERE `ads_counties`.`ad_id` = ad_id_in
AND `ads_counties`.`county_id` = county_id_in$$

DROP PROCEDURE IF EXISTS `deleteFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteFavorite` (IN `id_in` INT(11))  DELETE FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteImage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteImage` (IN `id_in` INT(11))  DELETE FROM `images`
WHERE `images`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteJob` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`deleted` = 1
WHERE `jobs`.`id` = id_in
AND (`jobs`.`worker_accepted` != 1
OR `jobs`.`customer_accepted` != 1)$$

DROP PROCEDURE IF EXISTS `deleteRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRatingById` (IN `id_in` INT(11))  UPDATE `ratings`
SET `ratings`.`deleted` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `id_in` INT(11))  UPDATE `users`
SET `users`.`deleted` = 1
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteUserJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUserJob` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11))  DELETE FROM `users_jobs`
WHERE `users_jobs`.`user_id` = user_id_in
AND `users_jobs`.`job_tag_id` = job_tag_id_in$$

DROP PROCEDURE IF EXISTS `getAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAddressById` (IN `id_in` INT(11))  SELECT * FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAdsById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdsById` (IN `id_in` INT(11))  SELECT * FROM `ads`
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAllAcceptedAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAcceptedAds` ()  SELECT * FROM `ads`
WHERE `ads`.`deleted` != 1
AND `ads`.`status` = 1$$

DROP PROCEDURE IF EXISTS `getAllAcceptedImagesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAcceptedImagesByUserId` (IN `user_id_in` INT(11))  SELECT * FROM `images`
WHERE `images`.`status` = 1
AND `images`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAds` ()  SELECT * FROM `ads`$$

DROP PROCEDURE IF EXISTS `getAllAdsByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAdsByUserId` (IN `user_id_in` INT(11))  SELECT * FROM `ads`
WHERE `ads`.`deleted` != 1
AND `ads`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllChatsByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllChatsByUserId` (IN `user_id_in` INT(11))  SELECT * FROM `chats`
WHERE `chats`.`sender_id` = user_id_in
OR `chats`.`receiver_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllCounties`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCounties` ()  SELECT * FROM `counties`$$

DROP PROCEDURE IF EXISTS `getAllCountiesByAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCountiesByAd` (IN `ad_id_in` INT(11))  SELECT `counties`.`id`, `counties`.`name`
FROM `ads_counties`
INNER JOIN `counties`
ON `ads_counties`.`county_id` = `counties`.`id`
WHERE `ads_counties`.`ad_id` = ad_id_in$$

DROP PROCEDURE IF EXISTS `getAllFavoritesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllFavoritesByUserId` (IN `user_id_in` INT)  SELECT * FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllImages`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllImages` ()  SELECT * FROM `images`$$

DROP PROCEDURE IF EXISTS `getAllJobs`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobs` ()  SELECT * FROM `jobs`$$

DROP PROCEDURE IF EXISTS `getAllJobsByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByCustomer` (IN `customer_id_in` INT(11))  SELECT * FROM `jobs`
WHERE `jobs`.`deleted` != 1
AND `jobs`.`customer_id` = customer_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobsByUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByUser` (IN `user_id_in` INT(11))  SELECT `job_tags`.`id`, `job_tags`.`name` FROM `job_tags`
INNER JOIN `users_jobs`
ON `users_jobs`.`job_tag_id` = `job_tags`.`id`
WHERE `users_jobs`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobsByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByWorker` (IN `worker_id_in` INT(11))  SELECT * FROM `jobs`
WHERE `jobs`.`deleted` != 1
AND `jobs`.`worker_id` = worker_id_in$$

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

DROP PROCEDURE IF EXISTS `getAllNonAcceptedAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNonAcceptedAds` ()  SELECT * FROM `ads`
WHERE `ads`.`status` = 0
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllNotAcceptedImages`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNotAcceptedImages` ()  SELECT * FROM `images`
WHERE `images`.`status` = 0$$

DROP PROCEDURE IF EXISTS `getAllNotAcceptedRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNotAcceptedRatings` ()  SELECT * FROM `ratings`
WHERE `ratings`.`status` = 0$$

DROP PROCEDURE IF EXISTS `getAllRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatings` ()  SELECT * FROM `ratings`$$

DROP PROCEDURE IF EXISTS `getAllRatingsByRatinged`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatingsByRatinged` (IN `user_id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`ratinged_user_id` = user_id_in
AND `ratings`.`status` = 1
AND `ratings`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllRatingsByRatinger`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatingsByRatinger` (IN `user_id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`ratinger_user_id` = user_id_in
AND `ratings`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllUsers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsers` ()  SELECT * FROM `users`$$

DROP PROCEDURE IF EXISTS `getCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCompanyById` (IN `id_in` INT(11))  SELECT * FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCountyFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCountyFilteredAds` (IN `county_id_in` INT(11))  SELECT `ads`.`id`, `ads`.`user_id`, `ads`.`job_tag_id`, `ads`.`description`, `ads`.`updated_at`, `ads`.`status`, `ads`.`deleted` FROM `ads`
INNER JOIN `ads_counties`
ON `ads`.`id` = `ads_counties`.`ad_id`
WHERE `ads_counties`.`county_id` = county_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getFavoriteById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFavoriteById` (IN `id_in` INT(11))  SELECT * FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFilteredAds` (IN `county_id_in` INT(11), IN `job_tag_id_in` INT(11))  SELECT `ads`.`id`, `ads`.`user_id`, `ads`.`job_tag_id`, `ads`.`description`, `ads`.`updated_at`, `ads`.`status`, `ads`.`deleted` FROM `ads`
INNER JOIN `ads_counties`
ON `ads`.`id` = `ads_counties`.`ad_id`
WHERE `ads_counties`.`county_id` = county_id_in
AND `ads`.`job_tag_id` = job_tag_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getImagesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getImagesByUserId` (IN `user_id_in` INT(11))  SELECT * FROM `images`
WHERE `images`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getJobById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobById` (IN `id_in` INT(11))  SELECT * FROM `jobs`
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getJobFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobFilteredAds` (IN `job_tag_id_in` INT(11))  SELECT * FROM `ads`
WHERE `ads`.`job_tag_id` = job_tag_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getJobTagById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobTagById` (IN `id_in` INT(11))  SELECT * FROM `job_tags`
WHERE `job_tags`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingById` (IN `id_in` INT(11))  SELECT * FROM `ratings`
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getUserById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById` (IN `id_in` INT(11))  SELECT * FROM `users`
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `integerToNull`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `integerToNull` (INOUT `int_in` INT)  IF(int_in = -1)
	THEN SET int_in = null;
END IF$$

DROP PROCEDURE IF EXISTS `loginUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `loginUser` (IN `us_in` VARCHAR(200) CHARSET utf8, IN `psw_in` VARCHAR(255) CHARSET utf8)  BEGIN
	DECLARE user_id INT(11) DEFAULT -1;

	SELECT `users`.`id` INTO user_id  
    FROM `users`
    WHERE (`users`.`email` = us_in
    OR `users`.`phone` = us_in)
    AND `users`.`password` = psw_in
    AND `users`.`deleted` != 1
    AND `users`.`status` != -1;
    
    IF(user_id != -1)
    	THEN 
        	UPDATE `users`
            SET `users`.`status` = 1,
            	`users`.`last_login_at` = CURRENT_TIMESTAMP()
            WHERE `users`.`id` = user_id;
        	SELECT `users`.`id`, `users`.`first_name`, `users`.`last_name`, `users`.`access_type`
        	FROM `users`
            WHERE `users`.`id` = user_id;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `logoutUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `logoutUser` (IN `id_in` INT(11))  UPDATE `users`
SET `users`.`status` = 0
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `stringToNull`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `stringToNull` (INOUT `str_in` VARCHAR(255) CHARSET utf8)  IF(str_in = "")
	THEN SET str_in = null;
END IF$$

DROP PROCEDURE IF EXISTS `updateAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAd` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)  UPDATE `ads`
SET `ads`.`description` = desc_in,
    `ads`.`status` = 0
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAddressById` (IN `id_in` INT(11), IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))  BEGIN
	CALL `stringToNull`(staircase_in);
    CALL `integerToNull`(floor_in);
    CALL `integerToNull`(door_in);
    UPDATE `addresses`
    SET `addresses`.`county_id` = county_id_in,
        `addresses`.`zip_code` = zip_code_in,
        `addresses`.`city` = city_in,
        `addresses`.`street` = street_in,
        `addresses`.`number` = number_in,
        `addresses`.`staircase` = staircase_in,
        `addresses`.`floor` = floor_in,
        `addresses`.`door` = door_in
    WHERE `addresses`.`id` = id_in;
END$$

DROP PROCEDURE IF EXISTS `updateAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAds` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)  UPDATE `ads`
SET `ads`.`desc` = desc_in,
    `ads`.`status` = 0
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCompanyById` (IN `id_in` INT(11), IN `name_in` VARCHAR(200) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)  UPDATE `companies`
SET `companies`.`name` = name_in,
    `companies`.`tax_number` = tax_number_in
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateJobByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateJobByCustomer` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)  UPDATE `jobs`
SET `jobs`.`description` = desc_in,
	`jobs`.`customer_accepted` = 1,
    `jobs`.`worker_accepted` = 0
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateJobByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateJobByWorker` (IN `id_in` INT(11), IN `total_in` INT(11))  UPDATE `jobs`
SET `jobs`.`total` = total_in,
	`jobs`.`worker_accepted` = 1,
	`jobs`.`customer_accepted` = 0
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRatingById` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))  UPDATE `ratings`
SET `ratings`.`description` = desc_in,
	`ratings`.`ratings_stars` = ratings_stars_in,
    `ratings`.`status` = 0
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `id_in` INT(11), IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8)  UPDATE `users`
SET `users`.`first_name` = first_name_in,
	`users`.`last_name` = last_name_in,
	`users`.`email` = email_in,
	`users`.`phone` = phone_in
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `validateEmail`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `validateEmail` (IN `id_in` INT(11))  UPDATE `users`
SET `users`.`status` = 0
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `validateEmailByToken`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `validateEmailByToken` (IN `token_in` VARCHAR(255) CHARSET utf8)  UPDATE `users`
SET `users`.`status` = 0,
	`users`.`token` = null,
    `users`.`token_expired_at` = null
WHERE `users`.`token` = token_in
AND `users`.`token_expired_at` > NOW()$$

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
(3, 1, 4532, 'Budapest', 'A utca', '23/A', NULL, NULL, NULL),
(4, 2, 7600, 'Pécs', 'Ág utca', '56', NULL, NULL, NULL),
(6, 5, 4532, 'Pécs', 'Petőfi', '13/A', 'Első', 2, 12),
(40, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(41, 4, 1111, 'Bp', 'AAAA utca', '56', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ads`
--

DROP TABLE IF EXISTS `ads`;
CREATE TABLE `ads` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `job_tag_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` int(1) NOT NULL DEFAULT 0,
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ads`
--

INSERT INTO `ads` (`id`, `user_id`, `job_tag_id`, `description`, `updated_at`, `status`, `deleted`) VALUES
(1, 2, 1, 'Valami', '2023-01-17 15:24:57', 1, 0),
(2, 2, 3, 'aaaaaaaaaaaaa', '2023-02-01 17:45:46', 1, 0),
(3, 2, 5, 'asfddsgfs', '2023-01-29 16:10:55', 1, 0),
(4, 2, 5, 'Semmi', '2023-01-29 14:53:19', 0, 0),
(5, 4, 1, 'a', '2023-02-01 17:46:21', 1, 0),
(6, 2, 5, 'Semmi', '2023-01-29 15:42:46', 1, 0),
(7, 2, 5, 'aaaaaaaaaaaaa', '2023-01-29 15:42:30', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ads_counties`
--

DROP TABLE IF EXISTS `ads_counties`;
CREATE TABLE `ads_counties` (
  `id` int(11) NOT NULL,
  `ad_id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ads_counties`
--

INSERT INTO `ads_counties` (`id`, `ad_id`, `county_id`) VALUES
(1, 1, 2),
(2, 1, 3),
(5, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`id`, `sender_id`, `receiver_id`) VALUES
(1, 2, 3),
(2, 3, 1),
(3, 2, 1),
(4, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address_id` int(11) NOT NULL,
  `tax_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`id`, `name`, `address_id`, `tax_number`) VALUES
(1, 'ABC Kft.', 6, '123423543'),
(3, 'A kft.', 40, '2132165465'),
(4, 'Nagy Kft.', 41, '124556222');

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
  `ad_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `favorites`
--

INSERT INTO `favorites` (`id`, `user_id`, `ad_id`) VALUES
(1, 1, 1),
(2, 2, 4),
(4, 4, 2),
(5, 2, 10);

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 0,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `url`, `title`, `status`, `user_id`, `created_at`) VALUES
(1, 'asd', 'b', 1, 1, '2023-02-08 20:20:56'),
(2, 'asd', 'b', 0, 1, '2023-02-08 20:05:11'),
(3, 'bffg', 'x', 1, 2, '2023-02-08 20:05:11'),
(4, 'a', 'b', 0, 2, '2002-02-02 19:20:20'),
(5, 'url', 'title', 0, 3, '2023-02-08 20:05:11'),
(6, 'url', 'title', 0, 3, '2023-02-08 20:05:11'),
(7, 'url', 'title', 0, 3, '2000-02-02 20:20:20'),
(8, 'url', 'title', 0, 3, '2030-02-02 20:20:20');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `total` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `customer_accepted` int(1) NOT NULL DEFAULT 0,
  `worker_accepted` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `description`, `total`, `status`, `customer_id`, `worker_id`, `customer_accepted`, `worker_accepted`, `updated_at`, `deleted`) VALUES
(1, 'Alma', 200000, 1, 2, 4, 1, 1, '2023-02-02 14:26:43', 1),
(2, 'Leírás', 0, 0, 1, 2, 1, 0, '2023-02-02 14:44:09', 0);

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
  `chat_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `checked` int(1) NOT NULL DEFAULT 0,
  `sended_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `chat_id`, `sender_id`, `receiver_id`, `message`, `checked`, `sended_at`) VALUES
(1, 3, 1, 2, 'Alma', 0, '2023-01-24 13:33:26'),
(2, 4, 3, 2, 'Nem', 1, '2023-02-08 15:50:49'),
(3, 1, 5, 6, 'Kettő is', 0, '2023-02-08 15:50:49'),
(4, 4, 2, 3, 'De', 1, '2023-02-08 15:51:39'),
(5, 4, 3, 2, 'Nemssssssss', 1, '2023-02-08 16:34:38'),
(6, 3, 2, 3, 'Uzi', 0, '2023-02-08 16:35:51');

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `ratinged_user_id` int(11) NOT NULL,
  `ratinger_user_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `ratings_stars` int(2) NOT NULL DEFAULT 0,
  `status` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `ratinged_user_id`, `ratinger_user_id`, `description`, `ratings_stars`, `status`, `updated_at`, `deleted`) VALUES
(1, 2, 3, 'nem hozta az anyagot', 1, 1, '2023-02-09 09:57:36', 0),
(3, 1, 2, 'kettő is', 2, 0, '2023-02-09 10:05:04', 1),
(4, 2, 3, 'mindent megcsinált csak nem jól', 2, 0, '2023-02-09 09:59:50', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `access_type` int(1) NOT NULL DEFAULT 0,
  `email` varchar(200) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `password` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `address_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT -1,
  `token` varchar(255) DEFAULT NULL,
  `token_expired_at` timestamp NULL DEFAULT NULL,
  `last_login_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `activated_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `access_type`, `email`, `phone`, `password`, `company_id`, `address_id`, `status`, `token`, `token_expired_at`, `last_login_at`, `created_at`, `activated_at`, `updated_at`, `deleted`) VALUES
(1, 'Teszt', 'Ferenc', 0, 'tesztf@teszt-user.com', '+36202567896', '1234', NULL, 0, -1, NULL, NULL, NULL, '2023-01-05 15:57:39', NULL, '2023-01-05 15:57:39', 0),
(2, 'Teszt', 'László', 1, 'tesztl@teszt-user.com', '+36202567894', '1234', 1, 0, 0, NULL, NULL, '2023-01-27 10:35:06', '2023-01-05 15:57:39', '2023-01-05 15:48:18', '2023-01-27 10:35:31', 0),
(3, 'Teszt', 'Izabella', 1, 'tesztiza@teszt-user.com', '+36302987764', '1234', NULL, 0, 0, NULL, NULL, '2023-01-05 15:55:18', '2023-01-05 15:57:39', '2023-01-04 15:48:18', '2023-01-27 11:31:13', 0),
(4, 'Teszt', 'Admin', 2, 'teszta@teszt-user.com', '+36702753456', '1234', NULL, 0, 0, NULL, NULL, '2023-01-06 15:48:18', '2023-01-05 15:57:39', '2023-01-01 15:48:18', '2023-01-05 15:57:39', 0),
(8, 'TESZT', 'AA', 1, 'A@gmail.com', '+36123456789', 'jelszo123', 3, 39, -1, NULL, NULL, NULL, '2023-01-28 15:00:51', NULL, '2023-01-28 15:00:51', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users_jobs`
--

DROP TABLE IF EXISTS `users_jobs`;
CREATE TABLE `users_jobs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `job_tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_jobs`
--

INSERT INTO `users_jobs` (`id`, `user_id`, `job_tag_id`) VALUES
(2, 4, 1),
(3, 4, 5);

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
-- Indexes for table `ads_counties`
--
ALTER TABLE `ads_counties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chats`
--
ALTER TABLE `chats`
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
-- Indexes for table `users_jobs`
--
ALTER TABLE `users_jobs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ads_counties`
--
ALTER TABLE `ads_counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `job_tags`
--
ALTER TABLE `job_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users_jobs`
--
ALTER TABLE `users_jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
