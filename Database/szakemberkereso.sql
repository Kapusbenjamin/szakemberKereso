-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2023 at 04:47 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptAd` (IN `id_in` INT(11))   UPDATE `ads`
SET `ads`.`status` = 1
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByCustomer` (IN `id_in` INT(11))   UPDATE `jobs`
SET `jobs`.`customer_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptByWorker` (IN `id_in` INT(11))   UPDATE `jobs`
SET `jobs`.`worker_accepted` = 1
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `acceptRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptRating` (IN `id_in` INT(11))   UPDATE `ratings`
SET `ratings`.`status` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `addFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFavorite` (IN `user_id_in` INT(11), IN `ad_id_in` INT(11))   INSERT INTO `favorites`
(
	`favorites`.`user_id`,
    `favorites`.`ad_id`
)
VALUES
(
	user_id_in,
    ad_id_in
)$$

DROP PROCEDURE IF EXISTS `addNewCountyToAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewCountyToAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))   BEGIN
	DECLARE db int;

	SELECT COUNT(*) INTO db FROM `ads_counties`
    WHERE `ads_counties`.`ad_id` = ad_id_in
    AND `ads_counties`.`county_id` = county_id_in;

    IF(db = 0)
    	THEN
            INSERT INTO `ads_counties`
            (
                `ads_counties`.`ad_id`,
                `ads_counties`.`county_id`
            )
            VALUE
            (
                ad_id_in,
                county_id_in
            );
    END IF;
END$$

DROP PROCEDURE IF EXISTS `addNewJobToUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewJobToUser` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11))   BEGIN
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

DROP PROCEDURE IF EXISTS `canWriteRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `canWriteRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), OUT `result` INT(1))   BEGIN
	IF((SELECT COUNT(*) FROM `ratings`
            WHERE `ratings`.`ratinged_user_id` = ratinged_user_id_in
            AND `ratings`.`ratinger_user_id` = ratinger_user_id_in) 
       = 
       (SELECT COUNT(*) FROM `jobs`
            WHERE `jobs`.`worker_id` = ratinged_user_id_in
            AND `jobs`.`customer_id` = ratinger_user_id_in))
  		THEN
        	SET result = 0;
	ELSE
		SET result = 1;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `changeAccess`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeAccess` (IN `user_id_in` INT(11))   UPDATE `users`
SET `users`.`access_type` = 1
WHERE `users`.`id` = user_id_in$$

DROP PROCEDURE IF EXISTS `changeJobStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeJobStatus` (IN `id_in` INT(11))   UPDATE `jobs`
SET `jobs`.`status` = 1
WHERE `jobs`.`id` = id_in
AND `jobs`.`customer_accepted` = 1
AND `jobs`.`worker_accepted` = 1$$

DROP PROCEDURE IF EXISTS `changePassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changePassword` (IN `id_in` INT(11), IN `password_in` VARCHAR(255) CHARSET utf8)   UPDATE `users`
SET `users`.`password` = password_in
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `checkMessage`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `checkMessage` (IN `chat_id_in` INT(11), IN `user_id_in` INT(11))   UPDATE `messages`
SET `messages`.`checked` = 1
WHERE `messages`.`chat_id` = chat_id_in
AND `messages`.`receiver_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `createAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAd` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, OUT `last_id_out` INT(11))   BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createAddress` (IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))   BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createChat` (IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11))   INSERT INTO `chats`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCompany` (IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8, IN `user_id_in` INT(11))   BEGIN
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
    
    IF(user_id_in != -1)
    	THEN
        	UPDATE `users`
            SET `users`.`company_id` = LAST_INSERT_ID()
            WHERE `users`.`id` = user_id_in;
 	END IF;
END$$

DROP PROCEDURE IF EXISTS `createJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createJob` (IN `customer_id_in` INT(11), IN `worker_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)   INSERT INTO `jobs`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createMessage` (IN `chat_id_in` INT(11), IN `sender_id_in` INT(11), IN `receiver_id_in` INT(11), IN `message_in` TEXT CHARSET utf8)   INSERT INTO `messages`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createNewAds` (IN `user_id_in` INT(11), IN `job_tag_id` INT(11), IN `desc_in` TEXT CHARSET utf8, OUT `last_id_out` INT(11))   BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))   INSERT INTO `ratings`
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8), IN `token_in` VARCHAR(255) CHARSET utf8, OUT `user_id_out` INT(11))   BEGIN
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
        `users`.`address_id`,
        `users`.`token`,
        `users`.`token_expired_at`
    )
    VALUES (
    	first_name_in,
        last_name_in,
        email_in,
        phone_in,
        password_in,
        address_id,
        token_in,
        DATE_ADD(NOW(), INTERVAL 10 MINUTE)
    );
    SET user_id_out = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `createUserWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUserWorker` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8), IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8, IN `token_in` VARCHAR(255) CHARSET utf8, OUT `user_id_out` INT(11))   BEGIN
	DECLARE address_id INT(11);
    DECLARE company_id INT(11);
    
    CALL `createAddress`(county_id_in, zip_code_in, city_in, street_in, number_in, staircase_in, floor_in, door_in);
    SELECT LAST_INSERT_ID() INTO address_id;
    
    CALL `createCompany`(company_name_in, premise_county_id_in, premise_zip_code_in, premise_city_in, premise_street_in, premise_number_in, premise_staircase_in, premise_floor_in, premise_door_in, tax_number_in, -1);
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
        `users`.`address_id`,
        `users`.`token`,
        `users`.`token_expired_at`
    )
    VALUES (
    	first_name_in,
        last_name_in,
        1,
        email_in,
        phone_in,
        password_in,
        company_id,
        address_id,
        token_in,
        DATE_ADD(NOW(),INTERVAL 10 MINUTE)
    );
    SET user_id_out = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `deleteAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAd` (IN `id_in` INT(11))   UPDATE `ads`
SET `ads`.`deleted` = 1
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAddressById` (IN `id_in` INT(11))   DELETE FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCompanyById` (IN `id_in` INT(11))   BEGIN
	DELETE FROM `companies`
	WHERE `companies`.`id` = id_in;
    
    UPDATE `users`
    SET `users`.`company_id` = null
    WHERE `users`.`company_id` = id_in;
END$$

DROP PROCEDURE IF EXISTS `deleteCountyFromAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCountyFromAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))   DELETE FROM `ads_counties`
WHERE `ads_counties`.`ad_id` = ad_id_in
AND `ads_counties`.`county_id` = county_id_in$$

DROP PROCEDURE IF EXISTS `deleteFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteFavorite` (IN `user_id_in` INT(11), IN `ad_id_in` INT(11))   DELETE FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in
AND `favorites`.`ad_id` = ad_id_in$$

DROP PROCEDURE IF EXISTS `deleteJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteJob` (IN `id_in` INT(11))   UPDATE `jobs`
SET `jobs`.`deleted` = 1
WHERE `jobs`.`id` = id_in
AND (`jobs`.`worker_accepted` != 1
OR `jobs`.`customer_accepted` != 1)$$

DROP PROCEDURE IF EXISTS `deleteRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteRatingById` (IN `id_in` INT(11))   UPDATE `ratings`
SET `ratings`.`deleted` = 1
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `id_in` INT(11))   BEGIN
    UPDATE `users`
    SET `users`.`deleted` = 1
    WHERE `users`.`id` = id_in;
    UPDATE `ads`
    SET `ads`.`deleted` = 1
    WHERE `ads`.`user_id` = id_in;
END$$

DROP PROCEDURE IF EXISTS `deleteUserJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUserJob` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11))   DELETE FROM `users_jobs`
WHERE `users_jobs`.`user_id` = user_id_in
AND `users_jobs`.`job_tag_id` = job_tag_id_in$$

