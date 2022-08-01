CREATE TABLE `diligence` (
                             `diligence_id` int NOT NULL AUTO_INCREMENT,
                             `user_id` int DEFAULT NULL,
                             `timeStart` datetime DEFAULT NULL,
                             `timeStop` datetime DEFAULT NULL,
                             `totalWorking` double DEFAULT NULL,
                             `status` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`diligence_id`),
                             KEY `fk_user_diligence_idx` (`user_id`),
                             CONSTRAINT `fk_user_diligence` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci