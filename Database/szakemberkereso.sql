-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Máj 03. 12:34
-- Kiszolgáló verziója: 10.4.22-MariaDB
-- PHP verzió: 8.0.13

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

DROP PROCEDURE IF EXISTS `acceptRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `acceptRating` (IN `id_in` INT(11))  UPDATE `ratings`
SET `ratings`.`status` = 1
WHERE `ratings`.`id` = id_in$$

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

DROP PROCEDURE IF EXISTS `addNewCountyToAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewCountyToAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))  BEGIN
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

DROP PROCEDURE IF EXISTS `canWriteRating`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `canWriteRating` (IN `ratinged_user_id_in` INT(11), IN `ratinger_user_id_in` INT(11), OUT `result` INT(1))  BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeAccess` (IN `user_id_in` INT(11))  UPDATE `users`
SET `users`.`access_type` = 1
WHERE `users`.`id` = user_id_in$$

DROP PROCEDURE IF EXISTS `changeJobStatus`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `changeJobStatus` (IN `id_in` INT(11))  UPDATE `jobs`
SET `jobs`.`status` = 1
WHERE `jobs`.`id` = id_in
AND `jobs`.`customer_accepted` = 1
AND `jobs`.`worker_accepted` = 1$$

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCompany` (IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8, IN `user_id_in` INT(11))  BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8), IN `token_in` VARCHAR(255) CHARSET utf8, OUT `user_id_out` INT(11))  BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `createUserWorker` (IN `first_name_in` VARCHAR(100) CHARSET utf8, IN `last_name_in` VARCHAR(100) CHARSET utf8, IN `email_in` VARCHAR(200) CHARSET utf8, IN `phone_in` VARCHAR(12) CHARSET utf8, IN `password_in` VARCHAR(255) CHARSET utf8, IN `county_id_in` INT(11), IN `zip_code_in` INT(5), IN `city_in` VARCHAR(255) CHARSET utf8, IN `street_in` VARCHAR(255) CHARSET utf8, IN `number_in` VARCHAR(30) CHARSET utf8, IN `staircase_in` VARCHAR(30) CHARSET utf8, IN `floor_in` INT(4), IN `door_in` INT(8), IN `company_name_in` VARCHAR(200) CHARSET utf8, IN `premise_county_id_in` INT(11), IN `premise_zip_code_in` INT(5), IN `premise_city_in` VARCHAR(255) CHARSET utf8, IN `premise_street_in` VARCHAR(255) CHARSET utf8, IN `premise_number_in` VARCHAR(30) CHARSET utf8, IN `premise_staircase_in` VARCHAR(30) CHARSET utf8, IN `premise_floor_in` INT(4), IN `premise_door_in` INT(8), IN `tax_number_in` VARCHAR(255) CHARSET utf8, IN `token_in` VARCHAR(255) CHARSET utf8, OUT `user_id_out` INT(11))  BEGIN
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAd` (IN `id_in` INT(11))  UPDATE `ads`
SET `ads`.`deleted` = 1
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAddressById` (IN `id_in` INT(11))  DELETE FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `deleteCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCompanyById` (IN `id_in` INT(11))  BEGIN
	DELETE FROM `companies`
	WHERE `companies`.`id` = id_in;
    
    UPDATE `users`
    SET `users`.`company_id` = null
    WHERE `users`.`company_id` = id_in;
END$$

DROP PROCEDURE IF EXISTS `deleteCountyFromAd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCountyFromAd` (IN `ad_id_in` INT(11), IN `county_id_in` INT(11))  DELETE FROM `ads_counties`
WHERE `ads_counties`.`ad_id` = ad_id_in
AND `ads_counties`.`county_id` = county_id_in$$

DROP PROCEDURE IF EXISTS `deleteFavorite`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteFavorite` (IN `user_id_in` INT(11), IN `ad_id_in` INT(11))  DELETE FROM `favorites`
WHERE `favorites`.`user_id` = user_id_in
AND `favorites`.`ad_id` = ad_id_in$$

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `id_in` INT(11))  BEGIN
    UPDATE `users`
    SET `users`.`deleted` = 1
    WHERE `users`.`id` = id_in;
    UPDATE `ads`
    SET `ads`.`deleted` = 1
    WHERE `ads`.`user_id` = id_in;
END$$

DROP PROCEDURE IF EXISTS `deleteUserJob`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUserJob` (IN `user_id_in` INT(11), IN `job_tag_id_in` INT(11))  DELETE FROM `users_jobs`
WHERE `users_jobs`.`user_id` = user_id_in
AND `users_jobs`.`job_tag_id` = job_tag_id_in$$

DROP PROCEDURE IF EXISTS `forgotPassword`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `forgotPassword` (IN `email_in` VARCHAR(200) CHARSET utf8, IN `psw_in` VARCHAR(255) CHARSET utf8)  UPDATE `users`
SET `users`.`password` = psw_in
WHERE `users`.`email` = email_in$$

DROP PROCEDURE IF EXISTS `getAdById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdById` (IN `id_in` INT(11))  SELECT * FROM `ads`
WHERE `ads`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAddressById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAddressById` (IN `id_in` INT(11))  SELECT * FROM `addresses`
WHERE `addresses`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getAllAcceptedAds`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAcceptedAds` ()  SELECT * FROM `ads`
WHERE `ads`.`deleted` != 1
AND `ads`.`status` = 1$$

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

DROP PROCEDURE IF EXISTS `getAllNotAcceptedRatings`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllNotAcceptedRatings` ()  SELECT * FROM `ratings`
WHERE `ratings`.`status` = 0
AND `ratings`.`deleted` = 0$$

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

DROP PROCEDURE IF EXISTS `getChatById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getChatById` (IN `id_in` INT(11))  SELECT * FROM `chats`
WHERE `chats`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCompanyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCompanyById` (IN `id_in` INT(11))  SELECT * FROM `companies`
WHERE `companies`.`id` = id_in$$

DROP PROCEDURE IF EXISTS `getCountyById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCountyById` (IN `id_in` INT(11))  SELECT * FROM `counties`
WHERE `counties`.`id` = id_in$$

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

DROP PROCEDURE IF EXISTS `isEmailUnique`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `isEmailUnique` (IN `email_in` VARCHAR(255) CHARSET utf8, OUT `result` BOOLEAN)  BEGIN
	IF((SELECT COUNT(*) FROM `users`
      	WHERE `users`.`email` = email_in) = 0)
        THEN
        	SET result = true;
	ELSE
    	SET result = false;
 	END IF;
END$$

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

DROP PROCEDURE IF EXISTS `validateEmailByToken`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `validateEmailByToken` (IN `token_in` VARCHAR(255) CHARSET utf8)  UPDATE `users`
SET `users`.`status` = 0,
	`users`.`token` = null,
    `users`.`token_expired_at` = null,
    `users`.`activated_at` = NOW()
WHERE `users`.`token` = token_in
AND `users`.`token_expired_at` > NOW()$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `addresses`
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
-- A tábla adatainak kiíratása `addresses`
--

INSERT INTO `addresses` (`id`, `county_id`, `zip_code`, `city`, `street`, `number`, `staircase`, `floor`, `door`) VALUES
(1, 2, 7600, 'Pécs', '48-as tér', '12', NULL, NULL, NULL),
(2, 2, 7600, 'Pécs', 'Apafi utca', '23', '1', 2, 3),
(3, 1, 4532, 'Budapest', 'A utca', '23/A', NULL, NULL, NULL),
(4, 2, 7600, 'Pécs', 'Ág utca', '56', NULL, NULL, NULL),
(6, 5, 4532, 'Pécs', 'Petőfi', '13/A', 'Első', 2, 12),
(40, 1, 4532, 'Budapest', 'A utca', '23/A', 'Hátsó', 2, 11),
(41, 4, 1111, 'Bp', 'AAAA utca', '56', NULL, NULL, NULL),
(42, 4, 1111, 'Bp', 'AAAA utca', '56', NULL, NULL, NULL),
(43, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(44, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(45, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(46, 4, 1111, 'Bp', 'AAAA utca', '56', NULL, NULL, NULL),
(47, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(48, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(49, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(50, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(51, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(52, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(53, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(54, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(55, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(56, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(57, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(58, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(59, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(60, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(61, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(62, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(63, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(64, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(65, 4, 1111, 'Bp', 'AAAA utca', '56', NULL, NULL, NULL),
(66, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(67, 10, 2222, 'Teszt', 'Cég utca', '42', NULL, NULL, NULL),
(68, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL),
(69, 10, 2222, 'Teszt', 'ATesztAAA utca', '474/C', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ads`
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
-- A tábla adatainak kiíratása `ads`
--