DROP PROCEDURE IF EXISTS `forgotPassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `forgotPassword` (IN `email_in` VARCHAR(200) CHARSET utf8, IN `psw_in` VARCHAR(255) CHARSET utf8)   UPDATE `users`
SET `users`.`password` = psw_in
WHERE `users`.`email` = email_in$$

DROP PROCEDURE IF EXISTS `getAdById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdById` (IN `id_in` INT(11))   SELECT * FROM `ads`
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAddressById` (IN `id_in` INT(11))   SELECT * FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAllAcceptedAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAcceptedAds` ()   SELECT * FROM `ads`
WHERE `ads`.`deleted` != 1
AND `ads`.`status` = 1$$

DROP PROCEDURE IF EXISTS `getAllAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAds` ()   SELECT * FROM `ads`$$

DROP PROCEDURE IF EXISTS `getAllAdsByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAdsByUserId` (IN `user_id_in` INT(11))   SELECT * FROM `ads`
WHERE `ads`.`deleted` != 1
AND `ads`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllChatsByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllChatsByUserId` (IN `user_id_in` INT(11))   SELECT * FROM `chats`
WHERE `chats`.`sender_id` = user_id_in
OR `chats`.`receiver_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllCounties`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCounties` ()   SELECT * FROM `counties`$$

DROP PROCEDURE IF EXISTS `getAllCountiesByAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCountiesByAd` (IN `ad_id_in` INT(11))   SELECT `counties`.`id`, `counties`.`name`
FROM `ads_counties`
INNER JOIN `counties`
ON `ads_counties`.`county_id` = `counties`.`id`
WHERE `ads_counties`.`ad_id` = ad_id_in$$

DROP PROCEDURE IF EXISTS `getAllFavoritesByUserId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllFavoritesByUserId` (IN `user_id_in` INT)   SELECT * FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobs`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobs` ()   SELECT * FROM `jobs`$$

DROP PROCEDURE IF EXISTS `getAllJobsByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByCustomer` (IN `customer_id_in` INT(11))   SELECT * FROM `jobs`
WHERE `jobs`.`deleted` != 1
AND `jobs`.`customer_id` = customer_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobsByUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByUser` (IN `user_id_in` INT(11))   SELECT `job_tags`.`id`, `job_tags`.`name` FROM `job_tags`
INNER JOIN `users_jobs`
ON `users_jobs`.`job_tag_id` = `job_tags`.`id`
WHERE `users_jobs`.`user_id` = user_id_in$$

DROP PROCEDURE IF EXISTS `getAllJobsByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllJobsByWorker` (IN `worker_id_in` INT(11))   SELECT * FROM `jobs`
WHERE `jobs`.`deleted` != 1
AND `jobs`.`worker_id` = worker_id_in$$

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

