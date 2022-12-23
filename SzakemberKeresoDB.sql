CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `acces_type` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `password` varchar(255),
  `company_id` int,
  `job_tag_id` int,
  `status` int,
  `last_login_at` timestamp,
  `created_at` timestamp,
  `activated_at` timestamp,
  `updated_at` timestamp,
  `address_id` int,
  `deleted` int
);

CREATE TABLE `jobs` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `customer_id` int,
  `worker_id` int,
  `desc` varchar(255),
  `total` int,
  `status` int,
  `worker_accepted` int,
  `customer_accepted` int,
  `updated_at` timestamp,
  `deleted` int
);

CREATE TABLE `companies` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `premise_address` varchar(255),
  `tax_number` varchar(255)
);

CREATE TABLE `ads` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `job_tag_id` int,
  `desc` varchar(255),
  `title` varchar(255),
  `county_id` int,
  `updated_at` timestamp,
  `deleted` int
);

CREATE TABLE `counties` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `cities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `postal_code` int,
  `city` varchar(255),
  `county_id` int
);

CREATE TABLE `streets` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `street_name_id` int,
  `city_id` int
);

CREATE TABLE `addresses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `number` varchar(255),
  `staircase` varchar(255),
  `floor` int,
  `door` int,
  `street_id` int
);

CREATE TABLE `street_names` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `job_tags` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255)
);

CREATE TABLE `messages` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `sender_id` int,
  `receiver_id` int,
  `message` varchar(255),
  `checked` int,
  `sended_at` timestamp
);

CREATE TABLE `ratings` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `ratinged_user_id` int,
  `ratinger_user_id` int,
  `desc` varchar(255),
  `ratings_stars` int
);

CREATE TABLE `favorites` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `ads_id` int
);

CREATE TABLE `images` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `url` varchar(255),
  `title` varchar(255),
  `created_at` date,
  `user_id` int
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