INSERT INTO `ads` (`id`, `user_id`, `job_tag_id`, `description`, `updated_at`, `status`, `deleted`) VALUES
(1, 2, 1, 'Valami', '2023-01-17 15:24:57', 1, 0),
(2, 2, 3, 'aaaaaaaaaaaaa', '2023-02-16 16:18:17', 0, 0),
(3, 2, 5, 'asfddsgfs', '2023-01-29 16:10:55', 1, 0),
(4, 2, 5, 'Semmi', '2023-04-22 16:59:52', 1, 0),
(5, 4, 1, 'a', '2023-04-22 16:59:58', 1, 0),
(6, 2, 5, 'Semmi', '2023-02-16 16:18:20', 1, 1),
(7, 2, 5, 'aaaaaaaaaaaaa', '2023-01-29 15:42:30', 1, 1),
(10, 2, 5, 'Semmi', '2023-04-22 17:00:00', 0, 1),
(11, 4, 5, 'aaaqqqq', '2023-04-22 17:04:47', 1, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ads_counties`
--

DROP TABLE IF EXISTS `ads_counties`;
CREATE TABLE `ads_counties` (
  `id` int(11) NOT NULL,
  `ad_id` int(11) NOT NULL,
  `county_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `ads_counties`
--

INSERT INTO `ads_counties` (`id`, `ad_id`, `county_id`) VALUES
(1, 1, 2),
(2, 1, 3),
(5, 2, 5),
(6, 2, 5),
(7, 11, 4);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `chats`
--

DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `chats`
--

INSERT INTO `chats` (`id`, `sender_id`, `receiver_id`) VALUES
(1, 2, 3),
(2, 3, 1),
(3, 2, 1),
(4, 4, 3),
(5, 4, 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address_id` int(11) NOT NULL,
  `tax_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `companies`
--

INSERT INTO `companies` (`id`, `name`, `address_id`, `tax_number`) VALUES
(1, 'ABC Kft.', 6, '123423543'),
(3, 'A kft.', 40, '2132165465'),
(5, 'A kft.', 45, '2132165465'),
(6, 'Kis és társai Bt.', 46, '6542187486'),
(7, 'A kft.', 50, '2132165465'),
(8, 'A kft.', 52, '2132165465'),
(9, 'A kft.', 55, '2132165465'),
(10, 'A kft.', 58, '2132165465'),
(11, 'A kft.', 60, '2132165465'),
(13, 'A kft.', 64, '2132165465'),
(14, 'Kis és társai Bt.', 65, '6542187486'),
(15, 'A kft.', 67, '2132165465');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `counties`
--

DROP TABLE IF EXISTS `counties`;
CREATE TABLE `counties` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `counties`
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
-- Tábla szerkezet ehhez a táblához `favorites`
--

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ad_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `favorites`
--

INSERT INTO `favorites` (`id`, `user_id`, `ad_id`) VALUES
(1, 1, 1),
(4, 4, 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jobs`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `jobs`
--

INSERT INTO `jobs` (`id`, `description`, `total`, `status`, `customer_id`, `worker_id`, `customer_accepted`, `worker_accepted`, `updated_at`, `deleted`) VALUES
(1, 'Alma', 200000, 1, 2, 4, 1, 1, '2023-03-21 14:52:00', 0),
(2, 'Leírás', 0, 0, 1, 2, 1, 0, '2023-02-02 14:44:09', 0),
(3, 'Leírás', 0, 0, 1, 2, 1, 0, '2023-02-16 16:24:17', 0),
(4, 'asd fg d', 0, 1, 4, 2, 1, 0, '2023-04-22 16:49:07', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `job_tags`
--

DROP TABLE IF EXISTS `job_tags`;
CREATE TABLE `job_tags` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `job_tags`
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
-- Tábla szerkezet ehhez a táblához `messages`
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
-- A tábla adatainak kiíratása `messages`
--

INSERT INTO `messages` (`id`, `chat_id`, `sender_id`, `receiver_id`, `message`, `checked`, `sended_at`) VALUES
(1, 3, 1, 2, 'Alma', 0, '2023-01-24 13:33:26'),
(2, 4, 3, 2, 'Nem', 1, '2023-02-08 15:50:49'),
(3, 1, 5, 6, 'Kettő is', 0, '2023-02-08 15:50:49'),
(4, 4, 2, 3, 'De', 1, '2023-02-08 15:51:39'),
(5, 4, 3, 2, 'Nemssssssss', 1, '2023-02-08 16:34:38'),
(6, 3, 2, 3, 'Uzi', 0, '2023-02-08 16:35:51'),
(7, 3, 2, 3, 'Uzi', 0, '2023-02-16 16:19:34'),
(8, 3, 4, 3, 'Uzi', 0, '2023-03-21 20:16:07'),
(9, 5, 4, 2, 'asdasdd', 0, '2023-04-22 17:03:14');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ratings`
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
-- A tábla adatainak kiíratása `ratings`
--

INSERT INTO `ratings` (`id`, `ratinged_user_id`, `ratinger_user_id`, `description`, `ratings_stars`, `status`, `updated_at`, `deleted`) VALUES
(1, 2, 3, 'nem hozta az anyagot', 1, 1, '2023-02-09 09:57:36', 0),
(3, 1, 2, 'kettő is', 2, 0, '2023-02-09 10:05:04', 1),
(4, 2, 3, 'mindent megcsinált csak nem jól', 2, 0, '2023-02-09 09:59:50', 0),
(5, 2, 3, 'nem hozta az anyagot', 1, 0, '2023-02-16 16:23:27', 0),
(6, 4, 2, 'asfddsgfs', 4, 1, '2023-04-22 17:01:18', 0),
(7, 2, 4, 'mnenm', 3, 0, '2023-04-22 16:48:56', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `access_type`, `email`, `phone`, `password`, `company_id`, `address_id`, `status`, `token`, `token_expired_at`, `last_login_at`, `created_at`, `activated_at`, `updated_at`, `deleted`) VALUES
(1, 'Teszt', 'Ferenc', 0, 'tesztf@teszt-user.com', '+36202567896', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', NULL, 1, -1, NULL, NULL, NULL, '2023-01-05 15:57:39', NULL, '2023-04-13 15:53:33', 0),
(2, 'Teszt', 'László', 1, 'tesztl@teszt-user.com', '+36202567894', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', 1, 1, 0, NULL, NULL, '2023-02-16 16:16:19', '2023-01-05 15:57:39', '2023-01-05 15:48:18', '2023-04-13 15:53:34', 0),
(3, 'Teszt', 'Izabella', 0, 'tesztiza@teszt-user.com', '+36302987764', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', NULL, 1, 0, NULL, NULL, '2023-01-05 15:55:18', '2023-01-05 15:57:39', '2023-01-04 15:48:18', '2023-04-13 15:53:36', 0),
(4, 'Teszt', 'Admin', 2, 'teszta@teszt-user.com', '+36702753456', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', 14, 1, 0, NULL, NULL, '2023-04-22 17:10:52', '2023-01-05 15:57:39', '2023-01-01 15:48:18', '2023-05-03 10:15:59', 0),
(8, 'TESZT', 'AA', 1, 'A@gmail.com', '+36123456789', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', 3, 1, -1, NULL, NULL, NULL, '2023-01-28 15:00:51', NULL, '2023-04-13 15:53:30', 0),
(9, 'TESZT', 'AA', 0, 'A@gmail.com', '+36123456789', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', NULL, 43, -1, NULL, NULL, NULL, '2023-02-16 16:16:26', NULL, '2023-02-24 12:08:19', 0),
(10, 'TESZT', 'AA', 1, 'A@gmail.com', '+36123456789', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', 5, 44, -1, NULL, NULL, NULL, '2023-02-16 16:16:31', NULL, '2023-02-24 12:08:16', 0),
(19, 'TESZT', 'AA', 1, 'bkap100@gmail.com', '+36123456789', '26687a2ec1ab0d4ba2a0fc990ca1ec5621501db7b457884f9764ca7e6213955a', NULL, 61, 1, NULL, NULL, '2023-02-24 10:59:18', '2023-02-23 18:29:08', '2023-02-23 18:30:16', '2023-04-22 16:52:30', 1),
(21, 'TESZT', 'AA', 1, 'regteszt@teszt-user.hu', '+36123456789', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 15, 66, -1, 'CIcv8iedfXusSvJWFmxCvAk7qSJU0c3TsVrU5PNG', '2023-04-14 13:55:57', NULL, '2023-04-14 13:45:57', NULL, '2023-04-22 16:51:56', 1),
(22, 'TESZT', 'AA', 0, 'tdsft@teszt-user.hu', '+36123456789', '74926c3e1d068ee0d655bf9248da00994c0fe2d474ce2337e6024e439c5f7081', NULL, 68, 1, NULL, NULL, '2023-05-03 10:30:19', '2023-05-03 09:49:33', '2023-05-03 09:55:11', '2023-05-03 10:30:19', 0),
(23, 'TESZT', 'AA', 0, 'regSteszt@teszt-user.hu', '+36123456789', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', NULL, 69, 0, NULL, NULL, NULL, '2023-05-03 09:55:43', '2023-05-03 09:55:53', '2023-05-03 09:55:53', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users_jobs`
--

DROP TABLE IF EXISTS `users_jobs`;
CREATE TABLE `users_jobs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `job_tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `users_jobs`
--

INSERT INTO `users_jobs` (`id`, `user_id`, `job_tag_id`) VALUES
(2, 4, 1),
(3, 4, 5);

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
-- A tábla indexei `ads_counties`
--
ALTER TABLE `ads_counties`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `chats`
--
ALTER TABLE `chats`
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
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `users_jobs`
--
ALTER TABLE `users_jobs`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT a táblához `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `ads_counties`
--
ALTER TABLE `ads_counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT a táblához `chats`
--
ALTER TABLE `chats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT a táblához `counties`
--
ALTER TABLE `counties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT a táblához `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT a táblához `jobs`
--
ALTER TABLE `jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `job_tags`
--
ALTER TABLE `job_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT a táblához `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT a táblához `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT a táblához `users_jobs`
--
ALTER TABLE `users_jobs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