DROP PROCEDURE IF EXISTS `getAllNonAcceptedAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNonAcceptedAds` ()   SELECT * FROM `ads`
WHERE `ads`.`status` = 0
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllNotAcceptedRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNotAcceptedRatings` ()   SELECT * FROM `ratings`
WHERE `ratings`.`status` = 0
AND `ratings`.`deleted` = 0$$

DROP PROCEDURE IF EXISTS `getAllRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatings` ()   SELECT * FROM `ratings`$$

DROP PROCEDURE IF EXISTS `getAllRatingsByRatinged`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatingsByRatinged` (IN `user_id_in` INT(11))   SELECT * FROM `ratings`
WHERE `ratings`.`ratinged_user_id` = user_id_in
AND `ratings`.`status` = 1
AND `ratings`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllRatingsByRatinger`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRatingsByRatinger` (IN `user_id_in` INT(11))   SELECT * FROM `ratings`
WHERE `ratings`.`ratinger_user_id` = user_id_in
AND `ratings`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getAllUsers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsers` ()   SELECT * FROM `users`$$

DROP PROCEDURE IF EXISTS `getChatById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getChatById` (IN `id_in` INT(11))   SELECT * FROM `chats`
WHERE `chats`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCompanyById` (IN `id_in` INT(11))   SELECT * FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCountyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCountyById` (IN `id_in` INT(11))   SELECT * FROM `counties`
WHERE `counties`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCountyFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCountyFilteredAds` (IN `county_id_in` INT(11))   SELECT `ads`.`id`, `ads`.`user_id`, `ads`.`job_tag_id`, `ads`.`description`, `ads`.`updated_at`, `ads`.`status`, `ads`.`deleted` FROM `ads`
INNER JOIN `ads_counties`
ON `ads`.`id` = `ads_counties`.`ad_id`
WHERE `ads_counties`.`county_id` = county_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getFavoriteById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFavoriteById` (IN `id_in` INT(11))   SELECT * FROM `favorites`
WHERE `favorites`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFilteredAds` (IN `county_id_in` INT(11), IN `job_tag_id_in` INT(11))   SELECT `ads`.`id`, `ads`.`user_id`, `ads`.`job_tag_id`, `ads`.`description`, `ads`.`updated_at`, `ads`.`status`, `ads`.`deleted` FROM `ads`
INNER JOIN `ads_counties`
ON `ads`.`id` = `ads_counties`.`ad_id`
WHERE `ads_counties`.`county_id` = county_id_in
AND `ads`.`job_tag_id` = job_tag_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getJobById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobById` (IN `id_in` INT(11))   SELECT * FROM `jobs`
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getJobFilteredAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobFilteredAds` (IN `job_tag_id_in` INT(11))   SELECT * FROM `ads`
WHERE `ads`.`job_tag_id` = job_tag_id_in
AND `ads`.`status` = 1
AND `ads`.`deleted` != 1$$

DROP PROCEDURE IF EXISTS `getJobTagById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getJobTagById` (IN `id_in` INT(11))   SELECT * FROM `job_tags`
WHERE `job_tags`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRatingById` (IN `id_in` INT(11))   SELECT * FROM `ratings`
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getUserById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById` (IN `id_in` INT(11))   SELECT * FROM `users`
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `integerToNull`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `integerToNull` (INOUT `int_in` INT)   IF(int_in = -1)
	THEN SET int_in = null;
END IF$$

DROP PROCEDURE IF EXISTS `isEmailUnique`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `isEmailUnique` (IN `email_in` VARCHAR(255) CHARSET utf8, OUT `result` BOOLEAN)   BEGIN
	IF((SELECT COUNT(*) FROM `users`
      	WHERE `users`.`email` = email_in) = 0)
        THEN
        	SET result = true;
	ELSE
    	SET result = false;
 	END IF;
END$$

DROP PROCEDURE IF EXISTS `loginUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `loginUser` (IN `us_in` VARCHAR(200) CHARSET utf8, IN `psw_in` VARCHAR(255) CHARSET utf8)   BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `logoutUser` (IN `id_in` INT(11))   UPDATE `users`
SET `users`.`status` = 0
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `resetPassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `resetPassword` (IN `email_in` VARCHAR(200) CHARSET utf8, IN `token_in` VARCHAR(255) CHARSET utf8, IN `psw_in` VARCHAR(255) CHARSET utf8)   UPDATE `users`
SET `users`.`password` = psw_in,
	`users`.`token` = null,
    `users`.`token_expired_at` = null
WHERE `users`.`email` = email_in
AND `users`.`token` = token_in
AND `users`.`token_expired_at` > NOW()$$

DROP PROCEDURE IF EXISTS `stringToNull`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `stringToNull` (INOUT `str_in` VARCHAR(255) CHARSET utf8)   IF(str_in = "")
	THEN SET str_in = null;
END IF$$

DROP PROCEDURE IF EXISTS `updateAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAd` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)   UPDATE `ads`
SET `ads`.`description` = desc_in,
    `ads`.`status` = 0
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAddressById` (IN `id_in` INT(11), IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8))   BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAds` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)   UPDATE `ads`
SET `ads`.`desc` = desc_in,
    `ads`.`status` = 0
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCompanyById` (IN `id_in` INT(11), IN `name_in` VARCHAR(200) CHARSET utf8, IN `tax_number_in` VARCHAR(255) CHARSET utf8)   UPDATE `companies`
SET `companies`.`name` = name_in,
    `companies`.`tax_number` = tax_number_in
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateJobByCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateJobByCustomer` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8)   UPDATE `jobs`
SET `jobs`.`description` = desc_in,
	`jobs`.`customer_accepted` = 1,
    `jobs`.`worker_accepted` = 0
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateJobByWorker`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateJobByWorker` (IN `id_in` INT(11), IN `total_in` INT(11))   UPDATE `jobs`
SET `jobs`.`total` = total_in,
	`jobs`.`worker_accepted` = 1,
	`jobs`.`customer_accepted` = 0
WHERE `jobs`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateRatingById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateRatingById` (IN `id_in` INT(11), IN `desc_in` TEXT CHARSET utf8, IN `ratings_stars_in` INT(2))   UPDATE `ratings`
SET `ratings`.`description` = desc_in,
	`ratings`.`ratings_stars` = ratings_stars_in,
    `ratings`.`status` = 0
WHERE `ratings`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `updateUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `id_in` INT(11), IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8)   UPDATE `users`
SET `users`.`first_name` = first_name_in,
	`users`.`last_name` = last_name_in,
	`users`.`email` = email_in,
	`users`.`phone` = phone_in
WHERE `users`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `validateEmailByToken`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `validateEmailByToken` (IN `token_in` VARCHAR(255) CHARSET utf8)   UPDATE `users`
SET `users`.`status` = 0,
	`users`.`token` = null,
    `users`.`token_expired_at` = null,
    `users`.`activated_at` = NOW()
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `addresses`
--

