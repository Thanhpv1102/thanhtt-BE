CREATE TABLE `requesttype` (
                               `type_id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(45) DEFAULT NULL,
                               `description` varchar(100) DEFAULT NULL,
                               `request_id` int DEFAULT NULL,
                               PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci