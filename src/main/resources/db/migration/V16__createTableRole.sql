CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_unq` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;