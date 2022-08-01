CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(45) DEFAULT NULL,
                        `phoneNumber` varchar(12) DEFAULT NULL,
                        `nation` varchar(45) DEFAULT NULL,
                        `idCard` varchar(45) DEFAULT NULL,
                        `gender` varchar(5) DEFAULT NULL,
                        `dateOfBirth` datetime DEFAULT NULL,
                        `department_id` int DEFAULT NULL,
                        `fullname` varchar(100) DEFAULT NULL,
                        `adress` varchar(100) DEFAULT NULL,
                        `role_id` int DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        KEY `fk_department_idx` (`department_id`),
                        KEY `fk_role_user_idx` (`role_id`),
                        CONSTRAINT `fk_department_user` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
                        CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci