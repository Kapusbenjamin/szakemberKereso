CREATE TABLE `users` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(100),
  `last_name` varchar(100),
  `acces_type` varchar(100),
  `email` varchar(200),
  `phone` varchar(12),
  `password` varchar(255),
  `company_id` int(11),
  `job_tag_id` int(11),
  `status` int,
  `last_login_at` timestamp,
  `created_at` timestamp,
  `activated_at` timestamp,
  `updated_at` timestamp,
  `address_id` int(11),
  `deleted` int(1)
);

CREATE TABLE `jobs` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `customer_id` int(11),
  `worker_id` int(11),
  `desc` text,
  `total` int,
  `status` int,
  `worker_accepted` int(1),
  `customer_accepted` int(1),
  `updated_at` timestamp,
  `deleted` int(1)
);

CREATE TABLE `companies` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(200),
  `premise_address` varchar(255),
  `tax_number` varchar(255)
);

CREATE TABLE `ads` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11),
  `job_tag_id` int(11),
  `desc` text,
  `county_id` int(11),
  `updated_at` timestamp,
  `deleted` int(1)
);

CREATE TABLE `counties` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100)
);

CREATE TABLE `cities` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `postal_code` int(5),
  `city` varchar(100),
  `county_id` int(11)
);

CREATE TABLE `streets` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `street_name_id` int(11),
  `city_id` int(11)
);

CREATE TABLE `addresses` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `number` varchar(30),
  `staircase` varchar(30),
  `floor` int(4),
  `door` int(8),
  `street_id` int(11)
);

CREATE TABLE `street_names` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100)
);

CREATE TABLE `job_tags` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100)
);

CREATE TABLE `messages` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sender_id` int(11),
  `receiver_id` int(11),
  `message` text,
  `checked` int(1),
  `sended_at` timestamp,
  `deleted` int(1)
);

CREATE TABLE `ratings` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `ratinged_user_id` int(11),
  `ratinger_user_id` int(11),
  `desc` text,
  `ratings_stars` int(2),
  `status` int(1)
);

CREATE TABLE `favorites` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `user_id` int(11),
  `ads_id` int(11)
);

CREATE TABLE `images` (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `url` varchar(255),
  `title` varchar(100),
  `created_at` date,
  `user_id` int(11)
);

ALTER TABLE `ads` ADD FOREIGN KEY (`id`) REFERENCES `users` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `ads` (`user_id`);

ALTER TABLE `job_tags` ADD FOREIGN KEY (`id`) REFERENCES `ads` (`job_tag_id`);

ALTER TABLE `counties` ADD FOREIGN KEY (`id`) REFERENCES `ads` (`county_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `ratings` (`ratinged_user_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `ratings` (`ratinger_user_id`);

ALTER TABLE `job_tags` ADD FOREIGN KEY (`id`) REFERENCES `users` (`job_tag_id`);

ALTER TABLE `favorites` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `favorites` ADD FOREIGN KEY (`ads_id`) REFERENCES `ads` (`id`);

ALTER TABLE `images` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `cities` ADD FOREIGN KEY (`county_id`) REFERENCES `counties` (`id`);

ALTER TABLE `streets` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

ALTER TABLE `streets` ADD FOREIGN KEY (`street_name_id`) REFERENCES `street_names` (`id`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`street_id`) REFERENCES `streets` (`id`);

ALTER TABLE `addresses` ADD FOREIGN KEY (`id`) REFERENCES `users` (`address_id`);

ALTER TABLE `companies` ADD FOREIGN KEY (`id`) REFERENCES `users` (`company_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `jobs` (`customer_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `jobs` (`worker_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `messages` (`sender_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `messages` (`receiver_id`);
