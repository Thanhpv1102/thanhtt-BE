CREATE TABLE `request` (
                           `request_id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int DEFAULT NULL,
                           `createDate` timestamp NULL DEFAULT NULL,
                           `timeStart` datetime DEFAULT NULL,
                           `timeStop` datetime DEFAULT NULL,
                           `reason` varchar(100) DEFAULT NULL,
                           `status` varchar(15) DEFAULT NULL,
                           `handleDate` datetime DEFAULT NULL,
                           `type_id` int DEFAULT NULL,
                           PRIMARY KEY (`request_id`),
                           KEY `fk_user_request_idx` (`user_id`),
                           KEY `fk_requestType_request_idx` (`type_id`),
                           CONSTRAINT `fk_requestType_request` FOREIGN KEY (`type_id`) REFERENCES `requesttype` (`type_id`),
                           CONSTRAINT `fk_user_request` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci