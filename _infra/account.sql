CREATE TABLE `account` (
                           `account_id` int NOT NULL AUTO_INCREMENT,
                           `username` varchar(45) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `createdDate` timestamp NULL DEFAULT NULL,
                           `status` varchar(15) DEFAULT NULL,
                           `user_id` int DEFAULT NULL,
                           PRIMARY KEY (`account_id`),
                           KEY `fk_user_idx` (`user_id`),
                           CONSTRAINT `fk_user_account` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci