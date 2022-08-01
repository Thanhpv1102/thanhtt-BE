CREATE TABLE `role` (
                        `role_id` int NOT NULL AUTO_INCREMENT,
                        `user_id` int DEFAULT NULL,
                        `name` varchar(100) DEFAULT NULL,
                        `permission` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci