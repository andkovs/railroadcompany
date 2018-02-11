CREATE TABLE `test` (
  `test_id` bigint NOT NULL,
  `test_title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`test_id`),
  UNIQUE KEY `test_title_UNIQUE` (`test_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;