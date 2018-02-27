CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `birth_date` datetime NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `enabled` bit(1) DEFAULT b'1' NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_unq` (`login`),
  UNIQUE KEY `email_unq` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;