TRUNCATE TABLE `addresses`;
--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `county_id`, `zip_code`, `city`, `street`, `number`, `staircase`, `floor`, `door`) VALUES
(1, 2, 7600, 'Pécs', 'Ács utca', '11', NULL, NULL, NULL),
(2, 2, 7635, 'Pécs', 'Árnyas út', '42', NULL, NULL, NULL),
(3, 14, 2092, 'Budakeszi', 'Kalmár utca', '84/C', NULL, NULL, NULL),
(4, 14, 2092, 'Budakeszi', 'Meggyes utca', '23', NULL, NULL, NULL),
(5, 14, 7700, 'Mohács', 'Hóvirág utca', '11', NULL, NULL, NULL),
(6, 2, 7700, 'Mohács', 'Tavasz utca', '48', 'Déli oldal', 2, 11),
(7, 2, 7632, 'Pécs', 'Berek utca', '35', NULL, NULL, NULL),
(8, 2, 7629, 'Pécs', 'Feketegyémánt tér', '28/D', NULL, NULL, NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `ads`
--

TRUNCATE TABLE `ads`;
--
-- Dumping data for table `ads`
--

INSERT INTO `ads` (`id`, `user_id`, `job_tag_id`, `description`, `updated_at`, `status`, `deleted`) VALUES
(1, 2, 6, 'Vállalom családi házak felújításását, megbízható munkavégzés, \n- felújítás, átalakítás\n- külső- és belső burkolás\n- hőszigetelés\n- hideg- és meleg burkolás\n- gipszkartonozás, festés', '2023-05-08 13:18:35', 1, 0),
(2, 3, 19, 'Teljes körű villanyszerelést vállalok több éves szakmai tapasztalattal. \nKisebb felújítástól a teljes generál kivitelezésig vállalom az elektromos hálózat kiépítését. \nSzolgáltatásaimról bővebben!\n-családi házak, lakások, nyaralók alapszerelése, szerelvényezése\n-hibakeresések és javítások\n-elektromos berendezések beüzemelése és kialakítása(tűzhely,főzőlap,bojler stb.)\n-biztosítékok, elosztótábla cseréje, fi-relé beépítése\n-lámpatestek cseréje\nKeressenek bizalommal!', '2023-05-08 13:25:05', 1, 0),
(3, 2, 14, 'Vállalom családi házak felújításását, megbízható munkavégzés, \n- Felújítás, bontás, építés\n- teraszok, kerítések\n- Járda, és térkő burkolás\n- garázsok és melléképületek', '2023-05-08 13:47:37', 1, 0),
(4, 3, 20, 'Vízszerelés Budapesten\n\nHa probléma van, minket hívjon\n\nProbléma van otthon a vízzel? Nem lehet lehúzni a wc-t? Csöpög a csap? Nem tölt föl a tartály? Az ilyen, és ehhez hasonló problémákkal feltétlenül keressen fel minket. Hogy miért minket? Azért, mert mi már régóta benne vagyunk a szakmában, így biztos, hogy mi tökéletes munkát fogunk végezni. Kattintson a részletekért. ', '2023-05-08 13:41:08', 1, 0),
(5, 3, 9, '25 éve a szakmában és a lakosság szolgálatában!\n\nGázszerelés: gázkészülék javítás, tisztítás, csere, éves karbantartás, tűzhely-bekötés, gáz és szénmonoxid szivárgás műszeres vizsgálat!', '2023-05-08 13:40:31', 1, 0),
(6, 1, 8, 'Szeretné falait, mennyezetét felújítani, újra festeni? Akkor jár a legjobban, ha a munkát szakemberekre bízza. Vállaljuk a régi festékréteg vagy tapéta eltávolítását, a falfelület glettelését, csiszolását illetve a Önök által kiválasztott színekre való festését, tapétázását. \nSzolgáltatások:\n- Tisztasági festés, Szobafestés, Penészmentesítés\n- Beázások, repedések javítása\n- Tapétázás, Dekorációs tapéták, Poszterek felrakása\n- Gipszkarton szerelés, glettelés\n- Rejtett világítások kialakítása\n- Nomasztil felrakása (díszcsík hungarocellből)\n- Nyílászárók, fa-és fémfelületek mázolása\n- Fa pácolása, lakkozása, Széldeszkák lazúrozása, Kerítés mázolás ', '2023-05-08 13:51:42', 1, 0),
(7, 1, 5, 'Az általunk kialakított biztonságtechnikai rendszereket a kor vagyonvédelmi elvárásainak leginkább megfelelő és legjobb minőségű eszközök alkotják.\n\nA megrendelői igényeknek megfelelően szolgáltatásunk kiterjed az elektronikus és mechanikus vagyonvédelmi rendszerek teljes körű karbantartására, amely a telepített rendszerek függvényében lehet: éves, féléves, negyed éves vagy eseti jellegű.', '2023-05-08 13:53:03', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ads_counties`
--

DROP TABLE IF EXISTS `ads_counties`;
CREATE TABLE `ads_counties` (
  `id` int(11) NOT NULL,
  `ad_id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `ads_counties`
--

TRUNCATE TABLE `ads_counties`;
--
-- Dumping data for table `ads_counties`
--

INSERT INTO `ads_counties` (`id`, `ad_id`, `county_id`) VALUES
(1, 1, 15),
(2, 1, 2),
(3, 1, 17),
(4, 2, 13),
(5, 2, 5),
(6, 2, 12),
(7, 2, 14),
(11, 4, 5),
(12, 4, 14),
(13, 5, 1),
(14, 5, 19),
(15, 5, 7),
(16, 5, 8),
(17, 4, 13),
(18, 3, 4),
(19, 3, 16),
(20, 3, 10),
(21, 6, 3),
(22, 6, 6),
(23, 6, 11),
(24, 7, 17),
(25, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `chats`
--

TRUNCATE TABLE `chats`;
--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`id`, `sender_id`, `receiver_id`) VALUES
(1, 4, 2),
(2, 4, 3),
(3, 1, 2),
(4, 1, 3),
(5, 5, 3);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `companies`
--

TRUNCATE TABLE `companies`;
--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`id`, `name`, `address_id`, `tax_number`) VALUES
(1, 'Nagy és társa kft.', 2, '2132165465'),
(2, 'Kovacs-Burkoló bt.', 4, '6623446672'),
(3, 'Molnár Építészet kft.', 6, '642844244');

-- --------------------------------------------------------

--
-- Table structure for table `counties`
--

DROP TABLE IF EXISTS `counties`;
CREATE TABLE `counties` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `counties`
--

TRUNCATE TABLE `counties`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `favorites`
--

TRUNCATE TABLE `favorites`;
--
-- Dumping data for table `favorites`
--

INSERT INTO `favorites` (`id`, `user_id`, `ad_id`) VALUES
(1, 4, 1),
(3, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `total` int(11) NOT NULL DEFAULT 0,
  `status` int(1) NOT NULL DEFAULT 0,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `customer_accepted` int(1) NOT NULL DEFAULT 0,
  `worker_accepted` int(1) NOT NULL DEFAULT 0,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `jobs`
--

TRUNCATE TABLE `jobs`;
--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `description`, `total`, `status`, `customer_id`, `worker_id`, `customer_accepted`, `worker_accepted`, `updated_at`, `deleted`) VALUES
(1, '1986-ban épült házunkat (110 m2) szeretnénk hőszigetelni, és a konyhát gipszkartonozni', 500000, 1, 4, 2, 1, 1, '2023-05-08 14:01:33', 0),
(2, '1986-ban épült házunkban szeretnénk átnézetni a villany vezetékeket', 340000, 1, 4, 3, 1, 1, '2023-05-08 14:07:15', 0),
(3, 'Éves vizsgálat elvégzése', 20000, 1, 5, 3, 1, 1, '2023-05-08 14:41:43', 0);

-- --------------------------------------------------------

--
-- Table structure for table `job_tags`
--

DROP TABLE IF EXISTS `job_tags`;
CREATE TABLE `job_tags` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `job_tags`
--

TRUNCATE TABLE `job_tags`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `messages`
--

TRUNCATE TABLE `messages`;
--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `chat_id`, `sender_id`, `receiver_id`, `message`, `checked`, `sended_at`) VALUES
(1, 1, 4, 2, 'Jó napot!\nVállalná házunk hőszigetelését, Babarc (Baranya), lehetőleg Májusban?', 1, '2023-05-08 14:14:50'),
(2, 1, 2, 4, 'Jó napot\nLehet róla szó, kérem értesítsen megbízásban a ház méretéről és a  feledatot írja le mégegyszer. köszönöm ', 1, '2023-05-08 14:15:59'),
(3, 1, 4, 2, 'Megtörtént, köszönöm válaszát!', 1, '2023-05-08 14:16:39'),
(4, 1, 4, 2, 'Köszönöm a remek munkát', 1, '2023-05-08 14:17:08'),
(5, 2, 4, 3, 'Jó napot!\nVezeték fellülvizsgálatot vállal?', 1, '2023-05-08 14:18:40'),
(6, 2, 3, 4, 'Jó napot, persze lehet róla szó', 1, '2023-05-08 14:19:06'),
(7, 2, 3, 4, 'Írjon egy megbízást ne felejtsem el', 1, '2023-05-08 14:19:23'),
(8, 2, 4, 3, 'Megtörtént', 1, '2023-05-08 14:20:10'),
(9, 3, 1, 2, 'Burkoló hirdetés jóváhagyva', 0, '2023-05-08 14:22:18'),
(10, 3, 1, 2, 'Kőműves hirdetés jóváhagyva', 0, '2023-05-08 14:22:46'),
(11, 4, 1, 3, 'Villanyszerelő hirdetés jóváhagyva', 1, '2023-05-08 14:23:13'),
(12, 4, 1, 3, 'Vízszerelő hirdetés jóváhagyva', 1, '2023-05-08 14:23:29'),
(13, 5, 5, 3, 'Szakembert keresek az éves vizsgálatra', 1, '2023-05-08 14:39:50'),
(14, 5, 3, 5, 'Rendben, küldjön egy megbízást', 1, '2023-05-08 14:40:13');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `ratings`
--

