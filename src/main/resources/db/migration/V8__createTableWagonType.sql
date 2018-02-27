CREATE TABLE `wagon_type` (
  `wagon_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `wagon_type` varchar(45) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`wagon_type_id`),
  UNIQUE KEY `wagon_type_unq` (`wagon_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;