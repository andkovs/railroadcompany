CREATE TABLE `station` (
  `station_id` int(11) NOT NULL AUTO_INCREMENT,
  `station_title` varchar(45) DEFAULT NULL,
  `lat` decimal(18,14),
  `lng` decimal(18,14),
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;