TRUNCATE TABLE `ratings`;
--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `ratinged_user_id`, `ratinger_user_id`, `description`, `ratings_stars`, `status`, `updated_at`, `deleted`) VALUES
(1, 2, 4, 'Megbízható munkavégzés, korrekt árazás, csak ajánlani tudom', 5, 1, '2023-05-08 14:05:44', 0),
(2, 3, 4, 'A munkát szépen megcsinálta, de a modora kifogásolható', 4, 1, '2023-05-08 14:05:45', 0),
(3, 3, 5, 'Megbízható munkavégzés', 5, 1, '2023-05-08 14:42:09', 0);

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
  `status` int(1) NOT NULL DEFAULT -1,
  `token` varchar(255) DEFAULT NULL,
  `token_expired_at` timestamp NULL DEFAULT NULL,
  `last_login_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `activated_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `access_type`, `email`, `phone`, `password`, `company_id`, `address_id`, `status`, `token`, `token_expired_at`, `last_login_at`, `created_at`, `activated_at`, `updated_at`, `deleted`) VALUES
(1, 'Admin', 'Admin', 2, 'admin@teszt-user.hu', '+36123456789', '0c40d6baacfc32d0ea6145381058cc41227dd79a59a8bf4e58e3f47c40de524c', 1, 1, 0, NULL, NULL, '2023-05-08 13:09:17', '2023-05-08 12:23:52', '2023-05-08 12:24:05', '2023-05-08 14:44:05', 0),
(2, 'Kovács', 'János', 1, 'kovJanos@teszt-user.hu', '+36701234567', '0c40d6baacfc32d0ea6145381058cc41227dd79a59a8bf4e58e3f47c40de524c', 2, 3, 0, NULL, NULL, '2023-05-08 13:11:09', '2023-05-08 12:31:11', '2023-05-08 12:35:23', '2023-05-08 14:44:01', 0),
(3, 'Molnár', 'Árpád', 1, 'arpad1974@teszt-user.hu', '+36205551234', '0c40d6baacfc32d0ea6145381058cc41227dd79a59a8bf4e58e3f47c40de524c', 3, 5, 0, NULL, NULL, '2023-05-08 13:21:25', '2023-05-08 12:35:10', '2023-05-08 12:35:28', '2023-05-08 14:43:57', 0),
(4, 'Horváth', 'Emese', 0, 'emese.horvath@teszt-user.hu', '+36309991239', '0c40d6baacfc32d0ea6145381058cc41227dd79a59a8bf4e58e3f47c40de524c', NULL, 7, 0, NULL, NULL, '2023-05-08 13:26:38', '2023-05-08 12:37:26', '2023-05-08 12:39:01', '2023-05-08 14:31:19', 0),
(5, 'Márton', 'Réka', 0, 'mreka@teszt-user.hu', '+36301586825', '0c40d6baacfc32d0ea6145381058cc41227dd79a59a8bf4e58e3f47c40de524c', NULL, 8, 0, NULL, NULL, '2023-05-08 14:31:53', '2023-05-08 12:38:54', '2023-05-08 12:39:30', '2023-05-08 14:43:52', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users_jobs`
--

DROP TABLE IF EXISTS `users_jobs`;
CREATE TABLE `users_jobs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `job_tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncate table before insert `users_jobs`
--

TRUNCATE TABLE `users_jobs`;
--
-- Dumping data for table `users_jobs`
--

INSERT INTO `users_jobs` (`id`, `user_id`, `job_tag_id`) VALUES
(1, 1, 5),
(2, 1, 8),
(3, 2, 6),
(4, 3, 19),
(5, 2, 2),
(6, 2, 14),
(7, 3, 20),
(8, 3, 9);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ads_counties`
--
ALTER TABLE `ads_counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `job_tags`
--
ALTER TABLE `job_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users_jobs`
--
ALTER TABLE `users_jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
